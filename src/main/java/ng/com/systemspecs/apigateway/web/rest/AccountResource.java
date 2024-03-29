package ng.com.systemspecs.apigateway.web.rest;

import ng.com.systemspecs.apigateway.domain.User;
import ng.com.systemspecs.apigateway.repository.UserRepository;
import ng.com.systemspecs.apigateway.security.SecurityUtils;
import ng.com.systemspecs.apigateway.security.jwt.JWTFilter;
import ng.com.systemspecs.apigateway.security.jwt.TokenProvider;
import ng.com.systemspecs.apigateway.service.MailService;
import ng.com.systemspecs.apigateway.service.ProfileService;
import ng.com.systemspecs.apigateway.service.UserService;
import ng.com.systemspecs.apigateway.service.UsernameAlreadyUsedException;
import ng.com.systemspecs.apigateway.service.dto.PasswordChangeDTO;
import ng.com.systemspecs.apigateway.service.dto.PostResponseDTO;
import ng.com.systemspecs.apigateway.service.dto.PostResponseDataDTO;
import ng.com.systemspecs.apigateway.service.dto.RegisteredUserDTO;
import ng.com.systemspecs.apigateway.service.dto.RespondDTO;
import ng.com.systemspecs.apigateway.service.dto.ResponseDTO;
import ng.com.systemspecs.apigateway.service.dto.UserDTO;
import ng.com.systemspecs.apigateway.web.rest.UserJWTController.JWTToken;
import ng.com.systemspecs.apigateway.web.rest.errors.*;
import ng.com.systemspecs.apigateway.web.rest.vm.KeyAndPasswordVM;
import ng.com.systemspecs.apigateway.web.rest.vm.ManagedUserVM;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.ap.internal.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

/**
 * REST controller for managing the current user's account.
 */
@RestController
@RequestMapping("/api")
public class AccountResource {

	private final TokenProvider tokenProvider;

	private final AuthenticationManagerBuilder authenticationManagerBuilder;

	private static class AccountResourceException extends RuntimeException {
		private AccountResourceException(String message) {
			super(message);
		}
	}

	private final Logger log = LoggerFactory.getLogger(AccountResource.class);

	private final UserRepository userRepository;

	private final UserService userService;

	private final MailService mailService;
	private final ProfileService profileService;

	public AccountResource(UserRepository userRepository, UserService userService, MailService mailService,
			ProfileService profileService, TokenProvider tokenProvider,
			AuthenticationManagerBuilder authenticationManagerBuilder) {

		this.userRepository = userRepository;
		this.userService = userService;
		this.mailService = mailService;
		this.profileService = profileService;
		this.tokenProvider = tokenProvider;
		this.authenticationManagerBuilder = authenticationManagerBuilder;
	}

	/**
	 * {@code POST  /register} : register the user.
	 *
	 * @param managedUserVM the managed user View Model.
	 * @throws InvalidPasswordException  {@code 400 (Bad Request)} if the password
	 *                                   is incorrect.
	 * @throws EmailAlreadyUsedException {@code 400 (Bad Request)} if the email is
	 *                                   already used.
	 * @throws LoginAlreadyUsedException {@code 400 (Bad Request)} if the login is
	 *                                   already used.
	 */
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<RespondDTO<User>> registerAccount(@Valid @RequestBody RegisteredUserDTO registeredUserDTO,
			HttpSession session) {

		RespondDTO<User> RespondDTO = new RespondDTO<User>();
		RespondDTO.setMessage("User Created Successfully");		
		if(Strings.isEmpty(registeredUserDTO.getPhoneNumber())) {
			RespondDTO.setMessage("Phone Number Cannot be Empty");
			return new ResponseEntity<>(RespondDTO, new HttpHeaders(), HttpStatus.EXPECTATION_FAILED);
		}
		if(registeredUserDTO.getPhoneNumber().length() < 11 || registeredUserDTO.getPhoneNumber().length() > 15) {
			RespondDTO.setMessage("Phone number must be between 11 to 13 digits");
			return new ResponseEntity<>(RespondDTO, new HttpHeaders(), HttpStatus.EXPECTATION_FAILED);
		}
		if (!checkPasswordLength(registeredUserDTO.getPassword())) {
			RespondDTO.setMessage("Password Cannot be Empty");
			return new ResponseEntity<>(RespondDTO, new HttpHeaders(), HttpStatus.EXPECTATION_FAILED);
		}		
		try {
			User user = userService.registerUser(registeredUserDTO, registeredUserDTO.getPassword());
			RespondDTO.setUser(user);
		} catch (UsernameAlreadyUsedException e) {
			RespondDTO.setMessage("User Already Exist");
			return new ResponseEntity<>(RespondDTO, new HttpHeaders(), HttpStatus.EXPECTATION_FAILED);
		}
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				registeredUserDTO.getPhoneNumber(), registeredUserDTO.getPassword());

		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		boolean rememberMe = true;
		String jwt = tokenProvider.createToken(authentication, rememberMe);
		RespondDTO.setToken(jwt);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
		session.setAttribute("phoneNumber", registeredUserDTO.getPhoneNumber());

		session.setAttribute("phoneNumber", registeredUserDTO.getPhoneNumber());
		session.setAttribute("otp", "111111");
		return new ResponseEntity<>(RespondDTO, httpHeaders, HttpStatus.OK);
		// mailService.sendActivationEmail(user);
	}

	/**
	 * {@code GET  /activate} : activate the registered user.
	 *
	 * @param key the activation key.
	 * @throws RuntimeException {@code 500 (Internal Server Error)} if the user
	 *                          couldn't be activated.
	 */
	@GetMapping("/activate")
	public void activateAccount(@RequestParam(value = "key") String key) {
		Optional<User> user = userService.activateRegistration(key);
		if (!user.isPresent()) {
			throw new AccountResourceException("No user was found for this activation key");
		}
	}

	/**
	 * {@code GET  /authenticate} : check if the user is authenticated, and return
	 * its login.
	 *
	 * @param request the HTTP request.
	 * @return the login if the user is authenticated.
	 */
	@GetMapping("/authenticate")
	public String isAuthenticated(HttpServletRequest request) {
		log.debug("REST request to check if the current user is authenticated");
		return request.getRemoteUser();
	}

	/**
	 * {@code GET  /account} : get the current user.
	 *
	 * @return the current user.
	 * @throws RuntimeException {@code 500 (Internal Server Error)} if the user
	 *                          couldn't be returned.
	 */
	@GetMapping("/account")
	public UserDTO getAccount() {
		return userService.getUserWithAuthorities().map(UserDTO::new)
				.orElseThrow(() -> new AccountResourceException("User could not be found"));
	}

	/**
	 * {@code POST  /account} : update the current user information.
	 *
	 * @param userDTO the current user information.
	 * @throws EmailAlreadyUsedException {@code 400 (Bad Request)} if the email is
	 *                                   already used.
	 * @throws RuntimeException          {@code 500 (Internal Server Error)} if the
	 *                                   user login wasn't found.
	 */
	@PostMapping("/account")
	public void saveAccount(@Valid @RequestBody UserDTO userDTO) {
		String userLogin = SecurityUtils.getCurrentUserLogin()
				.orElseThrow(() -> new AccountResourceException("Current user login not found"));
		Optional<User> existingUser = userRepository.findOneByEmailIgnoreCase(userDTO.getEmail());
		if (existingUser.isPresent() && (!existingUser.get().getLogin().equalsIgnoreCase(userLogin))) {
			throw new EmailAlreadyUsedException();
		}
		Optional<User> user = userRepository.findOneByLogin(userLogin);
		if (!user.isPresent()) {
			throw new AccountResourceException("User could not be found");
		}
		userService.updateUser(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail(), userDTO.getLangKey(),
				userDTO.getImageUrl());
	}

	/**
	 * {@code POST  /account/change-password} : changes the current user's password.
	 *
	 * @param passwordChangeDto current and new password.
	 * @throws InvalidPasswordException {@code 400 (Bad Request)} if the new
	 *                                  password is incorrect.
	 */
	@PostMapping(path = "/account/change-password")
	public void changePassword(@RequestBody PasswordChangeDTO passwordChangeDto) {
		if (!checkPasswordLength(passwordChangeDto.getNewPassword())) {
			throw new InvalidPasswordException();
		}
		userService.changePassword(passwordChangeDto.getCurrentPassword(), passwordChangeDto.getNewPassword());
	}

	/**
	 * {@code POST   /account/reset-password/init} : Send an email to reset the
	 * password of the user.
	 *
	 * @param mail the mail of the user.
	 */
	@PostMapping(path = "/account/reset-password/init")
	public void requestPasswordReset(@RequestBody String mail) {
		Optional<User> user = userService.requestPasswordReset(mail);
		if (user.isPresent()) {
			mailService.sendPasswordResetMail(user.get());
		} else {
			// Pretend the request has been successful to prevent checking which emails
			// really exist
			// but log that an invalid attempt has been made
			log.warn("Password reset requested for non existing mail");
		}
	}

	/**
	 * {@code POST   /account/reset-password/finish} : Finish to reset the password
	 * of the user.
	 *
	 * @param keyAndPassword the generated key and the new password.
	 * @throws InvalidPasswordException {@code 400 (Bad Request)} if the password is
	 *                                  incorrect.
	 * @throws RuntimeException         {@code 500 (Internal Server Error)} if the
	 *                                  password could not be reset.
	 */
	@PostMapping(path = "/account/reset-password/finish")
	public void finishPasswordReset(@RequestBody KeyAndPasswordVM keyAndPassword) {
		if (!checkPasswordLength(keyAndPassword.getNewPassword())) {
			throw new InvalidPasswordException();
		}
		Optional<User> user = userService.completePasswordReset(keyAndPassword.getNewPassword(),
				keyAndPassword.getKey());

		if (!user.isPresent()) {
			throw new AccountResourceException("No user was found for this reset key");
		}
	}

	private static boolean checkPasswordLength(String password) {
		return !StringUtils.isEmpty(password) && password.length() >= ManagedUserVM.PASSWORD_MIN_LENGTH
				&& password.length() <= ManagedUserVM.PASSWORD_MAX_LENGTH;
	}
}

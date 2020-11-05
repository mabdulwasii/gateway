package ng.com.systemspecs.apigateway.web.rest;

import ng.com.systemspecs.apigateway.client.ExternalRESTClient3;
import ng.com.systemspecs.apigateway.domain.Address;
import ng.com.systemspecs.apigateway.domain.Kyclevel;
import ng.com.systemspecs.apigateway.domain.Profile;
import ng.com.systemspecs.apigateway.domain.User;
import ng.com.systemspecs.apigateway.domain.enumeration.Gender;
import ng.com.systemspecs.apigateway.repository.UserRepository;
import ng.com.systemspecs.apigateway.service.AddressService;
import ng.com.systemspecs.apigateway.service.ProfileService;
import ng.com.systemspecs.apigateway.service.WalletAccountService;
import ng.com.systemspecs.apigateway.web.rest.errors.BadRequestAlertException;
import ng.com.systemspecs.apigateway.web.rest.errors.InvalidPasswordException;
import ng.com.systemspecs.apigateway.web.rest.vm.ManagedUserVM;
import ng.com.systemspecs.apigateway.service.dto.AddressDTO;
import ng.com.systemspecs.apigateway.service.dto.OTPDTO;
import ng.com.systemspecs.apigateway.service.dto.PinDTO;
import ng.com.systemspecs.apigateway.service.dto.PostResponseDTO;
import ng.com.systemspecs.apigateway.service.dto.PostResponseDataDTO;
import ng.com.systemspecs.apigateway.service.dto.ProfileDTO;
import ng.com.systemspecs.apigateway.service.dto.RegisterCompleteResponseDTO;
import ng.com.systemspecs.apigateway.service.dto.RegistrationLastPageDTO;
import ng.com.systemspecs.apigateway.service.dto.RespondDTO;
import ng.com.systemspecs.apigateway.service.dto.WalletAccountDTO;
import ng.com.systemspecs.apigateway.service.validation.LastPageValidation;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * REST controller for managing {@link ng.com.systemspecs.apigateway.domain.Profile}.
 */
@RestController
@RequestMapping("/api")
public class ProfileResource {

    private final Logger log = LoggerFactory.getLogger(ProfileResource.class);

    private static long WALLET_ID=100000;
    private static long Lower_Bond = 10000000000L;
    private static long Upper_Bond = 90000000000L;

    private static final String ENTITY_NAME = "profile";
	 
    @Value("${jhipster.clientApp.name}")
    private String applicationName;
    private final ProfileService profileService;
    private final WalletAccountService walleAccountService;
    private final AddressService addressService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
	private final ExternalRESTClient3  externalRESTClient3;
	 
	 
    public ProfileResource(ProfileService profileService,WalletAccountService walleAccountService,
    		UserRepository userRepository, PasswordEncoder passwordEncoder,
    		AddressService addressService, ExternalRESTClient3  externalRESTClient3) {
		this.profileService = profileService;
        this.walleAccountService = walleAccountService;
        this.addressService=addressService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
		this.externalRESTClient3 = externalRESTClient3;
    }

    /**
     * {@code POST  /profiles} : Create a new profile.
     *
     * @param profileDTO the profileDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new profileDTO, or with status {@code 400 (Bad Request)} if the profile has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/profiles")
    public ResponseEntity<ProfileDTO> createProfile(@RequestBody ProfileDTO profileDTO) throws URISyntaxException {
        log.debug("REST request to save Profile : {}", profileDTO);
        if (profileDTO.getId() != null) {
            throw new BadRequestAlertException("A new profile cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProfileDTO result = profileService.save(profileDTO);
        return ResponseEntity.created(new URI("/api/profiles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /profiles} : Updates an existing profile.
     *
     * @param profileDTO the profileDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated profileDTO,
     * or with status {@code 400 (Bad Request)} if the profileDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the profileDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/profiles")
    public ResponseEntity<ProfileDTO> updateProfile(@RequestBody ProfileDTO profileDTO) throws URISyntaxException {
        log.debug("REST request to update Profile : {}", profileDTO);
        if (profileDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProfileDTO result = profileService.save(profileDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, profileDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /profiles} : get all the profiles.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of profiles in body.
     */
    @GetMapping("/profiles")
    public ResponseEntity<List<ProfileDTO>> getAllProfiles(Pageable pageable) {
        log.debug("REST request to get a page of Profiles");
        Page<ProfileDTO> page = profileService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /profiles/:id} : get the "id" profile.
     *
     * @param id the id of the profileDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the profileDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/profiles/{id}")
    public ResponseEntity<ProfileDTO> getProfile(@PathVariable Long id) {
        log.debug("REST request to get Profile : {}", id);
        Optional<ProfileDTO> profileDTO = profileService.findOne(id);
        return ResponseUtil.wrapOrNotFound(profileDTO);
    }

    /**
     * {@code GET  /profiles/:id} : get the "id" profile.
     *
     * @param id the id of the profileDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the profileDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/profiles/{phoneNumber}")
    public ResponseEntity<ProfileDTO> getProfileByPhoneNumber(@PathVariable String phoneNumber) {
        log.debug("REST request to get Profile : {}", phoneNumber);
        Optional<ProfileDTO> profileDTO = profileService.findOneByPhoneNumber(phoneNumber);
        return ResponseUtil.wrapOrNotFound(profileDTO);
    }
    /**
     * {@code DELETE  /profiles/:id} : delete the "id" profile.
     *
     * @param id the id of the profileDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/profiles/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        log.debug("REST request to delete Profile : {}", id);
        profileService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
    
    @PostMapping("/lastpage")
    public ResponseEntity<RegisterCompleteResponseDTO<WalletAccountDTO>>  updateAccount(@Valid @RequestBody RegistrationLastPageDTO lastPageDTO,HttpSession session) {
    	LastPageValidation validate = new LastPageValidation(lastPageDTO);
    	RegisterCompleteResponseDTO<WalletAccountDTO> ResponseDTO = new RegisterCompleteResponseDTO<WalletAccountDTO>();
    	if(!validate.checkErrors()) {
    		ResponseDTO.setMessage(validate.getErrors());
    		return new ResponseEntity<>(ResponseDTO, new HttpHeaders(), HttpStatus.EXPECTATION_FAILED);
    	}
    	String phoneNumber = (String) session.getAttribute("phoneNumber");
    	Profile profile = profileService.findByPhoneNumber(phoneNumber);
    	Optional<User> user = userRepository.findOneByLogin(phoneNumber);
        //user.setEmail();
    	user.ifPresent(theUser -> {
    		//System.out.println(theUser.getFirstName());
    		theUser.setEmail(lastPageDTO.getEmail());
    		userRepository.save(theUser);
    	});
    	
    	//User user = UserRepository
    	//profile.setAddress(profileDTO.getAddress());
    	AddressDTO addressDTO = new AddressDTO();
    	addressDTO.setAddress(lastPageDTO.getAddress());
    	addressDTO.setLatitude(lastPageDTO.getLatitude());
    	addressDTO.setLongitude(lastPageDTO.getLongitude());
    	addressDTO.setAddressOwner(profile);
    	addressService.save(addressDTO);
    	profile.setDateOfBirth(lastPageDTO.getDateOfBirth());
    	profile.setKyc(null);
    	profile.setProfileID("3");
    	profile.setGender(Gender.valueOf(lastPageDTO.getGender()));
            	
    	WalletAccountDTO walletAccountDTO = new WalletAccountDTO();
    	walletAccountDTO.setAccountNumber(ThreadLocalRandom.current().nextLong(Lower_Bond,Upper_Bond));
    	walletAccountDTO.setAccountOwnerPhoneNumber(profile.getPhoneNumber());
    	walletAccountDTO.setAccountOwnerId(profile.getId());
    	walletAccountDTO.setAccountName(profile.getUser().getFirstName());
    	walletAccountDTO.setDateOpened(LocalDate.now());
    	walletAccountDTO.setCurrentBalance(0.00);
    	walletAccountDTO.setSchemeId(1L);
    	WalletAccountDTO wallet = walleAccountService.save(walletAccountDTO);
    	ResponseDTO.setMessage("Registeration successfull");
    	ResponseDTO.setWallet(wallet);
		return new ResponseEntity<>(ResponseDTO, new HttpHeaders(), HttpStatus.OK);
    }   
	/*
	 * @PostMapping("/updatewithphonenumber")
	 * 
	 * @ResponseStatus(HttpStatus.ACCEPTED) public void
	 * updateProfileWithPhoneNumber(@Valid @RequestBody ProfileDTO profileDTO) {
	 * Profile profile =
	 * profileService.findByPhoneNumber(profileDTO.getPhoneNumber());
	 * profile.setAddress(profileDTO.getAddress());
	 * profile.setDateOfBirth(profileDTO.getDateOfBirth()); profile.setKyc(null);
	 * profile.setGender(Gender.valueOf(profileDTO.getGender()));
	 * profile=profileService.save(profile);
	 * 
	 * }
	 */
    
    @PostMapping("/pin")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<PostResponseDTO> createUpdatePin(@Valid @RequestBody PinDTO pinDTO,HttpSession session) {
    	String phoneNumber = (String) session.getAttribute("phoneNumber");
    	System.out.println(" phoneNumber ===" + phoneNumber);
		PostResponseDTO postResponseDTO = new PostResponseDTO();
		PostResponseDataDTO postResponseDataDTO = new PostResponseDataDTO();
		postResponseDTO.setMessage("pin successfully created");
    	if(Strings.isEmpty(phoneNumber)) {
    		postResponseDTO.setMessage("unable to validate user");
    		postResponseDataDTO.setCode("10");
    		postResponseDataDTO.setDescription("section authentication failed");
        	postResponseDTO.setPostResponseDataDTO(postResponseDataDTO);
			return new ResponseEntity<>(postResponseDTO, new HttpHeaders(), HttpStatus.EXPECTATION_FAILED);
    	}
    	if(Strings.isEmpty(pinDTO.getPin())) {
    		postResponseDTO.setMessage("unable to validate user");
    		postResponseDataDTO.setCode("10");
    		postResponseDataDTO.setDescription("section authentication failed");
        	postResponseDTO.setPostResponseDataDTO(postResponseDataDTO);
			return new ResponseEntity<>(postResponseDTO, new HttpHeaders(), HttpStatus.EXPECTATION_FAILED);
    	}
    	Profile profile = profileService.findByPhoneNumber(phoneNumber);
        String encryptedPassword = passwordEncoder.encode(pinDTO.getPin());
    	profile.setPin(encryptedPassword);
    	profile.setProfileID("2");
    	profile=profileService.save(profile);

		postResponseDTO.setMessage("pin successfully created");
		postResponseDataDTO.setCode("00");
		postResponseDataDTO.setDescription("Pin Successfully Created!");
    	postResponseDTO.setPostResponseDataDTO(postResponseDataDTO);

		return new ResponseEntity<>(postResponseDTO, new HttpHeaders(), HttpStatus.OK);
    }    
    @PostMapping("/verify-otp")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<PostResponseDTO> verifyOTP(@Valid @RequestBody OTPDTO otpDTO,HttpSession session) {
    	String otp = (String) session.getAttribute("otp");
    	//if (otp.isB) otp = "111111";
    	//if(otpDTO.getOtp())
		PostResponseDTO postResponseDTO = new PostResponseDTO();
		PostResponseDataDTO postResponseDataDTO = new PostResponseDataDTO();
		postResponseDTO.setMessage("otp successfully validated");
		postResponseDataDTO.setCode("00");
		postResponseDataDTO.setDescription("OTP successfully validated!");
		if(otpDTO.getOtp() == null) {
			postResponseDTO.setMessage("you must enter otp");
    		postResponseDataDTO.setCode("08");
    		postResponseDataDTO.setDescription(otpDTO.getOtp());
			return new ResponseEntity<>(postResponseDTO, new HttpHeaders(), HttpStatus.EXPECTATION_FAILED);
		}
    	if(!otpDTO.getOtp().trim().equals(otp.trim())) {
    		postResponseDTO.setMessage("invalid otp");
    		postResponseDataDTO.setCode("08");
    		postResponseDataDTO.setDescription(otpDTO.getOtp());
			return new ResponseEntity<>(postResponseDTO, new HttpHeaders(), HttpStatus.EXPECTATION_FAILED);

    	}
    	postResponseDTO.setPostResponseDataDTO(postResponseDataDTO);
    	System.out.println(" session otp==="+otp);

		return new ResponseEntity<>(postResponseDTO, new HttpHeaders(), HttpStatus.OK);

    		
    } 
    @GetMapping("/regStage")
    public String getMyRegStage(HttpSession session) {
    	String phoneNumber = (String) session.getAttribute("phoneNumber");
    	Profile profile = profileService.findByPhoneNumber(phoneNumber);
    	return profile.getProfileID();
    } 


@PostMapping(value = "/referencedataninandfingerprint")
    public  ResponseEntity<byte[]>  getFingerPrintData(@RequestBody  NinFingerPrintDTO  ninFingerPrintDTO) {
    	String base64StringData = "";
       String refId = "nimcDetailsByNin";
 	   String  authCode = "67777777";
 	   String secretKey = "610a64055d214207ee638c1dd7c610b1751dcc0510563fdc52c0a4f9f8e36e275c686c6cbae1ea4e636131a265f20f07e8d610a4733f4df974c0f915465048a1"; 	  
    	
    	Map<String,String> headers  =  new java.util.HashMap<>();
	   headers.put("X-API-PUBLIC-KEY", "QzAwMDAxMTU0MDF8MTUwOTM3NzUwMjMzNXw2MGFmMDZjYTk4ZWYwNzgyMjIzMDQ5MTY4MmZhMWYwODFlMTAwODg3NDczMzRkYjFjNWQ5MGMzZmM5ZDQwNDEyMmQ1ZThhZjAwM2YyMmU5ZDA1ZjZkM2QyNTg3OWYyZDFhMDRlYjE4NDM3MjVhODYwOGYxMjdhYmJmNzRkYmQwMA");
	 
	   MessageDigest md = null;
	   String saltedToken = refId + authCode + secretKey;
	   saltedToken = saltedToken.replaceAll("[\\n\\t ]", "");
	   try {
	       md = MessageDigest.getInstance("SHA-512");
	       md.update(saltedToken.getBytes());
	       byte byteData[] = md.digest();
	       base64StringData = java.util.Base64.getEncoder().encodeToString(byteData);
	   } catch (Exception e) {
	      log.info("Could not load MessageDigest: SHA-512");
	      
	   }
	   
	   headers.put("X-API-SIGNATURE",base64StringData);
	   HttpHeaders responseHeaders = new HttpHeaders();
     	responseHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
   	   log.info(String.valueOf(externalRESTClient3.getFingerPrintData(headers,ninFingerPrintDTO)));
	   return new ResponseEntity<byte[]>(externalRESTClient3.getFingerPrintData(headers,ninFingerPrintDTO),responseHeaders, HttpStatus.OK);
 }



 
}

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
import ng.com.systemspecs.apigateway.service.dto.FingerDTO;
import ng.com.systemspecs.apigateway.service.dto.NinFingerPrintDTO;
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


 
    
    
    private  NinFingerPrintDTO  getNinFingerPrintDTO(NinFingerPrintDTO     ninFingerPrintDTO,  boolean LIVE) {
    	NinFingerPrintDTO  newNinFingerPrintDTO  = null;
    	if(LIVE == true) {
    		newNinFingerPrintDTO  = ninFingerPrintDTO;
    	}else {
    		//Test
    		newNinFingerPrintDTO  = new NinFingerPrintDTO();
   		   newNinFingerPrintDTO.setAuthorisationChannel("USSD");
 		   newNinFingerPrintDTO.setAuthorisationCode("67777777");
 		   newNinFingerPrintDTO.setRefId("nimcDetailsByNin"); 
 		   newNinFingerPrintDTO.setCustomFields(getCustomFieldForNin()); 	  
    	}
    	return    newNinFingerPrintDTO;    	
    }
    
    
    
    
    private  FingerDTO[]  getCustomFieldForNin() {    	
    	  FingerDTO[]   customFields   = new FingerDTO[3];	
    	  
    	  customFields[0] = new FingerDTO();
    	  customFields[1] = new FingerDTO();
    	  customFields[2] = new FingerDTO();
    	  
		   customFields[0].setKey("nin");
		   customFields[0].setValue("6337373733");		   
		   customFields[1].setKey("fingerData");
		   customFields[1].setValue("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");		   
		   customFields[2].setKey("fingerPositionCode");
		   customFields[2].setValue("3");		   
		   return customFields;
    }
 
	

 
}

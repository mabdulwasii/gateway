package ng.com.systemspecs.apigateway.web.rest;

import ng.com.systemspecs.apigateway.client.ExternalRESTClient2;
import ng.com.systemspecs.apigateway.domain.Profile;
import ng.com.systemspecs.apigateway.domain.User;
import ng.com.systemspecs.apigateway.repository.UserRepository;
import ng.com.systemspecs.apigateway.security.SecurityUtils;
import ng.com.systemspecs.apigateway.service.InvalidPasswordException;
import ng.com.systemspecs.apigateway.service.ProfileService;
import ng.com.systemspecs.apigateway.service.RITSService;
import ng.com.systemspecs.apigateway.service.UserService;
import ng.com.systemspecs.apigateway.service.WalletAccountService;
import ng.com.systemspecs.apigateway.web.rest.errors.BadRequestAlertException;
import ng.com.systemspecs.apigateway.service.dto.BankAccountDTO;
import ng.com.systemspecs.apigateway.service.dto.BankDTO;
import ng.com.systemspecs.apigateway.service.dto.BvnDTO;
import ng.com.systemspecs.apigateway.service.dto.FundDTO;
import ng.com.systemspecs.apigateway.service.dto.PaymentResponseDTO;
import ng.com.systemspecs.apigateway.service.dto.PaymentTransactionDTO;
import ng.com.systemspecs.apigateway.service.dto.ResponseDTO;
import ng.com.systemspecs.apigateway.service.dto.SendMoneyDTO;
import ng.com.systemspecs.apigateway.service.dto.VerifyBankAccountDTO;
import ng.com.systemspecs.apigateway.service.dto.WalletAccountDTO; 

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.jsonwebtoken.lang.Strings;
import net.logstash.logback.encoder.org.apache.commons.lang3.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
 
import ng.com.systemspecs.apigateway.web.rest.errors.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.util.Optional;
 
import  ng.com.systemspecs.remitarits.util.*;
import  ng.com.systemspecs.remitarits.bulkpayment.*;
import  ng.com.systemspecs.remitarits.accountenquiry.*;
import  ng.com.systemspecs.remitarits.bankenquiry.*; 
import  ng.com.systemspecs.remitarits.singlepayment.*;
import  ng.com.systemspecs.remitarits.singlepaymentstatus.*;
import  ng.com.systemspecs.remitarits.bulkpayment.*;
import  ng.com.systemspecs.remitarits.bulkpaymentstatus.*;
import  ng.com.systemspecs.remitarits.bankenquiry.*;
import  ng.com.systemspecs.remitarits.configuration.*;
import  ng.com.systemspecs.remitarits.service.*;
import  ng.com.systemspecs.remitarits.service.impl.*;

/**
 * REST controller for managing {@link ng.com.systemspecs.apigateway.domain.WalletAccount}.
 */
@RestController
@RequestMapping("/api")
public class WalletAccountResource {

    private final Logger log = LoggerFactory.getLogger(WalletAccountResource.class);
    private final UserRepository userRepository;
    private static final String ENTITY_NAME = "walletAccount";
    private final PasswordEncoder passwordEncoder;
    private static long Lower_Bond = 10000000000L;
    private static long Upper_Bond = 90000000000L;
    
    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WalletAccountService walletAccountService;
    private final ProfileService profileService;
    private  User theUser;
	private final ExternalRESTClient2  externalRESTClient2;
	private final RITSService  rITSService;
	
	
 
    public WalletAccountResource(WalletAccountService walletAccountService, ProfileService profileService,
    		UserRepository userRepository, PasswordEncoder passwordEncoder, RITSService  rITSService, ExternalRESTClient2  externalRESTClient2) {
        this.walletAccountService = walletAccountService;
        this.userRepository = userRepository;
        this.profileService = profileService;
        this.passwordEncoder = passwordEncoder;
		this.rITSService =  rITSService;
		this.externalRESTClient2  = externalRESTClient2;
    }

    /**
     * {@code POST  /wallet-accounts} : Create a new walletAccount.
     *
     * @param walletAccountDTO the walletAccountDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new walletAccountDTO, or with status {@code 400 (Bad Request)} if the walletAccount has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/wallet-accounts")
    public ResponseEntity<WalletAccountDTO> createWalletAccount(@RequestBody WalletAccountDTO walletAccountDTO) throws URISyntaxException {
        log.debug("REST request to save WalletAccount : {}", walletAccountDTO);
        if (walletAccountDTO.getId() != null) {
            throw new BadRequestAlertException("A new walletAccount cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SecurityUtils.getCurrentUserLogin()
        .flatMap(userRepository::findOneByLogin)
        .ifPresent(user -> {
        	this.theUser = user;
        });
        Profile profile = profileService.findByPhoneNumber(this.theUser.getLogin());
        long accountNumber = ThreadLocalRandom.current().nextLong(Lower_Bond,Upper_Bond);
    	walletAccountDTO.setAccountNumber(accountNumber);
    	walletAccountDTO.setAccountOwnerPhoneNumber(this.theUser.getLogin());
    	walletAccountDTO.setAccountOwnerId(profile.getId());
    	walletAccountDTO.setAccountName(walletAccountDTO.getAccountName());
    	walletAccountDTO.setDateOpened(LocalDate.now());
    	walletAccountDTO.setCurrentBalance(0.00);
    	walletAccountDTO.setSchemeId(1L);
        WalletAccountDTO result = walletAccountService.save(walletAccountDTO);
        return ResponseEntity.created(new URI("/api/wallet-accounts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /wallet-accounts} : Updates an existing walletAccount.
     *
     * @param walletAccountDTO the walletAccountDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated walletAccountDTO,
     * or with status {@code 400 (Bad Request)} if the walletAccountDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the walletAccountDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/wallet-accounts")
    public ResponseEntity<WalletAccountDTO> updateWalletAccount(@RequestBody WalletAccountDTO walletAccountDTO) throws URISyntaxException {
        log.debug("REST request to update WalletAccount : {}", walletAccountDTO);
        if (walletAccountDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WalletAccountDTO result = walletAccountService.save(walletAccountDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, walletAccountDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /wallet-accounts} : get all the walletAccounts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of walletAccounts in body.
     */
    @GetMapping("/wallet-accounts")
    public List<WalletAccountDTO> getAllWalletAccounts() {
        log.debug("REST request to get all WalletAccounts");
        return walletAccountService.findAll();
    }
    @GetMapping("/customer_wallet-accounts")
    public List<WalletAccountDTO> getAllCustomerWalletAccounts() {
        log.debug("REST request to get all WalletAccounts");
        return walletAccountService.findByUserIsCurrentUser();
    }
    /**
     * {@code GET  /wallet-accounts/:id} : get the "id" walletAccount.
     *
     * @param id the id of the walletAccountDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the walletAccountDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/wallet-accounts/{id}")
    public ResponseEntity<WalletAccountDTO> getWalletAccount(@PathVariable Long id) {
        log.debug("REST request to get WalletAccount : {}", id);
        Optional<WalletAccountDTO> walletAccountDTO = walletAccountService.findOne(id);
        return ResponseUtil.wrapOrNotFound(walletAccountDTO);
    }

    /**
     * {@code DELETE  /wallet-accounts/:id} : delete the "id" walletAccount.
     *
     * @param id the id of the walletAccountDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/wallet-accounts/{id}")
    public ResponseEntity<Void> deleteWalletAccount(@PathVariable Long id) {
        log.debug("REST request to delete WalletAccount : {}", id);
        walletAccountService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
    @PostMapping("/fund-wallet")
    public PaymentResponseDTO fundWalletAccount(@RequestBody FundDTO fundDTO) throws URISyntaxException {
        PaymentResponseDTO response = walletAccountService.fund(fundDTO);
        return response;
    }  
    
    @PostMapping("/send-money")
    public ResponseEntity<PaymentResponseDTO> sendMoney(@RequestBody FundDTO sendMoneyDTO) throws URISyntaxException {
    	//this.pinCorrect = true;
        SecurityUtils.getCurrentUserLogin()
        .flatMap(userRepository::findOneByLogin)
        .ifPresent(user -> {
        	this.theUser = user;
        });
        Profile profile = profileService.findByPhoneNumber(this.theUser.getLogin());
    	//Profile profile = profileService.
          String currentEncryptedPin = profile.getPin();
        if (!passwordEncoder.matches(sendMoneyDTO.getPin(), currentEncryptedPin)) {
            //throw new InvalidPasswordException();
        	//pinCorrect = false;
        	PaymentResponseDTO response = new PaymentResponseDTO();
        	response.setMessage("invalid pin");
            return  new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
        }
       
        PaymentResponseDTO response = walletAccountService.sendMoney(sendMoneyDTO);
        if(response.getError()) {
            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        else return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }    


    
 @PostMapping({"/payment","/rits-payment"})
	  public SinglePaymentResponse singlePayment(@RequestBody SinglePaymentRequest   singleRequest) {
    	 return rITSService.singlePayment(singleRequest);
    }
    
	  
    @PostMapping({"/bulk-payment","/rits-bulk-payment"})
	  public BulkPaymentResponse postBulkPayment(@RequestBody BulkPaymentRequest request) {
    	 return rITSService.postBulkPayment(request);
    }


	  @PostMapping({"/payment-status","/rits-payment-status"})
	    public PaymentStatusResponse singlePaymentStatus(@RequestBody PaymentStatusRequest request) {
		  return rITSService.singlePaymentStatus(request);
	  }
	    
	  @PostMapping({"/bulk-payment-status","/rits-bulk-payment-status"}) 
	    public BulkPaymentStatusResponse bulkPaymentStatus(@RequestBody BulkPaymentStatusRequest request) {
		  return rITSService.bulkPaymentStatus(request);
	  }
	    
	   
	  @PostMapping({"/verify-account","/rits-account-enquiry"})
	    public AccountEnquiryResponse getAccountEnquiry(@RequestBody AccountEnqiryRequest accountEnqiryRequest) {
		  return rITSService.getAccountEnquiry(accountEnqiryRequest);
	  } 
	    
	  @GetMapping({"/banks/all","/rits-banks"})  
	    public GetActiveBankResponse getActiveBanks(){
		      return rITSService.getActiveBanks();
	    }
	    
	   @PostMapping("/validate-bvn")
	    public Object validateBvn( @RequestBody  BvnDTO bvnDTO) {
		   Map<String,String> headers  =  new java.util.HashMap<>();
		   headers.put("X-API-PUBLIC-KEY", "U09MRHwyNjk5MzI0MjIzfGRiMjZlNjY5NDVjOGQxMjVjMjBkNzIwZWQ2NTE1ZTgxNTEwNzEyMGRiZGQ3MzZlOTIyYzk1MzA1ZjM4YjM2ZTk5MDUxYTE1YmZhZTc4MDcyM2VmZWU5NGQ0MzM1YmM0NzYxMzJjNDk3M2YzMWI5NWMyOWY5OWUwNDEwMWNjOTEx");
		  return externalRESTClient2.validateBvn(headers, bvnDTO);
	  } 
	  
}

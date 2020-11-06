package ng.com.systemspecs.apigateway.service.impl;

 
import ng.com.systemspecs.apigateway.service.ProfileService;
import ng.com.systemspecs.apigateway.service.RITSService;
import ng.com.systemspecs.apigateway.service.WalletAccountService;
import ng.com.systemspecs.apigateway.domain.Profile;
import ng.com.systemspecs.apigateway.domain.User;
import ng.com.systemspecs.apigateway.domain.WalletAccount;
import ng.com.systemspecs.apigateway.repository.ProfileRepository;
import ng.com.systemspecs.apigateway.repository.UserRepository;
import ng.com.systemspecs.apigateway.repository.WalletAccountRepository;
import ng.com.systemspecs.apigateway.security.SecurityUtils;
import ng.com.systemspecs.apigateway.service.dto.FundDTO;
import ng.com.systemspecs.apigateway.service.dto.PaymentResponseDTO;
import ng.com.systemspecs.apigateway.service.dto.PaymentTransactionDTO;
import ng.com.systemspecs.apigateway.service.dto.PostResponseDTO;
import ng.com.systemspecs.apigateway.service.dto.PostResponseDataDTO;
import ng.com.systemspecs.apigateway.service.dto.PushNotificationRequest;
import ng.com.systemspecs.apigateway.service.dto.ResponseDTO;
import ng.com.systemspecs.apigateway.service.dto.SendMoneyDTO;
import ng.com.systemspecs.apigateway.service.dto.WalletAccountDTO;
import ng.com.systemspecs.apigateway.service.fcm.PushNotificationService;
import ng.com.systemspecs.apigateway.service.kafka.producer.TransProducer;
import ng.com.systemspecs.apigateway.service.mapper.WalletAccountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import  ng.com.systemspecs.remitarits.singlepayment.SinglePayment;
import  ng.com.systemspecs.remitarits.singlepayment.SinglePaymentRequest;
import  ng.com.systemspecs.remitarits.singlepayment.SinglePaymentResponse;
 

import  ng.com.systemspecs.remitarits.singlepaymentstatus.PaymentStatus;
import  ng.com.systemspecs.remitarits.singlepaymentstatus.PaymentStatusRequest;
import  ng.com.systemspecs.remitarits.singlepaymentstatus.PaymentStatusResponse;
 


import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link WalletAccount}.
 */
@Service
@Transactional
public class WalletAccountServiceImpl implements WalletAccountService {

	private final Logger log = LoggerFactory.getLogger(WalletAccountServiceImpl.class);

	private final WalletAccountRepository walletAccountRepository;

	private final WalletAccountMapper walletAccountMapper;
	private final TransProducer producer;
	private final RITSService  rITSService;
	private final PushNotificationService  pushNotificationService;
	private final UserRepository  userRepository;
	private final  ProfileService   profileService;

	public WalletAccountServiceImpl(WalletAccountRepository walletAccountRepository,
			WalletAccountMapper walletAccountMapper,TransProducer producer,RITSService  rITSService,
			PushNotificationService  pushNotificationService, UserRepository  userRepository,
			ProfileService   profileService) {
		this.walletAccountRepository = walletAccountRepository;
		this.walletAccountMapper = walletAccountMapper;
		this.producer = producer;
		this.rITSService  =  rITSService;
		this.pushNotificationService  = pushNotificationService;
		this.userRepository  = userRepository;
		this.profileService  =  profileService;
	}

	@Override
	public WalletAccountDTO save(WalletAccountDTO walletAccountDTO) {
		log.debug("Request to save WalletAccount : {}", walletAccountDTO);
		WalletAccount walletAccount = walletAccountMapper.toEntity(walletAccountDTO);
		walletAccount = walletAccountRepository.save(walletAccount);
		return walletAccountMapper.toDto(walletAccount);
	}

	@Override
	@Transactional(readOnly = true)
	public List<WalletAccountDTO> findAll() {
		log.debug("Request to get all WalletAccounts");
		return walletAccountRepository.findAll().stream().map(walletAccountMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<WalletAccountDTO> findOne(Long id) {
		log.debug("Request to get WalletAccount : {}", id);
		return walletAccountRepository.findById(id).map(walletAccountMapper::toDto);
	}

	@Override
	public void delete(Long id) {
		log.debug("Request to delete WalletAccount : {}", id);
		walletAccountRepository.deleteById(id);
	}

	@Override
	public List<WalletAccountDTO> findByUserIsCurrentUser() {
		log.debug("Request to get all WalletAccounts of login user");
		return walletAccountRepository.findByUserIsCurrentUser().stream().map(walletAccountMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}

	@Override
	public PaymentResponseDTO fund(FundDTO fundDTO) {
		PaymentResponseDTO responseDTO = new PaymentResponseDTO();
		PaymentTransactionDTO paymentTransactionDTO = new PaymentTransactionDTO();
		User  theUser;		
	     SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findOneByLogin)
        .ifPresent(user -> {
        	 theUser = user;
        });
		if(fundDTO.getChannel() == "CARD") {
			 PaymentStatusRequest   paymentStatusRequest   =  new   PaymentStatusRequest();
			paymentStatusRequest.setTransRef(fundDTO.getTransRef()); 
			 PaymentStatusResponse paymentStatusResponse  = rITSService.singlePaymentStatus(paymentStatusRequest);
			
			 
			// responseDTO.setResponseMsg("Transaction Successful");	
				if((paymentStatusResponse.getData()).getResponseCode().equals("00") && 
						(paymentStatusResponse.getData()).getPaymentStatusCode().equals("00")) {
					responseDTO.setCode((paymentStatusResponse.getData()).getPaymentStatusCode());
					 producer.send(fundDTO); 
					 responseDTO.setStatus("successfull");
					 responseDTO.setMessage("Transaction Successful");				
				}else {
					responseDTO.setStatus("failed");
					Profile  profile  = profileService.findByPhoneNumber(theUser.getLogin());
					PushNotificationRequest notificationRequest  = new PushNotificationRequest();
					notificationRequest.setTitle("Fund Wallet through card");
					notificationRequest.setToken(profile.getDeviceNotificationToken());
					notificationRequest.setMessage("Error occurs. Transaction failed");
					pushNotificationService.sendPushNotification(notificationRequest);
				}
		   return  responseDTO;
		}else if(fundDTO.getChannel() == "USSD") {
			;
		}else if(fundDTO.getChannel() == "BANK") {
			 SinglePaymentRequest   singleRequest  =  new   SinglePaymentRequest();
			singleRequest.setAmount(String.valueOf(fundDTO.getAmount()));
			singleRequest.setBeneficiaryEmail("qa@test.com");
			singleRequest.setCreditAccount("0582915208099");
			singleRequest.setDebitAccount(fundDTO.getSourceAccountNumber());
			singleRequest.setFromBank(fundDTO.getSourceBankCode());
			singleRequest.setNarration("Regular Payment");
			singleRequest.setToBank("058");
			singleRequest.setTransRef(String.valueOf(System.currentTimeMillis()));
			 SinglePaymentResponse paymentResponse  =  rITSService.singlePayment(singleRequest);
			
			responseDTO.setCode((paymentResponse.getData()).getResponseCode());
			// responseDTO.setResponseMsg("Transaction Successful");
			if((paymentResponse.getData()).getResponseCode().equals("00")) {
				 producer.send(fundDTO); 
				 responseDTO.setStatus("successfull");
				 responseDTO.setMessage("Transaction Successful");				
			}else {
				responseDTO.setStatus("failed");
				Profile  profile  = profileService.findByPhoneNumber(theUser.getLogin());
				PushNotificationRequest notificationRequest  = new PushNotificationRequest();
				notificationRequest.setTitle("Fund Wallet through bank");
				notificationRequest.setToken(profile.getDeviceNotificationToken());
				notificationRequest.setMessage("Error occurs. Transaction failed");
				pushNotificationService.sendPushNotification(notificationRequest);
			}
			
		}else if(fundDTO.getChannel() == "WALLET") {
			  WalletAccount sourceAccount = walletAccountRepository.findOneByAccountNumber(Long.valueOf(fundDTO.getSourceAccountNumber()));
			  if (sourceAccount.getCurrentBalance() < fundDTO.getAmount()) {
					 responseDTO.setCode("99"); 
					 responseDTO.setStatus("failed");
					 responseDTO.setMessage("Insufficient Fund"); 
					 Profile  profile  = profileService.findByPhoneNumber(theUser.getLogin());
						PushNotificationRequest notificationRequest  = new PushNotificationRequest();
						notificationRequest.setTitle("Fund Wallet through wallet"); 
						notificationRequest.setToken(profile.getDeviceNotificationToken());
						notificationRequest.setMessage("Error occurs. Transaction failed");
						pushNotificationService.sendPushNotification(notificationRequest);
				 }else {
					 producer.send(fundDTO);
					 responseDTO.setCode("00");
					 responseDTO.setStatus("successfull");
					 responseDTO.setMessage("Transaction Successful");
					
				 }
		  return  responseDTO;
		}
	  
	}
	
	

	@Override
	public PaymentResponseDTO sendMoney(SendMoneyDTO sendMoneyDTO) {
		PaymentResponseDTO responseDTO = new PaymentResponseDTO();
		PaymentTransactionDTO paymentTransactionDTO = new PaymentTransactionDTO();
		User  theUser;		
	     SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findOneByLogin)
       .ifPresent(user -> {
       	 theUser = user;
       });
		  WalletAccount sourceAccount = walletAccountRepository.findOneByAccountNumber(Long.valueOf(sendMoneyDTO.getSourceAccount()));
		  if (sourceAccount.getCurrentBalance() < sendMoneyDTO.getAmount()) {
				 responseDTO.setCode("99"); 
				 responseDTO.setStatus("failed");
				 responseDTO.setMessage("Insufficient Fund"); 
			 }else {
				 producer.send(sendMoneyDTO);
				 responseDTO.setCode("00");
				 responseDTO.setStatus("successfull");
				 responseDTO.setMessage("Transaction Successful");
		     				 				 
				if(sendMoneyDTO.getChannel() == "CARD") {
					PaymentStatusRequest   paymentStatusRequest   =  new  PaymentStatusRequest();
					// paymentStatusRequest.setTransRef(sendMoneyDTO.getTransRef()); 
					PaymentStatusResponse paymentStatusResponse  = rITSService.singlePaymentStatus(paymentStatusRequest);
					
					 responseDTO.setCode((paymentStatusResponse.getData()).getResponseCode());
					// responseDTO.setResponseMsg("Transaction Successful");	
					 if((paymentStatusResponse.getData()).getResponseCode().equals("00") && 
								(paymentStatusResponse.getData()).getPaymentStatusCode().equals("00")) {
										 producer.send(responseDTO); 
										 responseDTO.setStatus("successfull");
										 responseDTO.setMessage("Transaction Successful");				
									}else {
										responseDTO.setStatus("failed");
									}
							   return  responseDTO;
							}else if(sendMoneyDTO.getChannel() == "USSD") {
								;
							}else if(sendMoneyDTO.getChannel() == "BANK") {
								SinglePaymentRequest   singleRequest  =  new SinglePaymentRequest();
								singleRequest.setAmount(String.valueOf(sendMoneyDTO.getAmount()));
								singleRequest.setBeneficiaryEmail("qa@test.com");
								singleRequest.setCreditAccount("0582915208099");
								singleRequest.setDebitAccount(sendMoneyDTO.getSourceAccount());
								singleRequest.setFromBank(sendMoneyDTO.getSourceBankCode());
								singleRequest.setNarration("Regular Payment");
								singleRequest.setToBank("058");
								singleRequest.setTransRef(String.valueOf(System.currentTimeMillis()));
							    SinglePaymentResponse paymentResponse  =  rITSService.singlePayment(singleRequest);
								
								responseDTO.setCode((paymentResponse.getData()).getResponseCode());
								// responseDTO.setResponseMsg("Transaction Successful");
								if((paymentResponse.getData()).getResponseCode().equals("00")) {
									 producer.send(sendMoneyDTO); 
									 responseDTO.setStatus("successfull");
									 responseDTO.setMessage("Transaction Successful");				
								}else {
									responseDTO.setStatus("failed");
								}
								
							}else if(sendMoneyDTO.getChannel() == "WALLET") {
								WalletAccount sourceAccount2 = walletAccountRepository.findOneByAccountNumber(Long.valueOf(sendMoneyDTO.getSourceAccount()));
								  if (sourceAccount2.getCurrentBalance() < sendMoneyDTO.getAmount()) {
										 responseDTO.setCode("99"); 
										 responseDTO.setStatus("failed");
										 responseDTO.setMessage("Insufficient Fund"); 
										 Profile  profile  = profileService.findByPhoneNumber(theUser.getLogin());
											PushNotificationRequest notificationRequest  = new PushNotificationRequest();
											notificationRequest.setTitle("Fund Wallet through wallet"); 
											notificationRequest.setToken(profile.getDeviceNotificationToken());
											notificationRequest.setMessage("Error occurs. Transaction failed");
											pushNotificationService.sendPushNotification(notificationRequest);
									 }else {
										 producer.send(sendMoneyDTO);
										 responseDTO.setCode("00");
										 responseDTO.setStatus("successfull");
										 responseDTO.setMessage("Transaction Successful");
									 }
										
							}
				   
			 }
		  return  responseDTO;
	}
	
	
	
}

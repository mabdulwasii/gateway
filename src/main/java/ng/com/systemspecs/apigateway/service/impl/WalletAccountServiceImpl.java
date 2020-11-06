package ng.com.systemspecs.apigateway.service.impl;

 
import ng.com.systemspecs.apigateway.service.RITSService;
import ng.com.systemspecs.apigateway.service.WalletAccountService;
import ng.com.systemspecs.apigateway.domain.WalletAccount;
import ng.com.systemspecs.apigateway.repository.WalletAccountRepository;
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

import  ng.com.systemspecs.remitarits.singlepayment.*;
import  ng.com.systemspecs.remitarits.singlepaymentstatus.*;

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

	public WalletAccountServiceImpl(WalletAccountRepository walletAccountRepository,
			WalletAccountMapper walletAccountMapper,TransProducer producer,RITSService  rITSService,
			PushNotificationService  pushNotificationService) {
		this.walletAccountRepository = walletAccountRepository;
		this.walletAccountMapper = walletAccountMapper;
		this.producer = producer;
		this.rITSService  =  rITSService;
		this.pushNotificationService  = pushNotificationService;
		
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
		 
		if(fundDTO.getChannel() == "CARD") {
			PaymentStatusRequest   paymentStatusRequest   =  new  PaymentStatusRequest();
			paymentStatusRequest.setTransRef(fundDTO.getTransRef());
			paymentStatusRequest.setRequestId(System.currentTimeMillis());
			PaymentStatusResponse paymentStatusResponse  = rITSService.singlePaymentStatus(paymentStatusRequest);
			
			 responseDTO.setCode(paymentStatusResponse.getResponseCode());
			// responseDTO.setResponseMsg("Transaction Successful");	
				if(paymentStatusResponse.getResponseCode().equals("00")) {
					 producer.send(fundDTO); 
					 responseDTO.setStatus("successfull");
					 responseDTO.setMessage("Transaction Successful");				
				}else {
					responseDTO.setStatus("failed");
					PushNotificationRequest notificationRequest  = new PushNotificationRequest();
					pushNotificationService.sendPushNotification(notificationRequest);
				}
		   return  responseDTO;
		}else if(fundDTO.getChannel() == "USSD") {
			;
		}else if(fundDTO.getChannel() == "BANK") {
			SinglePaymentRequest   singleRequest  =  new SinglePaymentRequest();
			    request.setAmount(fundDTO.getAmount());
		        request.setBeneficiaryEmail("qa@test.com");
		        request.setCreditAccount("0582915208099");
		        request.setDebitAccount(fundDTO.getSourceAccountNumber());
		        request.setFromBank(fundDTO.getSourceBankCode());
		        request.setNarration("Regular Payment");
		        request.setToBank("058");
		        request.setTransRef(String.valueOf(System.currentTimeMillis()));
			SinglePaymentResponse paymentResponse  =  singlePayment(singleRequest);
			
			responseDTO.setCode(paymentResponse.getResponseCode());
			// responseDTO.setResponseMsg("Transaction Successful");
			if(paymentResponse.getResponseCode().equals("00")) {
				 producer.send(fundDTO); 
				 responseDTO.setStatus("successfull");
				 responseDTO.setMessage("Transaction Successful");				
			}else {
				responseDTO.setStatus("failed");
				PushNotificationRequest notificationRequest  = new PushNotificationRequest();
				pushNotificationService.sendPushNotification(notificationRequest);
			}
			
		}else if(fundDTO.getChannel() == "WALLET") {
			  WalletAccount sourceAccount = walletAccountRepository.findOneByAccountNumber(Long.valueOf(fundDTO.getSourceAccountNumber()));
			  if (sourceAccount.getCurrentBalance() < fundDTO.getAmount()) {
					 responseDTO.setCode("99"); 
					 responseDTO.setStatus("failed");
					 responseDTO.setMessage("Insufficient Fund"); 
					 PushNotificationRequest notificationRequest  = new PushNotificationRequest();
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
		
		  WalletAccount sourceAccount = walletAccountRepository.findOneByAccountNumber(Long.valueOf(sendMoneyDTO.getSourceAccountNumber()));
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
					paymentStatusRequest.setTransRef(sendMoneyDTO.getTransRef());
					paymentStatusRequest.setRequestId(System.currentTimeMillis());
					PaymentStatusResponse paymentStatusResponse  = rITSService.singlePaymentStatus(paymentStatusRequest);
					
					 responseDTO.setCode(paymentStatusResponse.getResponseCode());
					// responseDTO.setResponseMsg("Transaction Successful");	
						if(paymentStatusResponse.getResponseCode().equals("00")) {
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
								    request.setAmount(sendMoneyDTO.getAmount());
							        request.setBeneficiaryEmail("qa@test.com");
							        request.setCreditAccount("0582915208099");
							        request.setDebitAccount(sendMoneyDTO.getSourceAccountNumber());
							        request.setFromBank(sendMoneyDTO.getSourceBankCode());
							        request.setNarration("Regular Payment");
							        request.setToBank("058");
							        request.setTransRef(String.valueOf(System.currentTimeMillis()));
								SinglePaymentResponse paymentResponse  =  singlePayment(singleRequest);
								
								responseDTO.setCode(paymentResponse.getResponseCode());
								// responseDTO.setResponseMsg("Transaction Successful");
								if(paymentResponse.getResponseCode().equals("00")) {
									 producer.send(sendMoneyDTO); 
									 responseDTO.setStatus("successfull");
									 responseDTO.setMessage("Transaction Successful");				
								}else {
									responseDTO.setStatus("failed");
								}
								
							}else if(sendMoneyDTO.getChannel() == "WALLET") {
								 ;
							}
				   return  responseDTO;
			 }
	 
		Random random = new Random();

		String generatedString = random.ints(97, 122 + 1).limit(15)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		responseDTO.setMessage("Transaction Successful");
		responseDTO.setCode("00");
	 
	}
}

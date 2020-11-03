package ng.com.systemspecs.apigateway.service.impl;

import java.math.BigDecimal;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ng.com.systemspecs.remitabillinggateway.billers.*;
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
 
 

import ng.com.systemspecs.apigateway.service.RITSService;
import ng.com.systemspecs.apigateway.service.dto.PaymentResponseDTO;
import ng.com.systemspecs.apigateway.service.dto.PaymentTransactionDTO; 
import ng.com.systemspecs.apigateway.service.kafka.producer.TransProducer;
 
@Service
@Transactional
public class RITSServiceImpl  implements RITSService {

    private final Logger log = LoggerFactory.getLogger(RITSServiceImpl.class);
    
    private static long Lower_Bond = 10000000000L;
    private static long Upper_Bond = 90000000000L;
    
    private final TransProducer producer;
    
    public RITSServiceImpl(TransProducer producer) {
    	this.producer =  producer;
    }
   
    @Override
	public  RemitaRITSService  getRemitaRITSService()  {
		
		   Credentials credentials = new Credentials();
	       credentials.setMerchantId("DEMOMDA1234");
	       credentials.setApiKey("REVNT01EQTEyMzR8REVNT01EQQ==");
	       credentials.setApiToken("bmR1ZFFFWEx5R2c2NmhnMEk5a25WenJaZWZwbHFFYldKOGY0bHlGZnBZQ1N5WEpXU2Y1dGt3PT0=");
	       credentials.setSecretKey("nbzjfdiehurgsxct");
	       credentials.setSecretKeyIv("sngtmqpfurxdbkwj");
	       credentials.setRequestId(String.valueOf(System.currentTimeMillis()));
	       credentials.setEnvironment(EnvironmentType.DEMO);

	       return  new RemitaRITSServiceImpl(credentials);
		
	}
	
	
	
	  @Override
	  public SinglePaymentResponse singlePayment(SinglePaymentRequest   singleRequest){
		  PaymentResponseDTO responseDTO = new PaymentResponseDTO();
	    	PaymentTransactionDTO paymentTransactionDTO = new PaymentTransactionDTO();
	    	
	    	 Credentials credentials = new Credentials();
		       credentials.setMerchantId("DEMOMDA1234");
		       credentials.setApiKey("REVNT01EQTEyMzR8REVNT01EQQ==");
		       credentials.setApiToken("bmR1ZFFFWEx5R2c2NmhnMEk5a25WenJaZWZwbHFFYldKOGY0bHlGZnBZQ1N5WEpXU2Y1dGt3PT0=");
		       credentials.setSecretKey("nbzjfdiehurgsxct");
		       credentials.setSecretKeyIv("sngtmqpfurxdbkwj");
		       credentials.setRequestId(String.valueOf(System.currentTimeMillis()));
		       credentials.setEnvironment(EnvironmentType.DEMO);

		       RemitaRITSService  ritsService  =  new RemitaRITSServiceImpl(credentials);
		    //    RemitaRITSService  ritsService  = getRemitaRITSService();
		        singleRequest.setTransRef(String.valueOf(new Random().nextLong()));
		        log.info("Payment transaction_id  = %s",singleRequest.getTransRef());
		        SinglePaymentResponse  singlePaymentResponse  =  ritsService.singlePayment(singleRequest);
		        
		    	if((singlePaymentResponse.getData()).getResponseCode().equals("00")) {
			    	//  paymentTransactionDTO.setAmount(new BigDecimal(Long.valueOf(singleRequest.getAmount())));
			    	// paymentTransactionDTO.setAmount(billRequest.getAmountDebitted());
			 		paymentTransactionDTO.setChannel("BANK");
			 		paymentTransactionDTO.setDestinationAccount(String.valueOf(singleRequest.getCreditAccount()));
			 		paymentTransactionDTO.setSourceAccount(singleRequest.getDebitAccount());
			 		paymentTransactionDTO.setDestinationNarration("Bank Transfer into ( "+singleRequest.getCreditAccount()+" )");
			 		paymentTransactionDTO.setPaymenttransID(System.currentTimeMillis());
			 		paymentTransactionDTO.setSourceAccountBankCode(singleRequest.getFromBank()); 
			 		paymentTransactionDTO.setTransactionRef((singlePaymentResponse.getData()).getTransRef());
			 		
			 		responseDTO.setCode("00");
			 		responseDTO.setPaymentTransactionDTO(paymentTransactionDTO);
			 		responseDTO.setMessage("successfull");
					producer.send(responseDTO);
		    	}else {
		    		responseDTO.setCode("99"); 
			 		responseDTO.setMessage("failed");
		    	}
		     return  singlePaymentResponse;
		    }
	  
	  @Override
	  public BulkPaymentResponse postBulkPayment(BulkPaymentRequest bulkPaymentRequest) {
		    RemitaRITSService  ritsService  = getRemitaRITSService();
		    return  ritsService.bulkPayment(bulkPaymentRequest);
	       
	    }

	  @Override
	    public PaymentStatusResponse singlePaymentStatus(PaymentStatusRequest paymentStatusRequest) {
	    	 RemitaRITSService  ritsService  = getRemitaRITSService();
	    	 return   ritsService.singlePaymentStatus(paymentStatusRequest);
	          
	        }
	    
	    
	  @Override
	    public BulkPaymentStatusResponse bulkPaymentStatus(BulkPaymentStatusRequest bulkPaymentStatusRequest){
	    	     RemitaRITSService  ritsService  = getRemitaRITSService();
	           return   ritsService.bulkPaymentStatus(bulkPaymentStatusRequest); 
	        }
	    
	  @Override
	    public AccountEnquiryResponse getAccountEnquiry(AccountEnqiryRequest accountEnqiryRequest){
	    	 RemitaRITSService  ritsService  = getRemitaRITSService();
	         return   ritsService.accountEnquiry(accountEnqiryRequest); 
	  	}
	    
	    
	  @Override
	    public GetActiveBankResponse getActiveBanks(){
	    	 RemitaRITSService  ritsService  = getRemitaRITSService();
	           return    ritsService.getActiveBanks(); 
	        }
	    
	    
	    


}

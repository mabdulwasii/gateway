package ng.com.systemspecs.apigateway.service.impl;

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
 
@Service
@Transactional
public class RITSServiceImpl  implements RITSService {

    private final Logger log = LoggerFactory.getLogger(RITSServiceImpl.class);
   
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
		        RemitaRITSService  ritsService  = getRemitaRITSService();
		        singleRequest.setTransRef(String.valueOf(new Random().nextLong()));
		        log.info("Payment transaction_id  = %s",singleRequest.getTransRef());
		        return ritsService.singlePayment(singleRequest);
		     
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

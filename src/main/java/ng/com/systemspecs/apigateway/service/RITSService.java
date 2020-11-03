package ng.com.systemspecs.apigateway.service;

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

public interface RITSService {
	
	public  RemitaRITSService getRemitaRITSService();
	

	
	  public SinglePaymentResponse singlePayment(SinglePaymentRequest   singlePaymentRequest);
	  
	  public BulkPaymentResponse postBulkPayment(BulkPaymentRequest bulkPaymentRequest) ;

	    public PaymentStatusResponse singlePaymentStatus(PaymentStatusRequest paymentStatusRequest);
	    
	    
	    public BulkPaymentStatusResponse bulkPaymentStatus(BulkPaymentStatusRequest bulkPaymentStatusRequest);
	    
	    
	    public AccountEnquiryResponse getAccountEnquiry(AccountEnqiryRequest accountEnqiryRequest);
	    
	    
	    
	    public GetActiveBankResponse getActiveBanks();
	    
	    

}

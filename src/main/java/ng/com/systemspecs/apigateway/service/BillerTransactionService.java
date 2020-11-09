package ng.com.systemspecs.apigateway.service;

import ng.com.systemspecs.apigateway.service.dto.BillerTransactionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
 
import ng.com.systemspecs.remitabillinggateway.service.RemitaBillingGatewayService;
import ng.com.systemspecs.remitabillinggateway.service.impl.*;
import ng.com.systemspecs.remitabillinggateway.servicetypes.GetServiceResponse;
import ng.com.systemspecs.remitabillinggateway.servicetypes.GetServiceResponseData;
import ng.com.systemspecs.remitabillinggateway.billers.*;
import ng.com.systemspecs.remitabillinggateway.util.*;
import ng.com.systemspecs.remitabillinggateway.validate.*;
import ng.com.systemspecs.remitabillinggateway.rrrdetails.*;
import ng.com.systemspecs.remitabillinggateway.notification.*;
import ng.com.systemspecs.remitabillinggateway.paymentstatus.*;
import ng.com.systemspecs.remitabillinggateway.generaterrr.*;
import ng.com.systemspecs.remitabillinggateway.configuration.*;
import ng.com.systemspecs.remitabillinggateway.customfields.*;

import java.util.Optional;

/**
 * Service Interface for managing {@link ng.com.systemspecs.apigateway.domain.BillerTransaction}.
 */
public interface BillerTransactionService {

    /**
     * Save a billerTransaction.
     *
     * @param billerTransactionDTO the entity to save.
     * @return the persisted entity.
     */
    BillerTransactionDTO save(BillerTransactionDTO billerTransactionDTO);

    /**
     * Get all the billerTransactions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BillerTransactionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" billerTransaction.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BillerTransactionDTO> findOne(Long id);

    /**
     * Delete the "id" billerTransaction.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
	
	
	 public   RemitaBillingGatewayService  getRemitaBillingGatewayService();
	 
	 public   RemitaBillingGatewayService  notifyBillingGatewayService();
    
    public GetRRRDetailsResponse getRRR(String rrr) ;   
    
    
    public ValidateResponse validate(ValidateRequest validateRequest);
    
    public GenerateResponse generateRRR(ValidateRequest validateRequest);   
    
    
    public BillNotificationResponse billNotification(BillRequest billRequest);
    
    
    public GetTransactionStatusResponse getTransactionStatus(String transactionId);
    
    
  public ValidateResponse validateTest();
    
    public GenerateResponse generateRRRTest();   
    
    public BillNotificationResponse billNotificationTest();
	
	
}

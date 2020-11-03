package ng.com.systemspecs.apigateway.service;

import ng.com.systemspecs.apigateway.service.dto.BillerDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



import ng.com.systemspecs.remitabillinggateway.service.*;
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
 * Service Interface for managing {@link ng.com.systemspecs.apigateway.domain.Biller}.
 */
public interface BillerService {

    /**
     * Save a biller.
     *
     * @param billerDTO the entity to save.
     * @return the persisted entity.
     */
    BillerDTO save(BillerDTO billerDTO);

    /**
     * Get all the billers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BillerDTO> findAll(Pageable pageable);


    /**
     * Get the "id" biller.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BillerDTO> findOne(Long id);

    /**
     * Delete the "id" biller.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
	
	
	 public  RemitaBillingGatewayService  getRemitaBillingGatewayService();
     
    public GetServiceResponse getServices(String billerName);
    
    public GetCustomFieldResponse getServiceCustomFields(String customFieldId);    
    
     public  GetBillerResponse getbillers();
   
   
}

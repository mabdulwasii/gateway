package ng.com.systemspecs.apigateway.service.impl;

import ng.com.systemspecs.apigateway.service.BillerService;
import ng.com.systemspecs.apigateway.domain.Biller;
import ng.com.systemspecs.apigateway.repository.BillerRepository;
import ng.com.systemspecs.apigateway.service.dto.BillerDTO;
import ng.com.systemspecs.apigateway.service.mapper.BillerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import ng.com.systemspecs.remitabillinggateway.configuration.Credentials;
import ng.com.systemspecs.remitabillinggateway.util.*;
import ng.com.systemspecs.remitabillinggateway.service.impl.*; 
import ng.com.systemspecs.remitabillinggateway.customfields.*;

import java.util.Optional;


import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;


/**
 * Service Implementation for managing {@link Biller}.
 */
@Service
@Transactional
public class BillerServiceImpl implements BillerService {

    private final Logger log = LoggerFactory.getLogger(BillerServiceImpl.class);

    private final BillerRepository billerRepository;

    private final BillerMapper billerMapper;
	
	

    public BillerServiceImpl(BillerRepository billerRepository, BillerMapper billerMapper) {
        this.billerRepository = billerRepository;
        this.billerMapper = billerMapper;
    }
	
	
	   
    @Override
    public   RemitaBillingGatewayService  getRemitaBillingGatewayService() {
    	Credentials credentials = new Credentials(); 
    	credentials.setPublicKey("MjMyfDQwODE4MzI3fGYyNjU3N2RjMGRjZGE1ZmExYmQ4YzU2M2I0ZjIxMDE0Yzc5MzQ5NjVmYzYxNWJjOWRkZjM2NjM5ZTg3ZTE2ZjQ1MzcxMjVmZjJlMzlmOGI2MjkzMGRhZjc2NTZiNzdjYTZkZGQwZDczZjIxZjA4ZDVlZTQ0NzZiZmY3MzAyZDA0");
    	credentials.setSecretKey("80bcb41920b30f27ac0fab456c5d79d0a58e622192013649b39d690ddacc8cc2fe37348406339c97c677f4de43bb1137527c3f5e25fd8a5c4bb7ec7ca5fc24af");
    	credentials.setTransactionId(String.valueOf(System.currentTimeMillis()));
    	credentials.setEnvironment(EnvironmentType.DEMO);
    	
    	return  new RemitaBillingGatewayServiceImpl(credentials);    	 
    }

	

    @Override
    public BillerDTO save(BillerDTO billerDTO) {
        log.debug("Request to save Biller : {}", billerDTO);
        Biller biller = billerMapper.toEntity(billerDTO);
        biller = billerRepository.save(biller);
        return billerMapper.toDto(biller);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BillerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Billers");
        return billerRepository.findAll(pageable)
            .map(billerMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<BillerDTO> findOne(Long id) {
        log.debug("Request to get Biller : {}", id);
        return billerRepository.findById(id)
            .map(billerMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Biller : {}", id);
        billerRepository.deleteById(id);
    }
	
	
	    @Override
    public GetServiceResponse getServices(String billerName) { 
    	   RemitaBillingGatewayService  gatewayService =  getRemitaBillingGatewayService();
    	   return   gatewayService.getService(billerName);
           
        }
    
    @Override
    public GetCustomFieldResponse getServiceCustomFields(String billerServiceId) { 
    	RemitaBillingGatewayService  gatewayService =  getRemitaBillingGatewayService();
        return gatewayService.getCustomField(billerServiceId);
    }
    
   
    @Override
    public GetBillerResponse getbillers(){ 
    	    RemitaBillingGatewayService  gatewayService =  getRemitaBillingGatewayService();
    	    return   gatewayService.getBillers();
           
     }
	 
}

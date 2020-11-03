package ng.com.systemspecs.apigateway.service.impl;

import ng.com.systemspecs.apigateway.service.BillerTransactionService;
import ng.com.systemspecs.apigateway.domain.BillerTransaction;
import ng.com.systemspecs.apigateway.repository.BillerTransactionRepository;
import ng.com.systemspecs.apigateway.service.dto.BillerTransactionDTO;
import ng.com.systemspecs.apigateway.service.mapper.BillerTransactionMapper;
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

import  java.util.List;
import  java.util.ArrayList;
import  java.math.BigDecimal;

/**
 * Service Implementation for managing {@link BillerTransaction}.
 */
@Service
@Transactional
public class BillerTransactionServiceImpl implements BillerTransactionService {

    private final Logger log = LoggerFactory.getLogger(BillerTransactionServiceImpl.class);

    private final BillerTransactionRepository billerTransactionRepository;

    private final BillerTransactionMapper billerTransactionMapper;
	
	

    public BillerTransactionServiceImpl(BillerTransactionRepository billerTransactionRepository, BillerTransactionMapper billerTransactionMapper) {
        this.billerTransactionRepository = billerTransactionRepository;
        this.billerTransactionMapper = billerTransactionMapper;
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
    public BillerTransactionDTO save(BillerTransactionDTO billerTransactionDTO) {
        log.debug("Request to save BillerTransaction : {}", billerTransactionDTO);
        BillerTransaction billerTransaction = billerTransactionMapper.toEntity(billerTransactionDTO);
        billerTransaction = billerTransactionRepository.save(billerTransaction);
        return billerTransactionMapper.toDto(billerTransaction);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BillerTransactionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BillerTransactions");
        return billerTransactionRepository.findAll(pageable)
            .map(billerTransactionMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<BillerTransactionDTO> findOne(Long id) {
        log.debug("Request to get BillerTransaction : {}", id);
        return billerTransactionRepository.findById(id)
            .map(billerTransactionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete BillerTransaction : {}", id);
        billerTransactionRepository.deleteById(id);
    }
	
	
	
	
	    @Override
    public GetRRRDetailsResponse getRRR(String rrr) {
    	RemitaBillingGatewayService  gatewayService =  getRemitaBillingGatewayService();
        return gatewayService.getRRRDetails(rrr);
    }
    
    
    @Override
    public ValidateResponse validate(ValidateRequest validateRequest){
        
    	RemitaBillingGatewayService  gatewayService =  getRemitaBillingGatewayService();
    	return   gatewayService.validate(validateRequest);
      
    }
    
    @Override
    public GenerateResponse generateRRR(ValidateRequest validateRequest){
        
    	RemitaBillingGatewayService  gatewayService =  getRemitaBillingGatewayService();
    	return  gatewayService.generateRRR(validateRequest);
        
    }
    
    
    @Override
    public BillNotificationResponse billNotification(BillRequest billRequest){
        
    	RemitaBillingGatewayService  gatewayService =  getRemitaBillingGatewayService();
    	 return   gatewayService.billNotification(billRequest);
        
    }
    
    
    
    @Override
    public GetTransactionStatusResponse getTransactionStatus(String transactionId){
        
    	RemitaBillingGatewayService  gatewayService =  getRemitaBillingGatewayService();
        return gatewayService.getTransactionStatus(transactionId);
    }
    
    
    
    
    
    @Override
    public ValidateResponse validateTest(){
        
    	RemitaBillingGatewayService  gatewayService =  getRemitaBillingGatewayService();
    	 ValidateRequest validateRequest = new ValidateRequest();
         List<CustomField> customFieldList = new ArrayList<CustomField>();
         List<Value> valueList = new ArrayList<Value>();
         List<Value> valueList2 = new ArrayList<Value>();
         CustomField customField = new CustomField();
         CustomField customField1 = new CustomField();
         Value value = new Value();
         Value value1 = new Value();
         value.setAmount(BigDecimal.valueOf(1000));
         value.setQuantity(1);
         value.setValue("25083616");
         value1.setAmount(BigDecimal.valueOf(1000));
         value1.setQuantity(0);
         value1.setValue("Amount");
         valueList.add(value);
         valueList2.add(value1);
         customField.setValues(valueList);
         customField.setId("25083613");
         customField1.setValues(valueList2);
         customField1.setId("25083617");
         customFieldList.add(customField);
         customFieldList.add(customField1);
         validateRequest.setCustomFields(customFieldList);
         validateRequest.setAmount(BigDecimal.valueOf(1000));
         validateRequest.setBillId("25083618");
         validateRequest.setCurrency("NGN");
         validateRequest.setPayerEmail("euniceswit@gmail.com");
         validateRequest.setPayerName("Eunice Olukitibi");
         validateRequest.setPayerPhone("080339887160");
    	return   gatewayService.validate(validateRequest);
      
    }
    
    @Override
    public GenerateResponse generateRRRTest(){
        
    	RemitaBillingGatewayService  gatewayService =  getRemitaBillingGatewayService();
    	ValidateRequest validateRequest = new ValidateRequest();
        List<CustomField> customFieldList = new ArrayList<CustomField>();
        List<Value> valueList = new ArrayList<Value>();
        List<Value> valueList2 = new ArrayList<Value>();
        CustomField customField = new CustomField();
        CustomField customField1 = new CustomField();
        Value value = new Value();
        Value value1 = new Value();
        value.setAmount(BigDecimal.valueOf(1000));
        value.setQuantity(1);
        value.setValue("25083616");
        value1.setAmount(BigDecimal.valueOf(1000));
        value1.setQuantity(0);
        value1.setValue("Amount");
        valueList.add(value);
        valueList2.add(value1);
        customField.setValues(valueList);
        customField.setId("25083613");
        customField1.setValues(valueList2);
        customField1.setId("25083617");
        customFieldList.add(customField);
        customFieldList.add(customField1);
        validateRequest.setCustomFields(customFieldList);
        validateRequest.setAmount(BigDecimal.valueOf(1000));
        validateRequest.setBillId("25083618");
        validateRequest.setCurrency("NGN");
        validateRequest.setPayerEmail("euniceswit@gmail.com");
        validateRequest.setPayerName("Eunice Olukitibi");
        validateRequest.setPayerPhone("080339887160");
    	return  gatewayService.generateRRR(validateRequest);
        
    }
    
    @Override
    public BillNotificationResponse billNotificationTest(){
        
    RemitaBillingGatewayService  gatewayService =  getRemitaBillingGatewayService(); 
    BillRequest billRequest = new BillRequest();
    billRequest.setRrr("270007870639");
    billRequest.setAmountDebitted("1000");
    billRequest.setDebittedAccount("0035509366");
    billRequest.setBranchCode("546789096");
    billRequest.setFundingSource("TOKS");
    billRequest.setIncomeAccount("0001061499");
    billRequest.setTellerName("INTERNETBANKING");
    billRequest.setPaymentChannel("INTERNETBANKING");
    billRequest.setPaymentAuthCode("5467890968");
	 return   gatewayService.billNotification(billRequest);
     
}
    
	
	
	
}

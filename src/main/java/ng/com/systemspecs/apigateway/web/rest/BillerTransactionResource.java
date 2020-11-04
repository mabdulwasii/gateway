package ng.com.systemspecs.apigateway.web.rest; 

import ng.com.systemspecs.apigateway.service.BillerTransactionService;
import ng.com.systemspecs.apigateway.web.rest.errors.BadRequestAlertException;
import ng.com.systemspecs.apigateway.service.dto.BillerTransactionDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import feign.Headers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;


import ng.com.systemspecs.apigateway.client.ExternalRESTClient;

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



/**
 * REST controller for managing {@link ng.com.systemspecs.apigateway.domain.BillerTransaction}.
 */
@RestController
@RequestMapping("/api")
public class BillerTransactionResource {

    private final Logger log = LoggerFactory.getLogger(BillerTransactionResource.class);

    private static final String ENTITY_NAME = "billerTransaction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BillerTransactionService billerTransactionService;
	
	private final ExternalRESTClient  externalRESTClient;

     public BillerTransactionResource(BillerTransactionService billerTransactionService, ExternalRESTClient  externalRESTClient) {
        this.billerTransactionService = billerTransactionService;
        this.externalRESTClient =  externalRESTClient;
    }

    /**
     * {@code POST  /biller-transactions} : Create a new billerTransaction.
     *
     * @param billerTransactionDTO the billerTransactionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new billerTransactionDTO, or with status {@code 400 (Bad Request)} if the billerTransaction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/biller-transactions")
    public ResponseEntity<BillerTransactionDTO> createBillerTransaction(@RequestBody BillerTransactionDTO billerTransactionDTO) throws URISyntaxException {
        log.debug("REST request to save BillerTransaction : {}", billerTransactionDTO);
        if (billerTransactionDTO.getId() != null) {
            throw new BadRequestAlertException("A new billerTransaction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BillerTransactionDTO result = billerTransactionService.save(billerTransactionDTO);
        return ResponseEntity.created(new URI("/api/biller-transactions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /biller-transactions} : Updates an existing billerTransaction.
     *
     * @param billerTransactionDTO the billerTransactionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated billerTransactionDTO,
     * or with status {@code 400 (Bad Request)} if the billerTransactionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the billerTransactionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/biller-transactions")
    public ResponseEntity<BillerTransactionDTO> updateBillerTransaction(@RequestBody BillerTransactionDTO billerTransactionDTO) throws URISyntaxException {
        log.debug("REST request to update BillerTransaction : {}", billerTransactionDTO);
        if (billerTransactionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BillerTransactionDTO result = billerTransactionService.save(billerTransactionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, billerTransactionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /biller-transactions} : get all the billerTransactions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of billerTransactions in body.
     */
    @GetMapping("/biller-transactions")
    public ResponseEntity<List<BillerTransactionDTO>> getAllBillerTransactions(Pageable pageable) {
        log.debug("REST request to get a page of BillerTransactions");
        Page<BillerTransactionDTO> page = billerTransactionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /biller-transactions/:id} : get the "id" billerTransaction.
     *
     * @param id the id of the billerTransactionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the billerTransactionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/biller-transactions/{id}")
    public ResponseEntity<BillerTransactionDTO> getBillerTransaction(@PathVariable Long id) {
        log.debug("REST request to get BillerTransaction : {}", id);
        Optional<BillerTransactionDTO> billerTransactionDTO = billerTransactionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(billerTransactionDTO);
    }

    /**
     * {@code DELETE  /biller-transactions/:id} : delete the "id" billerTransaction.
     *
     * @param id the id of the billerTransactionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/biller-transactions/{id}")
    public ResponseEntity<Void> deleteBillerTransaction(@PathVariable Long id) {
        log.debug("REST request to delete BillerTransaction : {}", id);
        billerTransactionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
	
	
	    

    @GetMapping("/validate-rrr/{rrr}")
    public GetRRRDetailsResponse getRRR(@PathVariable String rrr) {
    	return billerTransactionService.getRRR(rrr);
    }
    
    @PostMapping("/validate-request")
    public ValidateResponse validate(@RequestBody  ValidateRequest validateRequest) {
    	return billerTransactionService.validate(validateRequest);
    } 
    
    @PostMapping("/generate-rrr")
    public GenerateResponse generateRRR(@RequestBody ValidateRequest validateRequest) {
    	return billerTransactionService.generateRRR(validateRequest);
    }  
    
    @PostMapping("/pay-biller-and-notify")
    public BillNotificationResponse billNotification(@RequestBody BillRequest billRequest) {
    	return billerTransactionService.billNotification(billRequest);
    }
    
    @GetMapping("/check-transaction-status/{transactionId}")
    public GetTransactionStatusResponse getTransactionStatus(@PathVariable String transactionId) {
    	return billerTransactionService.getTransactionStatus(transactionId);
    }
    
    
    
    
    @PostMapping("/validate-request-test")
    public ValidateResponse validate() {
    	return billerTransactionService.validateTest();
    } 
    
    @PostMapping("/generate-rrr-test")
    public GenerateResponse generateRRR() {
    	return billerTransactionService.generateRRRTest();
    }  
    
    
    @PostMapping("/pay-biller-and-notify-test")
    public BillNotificationResponse billNotificationTest() {
    	return billerTransactionService.billNotificationTest();
    }
    

	@GetMapping("/billing/receipt/{rrr}/{requestId}/rest.reg")
    @ResponseStatus(HttpStatus.ACCEPTED) 
    public  ResponseEntity<byte[]> getRRRReceipt(@PathVariable("rrr") String rrr, @PathVariable("requestId") String requestId) {
    	String  publicKey  = "dC5vbW9udWJpQGdtYWlsLmNvbXxiM2RjMDhjZDRlZTc5ZDIxZDQwMjdjOWM3MmI5ZWY0ZDA3MTk2YTRkNGRkMjY3NjNkMGZkYzA4MjM1MzI4OWFhODE5OGM4MjM0NTI2YWI2ZjZkYzNhZmQzNDNkZmIzYmUwNTkxODlmMmNkOTkxNmM5MjVhNjYwZjk0ZTk1OTkwNw==";
    	HttpHeaders responseHeaders = new HttpHeaders();
    	responseHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM); 
    	return new ResponseEntity<byte[]>(externalRESTClient.getRRRReceipt(publicKey,rrr,requestId), responseHeaders, HttpStatus.OK);
    //	return externalRESTClient.getRRRReceipt(publicKey,rrr,requestId);
    }
	
	
}

package ng.com.systemspecs.apigateway.web.rest;

import ng.com.systemspecs.apigateway.service.WalletAccountService;
import ng.com.systemspecs.apigateway.web.rest.errors.BadRequestAlertException;
import ng.com.systemspecs.apigateway.service.dto.FundDTO;
import ng.com.systemspecs.apigateway.service.dto.ResponseDTO;
import ng.com.systemspecs.apigateway.service.dto.WalletAccountDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * REST controller for managing {@link ng.com.systemspecs.apigateway.domain.WalletAccount}.
 */
@RestController
@RequestMapping("/api")
public class WalletAccountResource {

    private final Logger log = LoggerFactory.getLogger(WalletAccountResource.class);

    private static final String ENTITY_NAME = "walletAccount";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WalletAccountService walletAccountService;

    public WalletAccountResource(WalletAccountService walletAccountService) {
        this.walletAccountService = walletAccountService;
    }

    /**
     * {@code POST  /wallet-accounts} : Create a new walletAccount.
     *
     * @param walletAccountDTO the walletAccountDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new walletAccountDTO, or with status {@code 400 (Bad Request)} if the walletAccount has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/wallet-accounts")
    public ResponseEntity<WalletAccountDTO> createWalletAccount(@RequestBody WalletAccountDTO walletAccountDTO) throws URISyntaxException {
        log.debug("REST request to save WalletAccount : {}", walletAccountDTO);
        if (walletAccountDTO.getId() != null) {
            throw new BadRequestAlertException("A new walletAccount cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Random rand = new Random();

        long drand = (long)(rand.nextDouble()*10000000000L);
        walletAccountDTO.setAccountNumber(drand);
        walletAccountDTO.setCurrentBalance(0.00);
        WalletAccountDTO result = walletAccountService.save(walletAccountDTO);
        return ResponseEntity.created(new URI("/api/wallet-accounts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /wallet-accounts} : Updates an existing walletAccount.
     *
     * @param walletAccountDTO the walletAccountDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated walletAccountDTO,
     * or with status {@code 400 (Bad Request)} if the walletAccountDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the walletAccountDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/wallet-accounts")
    public ResponseEntity<WalletAccountDTO> updateWalletAccount(@RequestBody WalletAccountDTO walletAccountDTO) throws URISyntaxException {
        log.debug("REST request to update WalletAccount : {}", walletAccountDTO);
        if (walletAccountDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WalletAccountDTO result = walletAccountService.save(walletAccountDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, walletAccountDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /wallet-accounts} : get all the walletAccounts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of walletAccounts in body.
     */
    @GetMapping("/wallet-accounts")
    public List<WalletAccountDTO> getAllWalletAccounts() {
        log.debug("REST request to get all WalletAccounts");
        return walletAccountService.findAll();
    }
    @GetMapping("/customer_wallet-accounts")
    public List<WalletAccountDTO> getAllCustomerWalletAccounts() {
        log.debug("REST request to get all WalletAccounts");
        return walletAccountService.findByUserIsCurrentUser();
    }
    /**
     * {@code GET  /wallet-accounts/:id} : get the "id" walletAccount.
     *
     * @param id the id of the walletAccountDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the walletAccountDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/wallet-accounts/{id}")
    public ResponseEntity<WalletAccountDTO> getWalletAccount(@PathVariable Long id) {
        log.debug("REST request to get WalletAccount : {}", id);
        Optional<WalletAccountDTO> walletAccountDTO = walletAccountService.findOne(id);
        return ResponseUtil.wrapOrNotFound(walletAccountDTO);
    }

    /**
     * {@code DELETE  /wallet-accounts/:id} : delete the "id" walletAccount.
     *
     * @param id the id of the walletAccountDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/wallet-accounts/{id}")
    public ResponseEntity<Void> deleteWalletAccount(@PathVariable Long id) {
        log.debug("REST request to delete WalletAccount : {}", id);
        walletAccountService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
    @PostMapping("/fund-wallet")
    public ResponseDTO fundWalletAccount(@RequestBody FundDTO fundDTO) throws URISyntaxException {
        log.debug("REST request to fund WalletAccount : {}", fundDTO);
		/*
		 * if (walletAccountDTO.getId() != null) { throw new
		 * BadRequestAlertException("A new walletAccount cannot already have an ID",
		 * ENTITY_NAME, "idexists"); }
		 */
        Random rand = new Random();
        ResponseDTO response = walletAccountService.fund(fundDTO);
        return response;
    }  
    
    @PostMapping("/send-money")
    public ResponseDTO sendMoney(@RequestBody FundDTO sendMoneyDTO) throws URISyntaxException {
        log.debug("REST request to send money from WalletAccount : {}", sendMoneyDTO);

        Random rand = new Random();
        ResponseDTO response = walletAccountService.sendMoney(sendMoneyDTO);
        return response;
    }     
}

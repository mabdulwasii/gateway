package ng.com.systemspecs.apigateway.web.rest;

import ng.com.systemspecs.apigateway.service.WalletAccountTypeService;
import ng.com.systemspecs.apigateway.web.rest.errors.BadRequestAlertException;
import ng.com.systemspecs.apigateway.service.dto.WalletAccountTypeDTO;

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

/**
 * REST controller for managing {@link ng.com.systemspecs.apigateway.domain.WalletAccountType}.
 */
@RestController
@RequestMapping("/api")
public class WalletAccountTypeResource {

    private final Logger log = LoggerFactory.getLogger(WalletAccountTypeResource.class);

    private static final String ENTITY_NAME = "walletAccountType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WalletAccountTypeService walletAccountTypeService;

    public WalletAccountTypeResource(WalletAccountTypeService walletAccountTypeService) {
        this.walletAccountTypeService = walletAccountTypeService;
    }

    /**
     * {@code POST  /wallet-account-types} : Create a new walletAccountType.
     *
     * @param walletAccountTypeDTO the walletAccountTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new walletAccountTypeDTO, or with status {@code 400 (Bad Request)} if the walletAccountType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/wallet-account-types")
    public ResponseEntity<WalletAccountTypeDTO> createWalletAccountType(@RequestBody WalletAccountTypeDTO walletAccountTypeDTO) throws URISyntaxException {
        log.debug("REST request to save WalletAccountType : {}", walletAccountTypeDTO);
        if (walletAccountTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new walletAccountType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WalletAccountTypeDTO result = walletAccountTypeService.save(walletAccountTypeDTO);
        return ResponseEntity.created(new URI("/api/wallet-account-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /wallet-account-types} : Updates an existing walletAccountType.
     *
     * @param walletAccountTypeDTO the walletAccountTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated walletAccountTypeDTO,
     * or with status {@code 400 (Bad Request)} if the walletAccountTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the walletAccountTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/wallet-account-types")
    public ResponseEntity<WalletAccountTypeDTO> updateWalletAccountType(@RequestBody WalletAccountTypeDTO walletAccountTypeDTO) throws URISyntaxException {
        log.debug("REST request to update WalletAccountType : {}", walletAccountTypeDTO);
        if (walletAccountTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WalletAccountTypeDTO result = walletAccountTypeService.save(walletAccountTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, walletAccountTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /wallet-account-types} : get all the walletAccountTypes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of walletAccountTypes in body.
     */
    @GetMapping("/wallet-account-types")
    public List<WalletAccountTypeDTO> getAllWalletAccountTypes() {
        log.debug("REST request to get all WalletAccountTypes");
        return walletAccountTypeService.findAll();
    }

    /**
     * {@code GET  /wallet-account-types/:id} : get the "id" walletAccountType.
     *
     * @param id the id of the walletAccountTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the walletAccountTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/wallet-account-types/{id}")
    public ResponseEntity<WalletAccountTypeDTO> getWalletAccountType(@PathVariable Long id) {
        log.debug("REST request to get WalletAccountType : {}", id);
        Optional<WalletAccountTypeDTO> walletAccountTypeDTO = walletAccountTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(walletAccountTypeDTO);
    }

    /**
     * {@code DELETE  /wallet-account-types/:id} : delete the "id" walletAccountType.
     *
     * @param id the id of the walletAccountTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/wallet-account-types/{id}")
    public ResponseEntity<Void> deleteWalletAccountType(@PathVariable Long id) {
        log.debug("REST request to delete WalletAccountType : {}", id);
        walletAccountTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

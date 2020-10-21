package ng.com.systemspecs.apigateway.web.rest;

import ng.com.systemspecs.apigateway.service.CountrolAccountService;
import ng.com.systemspecs.apigateway.web.rest.errors.BadRequestAlertException;
import ng.com.systemspecs.apigateway.service.dto.CountrolAccountDTO;

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
 * REST controller for managing {@link ng.com.systemspecs.apigateway.domain.CountrolAccount}.
 */
@RestController
@RequestMapping("/api")
public class CountrolAccountResource {

    private final Logger log = LoggerFactory.getLogger(CountrolAccountResource.class);

    private static final String ENTITY_NAME = "countrolAccount";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CountrolAccountService countrolAccountService;

    public CountrolAccountResource(CountrolAccountService countrolAccountService) {
        this.countrolAccountService = countrolAccountService;
    }

    /**
     * {@code POST  /countrol-accounts} : Create a new countrolAccount.
     *
     * @param countrolAccountDTO the countrolAccountDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new countrolAccountDTO, or with status {@code 400 (Bad Request)} if the countrolAccount has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/countrol-accounts")
    public ResponseEntity<CountrolAccountDTO> createCountrolAccount(@RequestBody CountrolAccountDTO countrolAccountDTO) throws URISyntaxException {
        log.debug("REST request to save CountrolAccount : {}", countrolAccountDTO);
        if (countrolAccountDTO.getId() != null) {
            throw new BadRequestAlertException("A new countrolAccount cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CountrolAccountDTO result = countrolAccountService.save(countrolAccountDTO);
        return ResponseEntity.created(new URI("/api/countrol-accounts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /countrol-accounts} : Updates an existing countrolAccount.
     *
     * @param countrolAccountDTO the countrolAccountDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated countrolAccountDTO,
     * or with status {@code 400 (Bad Request)} if the countrolAccountDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the countrolAccountDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/countrol-accounts")
    public ResponseEntity<CountrolAccountDTO> updateCountrolAccount(@RequestBody CountrolAccountDTO countrolAccountDTO) throws URISyntaxException {
        log.debug("REST request to update CountrolAccount : {}", countrolAccountDTO);
        if (countrolAccountDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CountrolAccountDTO result = countrolAccountService.save(countrolAccountDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, countrolAccountDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /countrol-accounts} : get all the countrolAccounts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of countrolAccounts in body.
     */
    @GetMapping("/countrol-accounts")
    public List<CountrolAccountDTO> getAllCountrolAccounts() {
        log.debug("REST request to get all CountrolAccounts");
        return countrolAccountService.findAll();
    }

    /**
     * {@code GET  /countrol-accounts/:id} : get the "id" countrolAccount.
     *
     * @param id the id of the countrolAccountDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the countrolAccountDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/countrol-accounts/{id}")
    public ResponseEntity<CountrolAccountDTO> getCountrolAccount(@PathVariable Long id) {
        log.debug("REST request to get CountrolAccount : {}", id);
        Optional<CountrolAccountDTO> countrolAccountDTO = countrolAccountService.findOne(id);
        return ResponseUtil.wrapOrNotFound(countrolAccountDTO);
    }

    /**
     * {@code DELETE  /countrol-accounts/:id} : delete the "id" countrolAccount.
     *
     * @param id the id of the countrolAccountDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/countrol-accounts/{id}")
    public ResponseEntity<Void> deleteCountrolAccount(@PathVariable Long id) {
        log.debug("REST request to delete CountrolAccount : {}", id);
        countrolAccountService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

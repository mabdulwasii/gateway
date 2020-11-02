package ng.com.systemspecs.apigateway.web.rest;

import ng.com.systemspecs.apigateway.service.CustomersubscriptionService;
import ng.com.systemspecs.apigateway.web.rest.errors.BadRequestAlertException;
import ng.com.systemspecs.apigateway.service.dto.CustomersubscriptionDTO;

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
 * REST controller for managing {@link ng.com.systemspecs.apigateway.domain.Customersubscription}.
 */
@RestController
@RequestMapping("/api")
public class CustomersubscriptionResource {

    private final Logger log = LoggerFactory.getLogger(CustomersubscriptionResource.class);

    private static final String ENTITY_NAME = "customersubscription";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CustomersubscriptionService customersubscriptionService;

    public CustomersubscriptionResource(CustomersubscriptionService customersubscriptionService) {
        this.customersubscriptionService = customersubscriptionService;
    }

    /**
     * {@code POST  /customersubscriptions} : Create a new customersubscription.
     *
     * @param customersubscriptionDTO the customersubscriptionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new customersubscriptionDTO, or with status {@code 400 (Bad Request)} if the customersubscription has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/customersubscriptions")
    public ResponseEntity<CustomersubscriptionDTO> createCustomersubscription(@RequestBody CustomersubscriptionDTO customersubscriptionDTO) throws URISyntaxException {
        log.debug("REST request to save Customersubscription : {}", customersubscriptionDTO);
        if (customersubscriptionDTO.getId() != null) {
            throw new BadRequestAlertException("A new customersubscription cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CustomersubscriptionDTO result = customersubscriptionService.save(customersubscriptionDTO);
        return ResponseEntity.created(new URI("/api/customersubscriptions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /customersubscriptions} : Updates an existing customersubscription.
     *
     * @param customersubscriptionDTO the customersubscriptionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customersubscriptionDTO,
     * or with status {@code 400 (Bad Request)} if the customersubscriptionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the customersubscriptionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/customersubscriptions")
    public ResponseEntity<CustomersubscriptionDTO> updateCustomersubscription(@RequestBody CustomersubscriptionDTO customersubscriptionDTO) throws URISyntaxException {
        log.debug("REST request to update Customersubscription : {}", customersubscriptionDTO);
        if (customersubscriptionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CustomersubscriptionDTO result = customersubscriptionService.save(customersubscriptionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, customersubscriptionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /customersubscriptions} : get all the customersubscriptions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of customersubscriptions in body.
     */
    @GetMapping("/customersubscriptions")
    public List<CustomersubscriptionDTO> getAllCustomersubscriptions() {
        log.debug("REST request to get all Customersubscriptions");
        return customersubscriptionService.findAll();
    }

    /**
     * {@code GET  /customersubscriptions/:id} : get the "id" customersubscription.
     *
     * @param id the id of the customersubscriptionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the customersubscriptionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/customersubscriptions/{id}")
    public ResponseEntity<CustomersubscriptionDTO> getCustomersubscription(@PathVariable Long id) {
        log.debug("REST request to get Customersubscription : {}", id);
        Optional<CustomersubscriptionDTO> customersubscriptionDTO = customersubscriptionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(customersubscriptionDTO);
    }

    /**
     * {@code DELETE  /customersubscriptions/:id} : delete the "id" customersubscription.
     *
     * @param id the id of the customersubscriptionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/customersubscriptions/{id}")
    public ResponseEntity<Void> deleteCustomersubscription(@PathVariable Long id) {
        log.debug("REST request to delete Customersubscription : {}", id);
        customersubscriptionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

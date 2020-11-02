package ng.com.systemspecs.apigateway.web.rest;

import ng.com.systemspecs.apigateway.service.BillerPlatformService;
import ng.com.systemspecs.apigateway.web.rest.errors.BadRequestAlertException;
import ng.com.systemspecs.apigateway.service.dto.BillerPlatformDTO;

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
 * REST controller for managing {@link ng.com.systemspecs.apigateway.domain.BillerPlatform}.
 */
@RestController
@RequestMapping("/api")
public class BillerPlatformResource {

    private final Logger log = LoggerFactory.getLogger(BillerPlatformResource.class);

    private static final String ENTITY_NAME = "billerPlatform";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BillerPlatformService billerPlatformService;

    public BillerPlatformResource(BillerPlatformService billerPlatformService) {
        this.billerPlatformService = billerPlatformService;
    }

    /**
     * {@code POST  /biller-platforms} : Create a new billerPlatform.
     *
     * @param billerPlatformDTO the billerPlatformDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new billerPlatformDTO, or with status {@code 400 (Bad Request)} if the billerPlatform has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/biller-platforms")
    public ResponseEntity<BillerPlatformDTO> createBillerPlatform(@RequestBody BillerPlatformDTO billerPlatformDTO) throws URISyntaxException {
        log.debug("REST request to save BillerPlatform : {}", billerPlatformDTO);
        if (billerPlatformDTO.getId() != null) {
            throw new BadRequestAlertException("A new billerPlatform cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BillerPlatformDTO result = billerPlatformService.save(billerPlatformDTO);
        return ResponseEntity.created(new URI("/api/biller-platforms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /biller-platforms} : Updates an existing billerPlatform.
     *
     * @param billerPlatformDTO the billerPlatformDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated billerPlatformDTO,
     * or with status {@code 400 (Bad Request)} if the billerPlatformDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the billerPlatformDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/biller-platforms")
    public ResponseEntity<BillerPlatformDTO> updateBillerPlatform(@RequestBody BillerPlatformDTO billerPlatformDTO) throws URISyntaxException {
        log.debug("REST request to update BillerPlatform : {}", billerPlatformDTO);
        if (billerPlatformDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BillerPlatformDTO result = billerPlatformService.save(billerPlatformDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, billerPlatformDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /biller-platforms} : get all the billerPlatforms.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of billerPlatforms in body.
     */
    @GetMapping("/biller-platforms")
    public List<BillerPlatformDTO> getAllBillerPlatforms() {
        log.debug("REST request to get all BillerPlatforms");
        return billerPlatformService.findAll();
    }

    /**
     * {@code GET  /biller-platforms/:id} : get the "id" billerPlatform.
     *
     * @param id the id of the billerPlatformDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the billerPlatformDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/biller-platforms/{id}")
    public ResponseEntity<BillerPlatformDTO> getBillerPlatform(@PathVariable Long id) {
        log.debug("REST request to get BillerPlatform : {}", id);
        Optional<BillerPlatformDTO> billerPlatformDTO = billerPlatformService.findOne(id);
        return ResponseUtil.wrapOrNotFound(billerPlatformDTO);
    }

    /**
     * {@code DELETE  /biller-platforms/:id} : delete the "id" billerPlatform.
     *
     * @param id the id of the billerPlatformDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/biller-platforms/{id}")
    public ResponseEntity<Void> deleteBillerPlatform(@PathVariable Long id) {
        log.debug("REST request to delete BillerPlatform : {}", id);
        billerPlatformService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

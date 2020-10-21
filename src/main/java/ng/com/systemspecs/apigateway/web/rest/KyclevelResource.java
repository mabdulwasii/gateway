package ng.com.systemspecs.apigateway.web.rest;

import ng.com.systemspecs.apigateway.service.KyclevelService;
import ng.com.systemspecs.apigateway.web.rest.errors.BadRequestAlertException;
import ng.com.systemspecs.apigateway.service.dto.KyclevelDTO;

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
 * REST controller for managing {@link ng.com.systemspecs.apigateway.domain.Kyclevel}.
 */
@RestController
@RequestMapping("/api")
public class KyclevelResource {

    private final Logger log = LoggerFactory.getLogger(KyclevelResource.class);

    private static final String ENTITY_NAME = "kyclevel";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final KyclevelService kyclevelService;

    public KyclevelResource(KyclevelService kyclevelService) {
        this.kyclevelService = kyclevelService;
    }

    /**
     * {@code POST  /kyclevels} : Create a new kyclevel.
     *
     * @param kyclevelDTO the kyclevelDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new kyclevelDTO, or with status {@code 400 (Bad Request)} if the kyclevel has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/kyclevels")
    public ResponseEntity<KyclevelDTO> createKyclevel(@RequestBody KyclevelDTO kyclevelDTO) throws URISyntaxException {
        log.debug("REST request to save Kyclevel : {}", kyclevelDTO);
        if (kyclevelDTO.getId() != null) {
            throw new BadRequestAlertException("A new kyclevel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        KyclevelDTO result = kyclevelService.save(kyclevelDTO);
        return ResponseEntity.created(new URI("/api/kyclevels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /kyclevels} : Updates an existing kyclevel.
     *
     * @param kyclevelDTO the kyclevelDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated kyclevelDTO,
     * or with status {@code 400 (Bad Request)} if the kyclevelDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the kyclevelDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/kyclevels")
    public ResponseEntity<KyclevelDTO> updateKyclevel(@RequestBody KyclevelDTO kyclevelDTO) throws URISyntaxException {
        log.debug("REST request to update Kyclevel : {}", kyclevelDTO);
        if (kyclevelDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        KyclevelDTO result = kyclevelService.save(kyclevelDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, kyclevelDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /kyclevels} : get all the kyclevels.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of kyclevels in body.
     */
    @GetMapping("/kyclevels")
    public List<KyclevelDTO> getAllKyclevels() {
        log.debug("REST request to get all Kyclevels");
        return kyclevelService.findAll();
    }

    /**
     * {@code GET  /kyclevels/:id} : get the "id" kyclevel.
     *
     * @param id the id of the kyclevelDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the kyclevelDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/kyclevels/{id}")
    public ResponseEntity<KyclevelDTO> getKyclevel(@PathVariable Long id) {
        log.debug("REST request to get Kyclevel : {}", id);
        Optional<KyclevelDTO> kyclevelDTO = kyclevelService.findOne(id);
        return ResponseUtil.wrapOrNotFound(kyclevelDTO);
    }

    /**
     * {@code DELETE  /kyclevels/:id} : delete the "id" kyclevel.
     *
     * @param id the id of the kyclevelDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/kyclevels/{id}")
    public ResponseEntity<Void> deleteKyclevel(@PathVariable Long id) {
        log.debug("REST request to delete Kyclevel : {}", id);
        kyclevelService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

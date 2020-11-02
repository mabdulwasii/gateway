package ng.com.systemspecs.apigateway.web.rest;

import ng.com.systemspecs.apigateway.service.SchemeCategoryService;
import ng.com.systemspecs.apigateway.web.rest.errors.BadRequestAlertException;
import ng.com.systemspecs.apigateway.service.dto.SchemeCategoryDTO;

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
 * REST controller for managing {@link ng.com.systemspecs.apigateway.domain.SchemeCategory}.
 */
@RestController
@RequestMapping("/api")
public class SchemeCategoryResource {

    private final Logger log = LoggerFactory.getLogger(SchemeCategoryResource.class);

    private static final String ENTITY_NAME = "schemeCategory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SchemeCategoryService schemeCategoryService;

    public SchemeCategoryResource(SchemeCategoryService schemeCategoryService) {
        this.schemeCategoryService = schemeCategoryService;
    }

    /**
     * {@code POST  /scheme-categories} : Create a new schemeCategory.
     *
     * @param schemeCategoryDTO the schemeCategoryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new schemeCategoryDTO, or with status {@code 400 (Bad Request)} if the schemeCategory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/scheme-categories")
    public ResponseEntity<SchemeCategoryDTO> createSchemeCategory(@RequestBody SchemeCategoryDTO schemeCategoryDTO) throws URISyntaxException {
        log.debug("REST request to save SchemeCategory : {}", schemeCategoryDTO);
        if (schemeCategoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new schemeCategory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SchemeCategoryDTO result = schemeCategoryService.save(schemeCategoryDTO);
        return ResponseEntity.created(new URI("/api/scheme-categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /scheme-categories} : Updates an existing schemeCategory.
     *
     * @param schemeCategoryDTO the schemeCategoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated schemeCategoryDTO,
     * or with status {@code 400 (Bad Request)} if the schemeCategoryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the schemeCategoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/scheme-categories")
    public ResponseEntity<SchemeCategoryDTO> updateSchemeCategory(@RequestBody SchemeCategoryDTO schemeCategoryDTO) throws URISyntaxException {
        log.debug("REST request to update SchemeCategory : {}", schemeCategoryDTO);
        if (schemeCategoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SchemeCategoryDTO result = schemeCategoryService.save(schemeCategoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, schemeCategoryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /scheme-categories} : get all the schemeCategories.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of schemeCategories in body.
     */
    @GetMapping("/scheme-categories")
    public List<SchemeCategoryDTO> getAllSchemeCategories() {
        log.debug("REST request to get all SchemeCategories");
        return schemeCategoryService.findAll();
    }

    /**
     * {@code GET  /scheme-categories/:id} : get the "id" schemeCategory.
     *
     * @param id the id of the schemeCategoryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the schemeCategoryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/scheme-categories/{id}")
    public ResponseEntity<SchemeCategoryDTO> getSchemeCategory(@PathVariable Long id) {
        log.debug("REST request to get SchemeCategory : {}", id);
        Optional<SchemeCategoryDTO> schemeCategoryDTO = schemeCategoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(schemeCategoryDTO);
    }

    /**
     * {@code DELETE  /scheme-categories/:id} : delete the "id" schemeCategory.
     *
     * @param id the id of the schemeCategoryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/scheme-categories/{id}")
    public ResponseEntity<Void> deleteSchemeCategory(@PathVariable Long id) {
        log.debug("REST request to delete SchemeCategory : {}", id);
        schemeCategoryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

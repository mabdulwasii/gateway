package ng.com.systemspecs.apigateway.web.rest;

import ng.com.systemspecs.apigateway.service.BillerCategoryService;
import ng.com.systemspecs.apigateway.web.rest.errors.BadRequestAlertException;
import ng.com.systemspecs.apigateway.service.dto.BillerCategoryDTO;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link ng.com.systemspecs.apigateway.domain.BillerCategory}.
 */
@RestController
@RequestMapping("/api")
public class BillerCategoryResource {

    private final Logger log = LoggerFactory.getLogger(BillerCategoryResource.class);

    private static final String ENTITY_NAME = "billerCategory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BillerCategoryService billerCategoryService;

    public BillerCategoryResource(BillerCategoryService billerCategoryService) {
        this.billerCategoryService = billerCategoryService;
    }

    /**
     * {@code POST  /biller-categories} : Create a new billerCategory.
     *
     * @param billerCategoryDTO the billerCategoryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new billerCategoryDTO, or with status {@code 400 (Bad Request)} if the billerCategory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/biller-categories")
    public ResponseEntity<BillerCategoryDTO> createBillerCategory(@RequestBody BillerCategoryDTO billerCategoryDTO) throws URISyntaxException {
        log.debug("REST request to save BillerCategory : {}", billerCategoryDTO);
        if (billerCategoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new billerCategory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BillerCategoryDTO result = billerCategoryService.save(billerCategoryDTO);
        return ResponseEntity.created(new URI("/api/biller-categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /biller-categories} : Updates an existing billerCategory.
     *
     * @param billerCategoryDTO the billerCategoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated billerCategoryDTO,
     * or with status {@code 400 (Bad Request)} if the billerCategoryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the billerCategoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/biller-categories")
    public ResponseEntity<BillerCategoryDTO> updateBillerCategory(@RequestBody BillerCategoryDTO billerCategoryDTO) throws URISyntaxException {
        log.debug("REST request to update BillerCategory : {}", billerCategoryDTO);
        if (billerCategoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BillerCategoryDTO result = billerCategoryService.save(billerCategoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, billerCategoryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /biller-categories} : get all the billerCategories.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of billerCategories in body.
     */
    @GetMapping("/biller-categories")
    public ResponseEntity<List<BillerCategoryDTO>> getAllBillerCategories(Pageable pageable) {
        log.debug("REST request to get a page of BillerCategories");
        Page<BillerCategoryDTO> page = billerCategoryService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /biller-categories/:id} : get the "id" billerCategory.
     *
     * @param id the id of the billerCategoryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the billerCategoryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/biller-categories/{id}")
    public ResponseEntity<BillerCategoryDTO> getBillerCategory(@PathVariable Long id) {
        log.debug("REST request to get BillerCategory : {}", id);
        Optional<BillerCategoryDTO> billerCategoryDTO = billerCategoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(billerCategoryDTO);
    }

    /**
     * {@code DELETE  /biller-categories/:id} : delete the "id" billerCategory.
     *
     * @param id the id of the billerCategoryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/biller-categories/{id}")
    public ResponseEntity<Void> deleteBillerCategory(@PathVariable Long id) {
        log.debug("REST request to delete BillerCategory : {}", id);
        billerCategoryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

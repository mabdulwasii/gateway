package ng.com.systemspecs.apigateway.web.rest;

import ng.com.systemspecs.apigateway.service.BillerService;
import ng.com.systemspecs.apigateway.web.rest.errors.BadRequestAlertException;
import ng.com.systemspecs.apigateway.service.dto.BillerDTO;

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
 * REST controller for managing {@link ng.com.systemspecs.apigateway.domain.Biller}.
 */
@RestController
@RequestMapping("/api")
public class BillerResource {

    private final Logger log = LoggerFactory.getLogger(BillerResource.class);

    private static final String ENTITY_NAME = "biller";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BillerService billerService;

    public BillerResource(BillerService billerService) {
        this.billerService = billerService;
    }

    /**
     * {@code POST  /billers} : Create a new biller.
     *
     * @param billerDTO the billerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new billerDTO, or with status {@code 400 (Bad Request)} if the biller has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/billers")
    public ResponseEntity<BillerDTO> createBiller(@RequestBody BillerDTO billerDTO) throws URISyntaxException {
        log.debug("REST request to save Biller : {}", billerDTO);
        if (billerDTO.getId() != null) {
            throw new BadRequestAlertException("A new biller cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BillerDTO result = billerService.save(billerDTO);
        return ResponseEntity.created(new URI("/api/billers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /billers} : Updates an existing biller.
     *
     * @param billerDTO the billerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated billerDTO,
     * or with status {@code 400 (Bad Request)} if the billerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the billerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/billers")
    public ResponseEntity<BillerDTO> updateBiller(@RequestBody BillerDTO billerDTO) throws URISyntaxException {
        log.debug("REST request to update Biller : {}", billerDTO);
        if (billerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BillerDTO result = billerService.save(billerDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, billerDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /billers} : get all the billers.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of billers in body.
     */
    @GetMapping("/billers")
    public ResponseEntity<List<BillerDTO>> getAllBillers(Pageable pageable) {
        log.debug("REST request to get a page of Billers");
        Page<BillerDTO> page = billerService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /billers/:id} : get the "id" biller.
     *
     * @param id the id of the billerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the billerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/billers/{id}")
    public ResponseEntity<BillerDTO> getBiller(@PathVariable Long id) {
        log.debug("REST request to get Biller : {}", id);
        Optional<BillerDTO> billerDTO = billerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(billerDTO);
    }

    /**
     * {@code DELETE  /billers/:id} : delete the "id" biller.
     *
     * @param id the id of the billerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/billers/{id}")
    public ResponseEntity<Void> deleteBiller(@PathVariable Long id) {
        log.debug("REST request to delete Biller : {}", id);
        billerService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

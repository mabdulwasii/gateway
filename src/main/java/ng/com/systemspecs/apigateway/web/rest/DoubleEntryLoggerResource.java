package ng.com.systemspecs.apigateway.web.rest;

import ng.com.systemspecs.apigateway.service.DoubleEntryLoggerService;
import ng.com.systemspecs.apigateway.web.rest.errors.BadRequestAlertException;
import ng.com.systemspecs.apigateway.service.dto.DoubleEntryLoggerDTO;

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
 * REST controller for managing {@link ng.com.systemspecs.apigateway.domain.DoubleEntryLogger}.
 */
@RestController
@RequestMapping("/api")
public class DoubleEntryLoggerResource {

    private final Logger log = LoggerFactory.getLogger(DoubleEntryLoggerResource.class);

    private static final String ENTITY_NAME = "doubleEntryLogger";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DoubleEntryLoggerService doubleEntryLoggerService;

    public DoubleEntryLoggerResource(DoubleEntryLoggerService doubleEntryLoggerService) {
        this.doubleEntryLoggerService = doubleEntryLoggerService;
    }

    /**
     * {@code POST  /double-entry-loggers} : Create a new doubleEntryLogger.
     *
     * @param doubleEntryLoggerDTO the doubleEntryLoggerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new doubleEntryLoggerDTO, or with status {@code 400 (Bad Request)} if the doubleEntryLogger has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/double-entry-loggers")
    public ResponseEntity<DoubleEntryLoggerDTO> createDoubleEntryLogger(@RequestBody DoubleEntryLoggerDTO doubleEntryLoggerDTO) throws URISyntaxException {
        log.debug("REST request to save DoubleEntryLogger : {}", doubleEntryLoggerDTO);
        if (doubleEntryLoggerDTO.getId() != null) {
            throw new BadRequestAlertException("A new doubleEntryLogger cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DoubleEntryLoggerDTO result = doubleEntryLoggerService.save(doubleEntryLoggerDTO);
        return ResponseEntity.created(new URI("/api/double-entry-loggers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /double-entry-loggers} : Updates an existing doubleEntryLogger.
     *
     * @param doubleEntryLoggerDTO the doubleEntryLoggerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated doubleEntryLoggerDTO,
     * or with status {@code 400 (Bad Request)} if the doubleEntryLoggerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the doubleEntryLoggerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/double-entry-loggers")
    public ResponseEntity<DoubleEntryLoggerDTO> updateDoubleEntryLogger(@RequestBody DoubleEntryLoggerDTO doubleEntryLoggerDTO) throws URISyntaxException {
        log.debug("REST request to update DoubleEntryLogger : {}", doubleEntryLoggerDTO);
        if (doubleEntryLoggerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DoubleEntryLoggerDTO result = doubleEntryLoggerService.save(doubleEntryLoggerDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, doubleEntryLoggerDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /double-entry-loggers} : get all the doubleEntryLoggers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of doubleEntryLoggers in body.
     */
    @GetMapping("/double-entry-loggers")
    public List<DoubleEntryLoggerDTO> getAllDoubleEntryLoggers() {
        log.debug("REST request to get all DoubleEntryLoggers");
        return doubleEntryLoggerService.findAll();
    }

    /**
     * {@code GET  /double-entry-loggers/:id} : get the "id" doubleEntryLogger.
     *
     * @param id the id of the doubleEntryLoggerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the doubleEntryLoggerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/double-entry-loggers/{id}")
    public ResponseEntity<DoubleEntryLoggerDTO> getDoubleEntryLogger(@PathVariable Long id) {
        log.debug("REST request to get DoubleEntryLogger : {}", id);
        Optional<DoubleEntryLoggerDTO> doubleEntryLoggerDTO = doubleEntryLoggerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(doubleEntryLoggerDTO);
    }

    /**
     * {@code DELETE  /double-entry-loggers/:id} : delete the "id" doubleEntryLogger.
     *
     * @param id the id of the doubleEntryLoggerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/double-entry-loggers/{id}")
    public ResponseEntity<Void> deleteDoubleEntryLogger(@PathVariable Long id) {
        log.debug("REST request to delete DoubleEntryLogger : {}", id);
        doubleEntryLoggerService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

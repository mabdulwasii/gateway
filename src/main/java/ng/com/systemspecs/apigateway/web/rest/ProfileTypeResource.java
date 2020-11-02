package ng.com.systemspecs.apigateway.web.rest;

import ng.com.systemspecs.apigateway.service.ProfileTypeService;
import ng.com.systemspecs.apigateway.web.rest.errors.BadRequestAlertException;
import ng.com.systemspecs.apigateway.service.dto.ProfileTypeDTO;

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
 * REST controller for managing {@link ng.com.systemspecs.apigateway.domain.ProfileType}.
 */
@RestController
@RequestMapping("/api")
public class ProfileTypeResource {

    private final Logger log = LoggerFactory.getLogger(ProfileTypeResource.class);

    private static final String ENTITY_NAME = "profileType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProfileTypeService profileTypeService;

    public ProfileTypeResource(ProfileTypeService profileTypeService) {
        this.profileTypeService = profileTypeService;
    }

    /**
     * {@code POST  /profile-types} : Create a new profileType.
     *
     * @param profileTypeDTO the profileTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new profileTypeDTO, or with status {@code 400 (Bad Request)} if the profileType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/profile-types")
    public ResponseEntity<ProfileTypeDTO> createProfileType(@RequestBody ProfileTypeDTO profileTypeDTO) throws URISyntaxException {
        log.debug("REST request to save ProfileType : {}", profileTypeDTO);
        if (profileTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new profileType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProfileTypeDTO result = profileTypeService.save(profileTypeDTO);
        return ResponseEntity.created(new URI("/api/profile-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /profile-types} : Updates an existing profileType.
     *
     * @param profileTypeDTO the profileTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated profileTypeDTO,
     * or with status {@code 400 (Bad Request)} if the profileTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the profileTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/profile-types")
    public ResponseEntity<ProfileTypeDTO> updateProfileType(@RequestBody ProfileTypeDTO profileTypeDTO) throws URISyntaxException {
        log.debug("REST request to update ProfileType : {}", profileTypeDTO);
        if (profileTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProfileTypeDTO result = profileTypeService.save(profileTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, profileTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /profile-types} : get all the profileTypes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of profileTypes in body.
     */
    @GetMapping("/profile-types")
    public List<ProfileTypeDTO> getAllProfileTypes() {
        log.debug("REST request to get all ProfileTypes");
        return profileTypeService.findAll();
    }

    /**
     * {@code GET  /profile-types/:id} : get the "id" profileType.
     *
     * @param id the id of the profileTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the profileTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/profile-types/{id}")
    public ResponseEntity<ProfileTypeDTO> getProfileType(@PathVariable Long id) {
        log.debug("REST request to get ProfileType : {}", id);
        Optional<ProfileTypeDTO> profileTypeDTO = profileTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(profileTypeDTO);
    }

    /**
     * {@code DELETE  /profile-types/:id} : delete the "id" profileType.
     *
     * @param id the id of the profileTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/profile-types/{id}")
    public ResponseEntity<Void> deleteProfileType(@PathVariable Long id) {
        log.debug("REST request to delete ProfileType : {}", id);
        profileTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

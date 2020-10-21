package ng.com.systemspecs.apigateway.service;

import ng.com.systemspecs.apigateway.service.dto.SchemeCategoryDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ng.com.systemspecs.apigateway.domain.SchemeCategory}.
 */
public interface SchemeCategoryService {

    /**
     * Save a schemeCategory.
     *
     * @param schemeCategoryDTO the entity to save.
     * @return the persisted entity.
     */
    SchemeCategoryDTO save(SchemeCategoryDTO schemeCategoryDTO);

    /**
     * Get all the schemeCategories.
     *
     * @return the list of entities.
     */
    List<SchemeCategoryDTO> findAll();


    /**
     * Get the "id" schemeCategory.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SchemeCategoryDTO> findOne(Long id);

    /**
     * Delete the "id" schemeCategory.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

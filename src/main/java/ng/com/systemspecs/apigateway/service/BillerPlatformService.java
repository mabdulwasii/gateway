package ng.com.systemspecs.apigateway.service;

import ng.com.systemspecs.apigateway.service.dto.BillerPlatformDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ng.com.systemspecs.apigateway.domain.BillerPlatform}.
 */
public interface BillerPlatformService {

    /**
     * Save a billerPlatform.
     *
     * @param billerPlatformDTO the entity to save.
     * @return the persisted entity.
     */
    BillerPlatformDTO save(BillerPlatformDTO billerPlatformDTO);

    /**
     * Get all the billerPlatforms.
     *
     * @return the list of entities.
     */
    List<BillerPlatformDTO> findAll();


    /**
     * Get the "id" billerPlatform.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BillerPlatformDTO> findOne(Long id);

    /**
     * Delete the "id" billerPlatform.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

package ng.com.systemspecs.apigateway.service;

import ng.com.systemspecs.apigateway.domain.Kyclevel;
import ng.com.systemspecs.apigateway.service.dto.KyclevelDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ng.com.systemspecs.apigateway.domain.Kyclevel}.
 */
public interface KyclevelService {

    /**
     * Save a kyclevel.
     *
     * @param kyclevelDTO the entity to save.
     * @return the persisted entity.
     */
    KyclevelDTO save(KyclevelDTO kyclevelDTO);

    /**
     * Get all the kyclevels.
     *
     * @return the list of entities.
     */
    List<KyclevelDTO> findAll();


    /**
     * Get the "id" kyclevel.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<KyclevelDTO> findOne(Long id);
    Kyclevel findByKycLevel(Integer kycLevel);

    /**
     * Delete the "id" kyclevel.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

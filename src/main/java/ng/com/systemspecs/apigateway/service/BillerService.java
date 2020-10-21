package ng.com.systemspecs.apigateway.service;

import ng.com.systemspecs.apigateway.service.dto.BillerDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ng.com.systemspecs.apigateway.domain.Biller}.
 */
public interface BillerService {

    /**
     * Save a biller.
     *
     * @param billerDTO the entity to save.
     * @return the persisted entity.
     */
    BillerDTO save(BillerDTO billerDTO);

    /**
     * Get all the billers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BillerDTO> findAll(Pageable pageable);


    /**
     * Get the "id" biller.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BillerDTO> findOne(Long id);

    /**
     * Delete the "id" biller.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

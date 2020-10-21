package ng.com.systemspecs.apigateway.service;

import ng.com.systemspecs.apigateway.service.dto.CustomersubscriptionDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ng.com.systemspecs.apigateway.domain.Customersubscription}.
 */
public interface CustomersubscriptionService {

    /**
     * Save a customersubscription.
     *
     * @param customersubscriptionDTO the entity to save.
     * @return the persisted entity.
     */
    CustomersubscriptionDTO save(CustomersubscriptionDTO customersubscriptionDTO);

    /**
     * Get all the customersubscriptions.
     *
     * @return the list of entities.
     */
    List<CustomersubscriptionDTO> findAll();


    /**
     * Get the "id" customersubscription.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CustomersubscriptionDTO> findOne(Long id);

    /**
     * Delete the "id" customersubscription.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

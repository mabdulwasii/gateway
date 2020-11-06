package ng.com.systemspecs.apigateway.service;

import ng.com.systemspecs.apigateway.domain.ProfileType;
import ng.com.systemspecs.apigateway.service.dto.ProfileTypeDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ng.com.systemspecs.apigateway.domain.ProfileType}.
 */
public interface ProfileTypeService {

    /**
     * Save a profileType.
     *
     * @param profileTypeDTO the entity to save.
     * @return the persisted entity.
     */
    ProfileTypeDTO save(ProfileTypeDTO profileTypeDTO);

    /**
     * Get all the profileTypes.
     *
     * @return the list of entities.
     */
    List<ProfileTypeDTO> findAll();


    /**
     * Get the "id" profileType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProfileTypeDTO> findOne(Long id);
    ProfileType findByProfiletype(String profiletype);

    /**
     * Delete the "id" profileType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

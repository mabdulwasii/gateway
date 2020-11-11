package ng.com.systemspecs.apigateway.service;

import ng.com.systemspecs.apigateway.domain.Profile;
import ng.com.systemspecs.apigateway.domain.Profile;
import ng.com.systemspecs.apigateway.domain.User;
import ng.com.systemspecs.apigateway.service.dto.ProfileDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ng.com.systemspecs.apigateway.domain.Profile}.
 */
public interface ProfileService {

    /**
     * Save a profile.
     *
     * @param profileDTO the entity to save.
     * @return the persisted entity.
     */
    ProfileDTO save(ProfileDTO profileDTO);
    Profile save(Profile profile);

    /**
     * Get all the profiles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProfileDTO> findAll(Pageable pageable);


    /**
     * Get the "id" profile.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProfileDTO> findOne(Long id);

    /**
     * Delete the "id" profile.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    Optional<ProfileDTO> findByUserIsCurrentUser();

    Optional<ProfileDTO> findOneByPhoneNumber(String phoneNumber);
    Profile findByPhoneNumber(String phoneNumber);
    Boolean canSpendOnAccount(String phoneNumber,Long accountNumber,Double amount);//for send Money "Your daily spend quota has been exceeded"
    Boolean canAccummulateOnAccount(String phoneNumber,Long accountNumber,Double amount); // for fending
}

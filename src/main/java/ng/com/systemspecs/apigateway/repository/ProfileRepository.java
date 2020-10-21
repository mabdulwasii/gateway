package ng.com.systemspecs.apigateway.repository;

import ng.com.systemspecs.apigateway.domain.Profile;
import ng.com.systemspecs.apigateway.domain.User;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Profile entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Query("select profile from Profile profile where profile.user.login = ?#{principal.username}")
    List<Profile> findByUserIsCurrentUser();
    Profile findOneByPhoneNumber(String phoneNumber);
}

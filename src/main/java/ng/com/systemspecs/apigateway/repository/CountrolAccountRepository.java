package ng.com.systemspecs.apigateway.repository;

import ng.com.systemspecs.apigateway.domain.CountrolAccount;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CountrolAccount entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CountrolAccountRepository extends JpaRepository<CountrolAccount, Long> {
}

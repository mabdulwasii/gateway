package ng.com.systemspecs.apigateway.repository;

import ng.com.systemspecs.apigateway.domain.Kyclevel;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Kyclevel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KyclevelRepository extends JpaRepository<Kyclevel, Long> {
	Kyclevel findByKycLevel(Integer kycLevel);
}

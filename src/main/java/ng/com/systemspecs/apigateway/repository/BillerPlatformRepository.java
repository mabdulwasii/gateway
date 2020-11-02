package ng.com.systemspecs.apigateway.repository;

import ng.com.systemspecs.apigateway.domain.BillerPlatform;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BillerPlatform entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BillerPlatformRepository extends JpaRepository<BillerPlatform, Long> {
}

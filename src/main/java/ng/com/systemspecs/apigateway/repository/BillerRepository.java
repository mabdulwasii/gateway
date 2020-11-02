package ng.com.systemspecs.apigateway.repository;

import ng.com.systemspecs.apigateway.domain.Biller;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Biller entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BillerRepository extends JpaRepository<Biller, Long> {
}

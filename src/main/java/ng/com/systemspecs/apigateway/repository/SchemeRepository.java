package ng.com.systemspecs.apigateway.repository;

import ng.com.systemspecs.apigateway.domain.Scheme;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Scheme entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SchemeRepository extends JpaRepository<Scheme, Long> {
}

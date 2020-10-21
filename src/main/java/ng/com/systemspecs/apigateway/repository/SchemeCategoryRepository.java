package ng.com.systemspecs.apigateway.repository;

import ng.com.systemspecs.apigateway.domain.SchemeCategory;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SchemeCategory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SchemeCategoryRepository extends JpaRepository<SchemeCategory, Long> {
}

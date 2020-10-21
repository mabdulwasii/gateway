package ng.com.systemspecs.apigateway.repository;

import ng.com.systemspecs.apigateway.domain.WalletAccountType;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the WalletAccountType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WalletAccountTypeRepository extends JpaRepository<WalletAccountType, Long> {
}

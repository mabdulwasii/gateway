package ng.com.systemspecs.apigateway.repository;

import ng.com.systemspecs.apigateway.domain.PaymentTransaction;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PaymentTransaction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaymentTransactionRepository extends JpaRepository<PaymentTransaction, Long> {
}

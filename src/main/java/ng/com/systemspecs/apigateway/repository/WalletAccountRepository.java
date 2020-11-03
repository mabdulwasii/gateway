package ng.com.systemspecs.apigateway.repository;

import ng.com.systemspecs.apigateway.domain.Profile;
import ng.com.systemspecs.apigateway.domain.WalletAccount;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the WalletAccount entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WalletAccountRepository extends JpaRepository<WalletAccount, Long> {
	
	@Query("select walletAccount from WalletAccount walletAccount where walletAccount.accountOwner.user.login = ?#{principal.username}")
    List<WalletAccount> findByUserIsCurrentUser();
	WalletAccount findOneByAccountNumber(Long accountNumber);
}

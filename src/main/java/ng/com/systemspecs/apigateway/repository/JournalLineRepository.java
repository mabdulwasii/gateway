package ng.com.systemspecs.apigateway.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ng.com.systemspecs.apigateway.domain.JournalLine;

public interface JournalLineRepository extends JpaRepository<JournalLine, Long>{
	
	List<JournalLine> findByWalletAccount_AccountNumber(Long accountNumber);
	
}

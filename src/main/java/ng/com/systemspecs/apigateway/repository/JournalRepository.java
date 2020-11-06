package ng.com.systemspecs.apigateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ng.com.systemspecs.apigateway.domain.Journal;

public interface JournalRepository extends JpaRepository<Journal, Long> {

}

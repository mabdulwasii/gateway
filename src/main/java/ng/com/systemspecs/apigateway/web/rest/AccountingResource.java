package ng.com.systemspecs.apigateway.web.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ng.com.systemspecs.apigateway.domain.JournalLine;
import ng.com.systemspecs.apigateway.service.JournalLineService;


/**
 * REST controller for managing {@link Accounting Functions of the Application}.
 */
@RestController
@RequestMapping("/api")
public class AccountingResource {
	
	private final Logger log = LoggerFactory.getLogger(AccountingResource.class);
	private final JournalLineService journalLineService;
	public AccountingResource(JournalLineService journalLineService) {
		this.journalLineService=journalLineService;
		
	}
	
	
	@GetMapping("/credit/{accountNumber}")
	public ResponseEntity<List<JournalLine>> getFundTransaction(@PathVariable String accountNumber) {
		log.debug("REST request to get PaymentTransaction : {}", accountNumber);
		List<JournalLine> creditLines = journalLineService.
				findByWalletAccount_AccountNumber(Long.parseLong(accountNumber));
		return new ResponseEntity<>(creditLines, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/debit/{accountNumber}")
	public ResponseEntity<List<JournalLine>> getSendMoneyTransaction(@PathVariable String accountNumber) {
		log.debug("REST request to get PaymentTransaction : {}", accountNumber);
		List<JournalLine> creditLines = journalLineService.
				findByWalletAccount_AccountNumber(Long.parseLong(accountNumber));
		return new ResponseEntity<>(creditLines, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/debit_credit")
	public ResponseEntity<List<JournalLine>> getAllMyTransaction() {

		log.debug("REST request to get PaymentTransaction : {}", "");
		List<JournalLine> creditLines = journalLineService.findAll();
		return new ResponseEntity<>(creditLines, new HttpHeaders(), HttpStatus.OK);
	}

}

package ng.com.systemspecs.apigateway.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ng.com.systemspecs.apigateway.domain.Journal;
import ng.com.systemspecs.apigateway.domain.JournalLine;
import ng.com.systemspecs.apigateway.domain.WalletAccount;
import ng.com.systemspecs.apigateway.repository.JournalLineRepository;
import ng.com.systemspecs.apigateway.service.JournalLineService;

@Service
@Transactional
public class JournalLineServiceImpl implements JournalLineService{

	private final JournalLineRepository journalLineRepository;
	public JournalLineServiceImpl(JournalLineRepository journalLineRepository) {
		this.journalLineRepository=journalLineRepository;
	}
	@Override
	public JournalLine save(JournalLine journalLine, Journal journal, WalletAccount walletAccount) {
		journalLine.setJounal(journal);
		journalLine.setWalletAccount(walletAccount);
		journalLineRepository.save(journalLine);
		return journalLine;
	}

	@Override
	public List<JournalLine> findAll() {
		// TODO Auto-generated method stub
		return journalLineRepository.findAll();
	}

	@Override
	public Optional<JournalLine> findOne(Long id) {
		// TODO Auto-generated method stub
		return journalLineRepository.findById(id);
	}

	@Override
	public List<JournalLine> findByWalletAccount_AccountNumber(Long accountNumber) {
		// TODO Auto-generated method stub
		return journalLineRepository.findByWalletAccount_AccountNumber(accountNumber);
	}

	@Override
	public void delete(Long id) {
		journalLineRepository.deleteById(id);
		
	}

}

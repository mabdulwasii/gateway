package ng.com.systemspecs.apigateway.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ng.com.systemspecs.apigateway.domain.Journal;
import ng.com.systemspecs.apigateway.repository.JournalRepository;
import ng.com.systemspecs.apigateway.service.JournalService;

@Service
@Transactional
public class JournalServiceImpl implements JournalService {
	private final Logger log = LoggerFactory.getLogger(JournalServiceImpl.class);


	private final JournalRepository journalRepository;
	public JournalServiceImpl(JournalRepository journalRepository) {
		this.journalRepository=journalRepository;
	}
	@Override
	public Journal save(Journal journal) {
		// TODO Auto-generated method stub
		return journalRepository.save(journal);
	}

	@Override
	public List<Journal> findAll() {
		// TODO Auto-generated method stub
		return journalRepository.findAll();
	}

	@Override
	public Optional<Journal> findOne(Long id) {
		// TODO Auto-generated method stub
		return journalRepository.findById(id);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}

package ng.com.systemspecs.apigateway.service.impl;

import ng.com.systemspecs.apigateway.service.WalletAccountService;
import ng.com.systemspecs.apigateway.domain.WalletAccount;
import ng.com.systemspecs.apigateway.repository.WalletAccountRepository;
import ng.com.systemspecs.apigateway.service.dto.FundDTO;
import ng.com.systemspecs.apigateway.service.dto.ResponseDTO;
import ng.com.systemspecs.apigateway.service.dto.WalletAccountDTO;
import ng.com.systemspecs.apigateway.service.kafka.producer.TransProducer;
import ng.com.systemspecs.apigateway.service.mapper.WalletAccountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link WalletAccount}.
 */
@Service
@Transactional
public class WalletAccountServiceImpl implements WalletAccountService {

	private final Logger log = LoggerFactory.getLogger(WalletAccountServiceImpl.class);

	private final WalletAccountRepository walletAccountRepository;

	private final WalletAccountMapper walletAccountMapper;
	private final TransProducer producer;

	public WalletAccountServiceImpl(WalletAccountRepository walletAccountRepository,
			WalletAccountMapper walletAccountMapper,TransProducer producer) {
		this.walletAccountRepository = walletAccountRepository;
		this.walletAccountMapper = walletAccountMapper;
		this.producer = producer;
		
	}

	@Override
	public WalletAccountDTO save(WalletAccountDTO walletAccountDTO) {
		log.debug("Request to save WalletAccount : {}", walletAccountDTO);
		WalletAccount walletAccount = walletAccountMapper.toEntity(walletAccountDTO);
		walletAccount = walletAccountRepository.save(walletAccount);
		return walletAccountMapper.toDto(walletAccount);
	}

	@Override
	@Transactional(readOnly = true)
	public List<WalletAccountDTO> findAll() {
		log.debug("Request to get all WalletAccounts");
		return walletAccountRepository.findAll().stream().map(walletAccountMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<WalletAccountDTO> findOne(Long id) {
		log.debug("Request to get WalletAccount : {}", id);
		return walletAccountRepository.findById(id).map(walletAccountMapper::toDto);
	}

	@Override
	public void delete(Long id) {
		log.debug("Request to delete WalletAccount : {}", id);
		walletAccountRepository.deleteById(id);
	}

	@Override
	public List<WalletAccountDTO> findByUserIsCurrentUser() {
		log.debug("Request to get all WalletAccounts of login user");
		return walletAccountRepository.findByUserIsCurrentUser().stream().map(walletAccountMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}

	@Override
	public ResponseDTO fund(FundDTO fundDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		WalletAccount account = walletAccountRepository.findOneByAccountNumber(fundDTO.getAccountNumber());
		/*
		 * if (account.getCurrentBalance() < fundDTO.getAmount()) {
		 * responseDTO.setCode("failed"); responseDTO.setMessage("Insufficient Fund");
		 * return responseDTO; }
		 */
		Random random = new Random();

		String generatedString = random.ints(97, 122 + 1).limit(15)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		responseDTO.setCode("success");
		responseDTO.setMessage("Transaction Successful");
		responseDTO.setTrasactionReference(generatedString);
		account.setCurrentBalance(account.getCurrentBalance() + fundDTO.getAmount());
		walletAccountRepository.save(account);

		producer.send(responseDTO);
		return responseDTO;
	}

	@Override
	public ResponseDTO sendMoney(FundDTO sendMoneyDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		WalletAccount account = walletAccountRepository.findOneByAccountNumber(sendMoneyDTO.getAccountNumber());

		if (account.getCurrentBalance() < sendMoneyDTO.getAmount()) {
			responseDTO.setCode("failed");
			responseDTO.setMessage("Insufficient Fund");
			return responseDTO;
		}

		Random random = new Random();

		String generatedString = random.ints(97, 122 + 1).limit(15)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		responseDTO.setCode("success");
		responseDTO.setMessage("Transaction Successful");
		responseDTO.setTrasactionReference(generatedString);
		account.setCurrentBalance(account.getCurrentBalance() - sendMoneyDTO.getAmount());
		walletAccountRepository.save(account);
		return responseDTO;
	}
}

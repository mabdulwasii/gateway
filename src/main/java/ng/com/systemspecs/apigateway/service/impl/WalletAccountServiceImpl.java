package ng.com.systemspecs.apigateway.service.impl;

import ng.com.systemspecs.apigateway.service.WalletAccountService;
import ng.com.systemspecs.apigateway.domain.WalletAccount;
import ng.com.systemspecs.apigateway.repository.WalletAccountRepository;
import ng.com.systemspecs.apigateway.service.dto.FundDTO;
import ng.com.systemspecs.apigateway.service.dto.PaymentResponseDTO;
import ng.com.systemspecs.apigateway.service.dto.PaymentTransactionDTO;
import ng.com.systemspecs.apigateway.service.dto.PostResponseDTO;
import ng.com.systemspecs.apigateway.service.dto.PostResponseDataDTO;
import ng.com.systemspecs.apigateway.service.dto.ResponseDTO;
import ng.com.systemspecs.apigateway.service.dto.WalletAccountDTO;
import ng.com.systemspecs.apigateway.service.kafka.producer.TransProducer;
import ng.com.systemspecs.apigateway.service.mapper.WalletAccountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
	public PaymentResponseDTO fund(FundDTO fundDTO) {
		PaymentResponseDTO responseDTO = new PaymentResponseDTO();
		PaymentTransactionDTO paymentTransactionDTO = new PaymentTransactionDTO();
		WalletAccount account = walletAccountRepository.findOneByAccountNumber(fundDTO.getAccountNumber());
		/*
		 * if (account.getCurrentBalance() < fundDTO.getAmount()) {
		 * responseDTO.setCode("failed"); responseDTO.setMessage("Insufficient Fund");
		 * return responseDTO; }
		 */
		Random random = new Random();

		String generatedString = random.ints(97, 122 + 1).limit(15)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		responseDTO.setMessage("Transaction Successful");
		responseDTO.setCode("00");
		
		account.setCurrentBalance(account.getCurrentBalance() + fundDTO.getAmount());		
		account = walletAccountRepository.save(account);
		paymentTransactionDTO.setAmount(BigDecimal.valueOf(fundDTO.getAmount()));
		paymentTransactionDTO.setChannel(fundDTO.getChannel());
		paymentTransactionDTO.setDestinationAccount(String.valueOf(fundDTO.getAccountNumber()));
		paymentTransactionDTO.setSourceAccount(fundDTO.getSourceAccountNumber());
		paymentTransactionDTO.setDestinationNarration("Funding the Wallet "+account.getAccountName()+
				"( "+account.getAccountNumber()+" )");
		paymentTransactionDTO.setPaymenttransID(System.currentTimeMillis());
		paymentTransactionDTO.setSourceAccountBankCode(fundDTO.getSourceBankCode());
		paymentTransactionDTO.setTransactionRef(generatedString);
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String phoneNumber = "";
		if(principal instanceof UserDetails) {
			phoneNumber = ((UserDetails)principal).getUsername();
		}else
			phoneNumber = principal.toString();
		
		paymentTransactionDTO.setTransactionOwnerPhoneNumber(phoneNumber);
		responseDTO.setPaymentTransactionDTO(paymentTransactionDTO);
		producer.send(responseDTO);
		return responseDTO;
	}

	@Override
	public PaymentResponseDTO sendMoney(FundDTO sendMoneyDTO) {
		PaymentResponseDTO responseDTO = new PaymentResponseDTO();
		PaymentTransactionDTO paymentTransactionDTO = new PaymentTransactionDTO();
		WalletAccount account = walletAccountRepository.findOneByAccountNumber(Long.parseLong(sendMoneyDTO.getSourceAccountNumber()));

		if (account.getCurrentBalance() < sendMoneyDTO.getAmount()) {
			responseDTO.setCode("failed");
			responseDTO.setMessage("Insufficient Fund");
			return responseDTO;
		}

		Random random = new Random();

		String generatedString = random.ints(97, 122 + 1).limit(15)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();


		account.setCurrentBalance(account.getCurrentBalance() - sendMoneyDTO.getAmount());
		account = walletAccountRepository.save(account);
		responseDTO.setMessage("Transaction Successful");
		responseDTO.setCode("00");
		
		account.setCurrentBalance(account.getCurrentBalance() + sendMoneyDTO.getAmount());		
		account = walletAccountRepository.save(account);
		paymentTransactionDTO.setAmount(BigDecimal.valueOf(sendMoneyDTO.getAmount()));
		paymentTransactionDTO.setChannel(sendMoneyDTO.getChannel());
		paymentTransactionDTO.setDestinationAccount(String.valueOf(sendMoneyDTO.getAccountNumber()));
		paymentTransactionDTO.setSourceAccount(sendMoneyDTO.getSourceAccountNumber());
		paymentTransactionDTO.setDestinationNarration("Send Money from the Wallet "+account.getAccountName()+
				"( "+account.getAccountNumber()+" )");
		paymentTransactionDTO.setPaymenttransID(System.currentTimeMillis());
		paymentTransactionDTO.setSourceAccountBankCode(sendMoneyDTO.getSourceBankCode());
		paymentTransactionDTO.setTransactionRef(generatedString);
		String phoneNumber = null;
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
			phoneNumber = ((UserDetails)principal).getUsername();
		}else
			phoneNumber = principal.toString();
		paymentTransactionDTO.setTransactionOwnerPhoneNumber(phoneNumber);
		responseDTO.setPaymentTransactionDTO(paymentTransactionDTO);
		
		producer.send(responseDTO);
		return responseDTO;
	}

	@Override
	public WalletAccount findOneByAccountNumber(Long accountNumber) {
		return walletAccountRepository.findOneByAccountNumber(accountNumber);
	}

	@Override
	public WalletAccount save(WalletAccount walletAccount) {
		return walletAccountRepository.save(walletAccount);
	}
}

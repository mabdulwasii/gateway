package ng.com.systemspecs.apigateway.service.impl;

import ng.com.systemspecs.apigateway.client.InlineStatusResponse;
import ng.com.systemspecs.apigateway.domain.PaymentTransaction;
import ng.com.systemspecs.apigateway.service.WalletAccountService;
import ng.com.systemspecs.apigateway.domain.WalletAccount;
import ng.com.systemspecs.apigateway.repository.WalletAccountRepository;
import ng.com.systemspecs.apigateway.service.dto.FundDTO;
import ng.com.systemspecs.apigateway.service.dto.PaymentResponseDTO;
import ng.com.systemspecs.apigateway.service.dto.PaymentTransactionDTO;
import ng.com.systemspecs.apigateway.service.dto.WalletAccountDTO;
import ng.com.systemspecs.apigateway.service.kafka.producer.TransProducer;
import ng.com.systemspecs.apigateway.service.mapper.WalletAccountMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link WalletAccount}.
 */
@Service
@Transactional
public class WalletAccountServiceImpl implements WalletAccountService {

	private final Logger log = LoggerFactory.getLogger(WalletAccountServiceImpl.class);

    private final String  secretKey =
        "23093b2bda801eece94fc6e8363c05fad90a4ba3e12045005141b0bab41704b3a148904529239afd1c9a3880d51b4018d13fd626b2cef77a6f858fe854834e54";

    private final String  inlinePmtStatusPublicKey  =
        "QzAwMDAyNzEyNTl8MTEwNjE4NjF8OWZjOWYwNmMyZDk3MDRhYWM3YThiOThlNTNjZTE3ZjYxOTY5NDdmZWE1YzU3NDc0ZjE2ZDZjNTg1YWYxNWY3NWM4ZjMzNzZhNjNhZWZlOWQwNmJhNTFkMjIxYTRiMjYzZDkzNGQ3NTUxNDIxYWNlOGY4ZWEyODY3ZjlhNGUwYTY=";

    private final String url = "https://remitademo.net/payment/v1/payment/query/{transId}";

    private final WalletAccountRepository walletAccountRepository;

	private final WalletAccountMapper walletAccountMapper;
	private final TransProducer producer;
    private PaymentResponseDTO responseDTO;

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
	    responseDTO = new PaymentResponseDTO();

        //if channel is #wallet to wallet
        if (fundDTO.getChannel().equalsIgnoreCase("wallettowallet")){
            // and if the currentWallet balance is >= amount
            WalletAccount sourceAccount = findOneByAccountNumber(Long.valueOf(fundDTO.getSourceAccountNumber()));
            Double currentBalance = sourceAccount.getCurrentBalance();
            if (currentBalance >= fundDTO.getAmount()){
                // send FundDTO to kafka consumer
                producer.send(fundDTO);
                buildPaymentResponseDTO(false, "Transaction Successful!");
            } else {
                buildPaymentResponseDTO(true, "Insufficient fund!");
            }

        }else if (fundDTO.getChannel().equalsIgnoreCase("banktowallet")){
            //get transRef
            String transRef = fundDTO.getTransRef();
            //Verify the transaction using transRef
            boolean isVerified = verifyTransaction(transRef);
            //if success, return PaymentSuccessDTO
            if (isVerified){
                // and send FundDTO to kafka consumer
                producer.send(fundDTO);

                //return response to the client
                buildPaymentResponseDTO(false, "Funding successful");
            }else {
                buildPaymentResponseDTO(true, "cannot verify transaction");
            }

        }


		return responseDTO;
	}

    private void buildPaymentResponseDTO(boolean hasError, String message)  {
        responseDTO.setError(hasError);
        if (hasError){
            responseDTO.setMessage(message);
            responseDTO.setCode("Failed");
        }else {
            responseDTO.setMessage(message);
            responseDTO.setCode("success");

        }
    }

    private boolean verifyTransaction(String transRef) {

        String hash = new DigestUtils("SHA-512").digestAsHex(transRef + secretKey);

        try{
            RestTemplate restTemplate = new RestTemplate();

            Map<String, String> params = new HashMap<String, String>();
            params.put("transId", transRef);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("publicKey", inlinePmtStatusPublicKey);
            headers.set("TXN_HASH", hash);

            HttpEntity<String> entity = new HttpEntity<String>(headers);

            ResponseEntity<InlineStatusResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, InlineStatusResponse.class, params);

            log.debug(response.getHeaders().toString());
            log.debug(response.toString());
            InlineStatusResponse body = response.getBody();
            if (response.getStatusCode().equals(HttpStatus.OK) && body.getResponseData().get(0).getMessage().equalsIgnoreCase("Approved")){
                return true;
            } else {
                return false;
            }

        }catch(Exception ex) {
            ex.printStackTrace();
        }

        return  false;
    }

    @Override
	public PaymentResponseDTO sendMoney(FundDTO sendMoneyDTO) {
		PaymentResponseDTO responseDTO = new PaymentResponseDTO();
		PaymentTransactionDTO paymentTransactionDTO = new PaymentTransactionDTO();
		WalletAccount account = walletAccountRepository.findOneByAccountNumber(Long.parseLong(sendMoneyDTO.getSourceAccountNumber()));

		if (account.getCurrentBalance() < sendMoneyDTO.getAmount()) {
			responseDTO.setError(true);//
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

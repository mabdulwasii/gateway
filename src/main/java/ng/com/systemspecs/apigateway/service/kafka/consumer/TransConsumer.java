package ng.com.systemspecs.apigateway.service.kafka.consumer;

import io.vavr.control.Either;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Optional;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.stereotype.Service;

import ng.com.systemspecs.apigateway.client.ExternalRESTClient;
import ng.com.systemspecs.apigateway.config.KafkaProperties;
import ng.com.systemspecs.apigateway.domain.PaymentTransaction;
import ng.com.systemspecs.apigateway.domain.Profile;
import ng.com.systemspecs.apigateway.service.PaymentTransactionService;
import ng.com.systemspecs.apigateway.service.dto.PaymentTransactionDTO;
import ng.com.systemspecs.apigateway.service.dto.ResponseDTO;
import ng.com.systemspecs.apigateway.service.kafka.GenericConsumer;
import ng.com.systemspecs.apigateway.service.kafka.deserializer.DeserializationError;

@Service
public class TransConsumer extends GenericConsumer<Object> {
	@Autowired
	ExternalRESTClient externalRESTClient;

	private PaymentTransactionService paymentTransactionService;
	private final Logger log = LoggerFactory.getLogger(TransConsumer.class);

	public TransConsumer(@Value("${kafka.consumer.trans.name}") final String topicName,
			final KafkaProperties kafkaProperties, PaymentTransactionService paymentTransactionService) {
		super(topicName, kafkaProperties.getConsumer().get("trans"), kafkaProperties.getPollingTimeout());
		this.paymentTransactionService = paymentTransactionService;
	}

	@Override
	protected void handleMessage(final ConsumerRecord<String, Either<DeserializationError, Object>> record) {
		final Either<DeserializationError, Object> value = record.value();  
		System.out.println(" TransConsumer The Message 1=========================================================="
				+ "=========================================================================================="
				+ "============================================================================== " + value);

		System.out.println(" TransConsumer The Message 2=========================================================="
				+ "=========================================================================================="
				+ "============================================================================== " + value.get());
		if (value.isLeft()) {
			log.error("Deserialization record failure: {}", value.getLeft());
		} else {
			System.out.println(" TransConsumer The Message 3=========================================================="
					+ "=========================================================================================="
					+ "============================================================================== " + value.get());
			// String result =
			// externalRESTClient.getTransactionConfirmation("ABCD123456789012345678901139");
			// System.out.println(" result===="+result);
			// Maybe you could delete the next log.info(...) to avoid disclosing personal
			// user information

			LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) value.get();
			LinkedHashMap<String, Object> dto = null;
			System.out.println(" map====================================================== " + map);
			dto = (LinkedHashMap<String, Object>) map.get("payment_transaction_dto");
			System.out.println(" dto====================================================== " + dto);

			PaymentTransaction paymentTransaction = new PaymentTransaction();
			paymentTransaction.setAmount(BigDecimal.valueOf(Double.parseDouble(String.valueOf(dto.get("amount")))));
			paymentTransaction.setChannel((String) dto.get("channel"));
			
			paymentTransaction.setCurrency("NGN");
			paymentTransaction.setDestinationNarration("WALLET SCHEME");
			paymentTransaction.setDestinationAccountBankCode("ABC");
			
			
			paymentTransaction.setDestinationAccount((String) dto.get("destination_account"));
			paymentTransaction.setDestinationAccountBankCode((String) dto.get("source_account_bank_code"));
			paymentTransaction.setDestinationNarration((String) dto.get("destination_narration"));
			paymentTransaction.setPaymenttransID(Long.parseLong(String.valueOf(dto.get("paymenttrans_id"))));
			paymentTransaction.setSourceAccount((String) dto.get("source_account"));
			paymentTransaction.setSourceAccountBankCode((String) dto.get("source_account_bank_code"));
			paymentTransaction.setSourceNarration((String) dto.get("destination_narration"));
			paymentTransaction.setSourceAccountName((String)dto.get("transaction_owner_phone_number"));
			paymentTransaction.setTransactionRef((String) dto.get("transaction_ref"));
			paymentTransaction.setTransactionType(null);
			paymentTransaction.setTransactionDate(LocalDate.now());

			paymentTransactionService.save(paymentTransaction);
			log.info("Handling record: {}", map);

		}

		// TODO: Here is where you can handle your messages
	}

	@Bean
	public void executeKafkaTransRunner() {
		new SimpleAsyncTaskExecutor().execute(this);
	}
}

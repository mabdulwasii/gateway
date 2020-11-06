package ng.com.systemspecs.apigateway.service.kafka.consumer;

import io.vavr.control.Either;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Optional;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
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
import ng.com.systemspecs.apigateway.service.accounting.AccountingService;
import ng.com.systemspecs.apigateway.service.dto.FundDTO;
import ng.com.systemspecs.apigateway.service.dto.PaymentTransactionDTO;
import ng.com.systemspecs.apigateway.service.dto.ResponseDTO;
import ng.com.systemspecs.apigateway.service.kafka.GenericConsumer;
import ng.com.systemspecs.apigateway.service.kafka.deserializer.DeserializationError;

@Service
public class TransConsumer extends GenericConsumer<Object> {
	@Autowired
	ExternalRESTClient externalRESTClient;

	private AccountingService accountingService;
	private final Logger log = LoggerFactory.getLogger(TransConsumer.class);

	public TransConsumer(@Value("${kafka.consumer.trans.name}") final String topicName,
			final KafkaProperties kafkaProperties, AccountingService accountingService) {
		super(topicName, kafkaProperties.getConsumer().get("trans"), kafkaProperties.getPollingTimeout());
		this.accountingService = accountingService;
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

			LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) value.get();
			LinkedHashMap<String, Object> dto = null;
			System.out.println(" map====================================================== " + map);
			FundDTO fundDTO = new FundDTO();

			fundDTO.setAccountNumber(Long.parseLong(String.valueOf(map.get("account_number"))));
			fundDTO.setAmount(Double.parseDouble(String.valueOf(map.get("amount"))));
			fundDTO.setChannel(String.valueOf(map.get("channel")));
			fundDTO.setDestBankCode(String.valueOf(map.get("dest_bank_code")));
			fundDTO.setSourceAccountNumber(String.valueOf(map.get("source_account_number")));
			fundDTO.setSourceBankCode(String.valueOf(map.get("source_bank_code")));

			accountingService.fundWallet(fundDTO);

			log.info("Handling record: {}", map);

		}

		// TODO: Here is where you can handle your messages
	}

	@Bean
	public void executeKafkaTransRunner() {
		new SimpleAsyncTaskExecutor().execute(this);
	}
}

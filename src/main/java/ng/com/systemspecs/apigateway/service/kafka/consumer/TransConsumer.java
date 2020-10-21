package ng.com.systemspecs.apigateway.service.kafka.consumer;

import io.vavr.control.Either;

import java.math.BigDecimal;
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
import ng.com.systemspecs.apigateway.service.dto.ResponseDTO;
import ng.com.systemspecs.apigateway.service.kafka.GenericConsumer;
import ng.com.systemspecs.apigateway.service.kafka.deserializer.DeserializationError;

@Service
public class TransConsumer extends GenericConsumer<Object> {
	@Autowired
	ExternalRESTClient externalRESTClient;

	private PaymentTransactionService paymentTransactionService;
    private final Logger log = LoggerFactory.getLogger(TransConsumer.class);

    public TransConsumer(@Value("${kafka.consumer.trans.name}") final String topicName, final
    		KafkaProperties kafkaProperties,PaymentTransactionService paymentTransactionService) {
        super(topicName, kafkaProperties.getConsumer().get("trans"), kafkaProperties.getPollingTimeout());
        this.paymentTransactionService = paymentTransactionService;
    }

    @Override
    protected void handleMessage(final ConsumerRecord<String, Either<DeserializationError, Object>> record) {
        final Either<DeserializationError, Object> value = record.value();
        System.out.println(" TransConsumer The Message 1=========================================================="
        		+ "=========================================================================================="
        		+ "============================================================================== "+value);
        
        System.out.println(" TransConsumer The Message 2=========================================================="
        		+ "=========================================================================================="
        		+ "============================================================================== "+value.get());        
        if (value.isLeft()) {
            log.error("Deserialization record failure: {}", value.getLeft());
        } else {
        	String result = externalRESTClient.getTransactionConfirmation("ABCD123456789012345678901139");
        	System.out.println(" result===="+result);
            // Maybe you could delete the next log.info(...) to avoid disclosing personal user information
        	
			/*
			 * LinkedHashMap<String,Object> map = (LinkedHashMap<String,Object>)
			 * value.get();
			 * 
			 * PaymentTransaction paymentTransaction = new PaymentTransaction();
			 * paymentTransaction.setAmount((BigDecimal)(map.get("amount")));
			 * paymentTransaction.setChannel((String)map.get("channel"));
			 * paymentTransaction.setCurrency((String)map.get("currency"));
			 * paymentTransaction.setDestinationAccount((String)map.get("destinationAccount"
			 * )); paymentTransaction.setDestinationAccountBankCode((String)map.get(
			 * "destinationAccountBankCode"));
			 * paymentTransaction.setDestinationAccountName((String)map.get(
			 * "destinationAccountName"));
			 * paymentTransaction.setDestinationNarration((String)map.get(
			 * "destinationNarration"));
			 * paymentTransaction.setPaymenttransID((Long)map.get("paymenttransID"));
			 * paymentTransaction.setSourceAccount((String)map.get("sourceAccount"));
			 * paymentTransaction.setSourceAccountBankCode((String)map.get(
			 * "sourceAccountBankCode"));
			 * paymentTransaction.setSourceAccountName((String)map.get("sourceAccountName"))
			 * ; paymentTransaction.setSourceNarration((String)map.get("sourceNarration"));
			 * paymentTransaction.setTransactionOwner((Profile)map.get("transactionOwner"));
			 * paymentTransaction.setTransactionRef((String)map.get("transactionRef"));
			 * paymentTransaction.setTransactionType(null);
			 * 
			 * paymentTransactionService.save(paymentTransaction);
			 * log.info("Handling record: {}", map);
			 */
            
        }

        // TODO: Here is where you can handle your messages
    }

    @Bean
    public void executeKafkaTransRunner() {
        new SimpleAsyncTaskExecutor().execute(this);
    }
}

package ng.com.systemspecs.apigateway.service.kafka.consumer;

import io.vavr.control.Either;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.stereotype.Service;
import ng.com.systemspecs.apigateway.config.KafkaProperties;
import ng.com.systemspecs.apigateway.service.kafka.GenericConsumer;
import ng.com.systemspecs.apigateway.service.kafka.deserializer.DeserializationError;

@Service
public class LogConsumer extends GenericConsumer<String> {

    private final Logger log = LoggerFactory.getLogger(LogConsumer.class);

    public LogConsumer(@Value("${kafka.consumer.log.name}") final String topicName, final KafkaProperties kafkaProperties) {
        super(topicName, kafkaProperties.getConsumer().get("log"), kafkaProperties.getPollingTimeout());
    }

    @Override
    protected void handleMessage(final ConsumerRecord<String, Either<DeserializationError, String>> record) {
        final Either<DeserializationError, String> value = record.value();
        if (value.isLeft()) {
            log.error("Deserialization record failure: {}", value.getLeft());
        } else {
            // Maybe you could delete the next log.info(...) to avoid disclosing personal user information
            log.info("Handling record: {}", value.get());
        }

        // TODO: Here is where you can handle your messages
    }

    @Bean
    public void executeKafkaLogRunner() {
        new SimpleAsyncTaskExecutor().execute(this);
    }
}

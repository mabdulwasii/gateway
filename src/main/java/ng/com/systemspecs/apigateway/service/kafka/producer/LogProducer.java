package ng.com.systemspecs.apigateway.service.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;

import ng.com.systemspecs.apigateway.config.KafkaProperties;

@Service
public class LogProducer {

    private final Logger log = LoggerFactory.getLogger(LogProducer.class);

    private final KafkaProducer<String, String> kafkaProducer;

    private final String topicName;

    public LogProducer(@Value("${kafka.producer.log.name}") final String topicName, final KafkaProperties kafkaProperties) {
        this.topicName = topicName;
        this.kafkaProducer = new KafkaProducer<>(kafkaProperties.getProducer().get("log"));
    }

    @PostConstruct
    public void init() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));
    }

    public void send(final String message) {
        final ProducerRecord<String, String> record = new ProducerRecord<>(topicName, message);
        try {
            log.info("Sending asynchronously a String record to topic: '" + topicName + "'");
            kafkaProducer.send(record);
        } catch (final Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public void shutdown() {
        log.info("Shutdown Kafka producer");
        kafkaProducer.close();
    }
}

package ng.com.systemspecs.apigateway.web.rest.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ng.com.systemspecs.apigateway.service.kafka.producer.LogProducer;
import ng.com.systemspecs.apigateway.service.kafka.producer.TransProducer;

/**
 * REST controller for managing String through Kafka.
 */
@RestController
@RequestMapping("/api")
public class LogKafkaResource {

    private final Logger log = LoggerFactory.getLogger(LogKafkaResource.class);

    private final LogProducer logProducer;
    private final TransProducer transProducer;

    public LogKafkaResource(LogProducer logProducer, TransProducer transProducer) {
        this.logProducer = logProducer;
        this.transProducer = transProducer;
    }

    /**
     * {@code POST  /logs/kafka} : Send a log in Kafka.
     *
     * @param log the String to send.
     */
    @PostMapping("/logs/kafka")
    public void sendLog(@RequestBody String log) {
        //log.debug("REST request to send a String in Kafka: {}", log);
        logProducer.send(log);
        transProducer.send(log); 
    }
}

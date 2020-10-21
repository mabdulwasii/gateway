package ng.com.systemspecs.apigateway.web.rest.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ng.com.systemspecs.apigateway.service.dto.ResponseDTO;
import ng.com.systemspecs.apigateway.service.kafka.producer.TransProducer;

/**
 * REST controller for managing String through Kafka.
 */
@RestController
@RequestMapping("/api")
public class TransKafkaResource {

    private final Logger log = LoggerFactory.getLogger(TransKafkaResource.class);

    private final TransProducer transProducer;

    public TransKafkaResource(TransProducer transProducer) {
        this.transProducer = transProducer;
    }

    /**
     * {@code POST  /transs/kafka} : Send a trans in Kafka.
     *
     * @param trans the String to send.
     */
    @PostMapping("/transs/kafka")
    public void sendTrans(@RequestBody ResponseDTO trans) {
        log.debug("REST request to send a String in Kafka: {}", trans);
        transProducer.send(trans);
    }
}

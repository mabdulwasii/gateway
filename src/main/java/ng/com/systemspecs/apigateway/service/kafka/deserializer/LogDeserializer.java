package ng.com.systemspecs.apigateway.service.kafka.deserializer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import io.vavr.control.Either;
import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

public class LogDeserializer implements Deserializer<Either<DeserializationError, Object>> {

    private final Logger log = LoggerFactory.getLogger(LogDeserializer.class);

    private final ObjectMapper objectMapper;

    private String encoding = "UTF8";

    public LogDeserializer() {
        this.objectMapper =
            new ObjectMapper()
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule())
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
                .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
                .setDateFormat(new StdDateFormat());
    }

    @Override
    public Either<DeserializationError, Object> deserialize(final String topicName, final byte[] data) {
        try {
        	
            final String value = data == null ? null : new String(data, this.encoding);
            return Either.right(value);
        } catch (final UnsupportedEncodingException e) {
            return Either.left(new DeserializationError(data, e));
        }
    }
}

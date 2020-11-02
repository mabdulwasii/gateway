package ng.com.systemspecs.apigateway.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ng.com.systemspecs.apigateway.web.rest.TestUtil;

public class DoubleEntryLoggerTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DoubleEntryLogger.class);
        DoubleEntryLogger doubleEntryLogger1 = new DoubleEntryLogger();
        doubleEntryLogger1.setId(1L);
        DoubleEntryLogger doubleEntryLogger2 = new DoubleEntryLogger();
        doubleEntryLogger2.setId(doubleEntryLogger1.getId());
        assertThat(doubleEntryLogger1).isEqualTo(doubleEntryLogger2);
        doubleEntryLogger2.setId(2L);
        assertThat(doubleEntryLogger1).isNotEqualTo(doubleEntryLogger2);
        doubleEntryLogger1.setId(null);
        assertThat(doubleEntryLogger1).isNotEqualTo(doubleEntryLogger2);
    }
}

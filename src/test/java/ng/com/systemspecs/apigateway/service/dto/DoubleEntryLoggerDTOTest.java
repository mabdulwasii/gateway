package ng.com.systemspecs.apigateway.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ng.com.systemspecs.apigateway.web.rest.TestUtil;

public class DoubleEntryLoggerDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DoubleEntryLoggerDTO.class);
        DoubleEntryLoggerDTO doubleEntryLoggerDTO1 = new DoubleEntryLoggerDTO();
        doubleEntryLoggerDTO1.setId(1L);
        DoubleEntryLoggerDTO doubleEntryLoggerDTO2 = new DoubleEntryLoggerDTO();
        assertThat(doubleEntryLoggerDTO1).isNotEqualTo(doubleEntryLoggerDTO2);
        doubleEntryLoggerDTO2.setId(doubleEntryLoggerDTO1.getId());
        assertThat(doubleEntryLoggerDTO1).isEqualTo(doubleEntryLoggerDTO2);
        doubleEntryLoggerDTO2.setId(2L);
        assertThat(doubleEntryLoggerDTO1).isNotEqualTo(doubleEntryLoggerDTO2);
        doubleEntryLoggerDTO1.setId(null);
        assertThat(doubleEntryLoggerDTO1).isNotEqualTo(doubleEntryLoggerDTO2);
    }
}

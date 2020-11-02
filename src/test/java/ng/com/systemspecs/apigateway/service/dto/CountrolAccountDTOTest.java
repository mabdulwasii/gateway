package ng.com.systemspecs.apigateway.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ng.com.systemspecs.apigateway.web.rest.TestUtil;

public class CountrolAccountDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CountrolAccountDTO.class);
        CountrolAccountDTO countrolAccountDTO1 = new CountrolAccountDTO();
        countrolAccountDTO1.setId(1L);
        CountrolAccountDTO countrolAccountDTO2 = new CountrolAccountDTO();
        assertThat(countrolAccountDTO1).isNotEqualTo(countrolAccountDTO2);
        countrolAccountDTO2.setId(countrolAccountDTO1.getId());
        assertThat(countrolAccountDTO1).isEqualTo(countrolAccountDTO2);
        countrolAccountDTO2.setId(2L);
        assertThat(countrolAccountDTO1).isNotEqualTo(countrolAccountDTO2);
        countrolAccountDTO1.setId(null);
        assertThat(countrolAccountDTO1).isNotEqualTo(countrolAccountDTO2);
    }
}

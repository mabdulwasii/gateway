package ng.com.systemspecs.apigateway.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ng.com.systemspecs.apigateway.web.rest.TestUtil;

public class KyclevelDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(KyclevelDTO.class);
        KyclevelDTO kyclevelDTO1 = new KyclevelDTO();
        kyclevelDTO1.setId(1L);
        KyclevelDTO kyclevelDTO2 = new KyclevelDTO();
        assertThat(kyclevelDTO1).isNotEqualTo(kyclevelDTO2);
        kyclevelDTO2.setId(kyclevelDTO1.getId());
        assertThat(kyclevelDTO1).isEqualTo(kyclevelDTO2);
        kyclevelDTO2.setId(2L);
        assertThat(kyclevelDTO1).isNotEqualTo(kyclevelDTO2);
        kyclevelDTO1.setId(null);
        assertThat(kyclevelDTO1).isNotEqualTo(kyclevelDTO2);
    }
}

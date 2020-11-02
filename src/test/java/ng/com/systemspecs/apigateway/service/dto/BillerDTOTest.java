package ng.com.systemspecs.apigateway.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ng.com.systemspecs.apigateway.web.rest.TestUtil;

public class BillerDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BillerDTO.class);
        BillerDTO billerDTO1 = new BillerDTO();
        billerDTO1.setId(1L);
        BillerDTO billerDTO2 = new BillerDTO();
        assertThat(billerDTO1).isNotEqualTo(billerDTO2);
        billerDTO2.setId(billerDTO1.getId());
        assertThat(billerDTO1).isEqualTo(billerDTO2);
        billerDTO2.setId(2L);
        assertThat(billerDTO1).isNotEqualTo(billerDTO2);
        billerDTO1.setId(null);
        assertThat(billerDTO1).isNotEqualTo(billerDTO2);
    }
}

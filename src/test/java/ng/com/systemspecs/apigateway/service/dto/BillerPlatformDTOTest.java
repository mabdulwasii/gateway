package ng.com.systemspecs.apigateway.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ng.com.systemspecs.apigateway.web.rest.TestUtil;

public class BillerPlatformDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BillerPlatformDTO.class);
        BillerPlatformDTO billerPlatformDTO1 = new BillerPlatformDTO();
        billerPlatformDTO1.setId(1L);
        BillerPlatformDTO billerPlatformDTO2 = new BillerPlatformDTO();
        assertThat(billerPlatformDTO1).isNotEqualTo(billerPlatformDTO2);
        billerPlatformDTO2.setId(billerPlatformDTO1.getId());
        assertThat(billerPlatformDTO1).isEqualTo(billerPlatformDTO2);
        billerPlatformDTO2.setId(2L);
        assertThat(billerPlatformDTO1).isNotEqualTo(billerPlatformDTO2);
        billerPlatformDTO1.setId(null);
        assertThat(billerPlatformDTO1).isNotEqualTo(billerPlatformDTO2);
    }
}

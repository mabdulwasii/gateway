package ng.com.systemspecs.apigateway.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ng.com.systemspecs.apigateway.web.rest.TestUtil;

public class BillerPlatformTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BillerPlatform.class);
        BillerPlatform billerPlatform1 = new BillerPlatform();
        billerPlatform1.setId(1L);
        BillerPlatform billerPlatform2 = new BillerPlatform();
        billerPlatform2.setId(billerPlatform1.getId());
        assertThat(billerPlatform1).isEqualTo(billerPlatform2);
        billerPlatform2.setId(2L);
        assertThat(billerPlatform1).isNotEqualTo(billerPlatform2);
        billerPlatform1.setId(null);
        assertThat(billerPlatform1).isNotEqualTo(billerPlatform2);
    }
}

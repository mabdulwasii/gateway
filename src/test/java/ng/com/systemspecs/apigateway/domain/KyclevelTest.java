package ng.com.systemspecs.apigateway.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ng.com.systemspecs.apigateway.web.rest.TestUtil;

public class KyclevelTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Kyclevel.class);
        Kyclevel kyclevel1 = new Kyclevel();
        kyclevel1.setId(1L);
        Kyclevel kyclevel2 = new Kyclevel();
        kyclevel2.setId(kyclevel1.getId());
        assertThat(kyclevel1).isEqualTo(kyclevel2);
        kyclevel2.setId(2L);
        assertThat(kyclevel1).isNotEqualTo(kyclevel2);
        kyclevel1.setId(null);
        assertThat(kyclevel1).isNotEqualTo(kyclevel2);
    }
}

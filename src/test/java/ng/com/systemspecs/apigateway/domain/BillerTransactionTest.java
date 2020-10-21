package ng.com.systemspecs.apigateway.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ng.com.systemspecs.apigateway.web.rest.TestUtil;

public class BillerTransactionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BillerTransaction.class);
        BillerTransaction billerTransaction1 = new BillerTransaction();
        billerTransaction1.setId(1L);
        BillerTransaction billerTransaction2 = new BillerTransaction();
        billerTransaction2.setId(billerTransaction1.getId());
        assertThat(billerTransaction1).isEqualTo(billerTransaction2);
        billerTransaction2.setId(2L);
        assertThat(billerTransaction1).isNotEqualTo(billerTransaction2);
        billerTransaction1.setId(null);
        assertThat(billerTransaction1).isNotEqualTo(billerTransaction2);
    }
}

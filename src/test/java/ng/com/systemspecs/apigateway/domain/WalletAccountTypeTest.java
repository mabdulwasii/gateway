package ng.com.systemspecs.apigateway.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ng.com.systemspecs.apigateway.web.rest.TestUtil;

public class WalletAccountTypeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WalletAccountType.class);
        WalletAccountType walletAccountType1 = new WalletAccountType();
        walletAccountType1.setId(1L);
        WalletAccountType walletAccountType2 = new WalletAccountType();
        walletAccountType2.setId(walletAccountType1.getId());
        assertThat(walletAccountType1).isEqualTo(walletAccountType2);
        walletAccountType2.setId(2L);
        assertThat(walletAccountType1).isNotEqualTo(walletAccountType2);
        walletAccountType1.setId(null);
        assertThat(walletAccountType1).isNotEqualTo(walletAccountType2);
    }
}

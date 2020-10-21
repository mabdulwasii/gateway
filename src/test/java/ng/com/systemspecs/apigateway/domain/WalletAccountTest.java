package ng.com.systemspecs.apigateway.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ng.com.systemspecs.apigateway.web.rest.TestUtil;

public class WalletAccountTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WalletAccount.class);
        WalletAccount walletAccount1 = new WalletAccount();
        walletAccount1.setId(1L);
        WalletAccount walletAccount2 = new WalletAccount();
        walletAccount2.setId(walletAccount1.getId());
        assertThat(walletAccount1).isEqualTo(walletAccount2);
        walletAccount2.setId(2L);
        assertThat(walletAccount1).isNotEqualTo(walletAccount2);
        walletAccount1.setId(null);
        assertThat(walletAccount1).isNotEqualTo(walletAccount2);
    }
}

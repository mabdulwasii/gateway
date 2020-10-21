package ng.com.systemspecs.apigateway.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ng.com.systemspecs.apigateway.web.rest.TestUtil;

public class WalletAccountDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WalletAccountDTO.class);
        WalletAccountDTO walletAccountDTO1 = new WalletAccountDTO();
        walletAccountDTO1.setId(1L);
        WalletAccountDTO walletAccountDTO2 = new WalletAccountDTO();
        assertThat(walletAccountDTO1).isNotEqualTo(walletAccountDTO2);
        walletAccountDTO2.setId(walletAccountDTO1.getId());
        assertThat(walletAccountDTO1).isEqualTo(walletAccountDTO2);
        walletAccountDTO2.setId(2L);
        assertThat(walletAccountDTO1).isNotEqualTo(walletAccountDTO2);
        walletAccountDTO1.setId(null);
        assertThat(walletAccountDTO1).isNotEqualTo(walletAccountDTO2);
    }
}

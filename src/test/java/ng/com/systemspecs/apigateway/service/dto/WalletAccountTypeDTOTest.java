package ng.com.systemspecs.apigateway.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ng.com.systemspecs.apigateway.web.rest.TestUtil;

public class WalletAccountTypeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WalletAccountTypeDTO.class);
        WalletAccountTypeDTO walletAccountTypeDTO1 = new WalletAccountTypeDTO();
        walletAccountTypeDTO1.setId(1L);
        WalletAccountTypeDTO walletAccountTypeDTO2 = new WalletAccountTypeDTO();
        assertThat(walletAccountTypeDTO1).isNotEqualTo(walletAccountTypeDTO2);
        walletAccountTypeDTO2.setId(walletAccountTypeDTO1.getId());
        assertThat(walletAccountTypeDTO1).isEqualTo(walletAccountTypeDTO2);
        walletAccountTypeDTO2.setId(2L);
        assertThat(walletAccountTypeDTO1).isNotEqualTo(walletAccountTypeDTO2);
        walletAccountTypeDTO1.setId(null);
        assertThat(walletAccountTypeDTO1).isNotEqualTo(walletAccountTypeDTO2);
    }
}

package ng.com.systemspecs.apigateway.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ng.com.systemspecs.apigateway.web.rest.TestUtil;

public class ProfileTypeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProfileTypeDTO.class);
        ProfileTypeDTO profileTypeDTO1 = new ProfileTypeDTO();
        profileTypeDTO1.setId(1L);
        ProfileTypeDTO profileTypeDTO2 = new ProfileTypeDTO();
        assertThat(profileTypeDTO1).isNotEqualTo(profileTypeDTO2);
        profileTypeDTO2.setId(profileTypeDTO1.getId());
        assertThat(profileTypeDTO1).isEqualTo(profileTypeDTO2);
        profileTypeDTO2.setId(2L);
        assertThat(profileTypeDTO1).isNotEqualTo(profileTypeDTO2);
        profileTypeDTO1.setId(null);
        assertThat(profileTypeDTO1).isNotEqualTo(profileTypeDTO2);
    }
}

package ng.com.systemspecs.apigateway.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ng.com.systemspecs.apigateway.web.rest.TestUtil;

public class ProfileTypeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProfileType.class);
        ProfileType profileType1 = new ProfileType();
        profileType1.setId(1L);
        ProfileType profileType2 = new ProfileType();
        profileType2.setId(profileType1.getId());
        assertThat(profileType1).isEqualTo(profileType2);
        profileType2.setId(2L);
        assertThat(profileType1).isNotEqualTo(profileType2);
        profileType1.setId(null);
        assertThat(profileType1).isNotEqualTo(profileType2);
    }
}

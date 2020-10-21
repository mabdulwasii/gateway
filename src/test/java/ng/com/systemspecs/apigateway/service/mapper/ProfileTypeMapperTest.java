package ng.com.systemspecs.apigateway.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ProfileTypeMapperTest {

    private ProfileTypeMapper profileTypeMapper;

    @BeforeEach
    public void setUp() {
        profileTypeMapper = new ProfileTypeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(profileTypeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(profileTypeMapper.fromId(null)).isNull();
    }
}

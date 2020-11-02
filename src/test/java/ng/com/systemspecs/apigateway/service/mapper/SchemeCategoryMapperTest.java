package ng.com.systemspecs.apigateway.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SchemeCategoryMapperTest {

    private SchemeCategoryMapper schemeCategoryMapper;

    @BeforeEach
    public void setUp() {
        schemeCategoryMapper = new SchemeCategoryMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(schemeCategoryMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(schemeCategoryMapper.fromId(null)).isNull();
    }
}

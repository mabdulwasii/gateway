package ng.com.systemspecs.apigateway.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BillerCategoryMapperTest {

    private BillerCategoryMapper billerCategoryMapper;

    @BeforeEach
    public void setUp() {
        billerCategoryMapper = new BillerCategoryMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(billerCategoryMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(billerCategoryMapper.fromId(null)).isNull();
    }
}

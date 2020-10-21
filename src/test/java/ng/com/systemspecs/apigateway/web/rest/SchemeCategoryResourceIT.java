package ng.com.systemspecs.apigateway.web.rest;

import ng.com.systemspecs.apigateway.ApiGatewayApp;
import ng.com.systemspecs.apigateway.domain.SchemeCategory;
import ng.com.systemspecs.apigateway.repository.SchemeCategoryRepository;
import ng.com.systemspecs.apigateway.service.SchemeCategoryService;
import ng.com.systemspecs.apigateway.service.dto.SchemeCategoryDTO;
import ng.com.systemspecs.apigateway.service.mapper.SchemeCategoryMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link SchemeCategoryResource} REST controller.
 */
@SpringBootTest(classes = ApiGatewayApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SchemeCategoryResourceIT {

    private static final Long DEFAULT_SCHEMECATEGORY_ID = 1L;
    private static final Long UPDATED_SCHEMECATEGORY_ID = 2L;

    private static final String DEFAULT_SCHEME_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_SCHEME_CATEGORY = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private SchemeCategoryRepository schemeCategoryRepository;

    @Autowired
    private SchemeCategoryMapper schemeCategoryMapper;

    @Autowired
    private SchemeCategoryService schemeCategoryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSchemeCategoryMockMvc;

    private SchemeCategory schemeCategory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SchemeCategory createEntity(EntityManager em) {
        SchemeCategory schemeCategory = new SchemeCategory()
            .schemecategoryID(DEFAULT_SCHEMECATEGORY_ID)
            .schemeCategory(DEFAULT_SCHEME_CATEGORY)
            .description(DEFAULT_DESCRIPTION);
        return schemeCategory;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SchemeCategory createUpdatedEntity(EntityManager em) {
        SchemeCategory schemeCategory = new SchemeCategory()
            .schemecategoryID(UPDATED_SCHEMECATEGORY_ID)
            .schemeCategory(UPDATED_SCHEME_CATEGORY)
            .description(UPDATED_DESCRIPTION);
        return schemeCategory;
    }

    @BeforeEach
    public void initTest() {
        schemeCategory = createEntity(em);
    }

    @Test
    @Transactional
    public void createSchemeCategory() throws Exception {
        int databaseSizeBeforeCreate = schemeCategoryRepository.findAll().size();
        // Create the SchemeCategory
        SchemeCategoryDTO schemeCategoryDTO = schemeCategoryMapper.toDto(schemeCategory);
        restSchemeCategoryMockMvc.perform(post("/api/scheme-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(schemeCategoryDTO)))
            .andExpect(status().isCreated());

        // Validate the SchemeCategory in the database
        List<SchemeCategory> schemeCategoryList = schemeCategoryRepository.findAll();
        assertThat(schemeCategoryList).hasSize(databaseSizeBeforeCreate + 1);
        SchemeCategory testSchemeCategory = schemeCategoryList.get(schemeCategoryList.size() - 1);
        assertThat(testSchemeCategory.getSchemecategoryID()).isEqualTo(DEFAULT_SCHEMECATEGORY_ID);
        assertThat(testSchemeCategory.getSchemeCategory()).isEqualTo(DEFAULT_SCHEME_CATEGORY);
        assertThat(testSchemeCategory.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createSchemeCategoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = schemeCategoryRepository.findAll().size();

        // Create the SchemeCategory with an existing ID
        schemeCategory.setId(1L);
        SchemeCategoryDTO schemeCategoryDTO = schemeCategoryMapper.toDto(schemeCategory);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSchemeCategoryMockMvc.perform(post("/api/scheme-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(schemeCategoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SchemeCategory in the database
        List<SchemeCategory> schemeCategoryList = schemeCategoryRepository.findAll();
        assertThat(schemeCategoryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSchemeCategories() throws Exception {
        // Initialize the database
        schemeCategoryRepository.saveAndFlush(schemeCategory);

        // Get all the schemeCategoryList
        restSchemeCategoryMockMvc.perform(get("/api/scheme-categories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(schemeCategory.getId().intValue())))
            .andExpect(jsonPath("$.[*].schemecategoryID").value(hasItem(DEFAULT_SCHEMECATEGORY_ID.intValue())))
            .andExpect(jsonPath("$.[*].schemeCategory").value(hasItem(DEFAULT_SCHEME_CATEGORY)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getSchemeCategory() throws Exception {
        // Initialize the database
        schemeCategoryRepository.saveAndFlush(schemeCategory);

        // Get the schemeCategory
        restSchemeCategoryMockMvc.perform(get("/api/scheme-categories/{id}", schemeCategory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(schemeCategory.getId().intValue()))
            .andExpect(jsonPath("$.schemecategoryID").value(DEFAULT_SCHEMECATEGORY_ID.intValue()))
            .andExpect(jsonPath("$.schemeCategory").value(DEFAULT_SCHEME_CATEGORY))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }
    @Test
    @Transactional
    public void getNonExistingSchemeCategory() throws Exception {
        // Get the schemeCategory
        restSchemeCategoryMockMvc.perform(get("/api/scheme-categories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSchemeCategory() throws Exception {
        // Initialize the database
        schemeCategoryRepository.saveAndFlush(schemeCategory);

        int databaseSizeBeforeUpdate = schemeCategoryRepository.findAll().size();

        // Update the schemeCategory
        SchemeCategory updatedSchemeCategory = schemeCategoryRepository.findById(schemeCategory.getId()).get();
        // Disconnect from session so that the updates on updatedSchemeCategory are not directly saved in db
        em.detach(updatedSchemeCategory);
        updatedSchemeCategory
            .schemecategoryID(UPDATED_SCHEMECATEGORY_ID)
            .schemeCategory(UPDATED_SCHEME_CATEGORY)
            .description(UPDATED_DESCRIPTION);
        SchemeCategoryDTO schemeCategoryDTO = schemeCategoryMapper.toDto(updatedSchemeCategory);

        restSchemeCategoryMockMvc.perform(put("/api/scheme-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(schemeCategoryDTO)))
            .andExpect(status().isOk());

        // Validate the SchemeCategory in the database
        List<SchemeCategory> schemeCategoryList = schemeCategoryRepository.findAll();
        assertThat(schemeCategoryList).hasSize(databaseSizeBeforeUpdate);
        SchemeCategory testSchemeCategory = schemeCategoryList.get(schemeCategoryList.size() - 1);
        assertThat(testSchemeCategory.getSchemecategoryID()).isEqualTo(UPDATED_SCHEMECATEGORY_ID);
        assertThat(testSchemeCategory.getSchemeCategory()).isEqualTo(UPDATED_SCHEME_CATEGORY);
        assertThat(testSchemeCategory.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingSchemeCategory() throws Exception {
        int databaseSizeBeforeUpdate = schemeCategoryRepository.findAll().size();

        // Create the SchemeCategory
        SchemeCategoryDTO schemeCategoryDTO = schemeCategoryMapper.toDto(schemeCategory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSchemeCategoryMockMvc.perform(put("/api/scheme-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(schemeCategoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SchemeCategory in the database
        List<SchemeCategory> schemeCategoryList = schemeCategoryRepository.findAll();
        assertThat(schemeCategoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSchemeCategory() throws Exception {
        // Initialize the database
        schemeCategoryRepository.saveAndFlush(schemeCategory);

        int databaseSizeBeforeDelete = schemeCategoryRepository.findAll().size();

        // Delete the schemeCategory
        restSchemeCategoryMockMvc.perform(delete("/api/scheme-categories/{id}", schemeCategory.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SchemeCategory> schemeCategoryList = schemeCategoryRepository.findAll();
        assertThat(schemeCategoryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

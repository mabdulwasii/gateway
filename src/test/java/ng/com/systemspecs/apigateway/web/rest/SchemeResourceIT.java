package ng.com.systemspecs.apigateway.web.rest;

import ng.com.systemspecs.apigateway.ApiGatewayApp;
import ng.com.systemspecs.apigateway.domain.Scheme;
import ng.com.systemspecs.apigateway.repository.SchemeRepository;
import ng.com.systemspecs.apigateway.service.SchemeService;
import ng.com.systemspecs.apigateway.service.dto.SchemeDTO;
import ng.com.systemspecs.apigateway.service.mapper.SchemeMapper;

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
 * Integration tests for the {@link SchemeResource} REST controller.
 */
@SpringBootTest(classes = ApiGatewayApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SchemeResourceIT {

    private static final Long DEFAULT_SCHEME_ID = 1L;
    private static final Long UPDATED_SCHEME_ID = 2L;

    private static final String DEFAULT_SCHEME = "AAAAAAAAAA";
    private static final String UPDATED_SCHEME = "BBBBBBBBBB";

    @Autowired
    private SchemeRepository schemeRepository;

    @Autowired
    private SchemeMapper schemeMapper;

    @Autowired
    private SchemeService schemeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSchemeMockMvc;

    private Scheme scheme;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Scheme createEntity(EntityManager em) {
        Scheme scheme = new Scheme()
            .schemeID(DEFAULT_SCHEME_ID)
            .scheme(DEFAULT_SCHEME);
        return scheme;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Scheme createUpdatedEntity(EntityManager em) {
        Scheme scheme = new Scheme()
            .schemeID(UPDATED_SCHEME_ID)
            .scheme(UPDATED_SCHEME);
        return scheme;
    }

    @BeforeEach
    public void initTest() {
        scheme = createEntity(em);
    }

    @Test
    @Transactional
    public void createScheme() throws Exception {
        int databaseSizeBeforeCreate = schemeRepository.findAll().size();
        // Create the Scheme
        SchemeDTO schemeDTO = schemeMapper.toDto(scheme);
        restSchemeMockMvc.perform(post("/api/schemes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(schemeDTO)))
            .andExpect(status().isCreated());

        // Validate the Scheme in the database
        List<Scheme> schemeList = schemeRepository.findAll();
        assertThat(schemeList).hasSize(databaseSizeBeforeCreate + 1);
        Scheme testScheme = schemeList.get(schemeList.size() - 1);
        assertThat(testScheme.getSchemeID()).isEqualTo(DEFAULT_SCHEME_ID);
        assertThat(testScheme.getScheme()).isEqualTo(DEFAULT_SCHEME);
    }

    @Test
    @Transactional
    public void createSchemeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = schemeRepository.findAll().size();

        // Create the Scheme with an existing ID
        scheme.setId(1L);
        SchemeDTO schemeDTO = schemeMapper.toDto(scheme);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSchemeMockMvc.perform(post("/api/schemes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(schemeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Scheme in the database
        List<Scheme> schemeList = schemeRepository.findAll();
        assertThat(schemeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSchemes() throws Exception {
        // Initialize the database
        schemeRepository.saveAndFlush(scheme);

        // Get all the schemeList
        restSchemeMockMvc.perform(get("/api/schemes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(scheme.getId().intValue())))
            .andExpect(jsonPath("$.[*].schemeID").value(hasItem(DEFAULT_SCHEME_ID.intValue())))
            .andExpect(jsonPath("$.[*].scheme").value(hasItem(DEFAULT_SCHEME)));
    }
    
    @Test
    @Transactional
    public void getScheme() throws Exception {
        // Initialize the database
        schemeRepository.saveAndFlush(scheme);

        // Get the scheme
        restSchemeMockMvc.perform(get("/api/schemes/{id}", scheme.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(scheme.getId().intValue()))
            .andExpect(jsonPath("$.schemeID").value(DEFAULT_SCHEME_ID.intValue()))
            .andExpect(jsonPath("$.scheme").value(DEFAULT_SCHEME));
    }
    @Test
    @Transactional
    public void getNonExistingScheme() throws Exception {
        // Get the scheme
        restSchemeMockMvc.perform(get("/api/schemes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateScheme() throws Exception {
        // Initialize the database
        schemeRepository.saveAndFlush(scheme);

        int databaseSizeBeforeUpdate = schemeRepository.findAll().size();

        // Update the scheme
        Scheme updatedScheme = schemeRepository.findById(scheme.getId()).get();
        // Disconnect from session so that the updates on updatedScheme are not directly saved in db
        em.detach(updatedScheme);
        updatedScheme
            .schemeID(UPDATED_SCHEME_ID)
            .scheme(UPDATED_SCHEME);
        SchemeDTO schemeDTO = schemeMapper.toDto(updatedScheme);

        restSchemeMockMvc.perform(put("/api/schemes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(schemeDTO)))
            .andExpect(status().isOk());

        // Validate the Scheme in the database
        List<Scheme> schemeList = schemeRepository.findAll();
        assertThat(schemeList).hasSize(databaseSizeBeforeUpdate);
        Scheme testScheme = schemeList.get(schemeList.size() - 1);
        assertThat(testScheme.getSchemeID()).isEqualTo(UPDATED_SCHEME_ID);
        assertThat(testScheme.getScheme()).isEqualTo(UPDATED_SCHEME);
    }

    @Test
    @Transactional
    public void updateNonExistingScheme() throws Exception {
        int databaseSizeBeforeUpdate = schemeRepository.findAll().size();

        // Create the Scheme
        SchemeDTO schemeDTO = schemeMapper.toDto(scheme);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSchemeMockMvc.perform(put("/api/schemes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(schemeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Scheme in the database
        List<Scheme> schemeList = schemeRepository.findAll();
        assertThat(schemeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteScheme() throws Exception {
        // Initialize the database
        schemeRepository.saveAndFlush(scheme);

        int databaseSizeBeforeDelete = schemeRepository.findAll().size();

        // Delete the scheme
        restSchemeMockMvc.perform(delete("/api/schemes/{id}", scheme.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Scheme> schemeList = schemeRepository.findAll();
        assertThat(schemeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

package ng.com.systemspecs.apigateway.web.rest;

import ng.com.systemspecs.apigateway.ApiGatewayApp;
import ng.com.systemspecs.apigateway.domain.ProfileType;
import ng.com.systemspecs.apigateway.repository.ProfileTypeRepository;
import ng.com.systemspecs.apigateway.service.ProfileTypeService;
import ng.com.systemspecs.apigateway.service.dto.ProfileTypeDTO;
import ng.com.systemspecs.apigateway.service.mapper.ProfileTypeMapper;

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
 * Integration tests for the {@link ProfileTypeResource} REST controller.
 */
@SpringBootTest(classes = ApiGatewayApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProfileTypeResourceIT {

    private static final Long DEFAULT_PROFILETYPE_ID = 1L;
    private static final Long UPDATED_PROFILETYPE_ID = 2L;

    private static final String DEFAULT_PROFILETYPE = "AAAAAAAAAA";
    private static final String UPDATED_PROFILETYPE = "BBBBBBBBBB";

    @Autowired
    private ProfileTypeRepository profileTypeRepository;

    @Autowired
    private ProfileTypeMapper profileTypeMapper;

    @Autowired
    private ProfileTypeService profileTypeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProfileTypeMockMvc;

    private ProfileType profileType;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProfileType createEntity(EntityManager em) {
        ProfileType profileType = new ProfileType()
            .profiletypeID(DEFAULT_PROFILETYPE_ID)
            .profiletype(DEFAULT_PROFILETYPE);
        return profileType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProfileType createUpdatedEntity(EntityManager em) {
        ProfileType profileType = new ProfileType()
            .profiletypeID(UPDATED_PROFILETYPE_ID)
            .profiletype(UPDATED_PROFILETYPE);
        return profileType;
    }

    @BeforeEach
    public void initTest() {
        profileType = createEntity(em);
    }

    @Test
    @Transactional
    public void createProfileType() throws Exception {
        int databaseSizeBeforeCreate = profileTypeRepository.findAll().size();
        // Create the ProfileType
        ProfileTypeDTO profileTypeDTO = profileTypeMapper.toDto(profileType);
        restProfileTypeMockMvc.perform(post("/api/profile-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(profileTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the ProfileType in the database
        List<ProfileType> profileTypeList = profileTypeRepository.findAll();
        assertThat(profileTypeList).hasSize(databaseSizeBeforeCreate + 1);
        ProfileType testProfileType = profileTypeList.get(profileTypeList.size() - 1);
        assertThat(testProfileType.getProfiletypeID()).isEqualTo(DEFAULT_PROFILETYPE_ID);
        assertThat(testProfileType.getProfiletype()).isEqualTo(DEFAULT_PROFILETYPE);
    }

    @Test
    @Transactional
    public void createProfileTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = profileTypeRepository.findAll().size();

        // Create the ProfileType with an existing ID
        profileType.setId(1L);
        ProfileTypeDTO profileTypeDTO = profileTypeMapper.toDto(profileType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProfileTypeMockMvc.perform(post("/api/profile-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(profileTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProfileType in the database
        List<ProfileType> profileTypeList = profileTypeRepository.findAll();
        assertThat(profileTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProfileTypes() throws Exception {
        // Initialize the database
        profileTypeRepository.saveAndFlush(profileType);

        // Get all the profileTypeList
        restProfileTypeMockMvc.perform(get("/api/profile-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(profileType.getId().intValue())))
            .andExpect(jsonPath("$.[*].profiletypeID").value(hasItem(DEFAULT_PROFILETYPE_ID.intValue())))
            .andExpect(jsonPath("$.[*].profiletype").value(hasItem(DEFAULT_PROFILETYPE)));
    }
    
    @Test
    @Transactional
    public void getProfileType() throws Exception {
        // Initialize the database
        profileTypeRepository.saveAndFlush(profileType);

        // Get the profileType
        restProfileTypeMockMvc.perform(get("/api/profile-types/{id}", profileType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(profileType.getId().intValue()))
            .andExpect(jsonPath("$.profiletypeID").value(DEFAULT_PROFILETYPE_ID.intValue()))
            .andExpect(jsonPath("$.profiletype").value(DEFAULT_PROFILETYPE));
    }
    @Test
    @Transactional
    public void getNonExistingProfileType() throws Exception {
        // Get the profileType
        restProfileTypeMockMvc.perform(get("/api/profile-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProfileType() throws Exception {
        // Initialize the database
        profileTypeRepository.saveAndFlush(profileType);

        int databaseSizeBeforeUpdate = profileTypeRepository.findAll().size();

        // Update the profileType
        ProfileType updatedProfileType = profileTypeRepository.findById(profileType.getId()).get();
        // Disconnect from session so that the updates on updatedProfileType are not directly saved in db
        em.detach(updatedProfileType);
        updatedProfileType
            .profiletypeID(UPDATED_PROFILETYPE_ID)
            .profiletype(UPDATED_PROFILETYPE);
        ProfileTypeDTO profileTypeDTO = profileTypeMapper.toDto(updatedProfileType);

        restProfileTypeMockMvc.perform(put("/api/profile-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(profileTypeDTO)))
            .andExpect(status().isOk());

        // Validate the ProfileType in the database
        List<ProfileType> profileTypeList = profileTypeRepository.findAll();
        assertThat(profileTypeList).hasSize(databaseSizeBeforeUpdate);
        ProfileType testProfileType = profileTypeList.get(profileTypeList.size() - 1);
        assertThat(testProfileType.getProfiletypeID()).isEqualTo(UPDATED_PROFILETYPE_ID);
        assertThat(testProfileType.getProfiletype()).isEqualTo(UPDATED_PROFILETYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingProfileType() throws Exception {
        int databaseSizeBeforeUpdate = profileTypeRepository.findAll().size();

        // Create the ProfileType
        ProfileTypeDTO profileTypeDTO = profileTypeMapper.toDto(profileType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProfileTypeMockMvc.perform(put("/api/profile-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(profileTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProfileType in the database
        List<ProfileType> profileTypeList = profileTypeRepository.findAll();
        assertThat(profileTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProfileType() throws Exception {
        // Initialize the database
        profileTypeRepository.saveAndFlush(profileType);

        int databaseSizeBeforeDelete = profileTypeRepository.findAll().size();

        // Delete the profileType
        restProfileTypeMockMvc.perform(delete("/api/profile-types/{id}", profileType.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProfileType> profileTypeList = profileTypeRepository.findAll();
        assertThat(profileTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

package ng.com.systemspecs.apigateway.web.rest;

import ng.com.systemspecs.apigateway.ApiGatewayApp;
import ng.com.systemspecs.apigateway.domain.BillerPlatform;
import ng.com.systemspecs.apigateway.repository.BillerPlatformRepository;
import ng.com.systemspecs.apigateway.service.BillerPlatformService;
import ng.com.systemspecs.apigateway.service.dto.BillerPlatformDTO;
import ng.com.systemspecs.apigateway.service.mapper.BillerPlatformMapper;

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
 * Integration tests for the {@link BillerPlatformResource} REST controller.
 */
@SpringBootTest(classes = ApiGatewayApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BillerPlatformResourceIT {

    private static final Long DEFAULT_BILLERPLATFORM_ID = 1L;
    private static final Long UPDATED_BILLERPLATFORM_ID = 2L;

    private static final String DEFAULT_BILLER_PLATFORM = "AAAAAAAAAA";
    private static final String UPDATED_BILLER_PLATFORM = "BBBBBBBBBB";

    private static final Double DEFAULT_AMOUNT = 1D;
    private static final Double UPDATED_AMOUNT = 2D;

    @Autowired
    private BillerPlatformRepository billerPlatformRepository;

    @Autowired
    private BillerPlatformMapper billerPlatformMapper;

    @Autowired
    private BillerPlatformService billerPlatformService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBillerPlatformMockMvc;

    private BillerPlatform billerPlatform;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BillerPlatform createEntity(EntityManager em) {
        BillerPlatform billerPlatform = new BillerPlatform()
            .billerplatformID(DEFAULT_BILLERPLATFORM_ID)
            .billerPlatform(DEFAULT_BILLER_PLATFORM)
            .amount(DEFAULT_AMOUNT);
        return billerPlatform;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BillerPlatform createUpdatedEntity(EntityManager em) {
        BillerPlatform billerPlatform = new BillerPlatform()
            .billerplatformID(UPDATED_BILLERPLATFORM_ID)
            .billerPlatform(UPDATED_BILLER_PLATFORM)
            .amount(UPDATED_AMOUNT);
        return billerPlatform;
    }

    @BeforeEach
    public void initTest() {
        billerPlatform = createEntity(em);
    }

    @Test
    @Transactional
    public void createBillerPlatform() throws Exception {
        int databaseSizeBeforeCreate = billerPlatformRepository.findAll().size();
        // Create the BillerPlatform
        BillerPlatformDTO billerPlatformDTO = billerPlatformMapper.toDto(billerPlatform);
        restBillerPlatformMockMvc.perform(post("/api/biller-platforms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billerPlatformDTO)))
            .andExpect(status().isCreated());

        // Validate the BillerPlatform in the database
        List<BillerPlatform> billerPlatformList = billerPlatformRepository.findAll();
        assertThat(billerPlatformList).hasSize(databaseSizeBeforeCreate + 1);
        BillerPlatform testBillerPlatform = billerPlatformList.get(billerPlatformList.size() - 1);
        assertThat(testBillerPlatform.getBillerplatformID()).isEqualTo(DEFAULT_BILLERPLATFORM_ID);
        assertThat(testBillerPlatform.getBillerPlatform()).isEqualTo(DEFAULT_BILLER_PLATFORM);
        assertThat(testBillerPlatform.getAmount()).isEqualTo(DEFAULT_AMOUNT);
    }

    @Test
    @Transactional
    public void createBillerPlatformWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = billerPlatformRepository.findAll().size();

        // Create the BillerPlatform with an existing ID
        billerPlatform.setId(1L);
        BillerPlatformDTO billerPlatformDTO = billerPlatformMapper.toDto(billerPlatform);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBillerPlatformMockMvc.perform(post("/api/biller-platforms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billerPlatformDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BillerPlatform in the database
        List<BillerPlatform> billerPlatformList = billerPlatformRepository.findAll();
        assertThat(billerPlatformList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBillerPlatforms() throws Exception {
        // Initialize the database
        billerPlatformRepository.saveAndFlush(billerPlatform);

        // Get all the billerPlatformList
        restBillerPlatformMockMvc.perform(get("/api/biller-platforms?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(billerPlatform.getId().intValue())))
            .andExpect(jsonPath("$.[*].billerplatformID").value(hasItem(DEFAULT_BILLERPLATFORM_ID.intValue())))
            .andExpect(jsonPath("$.[*].billerPlatform").value(hasItem(DEFAULT_BILLER_PLATFORM)))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getBillerPlatform() throws Exception {
        // Initialize the database
        billerPlatformRepository.saveAndFlush(billerPlatform);

        // Get the billerPlatform
        restBillerPlatformMockMvc.perform(get("/api/biller-platforms/{id}", billerPlatform.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(billerPlatform.getId().intValue()))
            .andExpect(jsonPath("$.billerplatformID").value(DEFAULT_BILLERPLATFORM_ID.intValue()))
            .andExpect(jsonPath("$.billerPlatform").value(DEFAULT_BILLER_PLATFORM))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingBillerPlatform() throws Exception {
        // Get the billerPlatform
        restBillerPlatformMockMvc.perform(get("/api/biller-platforms/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBillerPlatform() throws Exception {
        // Initialize the database
        billerPlatformRepository.saveAndFlush(billerPlatform);

        int databaseSizeBeforeUpdate = billerPlatformRepository.findAll().size();

        // Update the billerPlatform
        BillerPlatform updatedBillerPlatform = billerPlatformRepository.findById(billerPlatform.getId()).get();
        // Disconnect from session so that the updates on updatedBillerPlatform are not directly saved in db
        em.detach(updatedBillerPlatform);
        updatedBillerPlatform
            .billerplatformID(UPDATED_BILLERPLATFORM_ID)
            .billerPlatform(UPDATED_BILLER_PLATFORM)
            .amount(UPDATED_AMOUNT);
        BillerPlatformDTO billerPlatformDTO = billerPlatformMapper.toDto(updatedBillerPlatform);

        restBillerPlatformMockMvc.perform(put("/api/biller-platforms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billerPlatformDTO)))
            .andExpect(status().isOk());

        // Validate the BillerPlatform in the database
        List<BillerPlatform> billerPlatformList = billerPlatformRepository.findAll();
        assertThat(billerPlatformList).hasSize(databaseSizeBeforeUpdate);
        BillerPlatform testBillerPlatform = billerPlatformList.get(billerPlatformList.size() - 1);
        assertThat(testBillerPlatform.getBillerplatformID()).isEqualTo(UPDATED_BILLERPLATFORM_ID);
        assertThat(testBillerPlatform.getBillerPlatform()).isEqualTo(UPDATED_BILLER_PLATFORM);
        assertThat(testBillerPlatform.getAmount()).isEqualTo(UPDATED_AMOUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingBillerPlatform() throws Exception {
        int databaseSizeBeforeUpdate = billerPlatformRepository.findAll().size();

        // Create the BillerPlatform
        BillerPlatformDTO billerPlatformDTO = billerPlatformMapper.toDto(billerPlatform);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBillerPlatformMockMvc.perform(put("/api/biller-platforms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billerPlatformDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BillerPlatform in the database
        List<BillerPlatform> billerPlatformList = billerPlatformRepository.findAll();
        assertThat(billerPlatformList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBillerPlatform() throws Exception {
        // Initialize the database
        billerPlatformRepository.saveAndFlush(billerPlatform);

        int databaseSizeBeforeDelete = billerPlatformRepository.findAll().size();

        // Delete the billerPlatform
        restBillerPlatformMockMvc.perform(delete("/api/biller-platforms/{id}", billerPlatform.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BillerPlatform> billerPlatformList = billerPlatformRepository.findAll();
        assertThat(billerPlatformList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

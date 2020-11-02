package ng.com.systemspecs.apigateway.web.rest;

import ng.com.systemspecs.apigateway.ApiGatewayApp;
import ng.com.systemspecs.apigateway.domain.BillerCategory;
import ng.com.systemspecs.apigateway.repository.BillerCategoryRepository;
import ng.com.systemspecs.apigateway.service.BillerCategoryService;
import ng.com.systemspecs.apigateway.service.dto.BillerCategoryDTO;
import ng.com.systemspecs.apigateway.service.mapper.BillerCategoryMapper;

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
 * Integration tests for the {@link BillerCategoryResource} REST controller.
 */
@SpringBootTest(classes = ApiGatewayApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BillerCategoryResourceIT {

    private static final Long DEFAULT_BILLERCATEGORY_ID = 1L;
    private static final Long UPDATED_BILLERCATEGORY_ID = 2L;

    private static final String DEFAULT_BILLER_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_BILLER_CATEGORY = "BBBBBBBBBB";

    @Autowired
    private BillerCategoryRepository billerCategoryRepository;

    @Autowired
    private BillerCategoryMapper billerCategoryMapper;

    @Autowired
    private BillerCategoryService billerCategoryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBillerCategoryMockMvc;

    private BillerCategory billerCategory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BillerCategory createEntity(EntityManager em) {
        BillerCategory billerCategory = new BillerCategory()
            .billercategoryID(DEFAULT_BILLERCATEGORY_ID)
            .billerCategory(DEFAULT_BILLER_CATEGORY);
        return billerCategory;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BillerCategory createUpdatedEntity(EntityManager em) {
        BillerCategory billerCategory = new BillerCategory()
            .billercategoryID(UPDATED_BILLERCATEGORY_ID)
            .billerCategory(UPDATED_BILLER_CATEGORY);
        return billerCategory;
    }

    @BeforeEach
    public void initTest() {
        billerCategory = createEntity(em);
    }

    @Test
    @Transactional
    public void createBillerCategory() throws Exception {
        int databaseSizeBeforeCreate = billerCategoryRepository.findAll().size();
        // Create the BillerCategory
        BillerCategoryDTO billerCategoryDTO = billerCategoryMapper.toDto(billerCategory);
        restBillerCategoryMockMvc.perform(post("/api/biller-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billerCategoryDTO)))
            .andExpect(status().isCreated());

        // Validate the BillerCategory in the database
        List<BillerCategory> billerCategoryList = billerCategoryRepository.findAll();
        assertThat(billerCategoryList).hasSize(databaseSizeBeforeCreate + 1);
        BillerCategory testBillerCategory = billerCategoryList.get(billerCategoryList.size() - 1);
        assertThat(testBillerCategory.getBillercategoryID()).isEqualTo(DEFAULT_BILLERCATEGORY_ID);
        assertThat(testBillerCategory.getBillerCategory()).isEqualTo(DEFAULT_BILLER_CATEGORY);
    }

    @Test
    @Transactional
    public void createBillerCategoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = billerCategoryRepository.findAll().size();

        // Create the BillerCategory with an existing ID
        billerCategory.setId(1L);
        BillerCategoryDTO billerCategoryDTO = billerCategoryMapper.toDto(billerCategory);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBillerCategoryMockMvc.perform(post("/api/biller-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billerCategoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BillerCategory in the database
        List<BillerCategory> billerCategoryList = billerCategoryRepository.findAll();
        assertThat(billerCategoryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBillerCategories() throws Exception {
        // Initialize the database
        billerCategoryRepository.saveAndFlush(billerCategory);

        // Get all the billerCategoryList
        restBillerCategoryMockMvc.perform(get("/api/biller-categories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(billerCategory.getId().intValue())))
            .andExpect(jsonPath("$.[*].billercategoryID").value(hasItem(DEFAULT_BILLERCATEGORY_ID.intValue())))
            .andExpect(jsonPath("$.[*].billerCategory").value(hasItem(DEFAULT_BILLER_CATEGORY)));
    }
    
    @Test
    @Transactional
    public void getBillerCategory() throws Exception {
        // Initialize the database
        billerCategoryRepository.saveAndFlush(billerCategory);

        // Get the billerCategory
        restBillerCategoryMockMvc.perform(get("/api/biller-categories/{id}", billerCategory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(billerCategory.getId().intValue()))
            .andExpect(jsonPath("$.billercategoryID").value(DEFAULT_BILLERCATEGORY_ID.intValue()))
            .andExpect(jsonPath("$.billerCategory").value(DEFAULT_BILLER_CATEGORY));
    }
    @Test
    @Transactional
    public void getNonExistingBillerCategory() throws Exception {
        // Get the billerCategory
        restBillerCategoryMockMvc.perform(get("/api/biller-categories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBillerCategory() throws Exception {
        // Initialize the database
        billerCategoryRepository.saveAndFlush(billerCategory);

        int databaseSizeBeforeUpdate = billerCategoryRepository.findAll().size();

        // Update the billerCategory
        BillerCategory updatedBillerCategory = billerCategoryRepository.findById(billerCategory.getId()).get();
        // Disconnect from session so that the updates on updatedBillerCategory are not directly saved in db
        em.detach(updatedBillerCategory);
        updatedBillerCategory
            .billercategoryID(UPDATED_BILLERCATEGORY_ID)
            .billerCategory(UPDATED_BILLER_CATEGORY);
        BillerCategoryDTO billerCategoryDTO = billerCategoryMapper.toDto(updatedBillerCategory);

        restBillerCategoryMockMvc.perform(put("/api/biller-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billerCategoryDTO)))
            .andExpect(status().isOk());

        // Validate the BillerCategory in the database
        List<BillerCategory> billerCategoryList = billerCategoryRepository.findAll();
        assertThat(billerCategoryList).hasSize(databaseSizeBeforeUpdate);
        BillerCategory testBillerCategory = billerCategoryList.get(billerCategoryList.size() - 1);
        assertThat(testBillerCategory.getBillercategoryID()).isEqualTo(UPDATED_BILLERCATEGORY_ID);
        assertThat(testBillerCategory.getBillerCategory()).isEqualTo(UPDATED_BILLER_CATEGORY);
    }

    @Test
    @Transactional
    public void updateNonExistingBillerCategory() throws Exception {
        int databaseSizeBeforeUpdate = billerCategoryRepository.findAll().size();

        // Create the BillerCategory
        BillerCategoryDTO billerCategoryDTO = billerCategoryMapper.toDto(billerCategory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBillerCategoryMockMvc.perform(put("/api/biller-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billerCategoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BillerCategory in the database
        List<BillerCategory> billerCategoryList = billerCategoryRepository.findAll();
        assertThat(billerCategoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBillerCategory() throws Exception {
        // Initialize the database
        billerCategoryRepository.saveAndFlush(billerCategory);

        int databaseSizeBeforeDelete = billerCategoryRepository.findAll().size();

        // Delete the billerCategory
        restBillerCategoryMockMvc.perform(delete("/api/biller-categories/{id}", billerCategory.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BillerCategory> billerCategoryList = billerCategoryRepository.findAll();
        assertThat(billerCategoryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

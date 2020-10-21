package ng.com.systemspecs.apigateway.web.rest;

import ng.com.systemspecs.apigateway.ApiGatewayApp;
import ng.com.systemspecs.apigateway.domain.Biller;
import ng.com.systemspecs.apigateway.repository.BillerRepository;
import ng.com.systemspecs.apigateway.service.BillerService;
import ng.com.systemspecs.apigateway.service.dto.BillerDTO;
import ng.com.systemspecs.apigateway.service.mapper.BillerMapper;

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
 * Integration tests for the {@link BillerResource} REST controller.
 */
@SpringBootTest(classes = ApiGatewayApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BillerResourceIT {

    private static final Long DEFAULT_BILLER_ID = 1L;
    private static final Long UPDATED_BILLER_ID = 2L;

    private static final String DEFAULT_BILLER = "AAAAAAAAAA";
    private static final String UPDATED_BILLER = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    @Autowired
    private BillerRepository billerRepository;

    @Autowired
    private BillerMapper billerMapper;

    @Autowired
    private BillerService billerService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBillerMockMvc;

    private Biller biller;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Biller createEntity(EntityManager em) {
        Biller biller = new Biller()
            .billerID(DEFAULT_BILLER_ID)
            .biller(DEFAULT_BILLER)
            .address(DEFAULT_ADDRESS)
            .phoneNumber(DEFAULT_PHONE_NUMBER);
        return biller;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Biller createUpdatedEntity(EntityManager em) {
        Biller biller = new Biller()
            .billerID(UPDATED_BILLER_ID)
            .biller(UPDATED_BILLER)
            .address(UPDATED_ADDRESS)
            .phoneNumber(UPDATED_PHONE_NUMBER);
        return biller;
    }

    @BeforeEach
    public void initTest() {
        biller = createEntity(em);
    }

    @Test
    @Transactional
    public void createBiller() throws Exception {
        int databaseSizeBeforeCreate = billerRepository.findAll().size();
        // Create the Biller
        BillerDTO billerDTO = billerMapper.toDto(biller);
        restBillerMockMvc.perform(post("/api/billers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billerDTO)))
            .andExpect(status().isCreated());

        // Validate the Biller in the database
        List<Biller> billerList = billerRepository.findAll();
        assertThat(billerList).hasSize(databaseSizeBeforeCreate + 1);
        Biller testBiller = billerList.get(billerList.size() - 1);
        assertThat(testBiller.getBillerID()).isEqualTo(DEFAULT_BILLER_ID);
        assertThat(testBiller.getBiller()).isEqualTo(DEFAULT_BILLER);
        assertThat(testBiller.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testBiller.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
    }

    @Test
    @Transactional
    public void createBillerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = billerRepository.findAll().size();

        // Create the Biller with an existing ID
        biller.setId(1L);
        BillerDTO billerDTO = billerMapper.toDto(biller);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBillerMockMvc.perform(post("/api/billers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Biller in the database
        List<Biller> billerList = billerRepository.findAll();
        assertThat(billerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBillers() throws Exception {
        // Initialize the database
        billerRepository.saveAndFlush(biller);

        // Get all the billerList
        restBillerMockMvc.perform(get("/api/billers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(biller.getId().intValue())))
            .andExpect(jsonPath("$.[*].billerID").value(hasItem(DEFAULT_BILLER_ID.intValue())))
            .andExpect(jsonPath("$.[*].biller").value(hasItem(DEFAULT_BILLER)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)));
    }
    
    @Test
    @Transactional
    public void getBiller() throws Exception {
        // Initialize the database
        billerRepository.saveAndFlush(biller);

        // Get the biller
        restBillerMockMvc.perform(get("/api/billers/{id}", biller.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(biller.getId().intValue()))
            .andExpect(jsonPath("$.billerID").value(DEFAULT_BILLER_ID.intValue()))
            .andExpect(jsonPath("$.biller").value(DEFAULT_BILLER))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER));
    }
    @Test
    @Transactional
    public void getNonExistingBiller() throws Exception {
        // Get the biller
        restBillerMockMvc.perform(get("/api/billers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBiller() throws Exception {
        // Initialize the database
        billerRepository.saveAndFlush(biller);

        int databaseSizeBeforeUpdate = billerRepository.findAll().size();

        // Update the biller
        Biller updatedBiller = billerRepository.findById(biller.getId()).get();
        // Disconnect from session so that the updates on updatedBiller are not directly saved in db
        em.detach(updatedBiller);
        updatedBiller
            .billerID(UPDATED_BILLER_ID)
            .biller(UPDATED_BILLER)
            .address(UPDATED_ADDRESS)
            .phoneNumber(UPDATED_PHONE_NUMBER);
        BillerDTO billerDTO = billerMapper.toDto(updatedBiller);

        restBillerMockMvc.perform(put("/api/billers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billerDTO)))
            .andExpect(status().isOk());

        // Validate the Biller in the database
        List<Biller> billerList = billerRepository.findAll();
        assertThat(billerList).hasSize(databaseSizeBeforeUpdate);
        Biller testBiller = billerList.get(billerList.size() - 1);
        assertThat(testBiller.getBillerID()).isEqualTo(UPDATED_BILLER_ID);
        assertThat(testBiller.getBiller()).isEqualTo(UPDATED_BILLER);
        assertThat(testBiller.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testBiller.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
    }

    @Test
    @Transactional
    public void updateNonExistingBiller() throws Exception {
        int databaseSizeBeforeUpdate = billerRepository.findAll().size();

        // Create the Biller
        BillerDTO billerDTO = billerMapper.toDto(biller);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBillerMockMvc.perform(put("/api/billers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Biller in the database
        List<Biller> billerList = billerRepository.findAll();
        assertThat(billerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBiller() throws Exception {
        // Initialize the database
        billerRepository.saveAndFlush(biller);

        int databaseSizeBeforeDelete = billerRepository.findAll().size();

        // Delete the biller
        restBillerMockMvc.perform(delete("/api/billers/{id}", biller.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Biller> billerList = billerRepository.findAll();
        assertThat(billerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

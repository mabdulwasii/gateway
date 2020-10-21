package ng.com.systemspecs.apigateway.web.rest;

import ng.com.systemspecs.apigateway.ApiGatewayApp;
import ng.com.systemspecs.apigateway.domain.Customersubscription;
import ng.com.systemspecs.apigateway.repository.CustomersubscriptionRepository;
import ng.com.systemspecs.apigateway.service.CustomersubscriptionService;
import ng.com.systemspecs.apigateway.service.dto.CustomersubscriptionDTO;
import ng.com.systemspecs.apigateway.service.mapper.CustomersubscriptionMapper;

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

import ng.com.systemspecs.apigateway.domain.enumeration.Frequency;
/**
 * Integration tests for the {@link CustomersubscriptionResource} REST controller.
 */
@SpringBootTest(classes = ApiGatewayApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CustomersubscriptionResourceIT {

    private static final String DEFAULT_UNIQUE_ID = "AAAAAAAAAA";
    private static final String UPDATED_UNIQUE_ID = "BBBBBBBBBB";

    private static final Frequency DEFAULT_FREQUENCY = Frequency.DAILY;
    private static final Frequency UPDATED_FREQUENCY = Frequency.WEEKLY;

    @Autowired
    private CustomersubscriptionRepository customersubscriptionRepository;

    @Autowired
    private CustomersubscriptionMapper customersubscriptionMapper;

    @Autowired
    private CustomersubscriptionService customersubscriptionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCustomersubscriptionMockMvc;

    private Customersubscription customersubscription;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Customersubscription createEntity(EntityManager em) {
        Customersubscription customersubscription = new Customersubscription()
            .uniqueID(DEFAULT_UNIQUE_ID)
            .frequency(DEFAULT_FREQUENCY);
        return customersubscription;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Customersubscription createUpdatedEntity(EntityManager em) {
        Customersubscription customersubscription = new Customersubscription()
            .uniqueID(UPDATED_UNIQUE_ID)
            .frequency(UPDATED_FREQUENCY);
        return customersubscription;
    }

    @BeforeEach
    public void initTest() {
        customersubscription = createEntity(em);
    }

    @Test
    @Transactional
    public void createCustomersubscription() throws Exception {
        int databaseSizeBeforeCreate = customersubscriptionRepository.findAll().size();
        // Create the Customersubscription
        CustomersubscriptionDTO customersubscriptionDTO = customersubscriptionMapper.toDto(customersubscription);
        restCustomersubscriptionMockMvc.perform(post("/api/customersubscriptions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customersubscriptionDTO)))
            .andExpect(status().isCreated());

        // Validate the Customersubscription in the database
        List<Customersubscription> customersubscriptionList = customersubscriptionRepository.findAll();
        assertThat(customersubscriptionList).hasSize(databaseSizeBeforeCreate + 1);
        Customersubscription testCustomersubscription = customersubscriptionList.get(customersubscriptionList.size() - 1);
        assertThat(testCustomersubscription.getUniqueID()).isEqualTo(DEFAULT_UNIQUE_ID);
        assertThat(testCustomersubscription.getFrequency()).isEqualTo(DEFAULT_FREQUENCY);
    }

    @Test
    @Transactional
    public void createCustomersubscriptionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = customersubscriptionRepository.findAll().size();

        // Create the Customersubscription with an existing ID
        customersubscription.setId(1L);
        CustomersubscriptionDTO customersubscriptionDTO = customersubscriptionMapper.toDto(customersubscription);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCustomersubscriptionMockMvc.perform(post("/api/customersubscriptions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customersubscriptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Customersubscription in the database
        List<Customersubscription> customersubscriptionList = customersubscriptionRepository.findAll();
        assertThat(customersubscriptionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCustomersubscriptions() throws Exception {
        // Initialize the database
        customersubscriptionRepository.saveAndFlush(customersubscription);

        // Get all the customersubscriptionList
        restCustomersubscriptionMockMvc.perform(get("/api/customersubscriptions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(customersubscription.getId().intValue())))
            .andExpect(jsonPath("$.[*].uniqueID").value(hasItem(DEFAULT_UNIQUE_ID)))
            .andExpect(jsonPath("$.[*].frequency").value(hasItem(DEFAULT_FREQUENCY.toString())));
    }
    
    @Test
    @Transactional
    public void getCustomersubscription() throws Exception {
        // Initialize the database
        customersubscriptionRepository.saveAndFlush(customersubscription);

        // Get the customersubscription
        restCustomersubscriptionMockMvc.perform(get("/api/customersubscriptions/{id}", customersubscription.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(customersubscription.getId().intValue()))
            .andExpect(jsonPath("$.uniqueID").value(DEFAULT_UNIQUE_ID))
            .andExpect(jsonPath("$.frequency").value(DEFAULT_FREQUENCY.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingCustomersubscription() throws Exception {
        // Get the customersubscription
        restCustomersubscriptionMockMvc.perform(get("/api/customersubscriptions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCustomersubscription() throws Exception {
        // Initialize the database
        customersubscriptionRepository.saveAndFlush(customersubscription);

        int databaseSizeBeforeUpdate = customersubscriptionRepository.findAll().size();

        // Update the customersubscription
        Customersubscription updatedCustomersubscription = customersubscriptionRepository.findById(customersubscription.getId()).get();
        // Disconnect from session so that the updates on updatedCustomersubscription are not directly saved in db
        em.detach(updatedCustomersubscription);
        updatedCustomersubscription
            .uniqueID(UPDATED_UNIQUE_ID)
            .frequency(UPDATED_FREQUENCY);
        CustomersubscriptionDTO customersubscriptionDTO = customersubscriptionMapper.toDto(updatedCustomersubscription);

        restCustomersubscriptionMockMvc.perform(put("/api/customersubscriptions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customersubscriptionDTO)))
            .andExpect(status().isOk());

        // Validate the Customersubscription in the database
        List<Customersubscription> customersubscriptionList = customersubscriptionRepository.findAll();
        assertThat(customersubscriptionList).hasSize(databaseSizeBeforeUpdate);
        Customersubscription testCustomersubscription = customersubscriptionList.get(customersubscriptionList.size() - 1);
        assertThat(testCustomersubscription.getUniqueID()).isEqualTo(UPDATED_UNIQUE_ID);
        assertThat(testCustomersubscription.getFrequency()).isEqualTo(UPDATED_FREQUENCY);
    }

    @Test
    @Transactional
    public void updateNonExistingCustomersubscription() throws Exception {
        int databaseSizeBeforeUpdate = customersubscriptionRepository.findAll().size();

        // Create the Customersubscription
        CustomersubscriptionDTO customersubscriptionDTO = customersubscriptionMapper.toDto(customersubscription);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustomersubscriptionMockMvc.perform(put("/api/customersubscriptions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customersubscriptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Customersubscription in the database
        List<Customersubscription> customersubscriptionList = customersubscriptionRepository.findAll();
        assertThat(customersubscriptionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCustomersubscription() throws Exception {
        // Initialize the database
        customersubscriptionRepository.saveAndFlush(customersubscription);

        int databaseSizeBeforeDelete = customersubscriptionRepository.findAll().size();

        // Delete the customersubscription
        restCustomersubscriptionMockMvc.perform(delete("/api/customersubscriptions/{id}", customersubscription.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Customersubscription> customersubscriptionList = customersubscriptionRepository.findAll();
        assertThat(customersubscriptionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

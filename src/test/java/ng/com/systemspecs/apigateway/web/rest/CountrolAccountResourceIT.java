package ng.com.systemspecs.apigateway.web.rest;

import ng.com.systemspecs.apigateway.ApiGatewayApp;
import ng.com.systemspecs.apigateway.domain.CountrolAccount;
import ng.com.systemspecs.apigateway.repository.CountrolAccountRepository;
import ng.com.systemspecs.apigateway.service.CountrolAccountService;
import ng.com.systemspecs.apigateway.service.dto.CountrolAccountDTO;
import ng.com.systemspecs.apigateway.service.mapper.CountrolAccountMapper;

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
 * Integration tests for the {@link CountrolAccountResource} REST controller.
 */
@SpringBootTest(classes = ApiGatewayApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CountrolAccountResourceIT {

    private static final String DEFAULT_COUNTROL_ACCOUNT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_COUNTROL_ACCOUNT_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTROL_ACCOUNT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COUNTROL_ACCOUNT_NAME = "BBBBBBBBBB";

    @Autowired
    private CountrolAccountRepository countrolAccountRepository;

    @Autowired
    private CountrolAccountMapper countrolAccountMapper;

    @Autowired
    private CountrolAccountService countrolAccountService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCountrolAccountMockMvc;

    private CountrolAccount countrolAccount;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CountrolAccount createEntity(EntityManager em) {
        CountrolAccount countrolAccount = new CountrolAccount()
            .countrolAccountCode(DEFAULT_COUNTROL_ACCOUNT_CODE)
            .countrolAccountName(DEFAULT_COUNTROL_ACCOUNT_NAME);
        return countrolAccount;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CountrolAccount createUpdatedEntity(EntityManager em) {
        CountrolAccount countrolAccount = new CountrolAccount()
            .countrolAccountCode(UPDATED_COUNTROL_ACCOUNT_CODE)
            .countrolAccountName(UPDATED_COUNTROL_ACCOUNT_NAME);
        return countrolAccount;
    }

    @BeforeEach
    public void initTest() {
        countrolAccount = createEntity(em);
    }

    @Test
    @Transactional
    public void createCountrolAccount() throws Exception {
        int databaseSizeBeforeCreate = countrolAccountRepository.findAll().size();
        // Create the CountrolAccount
        CountrolAccountDTO countrolAccountDTO = countrolAccountMapper.toDto(countrolAccount);
        restCountrolAccountMockMvc.perform(post("/api/countrol-accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(countrolAccountDTO)))
            .andExpect(status().isCreated());

        // Validate the CountrolAccount in the database
        List<CountrolAccount> countrolAccountList = countrolAccountRepository.findAll();
        assertThat(countrolAccountList).hasSize(databaseSizeBeforeCreate + 1);
        CountrolAccount testCountrolAccount = countrolAccountList.get(countrolAccountList.size() - 1);
        assertThat(testCountrolAccount.getCountrolAccountCode()).isEqualTo(DEFAULT_COUNTROL_ACCOUNT_CODE);
        assertThat(testCountrolAccount.getCountrolAccountName()).isEqualTo(DEFAULT_COUNTROL_ACCOUNT_NAME);
    }

    @Test
    @Transactional
    public void createCountrolAccountWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = countrolAccountRepository.findAll().size();

        // Create the CountrolAccount with an existing ID
        countrolAccount.setId(1L);
        CountrolAccountDTO countrolAccountDTO = countrolAccountMapper.toDto(countrolAccount);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCountrolAccountMockMvc.perform(post("/api/countrol-accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(countrolAccountDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CountrolAccount in the database
        List<CountrolAccount> countrolAccountList = countrolAccountRepository.findAll();
        assertThat(countrolAccountList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCountrolAccounts() throws Exception {
        // Initialize the database
        countrolAccountRepository.saveAndFlush(countrolAccount);

        // Get all the countrolAccountList
        restCountrolAccountMockMvc.perform(get("/api/countrol-accounts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(countrolAccount.getId().intValue())))
            .andExpect(jsonPath("$.[*].countrolAccountCode").value(hasItem(DEFAULT_COUNTROL_ACCOUNT_CODE)))
            .andExpect(jsonPath("$.[*].countrolAccountName").value(hasItem(DEFAULT_COUNTROL_ACCOUNT_NAME)));
    }
    
    @Test
    @Transactional
    public void getCountrolAccount() throws Exception {
        // Initialize the database
        countrolAccountRepository.saveAndFlush(countrolAccount);

        // Get the countrolAccount
        restCountrolAccountMockMvc.perform(get("/api/countrol-accounts/{id}", countrolAccount.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(countrolAccount.getId().intValue()))
            .andExpect(jsonPath("$.countrolAccountCode").value(DEFAULT_COUNTROL_ACCOUNT_CODE))
            .andExpect(jsonPath("$.countrolAccountName").value(DEFAULT_COUNTROL_ACCOUNT_NAME));
    }
    @Test
    @Transactional
    public void getNonExistingCountrolAccount() throws Exception {
        // Get the countrolAccount
        restCountrolAccountMockMvc.perform(get("/api/countrol-accounts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCountrolAccount() throws Exception {
        // Initialize the database
        countrolAccountRepository.saveAndFlush(countrolAccount);

        int databaseSizeBeforeUpdate = countrolAccountRepository.findAll().size();

        // Update the countrolAccount
        CountrolAccount updatedCountrolAccount = countrolAccountRepository.findById(countrolAccount.getId()).get();
        // Disconnect from session so that the updates on updatedCountrolAccount are not directly saved in db
        em.detach(updatedCountrolAccount);
        updatedCountrolAccount
            .countrolAccountCode(UPDATED_COUNTROL_ACCOUNT_CODE)
            .countrolAccountName(UPDATED_COUNTROL_ACCOUNT_NAME);
        CountrolAccountDTO countrolAccountDTO = countrolAccountMapper.toDto(updatedCountrolAccount);

        restCountrolAccountMockMvc.perform(put("/api/countrol-accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(countrolAccountDTO)))
            .andExpect(status().isOk());

        // Validate the CountrolAccount in the database
        List<CountrolAccount> countrolAccountList = countrolAccountRepository.findAll();
        assertThat(countrolAccountList).hasSize(databaseSizeBeforeUpdate);
        CountrolAccount testCountrolAccount = countrolAccountList.get(countrolAccountList.size() - 1);
        assertThat(testCountrolAccount.getCountrolAccountCode()).isEqualTo(UPDATED_COUNTROL_ACCOUNT_CODE);
        assertThat(testCountrolAccount.getCountrolAccountName()).isEqualTo(UPDATED_COUNTROL_ACCOUNT_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingCountrolAccount() throws Exception {
        int databaseSizeBeforeUpdate = countrolAccountRepository.findAll().size();

        // Create the CountrolAccount
        CountrolAccountDTO countrolAccountDTO = countrolAccountMapper.toDto(countrolAccount);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCountrolAccountMockMvc.perform(put("/api/countrol-accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(countrolAccountDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CountrolAccount in the database
        List<CountrolAccount> countrolAccountList = countrolAccountRepository.findAll();
        assertThat(countrolAccountList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCountrolAccount() throws Exception {
        // Initialize the database
        countrolAccountRepository.saveAndFlush(countrolAccount);

        int databaseSizeBeforeDelete = countrolAccountRepository.findAll().size();

        // Delete the countrolAccount
        restCountrolAccountMockMvc.perform(delete("/api/countrol-accounts/{id}", countrolAccount.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CountrolAccount> countrolAccountList = countrolAccountRepository.findAll();
        assertThat(countrolAccountList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

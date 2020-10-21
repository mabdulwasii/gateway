package ng.com.systemspecs.apigateway.web.rest;

import ng.com.systemspecs.apigateway.ApiGatewayApp;
import ng.com.systemspecs.apigateway.domain.BillerTransaction;
import ng.com.systemspecs.apigateway.repository.BillerTransactionRepository;
import ng.com.systemspecs.apigateway.service.BillerTransactionService;
import ng.com.systemspecs.apigateway.service.dto.BillerTransactionDTO;
import ng.com.systemspecs.apigateway.service.mapper.BillerTransactionMapper;

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
 * Integration tests for the {@link BillerTransactionResource} REST controller.
 */
@SpringBootTest(classes = ApiGatewayApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BillerTransactionResourceIT {

    private static final Long DEFAULT_BILLERTRANS_ID = 1L;
    private static final Long UPDATED_BILLERTRANS_ID = 2L;

    private static final String DEFAULT_TRANSACTION_REF = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTION_REF = "BBBBBBBBBB";

    private static final String DEFAULT_NARRATION = "AAAAAAAAAA";
    private static final String UPDATED_NARRATION = "BBBBBBBBBB";

    private static final Double DEFAULT_AMOUNT = 1D;
    private static final Double UPDATED_AMOUNT = 2D;

    @Autowired
    private BillerTransactionRepository billerTransactionRepository;

    @Autowired
    private BillerTransactionMapper billerTransactionMapper;

    @Autowired
    private BillerTransactionService billerTransactionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBillerTransactionMockMvc;

    private BillerTransaction billerTransaction;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BillerTransaction createEntity(EntityManager em) {
        BillerTransaction billerTransaction = new BillerTransaction()
            .billertransID(DEFAULT_BILLERTRANS_ID)
            .transactionRef(DEFAULT_TRANSACTION_REF)
            .narration(DEFAULT_NARRATION)
            .amount(DEFAULT_AMOUNT);
        return billerTransaction;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BillerTransaction createUpdatedEntity(EntityManager em) {
        BillerTransaction billerTransaction = new BillerTransaction()
            .billertransID(UPDATED_BILLERTRANS_ID)
            .transactionRef(UPDATED_TRANSACTION_REF)
            .narration(UPDATED_NARRATION)
            .amount(UPDATED_AMOUNT);
        return billerTransaction;
    }

    @BeforeEach
    public void initTest() {
        billerTransaction = createEntity(em);
    }

    @Test
    @Transactional
    public void createBillerTransaction() throws Exception {
        int databaseSizeBeforeCreate = billerTransactionRepository.findAll().size();
        // Create the BillerTransaction
        BillerTransactionDTO billerTransactionDTO = billerTransactionMapper.toDto(billerTransaction);
        restBillerTransactionMockMvc.perform(post("/api/biller-transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billerTransactionDTO)))
            .andExpect(status().isCreated());

        // Validate the BillerTransaction in the database
        List<BillerTransaction> billerTransactionList = billerTransactionRepository.findAll();
        assertThat(billerTransactionList).hasSize(databaseSizeBeforeCreate + 1);
        BillerTransaction testBillerTransaction = billerTransactionList.get(billerTransactionList.size() - 1);
        assertThat(testBillerTransaction.getBillertransID()).isEqualTo(DEFAULT_BILLERTRANS_ID);
        assertThat(testBillerTransaction.getTransactionRef()).isEqualTo(DEFAULT_TRANSACTION_REF);
        assertThat(testBillerTransaction.getNarration()).isEqualTo(DEFAULT_NARRATION);
        assertThat(testBillerTransaction.getAmount()).isEqualTo(DEFAULT_AMOUNT);
    }

    @Test
    @Transactional
    public void createBillerTransactionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = billerTransactionRepository.findAll().size();

        // Create the BillerTransaction with an existing ID
        billerTransaction.setId(1L);
        BillerTransactionDTO billerTransactionDTO = billerTransactionMapper.toDto(billerTransaction);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBillerTransactionMockMvc.perform(post("/api/biller-transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billerTransactionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BillerTransaction in the database
        List<BillerTransaction> billerTransactionList = billerTransactionRepository.findAll();
        assertThat(billerTransactionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBillerTransactions() throws Exception {
        // Initialize the database
        billerTransactionRepository.saveAndFlush(billerTransaction);

        // Get all the billerTransactionList
        restBillerTransactionMockMvc.perform(get("/api/biller-transactions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(billerTransaction.getId().intValue())))
            .andExpect(jsonPath("$.[*].billertransID").value(hasItem(DEFAULT_BILLERTRANS_ID.intValue())))
            .andExpect(jsonPath("$.[*].transactionRef").value(hasItem(DEFAULT_TRANSACTION_REF)))
            .andExpect(jsonPath("$.[*].narration").value(hasItem(DEFAULT_NARRATION)))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getBillerTransaction() throws Exception {
        // Initialize the database
        billerTransactionRepository.saveAndFlush(billerTransaction);

        // Get the billerTransaction
        restBillerTransactionMockMvc.perform(get("/api/biller-transactions/{id}", billerTransaction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(billerTransaction.getId().intValue()))
            .andExpect(jsonPath("$.billertransID").value(DEFAULT_BILLERTRANS_ID.intValue()))
            .andExpect(jsonPath("$.transactionRef").value(DEFAULT_TRANSACTION_REF))
            .andExpect(jsonPath("$.narration").value(DEFAULT_NARRATION))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingBillerTransaction() throws Exception {
        // Get the billerTransaction
        restBillerTransactionMockMvc.perform(get("/api/biller-transactions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBillerTransaction() throws Exception {
        // Initialize the database
        billerTransactionRepository.saveAndFlush(billerTransaction);

        int databaseSizeBeforeUpdate = billerTransactionRepository.findAll().size();

        // Update the billerTransaction
        BillerTransaction updatedBillerTransaction = billerTransactionRepository.findById(billerTransaction.getId()).get();
        // Disconnect from session so that the updates on updatedBillerTransaction are not directly saved in db
        em.detach(updatedBillerTransaction);
        updatedBillerTransaction
            .billertransID(UPDATED_BILLERTRANS_ID)
            .transactionRef(UPDATED_TRANSACTION_REF)
            .narration(UPDATED_NARRATION)
            .amount(UPDATED_AMOUNT);
        BillerTransactionDTO billerTransactionDTO = billerTransactionMapper.toDto(updatedBillerTransaction);

        restBillerTransactionMockMvc.perform(put("/api/biller-transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billerTransactionDTO)))
            .andExpect(status().isOk());

        // Validate the BillerTransaction in the database
        List<BillerTransaction> billerTransactionList = billerTransactionRepository.findAll();
        assertThat(billerTransactionList).hasSize(databaseSizeBeforeUpdate);
        BillerTransaction testBillerTransaction = billerTransactionList.get(billerTransactionList.size() - 1);
        assertThat(testBillerTransaction.getBillertransID()).isEqualTo(UPDATED_BILLERTRANS_ID);
        assertThat(testBillerTransaction.getTransactionRef()).isEqualTo(UPDATED_TRANSACTION_REF);
        assertThat(testBillerTransaction.getNarration()).isEqualTo(UPDATED_NARRATION);
        assertThat(testBillerTransaction.getAmount()).isEqualTo(UPDATED_AMOUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingBillerTransaction() throws Exception {
        int databaseSizeBeforeUpdate = billerTransactionRepository.findAll().size();

        // Create the BillerTransaction
        BillerTransactionDTO billerTransactionDTO = billerTransactionMapper.toDto(billerTransaction);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBillerTransactionMockMvc.perform(put("/api/biller-transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billerTransactionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BillerTransaction in the database
        List<BillerTransaction> billerTransactionList = billerTransactionRepository.findAll();
        assertThat(billerTransactionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBillerTransaction() throws Exception {
        // Initialize the database
        billerTransactionRepository.saveAndFlush(billerTransaction);

        int databaseSizeBeforeDelete = billerTransactionRepository.findAll().size();

        // Delete the billerTransaction
        restBillerTransactionMockMvc.perform(delete("/api/biller-transactions/{id}", billerTransaction.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BillerTransaction> billerTransactionList = billerTransactionRepository.findAll();
        assertThat(billerTransactionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

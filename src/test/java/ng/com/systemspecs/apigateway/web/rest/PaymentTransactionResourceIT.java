package ng.com.systemspecs.apigateway.web.rest;

import ng.com.systemspecs.apigateway.ApiGatewayApp;
import ng.com.systemspecs.apigateway.domain.PaymentTransaction;
import ng.com.systemspecs.apigateway.repository.PaymentTransactionRepository;
import ng.com.systemspecs.apigateway.service.PaymentTransactionService;
import ng.com.systemspecs.apigateway.service.dto.PaymentTransactionDTO;
import ng.com.systemspecs.apigateway.service.mapper.PaymentTransactionMapper;

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
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ng.com.systemspecs.apigateway.domain.enumeration.TransactionType;
/**
 * Integration tests for the {@link PaymentTransactionResource} REST controller.
 */
@SpringBootTest(classes = ApiGatewayApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PaymentTransactionResourceIT {

    private static final Long DEFAULT_PAYMENTTRANS_ID = 1L;
    private static final Long UPDATED_PAYMENTTRANS_ID = 2L;

    private static final TransactionType DEFAULT_TRANSACTION_TYPE = TransactionType.BANK_ACCOUNT_TRANSFER;
    private static final TransactionType UPDATED_TRANSACTION_TYPE = TransactionType.WALLET_TO_WALLET_TRANSFER;

    private static final String DEFAULT_TRANSACTION_REF = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTION_REF = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMOUNT = new BigDecimal(2);

    private static final String DEFAULT_CHANNEL = "AAAAAAAAAA";
    private static final String UPDATED_CHANNEL = "BBBBBBBBBB";

    private static final String DEFAULT_CURRENCY = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE_ACCOUNT = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_ACCOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE_ACCOUNT_BANK_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_ACCOUNT_BANK_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE_ACCOUNT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_ACCOUNT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE_NARRATION = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_NARRATION = "BBBBBBBBBB";

    private static final String DEFAULT_DESTINATION_ACCOUNT = "AAAAAAAAAA";
    private static final String UPDATED_DESTINATION_ACCOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_DESTINATION_ACCOUNT_BANK_CODE = "AAAAAAAAAA";
    private static final String UPDATED_DESTINATION_ACCOUNT_BANK_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DESTINATION_ACCOUNT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DESTINATION_ACCOUNT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESTINATION_NARRATION = "AAAAAAAAAA";
    private static final String UPDATED_DESTINATION_NARRATION = "BBBBBBBBBB";

    @Autowired
    private PaymentTransactionRepository paymentTransactionRepository;

    @Autowired
    private PaymentTransactionMapper paymentTransactionMapper;

    @Autowired
    private PaymentTransactionService paymentTransactionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPaymentTransactionMockMvc;

    private PaymentTransaction paymentTransaction;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PaymentTransaction createEntity(EntityManager em) {
        PaymentTransaction paymentTransaction = new PaymentTransaction()
            .paymenttransID(DEFAULT_PAYMENTTRANS_ID)
            .transactionType(DEFAULT_TRANSACTION_TYPE)
            .transactionRef(DEFAULT_TRANSACTION_REF)
            .amount(DEFAULT_AMOUNT)
            .channel(DEFAULT_CHANNEL)
            .currency(DEFAULT_CURRENCY)
            .sourceAccount(DEFAULT_SOURCE_ACCOUNT)
            .sourceAccountBankCode(DEFAULT_SOURCE_ACCOUNT_BANK_CODE)
            .sourceAccountName(DEFAULT_SOURCE_ACCOUNT_NAME)
            .sourceNarration(DEFAULT_SOURCE_NARRATION)
            .destinationAccount(DEFAULT_DESTINATION_ACCOUNT)
            .destinationAccountBankCode(DEFAULT_DESTINATION_ACCOUNT_BANK_CODE)
            .destinationAccountName(DEFAULT_DESTINATION_ACCOUNT_NAME)
            .destinationNarration(DEFAULT_DESTINATION_NARRATION);
        return paymentTransaction;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PaymentTransaction createUpdatedEntity(EntityManager em) {
        PaymentTransaction paymentTransaction = new PaymentTransaction()
            .paymenttransID(UPDATED_PAYMENTTRANS_ID)
            .transactionType(UPDATED_TRANSACTION_TYPE)
            .transactionRef(UPDATED_TRANSACTION_REF)
            .amount(UPDATED_AMOUNT)
            .channel(UPDATED_CHANNEL)
            .currency(UPDATED_CURRENCY)
            .sourceAccount(UPDATED_SOURCE_ACCOUNT)
            .sourceAccountBankCode(UPDATED_SOURCE_ACCOUNT_BANK_CODE)
            .sourceAccountName(UPDATED_SOURCE_ACCOUNT_NAME)
            .sourceNarration(UPDATED_SOURCE_NARRATION)
            .destinationAccount(UPDATED_DESTINATION_ACCOUNT)
            .destinationAccountBankCode(UPDATED_DESTINATION_ACCOUNT_BANK_CODE)
            .destinationAccountName(UPDATED_DESTINATION_ACCOUNT_NAME)
            .destinationNarration(UPDATED_DESTINATION_NARRATION);
        return paymentTransaction;
    }

    @BeforeEach
    public void initTest() {
        paymentTransaction = createEntity(em);
    }

    @Test
    @Transactional
    public void createPaymentTransaction() throws Exception {
        int databaseSizeBeforeCreate = paymentTransactionRepository.findAll().size();
        // Create the PaymentTransaction
        PaymentTransactionDTO paymentTransactionDTO = paymentTransactionMapper.toDto(paymentTransaction);
        restPaymentTransactionMockMvc.perform(post("/api/payment-transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paymentTransactionDTO)))
            .andExpect(status().isCreated());

        // Validate the PaymentTransaction in the database
        List<PaymentTransaction> paymentTransactionList = paymentTransactionRepository.findAll();
        assertThat(paymentTransactionList).hasSize(databaseSizeBeforeCreate + 1);
        PaymentTransaction testPaymentTransaction = paymentTransactionList.get(paymentTransactionList.size() - 1);
        assertThat(testPaymentTransaction.getPaymenttransID()).isEqualTo(DEFAULT_PAYMENTTRANS_ID);
        assertThat(testPaymentTransaction.getTransactionType()).isEqualTo(DEFAULT_TRANSACTION_TYPE);
        assertThat(testPaymentTransaction.getTransactionRef()).isEqualTo(DEFAULT_TRANSACTION_REF);
        assertThat(testPaymentTransaction.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testPaymentTransaction.getChannel()).isEqualTo(DEFAULT_CHANNEL);
        assertThat(testPaymentTransaction.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testPaymentTransaction.getSourceAccount()).isEqualTo(DEFAULT_SOURCE_ACCOUNT);
        assertThat(testPaymentTransaction.getSourceAccountBankCode()).isEqualTo(DEFAULT_SOURCE_ACCOUNT_BANK_CODE);
        assertThat(testPaymentTransaction.getSourceAccountName()).isEqualTo(DEFAULT_SOURCE_ACCOUNT_NAME);
        assertThat(testPaymentTransaction.getSourceNarration()).isEqualTo(DEFAULT_SOURCE_NARRATION);
        assertThat(testPaymentTransaction.getDestinationAccount()).isEqualTo(DEFAULT_DESTINATION_ACCOUNT);
        assertThat(testPaymentTransaction.getDestinationAccountBankCode()).isEqualTo(DEFAULT_DESTINATION_ACCOUNT_BANK_CODE);
        assertThat(testPaymentTransaction.getDestinationAccountName()).isEqualTo(DEFAULT_DESTINATION_ACCOUNT_NAME);
        assertThat(testPaymentTransaction.getDestinationNarration()).isEqualTo(DEFAULT_DESTINATION_NARRATION);
    }

    @Test
    @Transactional
    public void createPaymentTransactionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = paymentTransactionRepository.findAll().size();

        // Create the PaymentTransaction with an existing ID
        paymentTransaction.setId(1L);
        PaymentTransactionDTO paymentTransactionDTO = paymentTransactionMapper.toDto(paymentTransaction);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPaymentTransactionMockMvc.perform(post("/api/payment-transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paymentTransactionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PaymentTransaction in the database
        List<PaymentTransaction> paymentTransactionList = paymentTransactionRepository.findAll();
        assertThat(paymentTransactionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = paymentTransactionRepository.findAll().size();
        // set the field null
        paymentTransaction.setAmount(null);

        // Create the PaymentTransaction, which fails.
        PaymentTransactionDTO paymentTransactionDTO = paymentTransactionMapper.toDto(paymentTransaction);


        restPaymentTransactionMockMvc.perform(post("/api/payment-transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paymentTransactionDTO)))
            .andExpect(status().isBadRequest());

        List<PaymentTransaction> paymentTransactionList = paymentTransactionRepository.findAll();
        assertThat(paymentTransactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkChannelIsRequired() throws Exception {
        int databaseSizeBeforeTest = paymentTransactionRepository.findAll().size();
        // set the field null
        paymentTransaction.setChannel(null);

        // Create the PaymentTransaction, which fails.
        PaymentTransactionDTO paymentTransactionDTO = paymentTransactionMapper.toDto(paymentTransaction);


        restPaymentTransactionMockMvc.perform(post("/api/payment-transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paymentTransactionDTO)))
            .andExpect(status().isBadRequest());

        List<PaymentTransaction> paymentTransactionList = paymentTransactionRepository.findAll();
        assertThat(paymentTransactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCurrencyIsRequired() throws Exception {
        int databaseSizeBeforeTest = paymentTransactionRepository.findAll().size();
        // set the field null
        paymentTransaction.setCurrency(null);

        // Create the PaymentTransaction, which fails.
        PaymentTransactionDTO paymentTransactionDTO = paymentTransactionMapper.toDto(paymentTransaction);


        restPaymentTransactionMockMvc.perform(post("/api/payment-transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paymentTransactionDTO)))
            .andExpect(status().isBadRequest());

        List<PaymentTransaction> paymentTransactionList = paymentTransactionRepository.findAll();
        assertThat(paymentTransactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSourceAccountIsRequired() throws Exception {
        int databaseSizeBeforeTest = paymentTransactionRepository.findAll().size();
        // set the field null
        paymentTransaction.setSourceAccount(null);

        // Create the PaymentTransaction, which fails.
        PaymentTransactionDTO paymentTransactionDTO = paymentTransactionMapper.toDto(paymentTransaction);


        restPaymentTransactionMockMvc.perform(post("/api/payment-transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paymentTransactionDTO)))
            .andExpect(status().isBadRequest());

        List<PaymentTransaction> paymentTransactionList = paymentTransactionRepository.findAll();
        assertThat(paymentTransactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSourceAccountBankCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = paymentTransactionRepository.findAll().size();
        // set the field null
        paymentTransaction.setSourceAccountBankCode(null);

        // Create the PaymentTransaction, which fails.
        PaymentTransactionDTO paymentTransactionDTO = paymentTransactionMapper.toDto(paymentTransaction);


        restPaymentTransactionMockMvc.perform(post("/api/payment-transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paymentTransactionDTO)))
            .andExpect(status().isBadRequest());

        List<PaymentTransaction> paymentTransactionList = paymentTransactionRepository.findAll();
        assertThat(paymentTransactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSourceNarrationIsRequired() throws Exception {
        int databaseSizeBeforeTest = paymentTransactionRepository.findAll().size();
        // set the field null
        paymentTransaction.setSourceNarration(null);

        // Create the PaymentTransaction, which fails.
        PaymentTransactionDTO paymentTransactionDTO = paymentTransactionMapper.toDto(paymentTransaction);


        restPaymentTransactionMockMvc.perform(post("/api/payment-transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paymentTransactionDTO)))
            .andExpect(status().isBadRequest());

        List<PaymentTransaction> paymentTransactionList = paymentTransactionRepository.findAll();
        assertThat(paymentTransactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDestinationAccountIsRequired() throws Exception {
        int databaseSizeBeforeTest = paymentTransactionRepository.findAll().size();
        // set the field null
        paymentTransaction.setDestinationAccount(null);

        // Create the PaymentTransaction, which fails.
        PaymentTransactionDTO paymentTransactionDTO = paymentTransactionMapper.toDto(paymentTransaction);


        restPaymentTransactionMockMvc.perform(post("/api/payment-transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paymentTransactionDTO)))
            .andExpect(status().isBadRequest());

        List<PaymentTransaction> paymentTransactionList = paymentTransactionRepository.findAll();
        assertThat(paymentTransactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDestinationAccountBankCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = paymentTransactionRepository.findAll().size();
        // set the field null
        paymentTransaction.setDestinationAccountBankCode(null);

        // Create the PaymentTransaction, which fails.
        PaymentTransactionDTO paymentTransactionDTO = paymentTransactionMapper.toDto(paymentTransaction);


        restPaymentTransactionMockMvc.perform(post("/api/payment-transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paymentTransactionDTO)))
            .andExpect(status().isBadRequest());

        List<PaymentTransaction> paymentTransactionList = paymentTransactionRepository.findAll();
        assertThat(paymentTransactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDestinationNarrationIsRequired() throws Exception {
        int databaseSizeBeforeTest = paymentTransactionRepository.findAll().size();
        // set the field null
        paymentTransaction.setDestinationNarration(null);

        // Create the PaymentTransaction, which fails.
        PaymentTransactionDTO paymentTransactionDTO = paymentTransactionMapper.toDto(paymentTransaction);


        restPaymentTransactionMockMvc.perform(post("/api/payment-transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paymentTransactionDTO)))
            .andExpect(status().isBadRequest());

        List<PaymentTransaction> paymentTransactionList = paymentTransactionRepository.findAll();
        assertThat(paymentTransactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPaymentTransactions() throws Exception {
        // Initialize the database
        paymentTransactionRepository.saveAndFlush(paymentTransaction);

        // Get all the paymentTransactionList
        restPaymentTransactionMockMvc.perform(get("/api/payment-transactions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(paymentTransaction.getId().intValue())))
            .andExpect(jsonPath("$.[*].paymenttransID").value(hasItem(DEFAULT_PAYMENTTRANS_ID.intValue())))
            .andExpect(jsonPath("$.[*].transactionType").value(hasItem(DEFAULT_TRANSACTION_TYPE.toString())))
            .andExpect(jsonPath("$.[*].transactionRef").value(hasItem(DEFAULT_TRANSACTION_REF)))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].channel").value(hasItem(DEFAULT_CHANNEL)))
            .andExpect(jsonPath("$.[*].currency").value(hasItem(DEFAULT_CURRENCY)))
            .andExpect(jsonPath("$.[*].sourceAccount").value(hasItem(DEFAULT_SOURCE_ACCOUNT)))
            .andExpect(jsonPath("$.[*].sourceAccountBankCode").value(hasItem(DEFAULT_SOURCE_ACCOUNT_BANK_CODE)))
            .andExpect(jsonPath("$.[*].sourceAccountName").value(hasItem(DEFAULT_SOURCE_ACCOUNT_NAME)))
            .andExpect(jsonPath("$.[*].sourceNarration").value(hasItem(DEFAULT_SOURCE_NARRATION)))
            .andExpect(jsonPath("$.[*].destinationAccount").value(hasItem(DEFAULT_DESTINATION_ACCOUNT)))
            .andExpect(jsonPath("$.[*].destinationAccountBankCode").value(hasItem(DEFAULT_DESTINATION_ACCOUNT_BANK_CODE)))
            .andExpect(jsonPath("$.[*].destinationAccountName").value(hasItem(DEFAULT_DESTINATION_ACCOUNT_NAME)))
            .andExpect(jsonPath("$.[*].destinationNarration").value(hasItem(DEFAULT_DESTINATION_NARRATION)));
    }
    
    @Test
    @Transactional
    public void getPaymentTransaction() throws Exception {
        // Initialize the database
        paymentTransactionRepository.saveAndFlush(paymentTransaction);

        // Get the paymentTransaction
        restPaymentTransactionMockMvc.perform(get("/api/payment-transactions/{id}", paymentTransaction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(paymentTransaction.getId().intValue()))
            .andExpect(jsonPath("$.paymenttransID").value(DEFAULT_PAYMENTTRANS_ID.intValue()))
            .andExpect(jsonPath("$.transactionType").value(DEFAULT_TRANSACTION_TYPE.toString()))
            .andExpect(jsonPath("$.transactionRef").value(DEFAULT_TRANSACTION_REF))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.intValue()))
            .andExpect(jsonPath("$.channel").value(DEFAULT_CHANNEL))
            .andExpect(jsonPath("$.currency").value(DEFAULT_CURRENCY))
            .andExpect(jsonPath("$.sourceAccount").value(DEFAULT_SOURCE_ACCOUNT))
            .andExpect(jsonPath("$.sourceAccountBankCode").value(DEFAULT_SOURCE_ACCOUNT_BANK_CODE))
            .andExpect(jsonPath("$.sourceAccountName").value(DEFAULT_SOURCE_ACCOUNT_NAME))
            .andExpect(jsonPath("$.sourceNarration").value(DEFAULT_SOURCE_NARRATION))
            .andExpect(jsonPath("$.destinationAccount").value(DEFAULT_DESTINATION_ACCOUNT))
            .andExpect(jsonPath("$.destinationAccountBankCode").value(DEFAULT_DESTINATION_ACCOUNT_BANK_CODE))
            .andExpect(jsonPath("$.destinationAccountName").value(DEFAULT_DESTINATION_ACCOUNT_NAME))
            .andExpect(jsonPath("$.destinationNarration").value(DEFAULT_DESTINATION_NARRATION));
    }
    @Test
    @Transactional
    public void getNonExistingPaymentTransaction() throws Exception {
        // Get the paymentTransaction
        restPaymentTransactionMockMvc.perform(get("/api/payment-transactions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePaymentTransaction() throws Exception {
        // Initialize the database
        paymentTransactionRepository.saveAndFlush(paymentTransaction);

        int databaseSizeBeforeUpdate = paymentTransactionRepository.findAll().size();

        // Update the paymentTransaction
        PaymentTransaction updatedPaymentTransaction = paymentTransactionRepository.findById(paymentTransaction.getId()).get();
        // Disconnect from session so that the updates on updatedPaymentTransaction are not directly saved in db
        em.detach(updatedPaymentTransaction);
        updatedPaymentTransaction
            .paymenttransID(UPDATED_PAYMENTTRANS_ID)
            .transactionType(UPDATED_TRANSACTION_TYPE)
            .transactionRef(UPDATED_TRANSACTION_REF)
            .amount(UPDATED_AMOUNT)
            .channel(UPDATED_CHANNEL)
            .currency(UPDATED_CURRENCY)
            .sourceAccount(UPDATED_SOURCE_ACCOUNT)
            .sourceAccountBankCode(UPDATED_SOURCE_ACCOUNT_BANK_CODE)
            .sourceAccountName(UPDATED_SOURCE_ACCOUNT_NAME)
            .sourceNarration(UPDATED_SOURCE_NARRATION)
            .destinationAccount(UPDATED_DESTINATION_ACCOUNT)
            .destinationAccountBankCode(UPDATED_DESTINATION_ACCOUNT_BANK_CODE)
            .destinationAccountName(UPDATED_DESTINATION_ACCOUNT_NAME)
            .destinationNarration(UPDATED_DESTINATION_NARRATION);
        PaymentTransactionDTO paymentTransactionDTO = paymentTransactionMapper.toDto(updatedPaymentTransaction);

        restPaymentTransactionMockMvc.perform(put("/api/payment-transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paymentTransactionDTO)))
            .andExpect(status().isOk());

        // Validate the PaymentTransaction in the database
        List<PaymentTransaction> paymentTransactionList = paymentTransactionRepository.findAll();
        assertThat(paymentTransactionList).hasSize(databaseSizeBeforeUpdate);
        PaymentTransaction testPaymentTransaction = paymentTransactionList.get(paymentTransactionList.size() - 1);
        assertThat(testPaymentTransaction.getPaymenttransID()).isEqualTo(UPDATED_PAYMENTTRANS_ID);
        assertThat(testPaymentTransaction.getTransactionType()).isEqualTo(UPDATED_TRANSACTION_TYPE);
        assertThat(testPaymentTransaction.getTransactionRef()).isEqualTo(UPDATED_TRANSACTION_REF);
        assertThat(testPaymentTransaction.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testPaymentTransaction.getChannel()).isEqualTo(UPDATED_CHANNEL);
        assertThat(testPaymentTransaction.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testPaymentTransaction.getSourceAccount()).isEqualTo(UPDATED_SOURCE_ACCOUNT);
        assertThat(testPaymentTransaction.getSourceAccountBankCode()).isEqualTo(UPDATED_SOURCE_ACCOUNT_BANK_CODE);
        assertThat(testPaymentTransaction.getSourceAccountName()).isEqualTo(UPDATED_SOURCE_ACCOUNT_NAME);
        assertThat(testPaymentTransaction.getSourceNarration()).isEqualTo(UPDATED_SOURCE_NARRATION);
        assertThat(testPaymentTransaction.getDestinationAccount()).isEqualTo(UPDATED_DESTINATION_ACCOUNT);
        assertThat(testPaymentTransaction.getDestinationAccountBankCode()).isEqualTo(UPDATED_DESTINATION_ACCOUNT_BANK_CODE);
        assertThat(testPaymentTransaction.getDestinationAccountName()).isEqualTo(UPDATED_DESTINATION_ACCOUNT_NAME);
        assertThat(testPaymentTransaction.getDestinationNarration()).isEqualTo(UPDATED_DESTINATION_NARRATION);
    }

    @Test
    @Transactional
    public void updateNonExistingPaymentTransaction() throws Exception {
        int databaseSizeBeforeUpdate = paymentTransactionRepository.findAll().size();

        // Create the PaymentTransaction
        PaymentTransactionDTO paymentTransactionDTO = paymentTransactionMapper.toDto(paymentTransaction);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPaymentTransactionMockMvc.perform(put("/api/payment-transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paymentTransactionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PaymentTransaction in the database
        List<PaymentTransaction> paymentTransactionList = paymentTransactionRepository.findAll();
        assertThat(paymentTransactionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePaymentTransaction() throws Exception {
        // Initialize the database
        paymentTransactionRepository.saveAndFlush(paymentTransaction);

        int databaseSizeBeforeDelete = paymentTransactionRepository.findAll().size();

        // Delete the paymentTransaction
        restPaymentTransactionMockMvc.perform(delete("/api/payment-transactions/{id}", paymentTransaction.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PaymentTransaction> paymentTransactionList = paymentTransactionRepository.findAll();
        assertThat(paymentTransactionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

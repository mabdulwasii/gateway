package ng.com.systemspecs.apigateway.web.rest;

import ng.com.systemspecs.apigateway.ApiGatewayApp;
import ng.com.systemspecs.apigateway.domain.WalletAccount;
import ng.com.systemspecs.apigateway.repository.WalletAccountRepository;
import ng.com.systemspecs.apigateway.service.WalletAccountService;
import ng.com.systemspecs.apigateway.service.dto.WalletAccountDTO;
import ng.com.systemspecs.apigateway.service.mapper.WalletAccountMapper;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link WalletAccountResource} REST controller.
 */
@SpringBootTest(classes = ApiGatewayApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class WalletAccountResourceIT {

    private static final Long DEFAULT_ACCOUNT_NUMBER = 1L;
    private static final Long UPDATED_ACCOUNT_NUMBER = 2L;

    private static final Double DEFAULT_CURRENT_BALANCE = 1D;
    private static final Double UPDATED_CURRENT_BALANCE = 2D;

    private static final LocalDate DEFAULT_DATE_OPENED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_OPENED = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private WalletAccountRepository walletAccountRepository;

    @Autowired
    private WalletAccountMapper walletAccountMapper;

    @Autowired
    private WalletAccountService walletAccountService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWalletAccountMockMvc;

    private WalletAccount walletAccount;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WalletAccount createEntity(EntityManager em) {
        WalletAccount walletAccount = new WalletAccount()
            .accountNumber(DEFAULT_ACCOUNT_NUMBER)
            .currentBalance(DEFAULT_CURRENT_BALANCE)
            .dateOpened(DEFAULT_DATE_OPENED);
        return walletAccount;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WalletAccount createUpdatedEntity(EntityManager em) {
        WalletAccount walletAccount = new WalletAccount()
            .accountNumber(UPDATED_ACCOUNT_NUMBER)
            .currentBalance(UPDATED_CURRENT_BALANCE)
            .dateOpened(UPDATED_DATE_OPENED);
        return walletAccount;
    }

    @BeforeEach
    public void initTest() {
        walletAccount = createEntity(em);
    }

    @Test
    @Transactional
    public void createWalletAccount() throws Exception {
        int databaseSizeBeforeCreate = walletAccountRepository.findAll().size();
        // Create the WalletAccount
        WalletAccountDTO walletAccountDTO = walletAccountMapper.toDto(walletAccount);
        restWalletAccountMockMvc.perform(post("/api/wallet-accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(walletAccountDTO)))
            .andExpect(status().isCreated());

        // Validate the WalletAccount in the database
        List<WalletAccount> walletAccountList = walletAccountRepository.findAll();
        assertThat(walletAccountList).hasSize(databaseSizeBeforeCreate + 1);
        WalletAccount testWalletAccount = walletAccountList.get(walletAccountList.size() - 1);
        assertThat(testWalletAccount.getAccountNumber()).isEqualTo(DEFAULT_ACCOUNT_NUMBER);
        assertThat(testWalletAccount.getCurrentBalance()).isEqualTo(DEFAULT_CURRENT_BALANCE);
        assertThat(testWalletAccount.getDateOpened()).isEqualTo(DEFAULT_DATE_OPENED);
    }

    @Test
    @Transactional
    public void createWalletAccountWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = walletAccountRepository.findAll().size();

        // Create the WalletAccount with an existing ID
        walletAccount.setId(1L);
        WalletAccountDTO walletAccountDTO = walletAccountMapper.toDto(walletAccount);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWalletAccountMockMvc.perform(post("/api/wallet-accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(walletAccountDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WalletAccount in the database
        List<WalletAccount> walletAccountList = walletAccountRepository.findAll();
        assertThat(walletAccountList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllWalletAccounts() throws Exception {
        // Initialize the database
        walletAccountRepository.saveAndFlush(walletAccount);

        // Get all the walletAccountList
        restWalletAccountMockMvc.perform(get("/api/wallet-accounts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(walletAccount.getId().intValue())))
            .andExpect(jsonPath("$.[*].accountNumber").value(hasItem(DEFAULT_ACCOUNT_NUMBER.intValue())))
            .andExpect(jsonPath("$.[*].currentBalance").value(hasItem(DEFAULT_CURRENT_BALANCE.doubleValue())))
            .andExpect(jsonPath("$.[*].dateOpened").value(hasItem(DEFAULT_DATE_OPENED.toString())));
    }
    
    @Test
    @Transactional
    public void getWalletAccount() throws Exception {
        // Initialize the database
        walletAccountRepository.saveAndFlush(walletAccount);

        // Get the walletAccount
        restWalletAccountMockMvc.perform(get("/api/wallet-accounts/{id}", walletAccount.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(walletAccount.getId().intValue()))
            .andExpect(jsonPath("$.accountNumber").value(DEFAULT_ACCOUNT_NUMBER.intValue()))
            .andExpect(jsonPath("$.currentBalance").value(DEFAULT_CURRENT_BALANCE.doubleValue()))
            .andExpect(jsonPath("$.dateOpened").value(DEFAULT_DATE_OPENED.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingWalletAccount() throws Exception {
        // Get the walletAccount
        restWalletAccountMockMvc.perform(get("/api/wallet-accounts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWalletAccount() throws Exception {
        // Initialize the database
        walletAccountRepository.saveAndFlush(walletAccount);

        int databaseSizeBeforeUpdate = walletAccountRepository.findAll().size();

        // Update the walletAccount
        WalletAccount updatedWalletAccount = walletAccountRepository.findById(walletAccount.getId()).get();
        // Disconnect from session so that the updates on updatedWalletAccount are not directly saved in db
        em.detach(updatedWalletAccount);
        updatedWalletAccount
            .accountNumber(UPDATED_ACCOUNT_NUMBER)
            .currentBalance(UPDATED_CURRENT_BALANCE)
            .dateOpened(UPDATED_DATE_OPENED);
        WalletAccountDTO walletAccountDTO = walletAccountMapper.toDto(updatedWalletAccount);

        restWalletAccountMockMvc.perform(put("/api/wallet-accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(walletAccountDTO)))
            .andExpect(status().isOk());

        // Validate the WalletAccount in the database
        List<WalletAccount> walletAccountList = walletAccountRepository.findAll();
        assertThat(walletAccountList).hasSize(databaseSizeBeforeUpdate);
        WalletAccount testWalletAccount = walletAccountList.get(walletAccountList.size() - 1);
        assertThat(testWalletAccount.getAccountNumber()).isEqualTo(UPDATED_ACCOUNT_NUMBER);
        assertThat(testWalletAccount.getCurrentBalance()).isEqualTo(UPDATED_CURRENT_BALANCE);
        assertThat(testWalletAccount.getDateOpened()).isEqualTo(UPDATED_DATE_OPENED);
    }

    @Test
    @Transactional
    public void updateNonExistingWalletAccount() throws Exception {
        int databaseSizeBeforeUpdate = walletAccountRepository.findAll().size();

        // Create the WalletAccount
        WalletAccountDTO walletAccountDTO = walletAccountMapper.toDto(walletAccount);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWalletAccountMockMvc.perform(put("/api/wallet-accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(walletAccountDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WalletAccount in the database
        List<WalletAccount> walletAccountList = walletAccountRepository.findAll();
        assertThat(walletAccountList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWalletAccount() throws Exception {
        // Initialize the database
        walletAccountRepository.saveAndFlush(walletAccount);

        int databaseSizeBeforeDelete = walletAccountRepository.findAll().size();

        // Delete the walletAccount
        restWalletAccountMockMvc.perform(delete("/api/wallet-accounts/{id}", walletAccount.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WalletAccount> walletAccountList = walletAccountRepository.findAll();
        assertThat(walletAccountList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

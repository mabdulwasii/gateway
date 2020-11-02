package ng.com.systemspecs.apigateway.web.rest;

import ng.com.systemspecs.apigateway.ApiGatewayApp;
import ng.com.systemspecs.apigateway.domain.WalletAccountType;
import ng.com.systemspecs.apigateway.repository.WalletAccountTypeRepository;
import ng.com.systemspecs.apigateway.service.WalletAccountTypeService;
import ng.com.systemspecs.apigateway.service.dto.WalletAccountTypeDTO;
import ng.com.systemspecs.apigateway.service.mapper.WalletAccountTypeMapper;

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
 * Integration tests for the {@link WalletAccountTypeResource} REST controller.
 */
@SpringBootTest(classes = ApiGatewayApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class WalletAccountTypeResourceIT {

    private static final Long DEFAULT_ACCOUNTYPE_ID = 1L;
    private static final Long UPDATED_ACCOUNTYPE_ID = 2L;

    private static final String DEFAULT_WALLET_ACCOUNT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_WALLET_ACCOUNT_TYPE = "BBBBBBBBBB";

    @Autowired
    private WalletAccountTypeRepository walletAccountTypeRepository;

    @Autowired
    private WalletAccountTypeMapper walletAccountTypeMapper;

    @Autowired
    private WalletAccountTypeService walletAccountTypeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWalletAccountTypeMockMvc;

    private WalletAccountType walletAccountType;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WalletAccountType createEntity(EntityManager em) {
        WalletAccountType walletAccountType = new WalletAccountType()
            .accountypeID(DEFAULT_ACCOUNTYPE_ID)
            .walletAccountType(DEFAULT_WALLET_ACCOUNT_TYPE);
        return walletAccountType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WalletAccountType createUpdatedEntity(EntityManager em) {
        WalletAccountType walletAccountType = new WalletAccountType()
            .accountypeID(UPDATED_ACCOUNTYPE_ID)
            .walletAccountType(UPDATED_WALLET_ACCOUNT_TYPE);
        return walletAccountType;
    }

    @BeforeEach
    public void initTest() {
        walletAccountType = createEntity(em);
    }

    @Test
    @Transactional
    public void createWalletAccountType() throws Exception {
        int databaseSizeBeforeCreate = walletAccountTypeRepository.findAll().size();
        // Create the WalletAccountType
        WalletAccountTypeDTO walletAccountTypeDTO = walletAccountTypeMapper.toDto(walletAccountType);
        restWalletAccountTypeMockMvc.perform(post("/api/wallet-account-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(walletAccountTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the WalletAccountType in the database
        List<WalletAccountType> walletAccountTypeList = walletAccountTypeRepository.findAll();
        assertThat(walletAccountTypeList).hasSize(databaseSizeBeforeCreate + 1);
        WalletAccountType testWalletAccountType = walletAccountTypeList.get(walletAccountTypeList.size() - 1);
        assertThat(testWalletAccountType.getAccountypeID()).isEqualTo(DEFAULT_ACCOUNTYPE_ID);
        assertThat(testWalletAccountType.getWalletAccountType()).isEqualTo(DEFAULT_WALLET_ACCOUNT_TYPE);
    }

    @Test
    @Transactional
    public void createWalletAccountTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = walletAccountTypeRepository.findAll().size();

        // Create the WalletAccountType with an existing ID
        walletAccountType.setId(1L);
        WalletAccountTypeDTO walletAccountTypeDTO = walletAccountTypeMapper.toDto(walletAccountType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWalletAccountTypeMockMvc.perform(post("/api/wallet-account-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(walletAccountTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WalletAccountType in the database
        List<WalletAccountType> walletAccountTypeList = walletAccountTypeRepository.findAll();
        assertThat(walletAccountTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllWalletAccountTypes() throws Exception {
        // Initialize the database
        walletAccountTypeRepository.saveAndFlush(walletAccountType);

        // Get all the walletAccountTypeList
        restWalletAccountTypeMockMvc.perform(get("/api/wallet-account-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(walletAccountType.getId().intValue())))
            .andExpect(jsonPath("$.[*].accountypeID").value(hasItem(DEFAULT_ACCOUNTYPE_ID.intValue())))
            .andExpect(jsonPath("$.[*].walletAccountType").value(hasItem(DEFAULT_WALLET_ACCOUNT_TYPE)));
    }
    
    @Test
    @Transactional
    public void getWalletAccountType() throws Exception {
        // Initialize the database
        walletAccountTypeRepository.saveAndFlush(walletAccountType);

        // Get the walletAccountType
        restWalletAccountTypeMockMvc.perform(get("/api/wallet-account-types/{id}", walletAccountType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(walletAccountType.getId().intValue()))
            .andExpect(jsonPath("$.accountypeID").value(DEFAULT_ACCOUNTYPE_ID.intValue()))
            .andExpect(jsonPath("$.walletAccountType").value(DEFAULT_WALLET_ACCOUNT_TYPE));
    }
    @Test
    @Transactional
    public void getNonExistingWalletAccountType() throws Exception {
        // Get the walletAccountType
        restWalletAccountTypeMockMvc.perform(get("/api/wallet-account-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWalletAccountType() throws Exception {
        // Initialize the database
        walletAccountTypeRepository.saveAndFlush(walletAccountType);

        int databaseSizeBeforeUpdate = walletAccountTypeRepository.findAll().size();

        // Update the walletAccountType
        WalletAccountType updatedWalletAccountType = walletAccountTypeRepository.findById(walletAccountType.getId()).get();
        // Disconnect from session so that the updates on updatedWalletAccountType are not directly saved in db
        em.detach(updatedWalletAccountType);
        updatedWalletAccountType
            .accountypeID(UPDATED_ACCOUNTYPE_ID)
            .walletAccountType(UPDATED_WALLET_ACCOUNT_TYPE);
        WalletAccountTypeDTO walletAccountTypeDTO = walletAccountTypeMapper.toDto(updatedWalletAccountType);

        restWalletAccountTypeMockMvc.perform(put("/api/wallet-account-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(walletAccountTypeDTO)))
            .andExpect(status().isOk());

        // Validate the WalletAccountType in the database
        List<WalletAccountType> walletAccountTypeList = walletAccountTypeRepository.findAll();
        assertThat(walletAccountTypeList).hasSize(databaseSizeBeforeUpdate);
        WalletAccountType testWalletAccountType = walletAccountTypeList.get(walletAccountTypeList.size() - 1);
        assertThat(testWalletAccountType.getAccountypeID()).isEqualTo(UPDATED_ACCOUNTYPE_ID);
        assertThat(testWalletAccountType.getWalletAccountType()).isEqualTo(UPDATED_WALLET_ACCOUNT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingWalletAccountType() throws Exception {
        int databaseSizeBeforeUpdate = walletAccountTypeRepository.findAll().size();

        // Create the WalletAccountType
        WalletAccountTypeDTO walletAccountTypeDTO = walletAccountTypeMapper.toDto(walletAccountType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWalletAccountTypeMockMvc.perform(put("/api/wallet-account-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(walletAccountTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WalletAccountType in the database
        List<WalletAccountType> walletAccountTypeList = walletAccountTypeRepository.findAll();
        assertThat(walletAccountTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWalletAccountType() throws Exception {
        // Initialize the database
        walletAccountTypeRepository.saveAndFlush(walletAccountType);

        int databaseSizeBeforeDelete = walletAccountTypeRepository.findAll().size();

        // Delete the walletAccountType
        restWalletAccountTypeMockMvc.perform(delete("/api/wallet-account-types/{id}", walletAccountType.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WalletAccountType> walletAccountTypeList = walletAccountTypeRepository.findAll();
        assertThat(walletAccountTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

package ng.com.systemspecs.apigateway.web.rest;

import ng.com.systemspecs.apigateway.ApiGatewayApp;
import ng.com.systemspecs.apigateway.domain.Kyclevel;
import ng.com.systemspecs.apigateway.repository.KyclevelRepository;
import ng.com.systemspecs.apigateway.service.KyclevelService;
import ng.com.systemspecs.apigateway.service.dto.KyclevelDTO;
import ng.com.systemspecs.apigateway.service.mapper.KyclevelMapper;

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
 * Integration tests for the {@link KyclevelResource} REST controller.
 */
@SpringBootTest(classes = ApiGatewayApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class KyclevelResourceIT {

    private static final Long DEFAULT_KYC_ID = 1L;
    private static final Long UPDATED_KYC_ID = 2L;

    private static final String DEFAULT_KYC = "AAAAAAAAAA";
    private static final String UPDATED_KYC = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_KYC_LEVEL = 1;
    private static final Integer UPDATED_KYC_LEVEL = 2;

    private static final Boolean DEFAULT_PHONE_NUMBER = false;
    private static final Boolean UPDATED_PHONE_NUMBER = true;

    private static final Boolean DEFAULT_EMAIL_ADDRESS = false;
    private static final Boolean UPDATED_EMAIL_ADDRESS = true;

    private static final Boolean DEFAULT_FIRST_NAME = false;
    private static final Boolean UPDATED_FIRST_NAME = true;

    private static final Boolean DEFAULT_LAST_NAME = false;
    private static final Boolean UPDATED_LAST_NAME = true;

    private static final Boolean DEFAULT_GENDER = false;
    private static final Boolean UPDATED_GENDER = true;

    private static final Boolean DEFAULT_DATEOF_BIRTH = false;
    private static final Boolean UPDATED_DATEOF_BIRTH = true;

    private static final Boolean DEFAULT_ADDRESS = false;
    private static final Boolean UPDATED_ADDRESS = true;

    private static final Boolean DEFAULT_PHOTO_UPLOAD = false;
    private static final Boolean UPDATED_PHOTO_UPLOAD = true;

    private static final Boolean DEFAULT_VERIFIED_BVN = false;
    private static final Boolean UPDATED_VERIFIED_BVN = true;

    private static final Boolean DEFAULT_VERIFIED_VALID_ID = false;
    private static final Boolean UPDATED_VERIFIED_VALID_ID = true;

    private static final Boolean DEFAULT_EVIDENCEOF_ADDRESS = false;
    private static final Boolean UPDATED_EVIDENCEOF_ADDRESS = true;

    private static final Boolean DEFAULT_VERIFICATIONOF_ADDRESS = false;
    private static final Boolean UPDATED_VERIFICATIONOF_ADDRESS = true;

    private static final Boolean DEFAULT_EMPLOYMENT_DETAILS = false;
    private static final Boolean UPDATED_EMPLOYMENT_DETAILS = true;

    private static final Double DEFAULT_DAILY_TRANSACTION_LIMIT = 1D;
    private static final Double UPDATED_DAILY_TRANSACTION_LIMIT = 2D;

    private static final Double DEFAULT_CUMULATIVE_BALANCE_LIMIT = 1D;
    private static final Double UPDATED_CUMULATIVE_BALANCE_LIMIT = 2D;

    private static final Boolean DEFAULT_PAYMENT_TRANSACTION = false;
    private static final Boolean UPDATED_PAYMENT_TRANSACTION = true;

    private static final Boolean DEFAULT_BILLER_TRANSACTION = false;
    private static final Boolean UPDATED_BILLER_TRANSACTION = true;

    @Autowired
    private KyclevelRepository kyclevelRepository;

    @Autowired
    private KyclevelMapper kyclevelMapper;

    @Autowired
    private KyclevelService kyclevelService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restKyclevelMockMvc;

    private Kyclevel kyclevel;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Kyclevel createEntity(EntityManager em) {
        Kyclevel kyclevel = new Kyclevel()
            .kycID(DEFAULT_KYC_ID)
            .kyc(DEFAULT_KYC)
            .description(DEFAULT_DESCRIPTION)
            .kycLevel(DEFAULT_KYC_LEVEL)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .emailAddress(DEFAULT_EMAIL_ADDRESS)
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .gender(DEFAULT_GENDER)
            .dateofBirth(DEFAULT_DATEOF_BIRTH)
            .address(DEFAULT_ADDRESS)
            .photoUpload(DEFAULT_PHOTO_UPLOAD)
            .verifiedBVN(DEFAULT_VERIFIED_BVN)
            .verifiedValidID(DEFAULT_VERIFIED_VALID_ID)
            .evidenceofAddress(DEFAULT_EVIDENCEOF_ADDRESS)
            .verificationofAddress(DEFAULT_VERIFICATIONOF_ADDRESS)
            .employmentDetails(DEFAULT_EMPLOYMENT_DETAILS)
            .dailyTransactionLimit(DEFAULT_DAILY_TRANSACTION_LIMIT)
            .cumulativeBalanceLimit(DEFAULT_CUMULATIVE_BALANCE_LIMIT)
            .paymentTransaction(DEFAULT_PAYMENT_TRANSACTION)
            .billerTransaction(DEFAULT_BILLER_TRANSACTION);
        return kyclevel;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Kyclevel createUpdatedEntity(EntityManager em) {
        Kyclevel kyclevel = new Kyclevel()
            .kycID(UPDATED_KYC_ID)
            .kyc(UPDATED_KYC)
            .description(UPDATED_DESCRIPTION)
            .kycLevel(UPDATED_KYC_LEVEL)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .emailAddress(UPDATED_EMAIL_ADDRESS)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .gender(UPDATED_GENDER)
            .dateofBirth(UPDATED_DATEOF_BIRTH)
            .address(UPDATED_ADDRESS)
            .photoUpload(UPDATED_PHOTO_UPLOAD)
            .verifiedBVN(UPDATED_VERIFIED_BVN)
            .verifiedValidID(UPDATED_VERIFIED_VALID_ID)
            .evidenceofAddress(UPDATED_EVIDENCEOF_ADDRESS)
            .verificationofAddress(UPDATED_VERIFICATIONOF_ADDRESS)
            .employmentDetails(UPDATED_EMPLOYMENT_DETAILS)
            .dailyTransactionLimit(UPDATED_DAILY_TRANSACTION_LIMIT)
            .cumulativeBalanceLimit(UPDATED_CUMULATIVE_BALANCE_LIMIT)
            .paymentTransaction(UPDATED_PAYMENT_TRANSACTION)
            .billerTransaction(UPDATED_BILLER_TRANSACTION);
        return kyclevel;
    }

    @BeforeEach
    public void initTest() {
        kyclevel = createEntity(em);
    }

    @Test
    @Transactional
    public void createKyclevel() throws Exception {
        int databaseSizeBeforeCreate = kyclevelRepository.findAll().size();
        // Create the Kyclevel
        KyclevelDTO kyclevelDTO = kyclevelMapper.toDto(kyclevel);
        restKyclevelMockMvc.perform(post("/api/kyclevels")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(kyclevelDTO)))
            .andExpect(status().isCreated());

        // Validate the Kyclevel in the database
        List<Kyclevel> kyclevelList = kyclevelRepository.findAll();
        assertThat(kyclevelList).hasSize(databaseSizeBeforeCreate + 1);
        Kyclevel testKyclevel = kyclevelList.get(kyclevelList.size() - 1);
        assertThat(testKyclevel.getKycID()).isEqualTo(DEFAULT_KYC_ID);
        assertThat(testKyclevel.getKyc()).isEqualTo(DEFAULT_KYC);
        assertThat(testKyclevel.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testKyclevel.getKycLevel()).isEqualTo(DEFAULT_KYC_LEVEL);
        assertThat(testKyclevel.isPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testKyclevel.isEmailAddress()).isEqualTo(DEFAULT_EMAIL_ADDRESS);
        assertThat(testKyclevel.isFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testKyclevel.isLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testKyclevel.isGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testKyclevel.isDateofBirth()).isEqualTo(DEFAULT_DATEOF_BIRTH);
        assertThat(testKyclevel.isAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testKyclevel.isPhotoUpload()).isEqualTo(DEFAULT_PHOTO_UPLOAD);
        assertThat(testKyclevel.isVerifiedBVN()).isEqualTo(DEFAULT_VERIFIED_BVN);
        assertThat(testKyclevel.isVerifiedValidID()).isEqualTo(DEFAULT_VERIFIED_VALID_ID);
        assertThat(testKyclevel.isEvidenceofAddress()).isEqualTo(DEFAULT_EVIDENCEOF_ADDRESS);
        assertThat(testKyclevel.isVerificationofAddress()).isEqualTo(DEFAULT_VERIFICATIONOF_ADDRESS);
        assertThat(testKyclevel.isEmploymentDetails()).isEqualTo(DEFAULT_EMPLOYMENT_DETAILS);
        assertThat(testKyclevel.getDailyTransactionLimit()).isEqualTo(DEFAULT_DAILY_TRANSACTION_LIMIT);
        assertThat(testKyclevel.getCumulativeBalanceLimit()).isEqualTo(DEFAULT_CUMULATIVE_BALANCE_LIMIT);
        assertThat(testKyclevel.isPaymentTransaction()).isEqualTo(DEFAULT_PAYMENT_TRANSACTION);
        assertThat(testKyclevel.isBillerTransaction()).isEqualTo(DEFAULT_BILLER_TRANSACTION);
    }

    @Test
    @Transactional
    public void createKyclevelWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = kyclevelRepository.findAll().size();

        // Create the Kyclevel with an existing ID
        kyclevel.setId(1L);
        KyclevelDTO kyclevelDTO = kyclevelMapper.toDto(kyclevel);

        // An entity with an existing ID cannot be created, so this API call must fail
        restKyclevelMockMvc.perform(post("/api/kyclevels")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(kyclevelDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Kyclevel in the database
        List<Kyclevel> kyclevelList = kyclevelRepository.findAll();
        assertThat(kyclevelList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllKyclevels() throws Exception {
        // Initialize the database
        kyclevelRepository.saveAndFlush(kyclevel);

        // Get all the kyclevelList
        restKyclevelMockMvc.perform(get("/api/kyclevels?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(kyclevel.getId().intValue())))
            .andExpect(jsonPath("$.[*].kycID").value(hasItem(DEFAULT_KYC_ID.intValue())))
            .andExpect(jsonPath("$.[*].kyc").value(hasItem(DEFAULT_KYC)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].kycLevel").value(hasItem(DEFAULT_KYC_LEVEL)))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER.booleanValue())))
            .andExpect(jsonPath("$.[*].emailAddress").value(hasItem(DEFAULT_EMAIL_ADDRESS.booleanValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME.booleanValue())))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME.booleanValue())))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.booleanValue())))
            .andExpect(jsonPath("$.[*].dateofBirth").value(hasItem(DEFAULT_DATEOF_BIRTH.booleanValue())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.booleanValue())))
            .andExpect(jsonPath("$.[*].photoUpload").value(hasItem(DEFAULT_PHOTO_UPLOAD.booleanValue())))
            .andExpect(jsonPath("$.[*].verifiedBVN").value(hasItem(DEFAULT_VERIFIED_BVN.booleanValue())))
            .andExpect(jsonPath("$.[*].verifiedValidID").value(hasItem(DEFAULT_VERIFIED_VALID_ID.booleanValue())))
            .andExpect(jsonPath("$.[*].evidenceofAddress").value(hasItem(DEFAULT_EVIDENCEOF_ADDRESS.booleanValue())))
            .andExpect(jsonPath("$.[*].verificationofAddress").value(hasItem(DEFAULT_VERIFICATIONOF_ADDRESS.booleanValue())))
            .andExpect(jsonPath("$.[*].employmentDetails").value(hasItem(DEFAULT_EMPLOYMENT_DETAILS.booleanValue())))
            .andExpect(jsonPath("$.[*].dailyTransactionLimit").value(hasItem(DEFAULT_DAILY_TRANSACTION_LIMIT.doubleValue())))
            .andExpect(jsonPath("$.[*].cumulativeBalanceLimit").value(hasItem(DEFAULT_CUMULATIVE_BALANCE_LIMIT.doubleValue())))
            .andExpect(jsonPath("$.[*].paymentTransaction").value(hasItem(DEFAULT_PAYMENT_TRANSACTION.booleanValue())))
            .andExpect(jsonPath("$.[*].billerTransaction").value(hasItem(DEFAULT_BILLER_TRANSACTION.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getKyclevel() throws Exception {
        // Initialize the database
        kyclevelRepository.saveAndFlush(kyclevel);

        // Get the kyclevel
        restKyclevelMockMvc.perform(get("/api/kyclevels/{id}", kyclevel.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(kyclevel.getId().intValue()))
            .andExpect(jsonPath("$.kycID").value(DEFAULT_KYC_ID.intValue()))
            .andExpect(jsonPath("$.kyc").value(DEFAULT_KYC))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.kycLevel").value(DEFAULT_KYC_LEVEL))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER.booleanValue()))
            .andExpect(jsonPath("$.emailAddress").value(DEFAULT_EMAIL_ADDRESS.booleanValue()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME.booleanValue()))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME.booleanValue()))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.booleanValue()))
            .andExpect(jsonPath("$.dateofBirth").value(DEFAULT_DATEOF_BIRTH.booleanValue()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.booleanValue()))
            .andExpect(jsonPath("$.photoUpload").value(DEFAULT_PHOTO_UPLOAD.booleanValue()))
            .andExpect(jsonPath("$.verifiedBVN").value(DEFAULT_VERIFIED_BVN.booleanValue()))
            .andExpect(jsonPath("$.verifiedValidID").value(DEFAULT_VERIFIED_VALID_ID.booleanValue()))
            .andExpect(jsonPath("$.evidenceofAddress").value(DEFAULT_EVIDENCEOF_ADDRESS.booleanValue()))
            .andExpect(jsonPath("$.verificationofAddress").value(DEFAULT_VERIFICATIONOF_ADDRESS.booleanValue()))
            .andExpect(jsonPath("$.employmentDetails").value(DEFAULT_EMPLOYMENT_DETAILS.booleanValue()))
            .andExpect(jsonPath("$.dailyTransactionLimit").value(DEFAULT_DAILY_TRANSACTION_LIMIT.doubleValue()))
            .andExpect(jsonPath("$.cumulativeBalanceLimit").value(DEFAULT_CUMULATIVE_BALANCE_LIMIT.doubleValue()))
            .andExpect(jsonPath("$.paymentTransaction").value(DEFAULT_PAYMENT_TRANSACTION.booleanValue()))
            .andExpect(jsonPath("$.billerTransaction").value(DEFAULT_BILLER_TRANSACTION.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingKyclevel() throws Exception {
        // Get the kyclevel
        restKyclevelMockMvc.perform(get("/api/kyclevels/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateKyclevel() throws Exception {
        // Initialize the database
        kyclevelRepository.saveAndFlush(kyclevel);

        int databaseSizeBeforeUpdate = kyclevelRepository.findAll().size();

        // Update the kyclevel
        Kyclevel updatedKyclevel = kyclevelRepository.findById(kyclevel.getId()).get();
        // Disconnect from session so that the updates on updatedKyclevel are not directly saved in db
        em.detach(updatedKyclevel);
        updatedKyclevel
            .kycID(UPDATED_KYC_ID)
            .kyc(UPDATED_KYC)
            .description(UPDATED_DESCRIPTION)
            .kycLevel(UPDATED_KYC_LEVEL)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .emailAddress(UPDATED_EMAIL_ADDRESS)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .gender(UPDATED_GENDER)
            .dateofBirth(UPDATED_DATEOF_BIRTH)
            .address(UPDATED_ADDRESS)
            .photoUpload(UPDATED_PHOTO_UPLOAD)
            .verifiedBVN(UPDATED_VERIFIED_BVN)
            .verifiedValidID(UPDATED_VERIFIED_VALID_ID)
            .evidenceofAddress(UPDATED_EVIDENCEOF_ADDRESS)
            .verificationofAddress(UPDATED_VERIFICATIONOF_ADDRESS)
            .employmentDetails(UPDATED_EMPLOYMENT_DETAILS)
            .dailyTransactionLimit(UPDATED_DAILY_TRANSACTION_LIMIT)
            .cumulativeBalanceLimit(UPDATED_CUMULATIVE_BALANCE_LIMIT)
            .paymentTransaction(UPDATED_PAYMENT_TRANSACTION)
            .billerTransaction(UPDATED_BILLER_TRANSACTION);
        KyclevelDTO kyclevelDTO = kyclevelMapper.toDto(updatedKyclevel);

        restKyclevelMockMvc.perform(put("/api/kyclevels")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(kyclevelDTO)))
            .andExpect(status().isOk());

        // Validate the Kyclevel in the database
        List<Kyclevel> kyclevelList = kyclevelRepository.findAll();
        assertThat(kyclevelList).hasSize(databaseSizeBeforeUpdate);
        Kyclevel testKyclevel = kyclevelList.get(kyclevelList.size() - 1);
        assertThat(testKyclevel.getKycID()).isEqualTo(UPDATED_KYC_ID);
        assertThat(testKyclevel.getKyc()).isEqualTo(UPDATED_KYC);
        assertThat(testKyclevel.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testKyclevel.getKycLevel()).isEqualTo(UPDATED_KYC_LEVEL);
        assertThat(testKyclevel.isPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testKyclevel.isEmailAddress()).isEqualTo(UPDATED_EMAIL_ADDRESS);
        assertThat(testKyclevel.isFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testKyclevel.isLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testKyclevel.isGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testKyclevel.isDateofBirth()).isEqualTo(UPDATED_DATEOF_BIRTH);
        assertThat(testKyclevel.isAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testKyclevel.isPhotoUpload()).isEqualTo(UPDATED_PHOTO_UPLOAD);
        assertThat(testKyclevel.isVerifiedBVN()).isEqualTo(UPDATED_VERIFIED_BVN);
        assertThat(testKyclevel.isVerifiedValidID()).isEqualTo(UPDATED_VERIFIED_VALID_ID);
        assertThat(testKyclevel.isEvidenceofAddress()).isEqualTo(UPDATED_EVIDENCEOF_ADDRESS);
        assertThat(testKyclevel.isVerificationofAddress()).isEqualTo(UPDATED_VERIFICATIONOF_ADDRESS);
        assertThat(testKyclevel.isEmploymentDetails()).isEqualTo(UPDATED_EMPLOYMENT_DETAILS);
        assertThat(testKyclevel.getDailyTransactionLimit()).isEqualTo(UPDATED_DAILY_TRANSACTION_LIMIT);
        assertThat(testKyclevel.getCumulativeBalanceLimit()).isEqualTo(UPDATED_CUMULATIVE_BALANCE_LIMIT);
        assertThat(testKyclevel.isPaymentTransaction()).isEqualTo(UPDATED_PAYMENT_TRANSACTION);
        assertThat(testKyclevel.isBillerTransaction()).isEqualTo(UPDATED_BILLER_TRANSACTION);
    }

    @Test
    @Transactional
    public void updateNonExistingKyclevel() throws Exception {
        int databaseSizeBeforeUpdate = kyclevelRepository.findAll().size();

        // Create the Kyclevel
        KyclevelDTO kyclevelDTO = kyclevelMapper.toDto(kyclevel);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKyclevelMockMvc.perform(put("/api/kyclevels")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(kyclevelDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Kyclevel in the database
        List<Kyclevel> kyclevelList = kyclevelRepository.findAll();
        assertThat(kyclevelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteKyclevel() throws Exception {
        // Initialize the database
        kyclevelRepository.saveAndFlush(kyclevel);

        int databaseSizeBeforeDelete = kyclevelRepository.findAll().size();

        // Delete the kyclevel
        restKyclevelMockMvc.perform(delete("/api/kyclevels/{id}", kyclevel.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Kyclevel> kyclevelList = kyclevelRepository.findAll();
        assertThat(kyclevelList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

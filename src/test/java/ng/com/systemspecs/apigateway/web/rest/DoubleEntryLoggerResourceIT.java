package ng.com.systemspecs.apigateway.web.rest;

import ng.com.systemspecs.apigateway.ApiGatewayApp;
import ng.com.systemspecs.apigateway.domain.DoubleEntryLogger;
import ng.com.systemspecs.apigateway.repository.DoubleEntryLoggerRepository;
import ng.com.systemspecs.apigateway.service.DoubleEntryLoggerService;
import ng.com.systemspecs.apigateway.service.dto.DoubleEntryLoggerDTO;
import ng.com.systemspecs.apigateway.service.mapper.DoubleEntryLoggerMapper;

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
 * Integration tests for the {@link DoubleEntryLoggerResource} REST controller.
 */
@SpringBootTest(classes = ApiGatewayApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DoubleEntryLoggerResourceIT {

    private static final LocalDate DEFAULT_DATE_ENTERED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_ENTERED = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DOUBLE_ENTRY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_DOUBLE_ENTRY_CODE = "BBBBBBBBBB";

    private static final Double DEFAULT_AMOUNT = 1D;
    private static final Double UPDATED_AMOUNT = 2D;

    private static final String DEFAULT_NARRATION = "AAAAAAAAAA";
    private static final String UPDATED_NARRATION = "BBBBBBBBBB";

    @Autowired
    private DoubleEntryLoggerRepository doubleEntryLoggerRepository;

    @Autowired
    private DoubleEntryLoggerMapper doubleEntryLoggerMapper;

    @Autowired
    private DoubleEntryLoggerService doubleEntryLoggerService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDoubleEntryLoggerMockMvc;

    private DoubleEntryLogger doubleEntryLogger;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DoubleEntryLogger createEntity(EntityManager em) {
        DoubleEntryLogger doubleEntryLogger = new DoubleEntryLogger()
            .dateEntered(DEFAULT_DATE_ENTERED)
            .doubleEntryCode(DEFAULT_DOUBLE_ENTRY_CODE)
            .amount(DEFAULT_AMOUNT)
            .narration(DEFAULT_NARRATION);
        return doubleEntryLogger;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DoubleEntryLogger createUpdatedEntity(EntityManager em) {
        DoubleEntryLogger doubleEntryLogger = new DoubleEntryLogger()
            .dateEntered(UPDATED_DATE_ENTERED)
            .doubleEntryCode(UPDATED_DOUBLE_ENTRY_CODE)
            .amount(UPDATED_AMOUNT)
            .narration(UPDATED_NARRATION);
        return doubleEntryLogger;
    }

    @BeforeEach
    public void initTest() {
        doubleEntryLogger = createEntity(em);
    }

    @Test
    @Transactional
    public void createDoubleEntryLogger() throws Exception {
        int databaseSizeBeforeCreate = doubleEntryLoggerRepository.findAll().size();
        // Create the DoubleEntryLogger
        DoubleEntryLoggerDTO doubleEntryLoggerDTO = doubleEntryLoggerMapper.toDto(doubleEntryLogger);
        restDoubleEntryLoggerMockMvc.perform(post("/api/double-entry-loggers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(doubleEntryLoggerDTO)))
            .andExpect(status().isCreated());

        // Validate the DoubleEntryLogger in the database
        List<DoubleEntryLogger> doubleEntryLoggerList = doubleEntryLoggerRepository.findAll();
        assertThat(doubleEntryLoggerList).hasSize(databaseSizeBeforeCreate + 1);
        DoubleEntryLogger testDoubleEntryLogger = doubleEntryLoggerList.get(doubleEntryLoggerList.size() - 1);
        assertThat(testDoubleEntryLogger.getDateEntered()).isEqualTo(DEFAULT_DATE_ENTERED);
        assertThat(testDoubleEntryLogger.getDoubleEntryCode()).isEqualTo(DEFAULT_DOUBLE_ENTRY_CODE);
        assertThat(testDoubleEntryLogger.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testDoubleEntryLogger.getNarration()).isEqualTo(DEFAULT_NARRATION);
    }

    @Test
    @Transactional
    public void createDoubleEntryLoggerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = doubleEntryLoggerRepository.findAll().size();

        // Create the DoubleEntryLogger with an existing ID
        doubleEntryLogger.setId(1L);
        DoubleEntryLoggerDTO doubleEntryLoggerDTO = doubleEntryLoggerMapper.toDto(doubleEntryLogger);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDoubleEntryLoggerMockMvc.perform(post("/api/double-entry-loggers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(doubleEntryLoggerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DoubleEntryLogger in the database
        List<DoubleEntryLogger> doubleEntryLoggerList = doubleEntryLoggerRepository.findAll();
        assertThat(doubleEntryLoggerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDoubleEntryLoggers() throws Exception {
        // Initialize the database
        doubleEntryLoggerRepository.saveAndFlush(doubleEntryLogger);

        // Get all the doubleEntryLoggerList
        restDoubleEntryLoggerMockMvc.perform(get("/api/double-entry-loggers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(doubleEntryLogger.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateEntered").value(hasItem(DEFAULT_DATE_ENTERED.toString())))
            .andExpect(jsonPath("$.[*].doubleEntryCode").value(hasItem(DEFAULT_DOUBLE_ENTRY_CODE)))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].narration").value(hasItem(DEFAULT_NARRATION)));
    }
    
    @Test
    @Transactional
    public void getDoubleEntryLogger() throws Exception {
        // Initialize the database
        doubleEntryLoggerRepository.saveAndFlush(doubleEntryLogger);

        // Get the doubleEntryLogger
        restDoubleEntryLoggerMockMvc.perform(get("/api/double-entry-loggers/{id}", doubleEntryLogger.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(doubleEntryLogger.getId().intValue()))
            .andExpect(jsonPath("$.dateEntered").value(DEFAULT_DATE_ENTERED.toString()))
            .andExpect(jsonPath("$.doubleEntryCode").value(DEFAULT_DOUBLE_ENTRY_CODE))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.narration").value(DEFAULT_NARRATION));
    }
    @Test
    @Transactional
    public void getNonExistingDoubleEntryLogger() throws Exception {
        // Get the doubleEntryLogger
        restDoubleEntryLoggerMockMvc.perform(get("/api/double-entry-loggers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDoubleEntryLogger() throws Exception {
        // Initialize the database
        doubleEntryLoggerRepository.saveAndFlush(doubleEntryLogger);

        int databaseSizeBeforeUpdate = doubleEntryLoggerRepository.findAll().size();

        // Update the doubleEntryLogger
        DoubleEntryLogger updatedDoubleEntryLogger = doubleEntryLoggerRepository.findById(doubleEntryLogger.getId()).get();
        // Disconnect from session so that the updates on updatedDoubleEntryLogger are not directly saved in db
        em.detach(updatedDoubleEntryLogger);
        updatedDoubleEntryLogger
            .dateEntered(UPDATED_DATE_ENTERED)
            .doubleEntryCode(UPDATED_DOUBLE_ENTRY_CODE)
            .amount(UPDATED_AMOUNT)
            .narration(UPDATED_NARRATION);
        DoubleEntryLoggerDTO doubleEntryLoggerDTO = doubleEntryLoggerMapper.toDto(updatedDoubleEntryLogger);

        restDoubleEntryLoggerMockMvc.perform(put("/api/double-entry-loggers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(doubleEntryLoggerDTO)))
            .andExpect(status().isOk());

        // Validate the DoubleEntryLogger in the database
        List<DoubleEntryLogger> doubleEntryLoggerList = doubleEntryLoggerRepository.findAll();
        assertThat(doubleEntryLoggerList).hasSize(databaseSizeBeforeUpdate);
        DoubleEntryLogger testDoubleEntryLogger = doubleEntryLoggerList.get(doubleEntryLoggerList.size() - 1);
        assertThat(testDoubleEntryLogger.getDateEntered()).isEqualTo(UPDATED_DATE_ENTERED);
        assertThat(testDoubleEntryLogger.getDoubleEntryCode()).isEqualTo(UPDATED_DOUBLE_ENTRY_CODE);
        assertThat(testDoubleEntryLogger.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testDoubleEntryLogger.getNarration()).isEqualTo(UPDATED_NARRATION);
    }

    @Test
    @Transactional
    public void updateNonExistingDoubleEntryLogger() throws Exception {
        int databaseSizeBeforeUpdate = doubleEntryLoggerRepository.findAll().size();

        // Create the DoubleEntryLogger
        DoubleEntryLoggerDTO doubleEntryLoggerDTO = doubleEntryLoggerMapper.toDto(doubleEntryLogger);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDoubleEntryLoggerMockMvc.perform(put("/api/double-entry-loggers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(doubleEntryLoggerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DoubleEntryLogger in the database
        List<DoubleEntryLogger> doubleEntryLoggerList = doubleEntryLoggerRepository.findAll();
        assertThat(doubleEntryLoggerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDoubleEntryLogger() throws Exception {
        // Initialize the database
        doubleEntryLoggerRepository.saveAndFlush(doubleEntryLogger);

        int databaseSizeBeforeDelete = doubleEntryLoggerRepository.findAll().size();

        // Delete the doubleEntryLogger
        restDoubleEntryLoggerMockMvc.perform(delete("/api/double-entry-loggers/{id}", doubleEntryLogger.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DoubleEntryLogger> doubleEntryLoggerList = doubleEntryLoggerRepository.findAll();
        assertThat(doubleEntryLoggerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

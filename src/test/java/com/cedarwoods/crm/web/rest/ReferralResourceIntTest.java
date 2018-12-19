package com.cedarwoods.crm.web.rest;

import com.cedarwoods.crm.CwcrmApp;

import com.cedarwoods.crm.domain.Referral;
import com.cedarwoods.crm.repository.ReferralRepository;
import com.cedarwoods.crm.repository.search.ReferralSearchRepository;
import com.cedarwoods.crm.service.ReferralService;
import com.cedarwoods.crm.service.dto.ReferralDTO;
import com.cedarwoods.crm.service.mapper.ReferralMapper;
import com.cedarwoods.crm.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;


import static com.cedarwoods.crm.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ReferralResource REST controller.
 *
 * @see ReferralResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CwcrmApp.class)
public class ReferralResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private ReferralRepository referralRepository;

    @Autowired
    private ReferralMapper referralMapper;

    @Autowired
    private ReferralService referralService;

    /**
     * This repository is mocked in the com.cedarwoods.crm.repository.search test package.
     *
     * @see com.cedarwoods.crm.repository.search.ReferralSearchRepositoryMockConfiguration
     */
    @Autowired
    private ReferralSearchRepository mockReferralSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restReferralMockMvc;

    private Referral referral;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ReferralResource referralResource = new ReferralResource(referralService);
        this.restReferralMockMvc = MockMvcBuilders.standaloneSetup(referralResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Referral createEntity(EntityManager em) {
        Referral referral = new Referral()
            .name(DEFAULT_NAME);
        return referral;
    }

    @Before
    public void initTest() {
        referral = createEntity(em);
    }

    @Test
    @Transactional
    public void createReferral() throws Exception {
        int databaseSizeBeforeCreate = referralRepository.findAll().size();

        // Create the Referral
        ReferralDTO referralDTO = referralMapper.toDto(referral);
        restReferralMockMvc.perform(post("/api/referrals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(referralDTO)))
            .andExpect(status().isCreated());

        // Validate the Referral in the database
        List<Referral> referralList = referralRepository.findAll();
        assertThat(referralList).hasSize(databaseSizeBeforeCreate + 1);
        Referral testReferral = referralList.get(referralList.size() - 1);
        assertThat(testReferral.getName()).isEqualTo(DEFAULT_NAME);

        // Validate the Referral in Elasticsearch
        verify(mockReferralSearchRepository, times(1)).save(testReferral);
    }

    @Test
    @Transactional
    public void createReferralWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = referralRepository.findAll().size();

        // Create the Referral with an existing ID
        referral.setId(1L);
        ReferralDTO referralDTO = referralMapper.toDto(referral);

        // An entity with an existing ID cannot be created, so this API call must fail
        restReferralMockMvc.perform(post("/api/referrals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(referralDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Referral in the database
        List<Referral> referralList = referralRepository.findAll();
        assertThat(referralList).hasSize(databaseSizeBeforeCreate);

        // Validate the Referral in Elasticsearch
        verify(mockReferralSearchRepository, times(0)).save(referral);
    }

    @Test
    @Transactional
    public void getAllReferrals() throws Exception {
        // Initialize the database
        referralRepository.saveAndFlush(referral);

        // Get all the referralList
        restReferralMockMvc.perform(get("/api/referrals?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(referral.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getReferral() throws Exception {
        // Initialize the database
        referralRepository.saveAndFlush(referral);

        // Get the referral
        restReferralMockMvc.perform(get("/api/referrals/{id}", referral.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(referral.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingReferral() throws Exception {
        // Get the referral
        restReferralMockMvc.perform(get("/api/referrals/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateReferral() throws Exception {
        // Initialize the database
        referralRepository.saveAndFlush(referral);

        int databaseSizeBeforeUpdate = referralRepository.findAll().size();

        // Update the referral
        Referral updatedReferral = referralRepository.findById(referral.getId()).get();
        // Disconnect from session so that the updates on updatedReferral are not directly saved in db
        em.detach(updatedReferral);
        updatedReferral
            .name(UPDATED_NAME);
        ReferralDTO referralDTO = referralMapper.toDto(updatedReferral);

        restReferralMockMvc.perform(put("/api/referrals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(referralDTO)))
            .andExpect(status().isOk());

        // Validate the Referral in the database
        List<Referral> referralList = referralRepository.findAll();
        assertThat(referralList).hasSize(databaseSizeBeforeUpdate);
        Referral testReferral = referralList.get(referralList.size() - 1);
        assertThat(testReferral.getName()).isEqualTo(UPDATED_NAME);

        // Validate the Referral in Elasticsearch
        verify(mockReferralSearchRepository, times(1)).save(testReferral);
    }

    @Test
    @Transactional
    public void updateNonExistingReferral() throws Exception {
        int databaseSizeBeforeUpdate = referralRepository.findAll().size();

        // Create the Referral
        ReferralDTO referralDTO = referralMapper.toDto(referral);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReferralMockMvc.perform(put("/api/referrals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(referralDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Referral in the database
        List<Referral> referralList = referralRepository.findAll();
        assertThat(referralList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Referral in Elasticsearch
        verify(mockReferralSearchRepository, times(0)).save(referral);
    }

    @Test
    @Transactional
    public void deleteReferral() throws Exception {
        // Initialize the database
        referralRepository.saveAndFlush(referral);

        int databaseSizeBeforeDelete = referralRepository.findAll().size();

        // Get the referral
        restReferralMockMvc.perform(delete("/api/referrals/{id}", referral.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Referral> referralList = referralRepository.findAll();
        assertThat(referralList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Referral in Elasticsearch
        verify(mockReferralSearchRepository, times(1)).deleteById(referral.getId());
    }

    @Test
    @Transactional
    public void searchReferral() throws Exception {
        // Initialize the database
        referralRepository.saveAndFlush(referral);
        when(mockReferralSearchRepository.search(queryStringQuery("id:" + referral.getId())))
            .thenReturn(Collections.singletonList(referral));
        // Search the referral
        restReferralMockMvc.perform(get("/api/_search/referrals?query=id:" + referral.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(referral.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Referral.class);
        Referral referral1 = new Referral();
        referral1.setId(1L);
        Referral referral2 = new Referral();
        referral2.setId(referral1.getId());
        assertThat(referral1).isEqualTo(referral2);
        referral2.setId(2L);
        assertThat(referral1).isNotEqualTo(referral2);
        referral1.setId(null);
        assertThat(referral1).isNotEqualTo(referral2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReferralDTO.class);
        ReferralDTO referralDTO1 = new ReferralDTO();
        referralDTO1.setId(1L);
        ReferralDTO referralDTO2 = new ReferralDTO();
        assertThat(referralDTO1).isNotEqualTo(referralDTO2);
        referralDTO2.setId(referralDTO1.getId());
        assertThat(referralDTO1).isEqualTo(referralDTO2);
        referralDTO2.setId(2L);
        assertThat(referralDTO1).isNotEqualTo(referralDTO2);
        referralDTO1.setId(null);
        assertThat(referralDTO1).isNotEqualTo(referralDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(referralMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(referralMapper.fromId(null)).isNull();
    }
}

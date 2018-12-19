package com.cedarwoods.crm.web.rest;

import com.cedarwoods.crm.CwcrmApp;

import com.cedarwoods.crm.domain.ContactHistory;
import com.cedarwoods.crm.repository.ContactHistoryRepository;
import com.cedarwoods.crm.repository.search.ContactHistorySearchRepository;
import com.cedarwoods.crm.service.ContactHistoryService;
import com.cedarwoods.crm.service.dto.ContactHistoryDTO;
import com.cedarwoods.crm.service.mapper.ContactHistoryMapper;
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
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
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
 * Test class for the ContactHistoryResource REST controller.
 *
 * @see ContactHistoryResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CwcrmApp.class)
public class ContactHistoryResourceIntTest {

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_NOTES = "BBBBBBBBBB";

    @Autowired
    private ContactHistoryRepository contactHistoryRepository;

    @Autowired
    private ContactHistoryMapper contactHistoryMapper;

    @Autowired
    private ContactHistoryService contactHistoryService;

    /**
     * This repository is mocked in the com.cedarwoods.crm.repository.search test package.
     *
     * @see com.cedarwoods.crm.repository.search.ContactHistorySearchRepositoryMockConfiguration
     */
    @Autowired
    private ContactHistorySearchRepository mockContactHistorySearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restContactHistoryMockMvc;

    private ContactHistory contactHistory;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ContactHistoryResource contactHistoryResource = new ContactHistoryResource(contactHistoryService);
        this.restContactHistoryMockMvc = MockMvcBuilders.standaloneSetup(contactHistoryResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ContactHistory createEntity(EntityManager em) {
        ContactHistory contactHistory = new ContactHistory()
            .date(DEFAULT_DATE)
            .notes(DEFAULT_NOTES);
        return contactHistory;
    }

    @Before
    public void initTest() {
        contactHistory = createEntity(em);
    }

    @Test
    @Transactional
    public void createContactHistory() throws Exception {
        int databaseSizeBeforeCreate = contactHistoryRepository.findAll().size();

        // Create the ContactHistory
        ContactHistoryDTO contactHistoryDTO = contactHistoryMapper.toDto(contactHistory);
        restContactHistoryMockMvc.perform(post("/api/contact-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contactHistoryDTO)))
            .andExpect(status().isCreated());

        // Validate the ContactHistory in the database
        List<ContactHistory> contactHistoryList = contactHistoryRepository.findAll();
        assertThat(contactHistoryList).hasSize(databaseSizeBeforeCreate + 1);
        ContactHistory testContactHistory = contactHistoryList.get(contactHistoryList.size() - 1);
        assertThat(testContactHistory.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testContactHistory.getNotes()).isEqualTo(DEFAULT_NOTES);

        // Validate the ContactHistory in Elasticsearch
        verify(mockContactHistorySearchRepository, times(1)).save(testContactHistory);
    }

    @Test
    @Transactional
    public void createContactHistoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = contactHistoryRepository.findAll().size();

        // Create the ContactHistory with an existing ID
        contactHistory.setId(1L);
        ContactHistoryDTO contactHistoryDTO = contactHistoryMapper.toDto(contactHistory);

        // An entity with an existing ID cannot be created, so this API call must fail
        restContactHistoryMockMvc.perform(post("/api/contact-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contactHistoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ContactHistory in the database
        List<ContactHistory> contactHistoryList = contactHistoryRepository.findAll();
        assertThat(contactHistoryList).hasSize(databaseSizeBeforeCreate);

        // Validate the ContactHistory in Elasticsearch
        verify(mockContactHistorySearchRepository, times(0)).save(contactHistory);
    }

    @Test
    @Transactional
    public void getAllContactHistories() throws Exception {
        // Initialize the database
        contactHistoryRepository.saveAndFlush(contactHistory);

        // Get all the contactHistoryList
        restContactHistoryMockMvc.perform(get("/api/contact-histories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contactHistory.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES.toString())));
    }
    
    @Test
    @Transactional
    public void getContactHistory() throws Exception {
        // Initialize the database
        contactHistoryRepository.saveAndFlush(contactHistory);

        // Get the contactHistory
        restContactHistoryMockMvc.perform(get("/api/contact-histories/{id}", contactHistory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(contactHistory.getId().intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.notes").value(DEFAULT_NOTES.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingContactHistory() throws Exception {
        // Get the contactHistory
        restContactHistoryMockMvc.perform(get("/api/contact-histories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateContactHistory() throws Exception {
        // Initialize the database
        contactHistoryRepository.saveAndFlush(contactHistory);

        int databaseSizeBeforeUpdate = contactHistoryRepository.findAll().size();

        // Update the contactHistory
        ContactHistory updatedContactHistory = contactHistoryRepository.findById(contactHistory.getId()).get();
        // Disconnect from session so that the updates on updatedContactHistory are not directly saved in db
        em.detach(updatedContactHistory);
        updatedContactHistory
            .date(UPDATED_DATE)
            .notes(UPDATED_NOTES);
        ContactHistoryDTO contactHistoryDTO = contactHistoryMapper.toDto(updatedContactHistory);

        restContactHistoryMockMvc.perform(put("/api/contact-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contactHistoryDTO)))
            .andExpect(status().isOk());

        // Validate the ContactHistory in the database
        List<ContactHistory> contactHistoryList = contactHistoryRepository.findAll();
        assertThat(contactHistoryList).hasSize(databaseSizeBeforeUpdate);
        ContactHistory testContactHistory = contactHistoryList.get(contactHistoryList.size() - 1);
        assertThat(testContactHistory.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testContactHistory.getNotes()).isEqualTo(UPDATED_NOTES);

        // Validate the ContactHistory in Elasticsearch
        verify(mockContactHistorySearchRepository, times(1)).save(testContactHistory);
    }

    @Test
    @Transactional
    public void updateNonExistingContactHistory() throws Exception {
        int databaseSizeBeforeUpdate = contactHistoryRepository.findAll().size();

        // Create the ContactHistory
        ContactHistoryDTO contactHistoryDTO = contactHistoryMapper.toDto(contactHistory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContactHistoryMockMvc.perform(put("/api/contact-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contactHistoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ContactHistory in the database
        List<ContactHistory> contactHistoryList = contactHistoryRepository.findAll();
        assertThat(contactHistoryList).hasSize(databaseSizeBeforeUpdate);

        // Validate the ContactHistory in Elasticsearch
        verify(mockContactHistorySearchRepository, times(0)).save(contactHistory);
    }

    @Test
    @Transactional
    public void deleteContactHistory() throws Exception {
        // Initialize the database
        contactHistoryRepository.saveAndFlush(contactHistory);

        int databaseSizeBeforeDelete = contactHistoryRepository.findAll().size();

        // Get the contactHistory
        restContactHistoryMockMvc.perform(delete("/api/contact-histories/{id}", contactHistory.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ContactHistory> contactHistoryList = contactHistoryRepository.findAll();
        assertThat(contactHistoryList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the ContactHistory in Elasticsearch
        verify(mockContactHistorySearchRepository, times(1)).deleteById(contactHistory.getId());
    }

    @Test
    @Transactional
    public void searchContactHistory() throws Exception {
        // Initialize the database
        contactHistoryRepository.saveAndFlush(contactHistory);
        when(mockContactHistorySearchRepository.search(queryStringQuery("id:" + contactHistory.getId())))
            .thenReturn(Collections.singletonList(contactHistory));
        // Search the contactHistory
        restContactHistoryMockMvc.perform(get("/api/_search/contact-histories?query=id:" + contactHistory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contactHistory.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContactHistory.class);
        ContactHistory contactHistory1 = new ContactHistory();
        contactHistory1.setId(1L);
        ContactHistory contactHistory2 = new ContactHistory();
        contactHistory2.setId(contactHistory1.getId());
        assertThat(contactHistory1).isEqualTo(contactHistory2);
        contactHistory2.setId(2L);
        assertThat(contactHistory1).isNotEqualTo(contactHistory2);
        contactHistory1.setId(null);
        assertThat(contactHistory1).isNotEqualTo(contactHistory2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContactHistoryDTO.class);
        ContactHistoryDTO contactHistoryDTO1 = new ContactHistoryDTO();
        contactHistoryDTO1.setId(1L);
        ContactHistoryDTO contactHistoryDTO2 = new ContactHistoryDTO();
        assertThat(contactHistoryDTO1).isNotEqualTo(contactHistoryDTO2);
        contactHistoryDTO2.setId(contactHistoryDTO1.getId());
        assertThat(contactHistoryDTO1).isEqualTo(contactHistoryDTO2);
        contactHistoryDTO2.setId(2L);
        assertThat(contactHistoryDTO1).isNotEqualTo(contactHistoryDTO2);
        contactHistoryDTO1.setId(null);
        assertThat(contactHistoryDTO1).isNotEqualTo(contactHistoryDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(contactHistoryMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(contactHistoryMapper.fromId(null)).isNull();
    }
}

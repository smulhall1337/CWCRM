package com.cedarwoods.crm.web.rest;

import com.cedarwoods.crm.CwcrmApp;

import com.cedarwoods.crm.domain.Physician;
import com.cedarwoods.crm.repository.PhysicianRepository;
import com.cedarwoods.crm.repository.search.PhysicianSearchRepository;
import com.cedarwoods.crm.service.PhysicianService;
import com.cedarwoods.crm.service.dto.PhysicianDTO;
import com.cedarwoods.crm.service.mapper.PhysicianMapper;
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
 * Test class for the PhysicianResource REST controller.
 *
 * @see PhysicianResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CwcrmApp.class)
public class PhysicianResourceIntTest {

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_FAX = "AAAAAAAAAA";
    private static final String UPDATED_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_NOTES = "BBBBBBBBBB";

    private static final String DEFAULT_DISABILITY = "AAAAAAAAAA";
    private static final String UPDATED_DISABILITY = "BBBBBBBBBB";

    @Autowired
    private PhysicianRepository physicianRepository;

    @Autowired
    private PhysicianMapper physicianMapper;

    @Autowired
    private PhysicianService physicianService;

    /**
     * This repository is mocked in the com.cedarwoods.crm.repository.search test package.
     *
     * @see com.cedarwoods.crm.repository.search.PhysicianSearchRepositoryMockConfiguration
     */
    @Autowired
    private PhysicianSearchRepository mockPhysicianSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPhysicianMockMvc;

    private Physician physician;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PhysicianResource physicianResource = new PhysicianResource(physicianService);
        this.restPhysicianMockMvc = MockMvcBuilders.standaloneSetup(physicianResource)
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
    public static Physician createEntity(EntityManager em) {
        Physician physician = new Physician()
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .phone(DEFAULT_PHONE)
            .fax(DEFAULT_FAX)
            .address(DEFAULT_ADDRESS)
            .notes(DEFAULT_NOTES)
            .disability(DEFAULT_DISABILITY);
        return physician;
    }

    @Before
    public void initTest() {
        physician = createEntity(em);
    }

    @Test
    @Transactional
    public void createPhysician() throws Exception {
        int databaseSizeBeforeCreate = physicianRepository.findAll().size();

        // Create the Physician
        PhysicianDTO physicianDTO = physicianMapper.toDto(physician);
        restPhysicianMockMvc.perform(post("/api/physicians")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(physicianDTO)))
            .andExpect(status().isCreated());

        // Validate the Physician in the database
        List<Physician> physicianList = physicianRepository.findAll();
        assertThat(physicianList).hasSize(databaseSizeBeforeCreate + 1);
        Physician testPhysician = physicianList.get(physicianList.size() - 1);
        assertThat(testPhysician.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testPhysician.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testPhysician.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testPhysician.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testPhysician.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testPhysician.getNotes()).isEqualTo(DEFAULT_NOTES);
        assertThat(testPhysician.getDisability()).isEqualTo(DEFAULT_DISABILITY);

        // Validate the Physician in Elasticsearch
        verify(mockPhysicianSearchRepository, times(1)).save(testPhysician);
    }

    @Test
    @Transactional
    public void createPhysicianWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = physicianRepository.findAll().size();

        // Create the Physician with an existing ID
        physician.setId(1L);
        PhysicianDTO physicianDTO = physicianMapper.toDto(physician);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPhysicianMockMvc.perform(post("/api/physicians")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(physicianDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Physician in the database
        List<Physician> physicianList = physicianRepository.findAll();
        assertThat(physicianList).hasSize(databaseSizeBeforeCreate);

        // Validate the Physician in Elasticsearch
        verify(mockPhysicianSearchRepository, times(0)).save(physician);
    }

    @Test
    @Transactional
    public void getAllPhysicians() throws Exception {
        // Initialize the database
        physicianRepository.saveAndFlush(physician);

        // Get all the physicianList
        restPhysicianMockMvc.perform(get("/api/physicians?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(physician.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME.toString())))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.toString())))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES.toString())))
            .andExpect(jsonPath("$.[*].disability").value(hasItem(DEFAULT_DISABILITY.toString())));
    }
    
    @Test
    @Transactional
    public void getPhysician() throws Exception {
        // Initialize the database
        physicianRepository.saveAndFlush(physician);

        // Get the physician
        restPhysicianMockMvc.perform(get("/api/physicians/{id}", physician.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(physician.getId().intValue()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME.toString()))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME.toString()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.toString()))
            .andExpect(jsonPath("$.fax").value(DEFAULT_FAX.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.notes").value(DEFAULT_NOTES.toString()))
            .andExpect(jsonPath("$.disability").value(DEFAULT_DISABILITY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPhysician() throws Exception {
        // Get the physician
        restPhysicianMockMvc.perform(get("/api/physicians/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePhysician() throws Exception {
        // Initialize the database
        physicianRepository.saveAndFlush(physician);

        int databaseSizeBeforeUpdate = physicianRepository.findAll().size();

        // Update the physician
        Physician updatedPhysician = physicianRepository.findById(physician.getId()).get();
        // Disconnect from session so that the updates on updatedPhysician are not directly saved in db
        em.detach(updatedPhysician);
        updatedPhysician
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .phone(UPDATED_PHONE)
            .fax(UPDATED_FAX)
            .address(UPDATED_ADDRESS)
            .notes(UPDATED_NOTES)
            .disability(UPDATED_DISABILITY);
        PhysicianDTO physicianDTO = physicianMapper.toDto(updatedPhysician);

        restPhysicianMockMvc.perform(put("/api/physicians")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(physicianDTO)))
            .andExpect(status().isOk());

        // Validate the Physician in the database
        List<Physician> physicianList = physicianRepository.findAll();
        assertThat(physicianList).hasSize(databaseSizeBeforeUpdate);
        Physician testPhysician = physicianList.get(physicianList.size() - 1);
        assertThat(testPhysician.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testPhysician.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testPhysician.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testPhysician.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testPhysician.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testPhysician.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testPhysician.getDisability()).isEqualTo(UPDATED_DISABILITY);

        // Validate the Physician in Elasticsearch
        verify(mockPhysicianSearchRepository, times(1)).save(testPhysician);
    }

    @Test
    @Transactional
    public void updateNonExistingPhysician() throws Exception {
        int databaseSizeBeforeUpdate = physicianRepository.findAll().size();

        // Create the Physician
        PhysicianDTO physicianDTO = physicianMapper.toDto(physician);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPhysicianMockMvc.perform(put("/api/physicians")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(physicianDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Physician in the database
        List<Physician> physicianList = physicianRepository.findAll();
        assertThat(physicianList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Physician in Elasticsearch
        verify(mockPhysicianSearchRepository, times(0)).save(physician);
    }

    @Test
    @Transactional
    public void deletePhysician() throws Exception {
        // Initialize the database
        physicianRepository.saveAndFlush(physician);

        int databaseSizeBeforeDelete = physicianRepository.findAll().size();

        // Get the physician
        restPhysicianMockMvc.perform(delete("/api/physicians/{id}", physician.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Physician> physicianList = physicianRepository.findAll();
        assertThat(physicianList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Physician in Elasticsearch
        verify(mockPhysicianSearchRepository, times(1)).deleteById(physician.getId());
    }

    @Test
    @Transactional
    public void searchPhysician() throws Exception {
        // Initialize the database
        physicianRepository.saveAndFlush(physician);
        when(mockPhysicianSearchRepository.search(queryStringQuery("id:" + physician.getId())))
            .thenReturn(Collections.singletonList(physician));
        // Search the physician
        restPhysicianMockMvc.perform(get("/api/_search/physicians?query=id:" + physician.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(physician.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES.toString())))
            .andExpect(jsonPath("$.[*].disability").value(hasItem(DEFAULT_DISABILITY)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Physician.class);
        Physician physician1 = new Physician();
        physician1.setId(1L);
        Physician physician2 = new Physician();
        physician2.setId(physician1.getId());
        assertThat(physician1).isEqualTo(physician2);
        physician2.setId(2L);
        assertThat(physician1).isNotEqualTo(physician2);
        physician1.setId(null);
        assertThat(physician1).isNotEqualTo(physician2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PhysicianDTO.class);
        PhysicianDTO physicianDTO1 = new PhysicianDTO();
        physicianDTO1.setId(1L);
        PhysicianDTO physicianDTO2 = new PhysicianDTO();
        assertThat(physicianDTO1).isNotEqualTo(physicianDTO2);
        physicianDTO2.setId(physicianDTO1.getId());
        assertThat(physicianDTO1).isEqualTo(physicianDTO2);
        physicianDTO2.setId(2L);
        assertThat(physicianDTO1).isNotEqualTo(physicianDTO2);
        physicianDTO1.setId(null);
        assertThat(physicianDTO1).isNotEqualTo(physicianDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(physicianMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(physicianMapper.fromId(null)).isNull();
    }
}

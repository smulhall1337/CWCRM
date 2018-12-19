package com.cedarwoods.crm.web.rest;

import com.cedarwoods.crm.CwcrmApp;

import com.cedarwoods.crm.domain.EmployeeType;
import com.cedarwoods.crm.repository.EmployeeTypeRepository;
import com.cedarwoods.crm.repository.search.EmployeeTypeSearchRepository;
import com.cedarwoods.crm.service.EmployeeTypeService;
import com.cedarwoods.crm.service.dto.EmployeeTypeDTO;
import com.cedarwoods.crm.service.mapper.EmployeeTypeMapper;
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
 * Test class for the EmployeeTypeResource REST controller.
 *
 * @see EmployeeTypeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CwcrmApp.class)
public class EmployeeTypeResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private EmployeeTypeRepository employeeTypeRepository;

    @Autowired
    private EmployeeTypeMapper employeeTypeMapper;

    @Autowired
    private EmployeeTypeService employeeTypeService;

    /**
     * This repository is mocked in the com.cedarwoods.crm.repository.search test package.
     *
     * @see com.cedarwoods.crm.repository.search.EmployeeTypeSearchRepositoryMockConfiguration
     */
    @Autowired
    private EmployeeTypeSearchRepository mockEmployeeTypeSearchRepository;

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

    private MockMvc restEmployeeTypeMockMvc;

    private EmployeeType employeeType;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EmployeeTypeResource employeeTypeResource = new EmployeeTypeResource(employeeTypeService);
        this.restEmployeeTypeMockMvc = MockMvcBuilders.standaloneSetup(employeeTypeResource)
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
    public static EmployeeType createEntity(EntityManager em) {
        EmployeeType employeeType = new EmployeeType()
            .name(DEFAULT_NAME);
        return employeeType;
    }

    @Before
    public void initTest() {
        employeeType = createEntity(em);
    }

    @Test
    @Transactional
    public void createEmployeeType() throws Exception {
        int databaseSizeBeforeCreate = employeeTypeRepository.findAll().size();

        // Create the EmployeeType
        EmployeeTypeDTO employeeTypeDTO = employeeTypeMapper.toDto(employeeType);
        restEmployeeTypeMockMvc.perform(post("/api/employee-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the EmployeeType in the database
        List<EmployeeType> employeeTypeList = employeeTypeRepository.findAll();
        assertThat(employeeTypeList).hasSize(databaseSizeBeforeCreate + 1);
        EmployeeType testEmployeeType = employeeTypeList.get(employeeTypeList.size() - 1);
        assertThat(testEmployeeType.getName()).isEqualTo(DEFAULT_NAME);

        // Validate the EmployeeType in Elasticsearch
        verify(mockEmployeeTypeSearchRepository, times(1)).save(testEmployeeType);
    }

    @Test
    @Transactional
    public void createEmployeeTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = employeeTypeRepository.findAll().size();

        // Create the EmployeeType with an existing ID
        employeeType.setId(1L);
        EmployeeTypeDTO employeeTypeDTO = employeeTypeMapper.toDto(employeeType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmployeeTypeMockMvc.perform(post("/api/employee-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EmployeeType in the database
        List<EmployeeType> employeeTypeList = employeeTypeRepository.findAll();
        assertThat(employeeTypeList).hasSize(databaseSizeBeforeCreate);

        // Validate the EmployeeType in Elasticsearch
        verify(mockEmployeeTypeSearchRepository, times(0)).save(employeeType);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = employeeTypeRepository.findAll().size();
        // set the field null
        employeeType.setName(null);

        // Create the EmployeeType, which fails.
        EmployeeTypeDTO employeeTypeDTO = employeeTypeMapper.toDto(employeeType);

        restEmployeeTypeMockMvc.perform(post("/api/employee-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeTypeDTO)))
            .andExpect(status().isBadRequest());

        List<EmployeeType> employeeTypeList = employeeTypeRepository.findAll();
        assertThat(employeeTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEmployeeTypes() throws Exception {
        // Initialize the database
        employeeTypeRepository.saveAndFlush(employeeType);

        // Get all the employeeTypeList
        restEmployeeTypeMockMvc.perform(get("/api/employee-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(employeeType.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getEmployeeType() throws Exception {
        // Initialize the database
        employeeTypeRepository.saveAndFlush(employeeType);

        // Get the employeeType
        restEmployeeTypeMockMvc.perform(get("/api/employee-types/{id}", employeeType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(employeeType.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEmployeeType() throws Exception {
        // Get the employeeType
        restEmployeeTypeMockMvc.perform(get("/api/employee-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEmployeeType() throws Exception {
        // Initialize the database
        employeeTypeRepository.saveAndFlush(employeeType);

        int databaseSizeBeforeUpdate = employeeTypeRepository.findAll().size();

        // Update the employeeType
        EmployeeType updatedEmployeeType = employeeTypeRepository.findById(employeeType.getId()).get();
        // Disconnect from session so that the updates on updatedEmployeeType are not directly saved in db
        em.detach(updatedEmployeeType);
        updatedEmployeeType
            .name(UPDATED_NAME);
        EmployeeTypeDTO employeeTypeDTO = employeeTypeMapper.toDto(updatedEmployeeType);

        restEmployeeTypeMockMvc.perform(put("/api/employee-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeTypeDTO)))
            .andExpect(status().isOk());

        // Validate the EmployeeType in the database
        List<EmployeeType> employeeTypeList = employeeTypeRepository.findAll();
        assertThat(employeeTypeList).hasSize(databaseSizeBeforeUpdate);
        EmployeeType testEmployeeType = employeeTypeList.get(employeeTypeList.size() - 1);
        assertThat(testEmployeeType.getName()).isEqualTo(UPDATED_NAME);

        // Validate the EmployeeType in Elasticsearch
        verify(mockEmployeeTypeSearchRepository, times(1)).save(testEmployeeType);
    }

    @Test
    @Transactional
    public void updateNonExistingEmployeeType() throws Exception {
        int databaseSizeBeforeUpdate = employeeTypeRepository.findAll().size();

        // Create the EmployeeType
        EmployeeTypeDTO employeeTypeDTO = employeeTypeMapper.toDto(employeeType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEmployeeTypeMockMvc.perform(put("/api/employee-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EmployeeType in the database
        List<EmployeeType> employeeTypeList = employeeTypeRepository.findAll();
        assertThat(employeeTypeList).hasSize(databaseSizeBeforeUpdate);

        // Validate the EmployeeType in Elasticsearch
        verify(mockEmployeeTypeSearchRepository, times(0)).save(employeeType);
    }

    @Test
    @Transactional
    public void deleteEmployeeType() throws Exception {
        // Initialize the database
        employeeTypeRepository.saveAndFlush(employeeType);

        int databaseSizeBeforeDelete = employeeTypeRepository.findAll().size();

        // Get the employeeType
        restEmployeeTypeMockMvc.perform(delete("/api/employee-types/{id}", employeeType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<EmployeeType> employeeTypeList = employeeTypeRepository.findAll();
        assertThat(employeeTypeList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the EmployeeType in Elasticsearch
        verify(mockEmployeeTypeSearchRepository, times(1)).deleteById(employeeType.getId());
    }

    @Test
    @Transactional
    public void searchEmployeeType() throws Exception {
        // Initialize the database
        employeeTypeRepository.saveAndFlush(employeeType);
        when(mockEmployeeTypeSearchRepository.search(queryStringQuery("id:" + employeeType.getId())))
            .thenReturn(Collections.singletonList(employeeType));
        // Search the employeeType
        restEmployeeTypeMockMvc.perform(get("/api/_search/employee-types?query=id:" + employeeType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(employeeType.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EmployeeType.class);
        EmployeeType employeeType1 = new EmployeeType();
        employeeType1.setId(1L);
        EmployeeType employeeType2 = new EmployeeType();
        employeeType2.setId(employeeType1.getId());
        assertThat(employeeType1).isEqualTo(employeeType2);
        employeeType2.setId(2L);
        assertThat(employeeType1).isNotEqualTo(employeeType2);
        employeeType1.setId(null);
        assertThat(employeeType1).isNotEqualTo(employeeType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EmployeeTypeDTO.class);
        EmployeeTypeDTO employeeTypeDTO1 = new EmployeeTypeDTO();
        employeeTypeDTO1.setId(1L);
        EmployeeTypeDTO employeeTypeDTO2 = new EmployeeTypeDTO();
        assertThat(employeeTypeDTO1).isNotEqualTo(employeeTypeDTO2);
        employeeTypeDTO2.setId(employeeTypeDTO1.getId());
        assertThat(employeeTypeDTO1).isEqualTo(employeeTypeDTO2);
        employeeTypeDTO2.setId(2L);
        assertThat(employeeTypeDTO1).isNotEqualTo(employeeTypeDTO2);
        employeeTypeDTO1.setId(null);
        assertThat(employeeTypeDTO1).isNotEqualTo(employeeTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(employeeTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(employeeTypeMapper.fromId(null)).isNull();
    }
}

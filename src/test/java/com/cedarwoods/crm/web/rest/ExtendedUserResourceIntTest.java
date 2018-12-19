package com.cedarwoods.crm.web.rest;

import com.cedarwoods.crm.CwcrmApp;

import com.cedarwoods.crm.domain.ExtendedUser;
import com.cedarwoods.crm.repository.ExtendedUserRepository;
import com.cedarwoods.crm.repository.search.ExtendedUserSearchRepository;
import com.cedarwoods.crm.service.ExtendedUserService;
import com.cedarwoods.crm.service.dto.ExtendedUserDTO;
import com.cedarwoods.crm.service.mapper.ExtendedUserMapper;
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
 * Test class for the ExtendedUserResource REST controller.
 *
 * @see ExtendedUserResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CwcrmApp.class)
public class ExtendedUserResourceIntTest {

    @Autowired
    private ExtendedUserRepository extendedUserRepository;

    @Autowired
    private ExtendedUserMapper extendedUserMapper;

    @Autowired
    private ExtendedUserService extendedUserService;

    /**
     * This repository is mocked in the com.cedarwoods.crm.repository.search test package.
     *
     * @see com.cedarwoods.crm.repository.search.ExtendedUserSearchRepositoryMockConfiguration
     */
    @Autowired
    private ExtendedUserSearchRepository mockExtendedUserSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restExtendedUserMockMvc;

    private ExtendedUser extendedUser;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ExtendedUserResource extendedUserResource = new ExtendedUserResource(extendedUserService);
        this.restExtendedUserMockMvc = MockMvcBuilders.standaloneSetup(extendedUserResource)
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
    public static ExtendedUser createEntity(EntityManager em) {
        ExtendedUser extendedUser = new ExtendedUser();
        return extendedUser;
    }

    @Before
    public void initTest() {
        extendedUser = createEntity(em);
    }

    @Test
    @Transactional
    public void createExtendedUser() throws Exception {
        int databaseSizeBeforeCreate = extendedUserRepository.findAll().size();

        // Create the ExtendedUser
        ExtendedUserDTO extendedUserDTO = extendedUserMapper.toDto(extendedUser);
        restExtendedUserMockMvc.perform(post("/api/extended-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(extendedUserDTO)))
            .andExpect(status().isCreated());

        // Validate the ExtendedUser in the database
        List<ExtendedUser> extendedUserList = extendedUserRepository.findAll();
        assertThat(extendedUserList).hasSize(databaseSizeBeforeCreate + 1);
        ExtendedUser testExtendedUser = extendedUserList.get(extendedUserList.size() - 1);

        // Validate the ExtendedUser in Elasticsearch
        verify(mockExtendedUserSearchRepository, times(1)).save(testExtendedUser);
    }

    @Test
    @Transactional
    public void createExtendedUserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = extendedUserRepository.findAll().size();

        // Create the ExtendedUser with an existing ID
        extendedUser.setId(1L);
        ExtendedUserDTO extendedUserDTO = extendedUserMapper.toDto(extendedUser);

        // An entity with an existing ID cannot be created, so this API call must fail
        restExtendedUserMockMvc.perform(post("/api/extended-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(extendedUserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ExtendedUser in the database
        List<ExtendedUser> extendedUserList = extendedUserRepository.findAll();
        assertThat(extendedUserList).hasSize(databaseSizeBeforeCreate);

        // Validate the ExtendedUser in Elasticsearch
        verify(mockExtendedUserSearchRepository, times(0)).save(extendedUser);
    }

    @Test
    @Transactional
    public void getAllExtendedUsers() throws Exception {
        // Initialize the database
        extendedUserRepository.saveAndFlush(extendedUser);

        // Get all the extendedUserList
        restExtendedUserMockMvc.perform(get("/api/extended-users?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(extendedUser.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getExtendedUser() throws Exception {
        // Initialize the database
        extendedUserRepository.saveAndFlush(extendedUser);

        // Get the extendedUser
        restExtendedUserMockMvc.perform(get("/api/extended-users/{id}", extendedUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(extendedUser.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingExtendedUser() throws Exception {
        // Get the extendedUser
        restExtendedUserMockMvc.perform(get("/api/extended-users/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateExtendedUser() throws Exception {
        // Initialize the database
        extendedUserRepository.saveAndFlush(extendedUser);

        int databaseSizeBeforeUpdate = extendedUserRepository.findAll().size();

        // Update the extendedUser
        ExtendedUser updatedExtendedUser = extendedUserRepository.findById(extendedUser.getId()).get();
        // Disconnect from session so that the updates on updatedExtendedUser are not directly saved in db
        em.detach(updatedExtendedUser);
        ExtendedUserDTO extendedUserDTO = extendedUserMapper.toDto(updatedExtendedUser);

        restExtendedUserMockMvc.perform(put("/api/extended-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(extendedUserDTO)))
            .andExpect(status().isOk());

        // Validate the ExtendedUser in the database
        List<ExtendedUser> extendedUserList = extendedUserRepository.findAll();
        assertThat(extendedUserList).hasSize(databaseSizeBeforeUpdate);
        ExtendedUser testExtendedUser = extendedUserList.get(extendedUserList.size() - 1);

        // Validate the ExtendedUser in Elasticsearch
        verify(mockExtendedUserSearchRepository, times(1)).save(testExtendedUser);
    }

    @Test
    @Transactional
    public void updateNonExistingExtendedUser() throws Exception {
        int databaseSizeBeforeUpdate = extendedUserRepository.findAll().size();

        // Create the ExtendedUser
        ExtendedUserDTO extendedUserDTO = extendedUserMapper.toDto(extendedUser);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExtendedUserMockMvc.perform(put("/api/extended-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(extendedUserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ExtendedUser in the database
        List<ExtendedUser> extendedUserList = extendedUserRepository.findAll();
        assertThat(extendedUserList).hasSize(databaseSizeBeforeUpdate);

        // Validate the ExtendedUser in Elasticsearch
        verify(mockExtendedUserSearchRepository, times(0)).save(extendedUser);
    }

    @Test
    @Transactional
    public void deleteExtendedUser() throws Exception {
        // Initialize the database
        extendedUserRepository.saveAndFlush(extendedUser);

        int databaseSizeBeforeDelete = extendedUserRepository.findAll().size();

        // Get the extendedUser
        restExtendedUserMockMvc.perform(delete("/api/extended-users/{id}", extendedUser.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ExtendedUser> extendedUserList = extendedUserRepository.findAll();
        assertThat(extendedUserList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the ExtendedUser in Elasticsearch
        verify(mockExtendedUserSearchRepository, times(1)).deleteById(extendedUser.getId());
    }

    @Test
    @Transactional
    public void searchExtendedUser() throws Exception {
        // Initialize the database
        extendedUserRepository.saveAndFlush(extendedUser);
        when(mockExtendedUserSearchRepository.search(queryStringQuery("id:" + extendedUser.getId())))
            .thenReturn(Collections.singletonList(extendedUser));
        // Search the extendedUser
        restExtendedUserMockMvc.perform(get("/api/_search/extended-users?query=id:" + extendedUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(extendedUser.getId().intValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ExtendedUser.class);
        ExtendedUser extendedUser1 = new ExtendedUser();
        extendedUser1.setId(1L);
        ExtendedUser extendedUser2 = new ExtendedUser();
        extendedUser2.setId(extendedUser1.getId());
        assertThat(extendedUser1).isEqualTo(extendedUser2);
        extendedUser2.setId(2L);
        assertThat(extendedUser1).isNotEqualTo(extendedUser2);
        extendedUser1.setId(null);
        assertThat(extendedUser1).isNotEqualTo(extendedUser2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ExtendedUserDTO.class);
        ExtendedUserDTO extendedUserDTO1 = new ExtendedUserDTO();
        extendedUserDTO1.setId(1L);
        ExtendedUserDTO extendedUserDTO2 = new ExtendedUserDTO();
        assertThat(extendedUserDTO1).isNotEqualTo(extendedUserDTO2);
        extendedUserDTO2.setId(extendedUserDTO1.getId());
        assertThat(extendedUserDTO1).isEqualTo(extendedUserDTO2);
        extendedUserDTO2.setId(2L);
        assertThat(extendedUserDTO1).isNotEqualTo(extendedUserDTO2);
        extendedUserDTO1.setId(null);
        assertThat(extendedUserDTO1).isNotEqualTo(extendedUserDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(extendedUserMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(extendedUserMapper.fromId(null)).isNull();
    }
}

package com.cedarwoods.crm.web.rest;

import com.cedarwoods.crm.CwcrmApp;

import com.cedarwoods.crm.domain.ParticipantNotes;
import com.cedarwoods.crm.repository.ParticipantNotesRepository;
import com.cedarwoods.crm.repository.search.ParticipantNotesSearchRepository;
import com.cedarwoods.crm.service.ParticipantNotesService;
import com.cedarwoods.crm.service.dto.ParticipantNotesDTO;
import com.cedarwoods.crm.service.mapper.ParticipantNotesMapper;
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
 * Test class for the ParticipantNotesResource REST controller.
 *
 * @see ParticipantNotesResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CwcrmApp.class)
public class ParticipantNotesResourceIntTest {

    private static final String DEFAULT_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_NOTES = "BBBBBBBBBB";

    @Autowired
    private ParticipantNotesRepository participantNotesRepository;

    @Autowired
    private ParticipantNotesMapper participantNotesMapper;

    @Autowired
    private ParticipantNotesService participantNotesService;

    /**
     * This repository is mocked in the com.cedarwoods.crm.repository.search test package.
     *
     * @see com.cedarwoods.crm.repository.search.ParticipantNotesSearchRepositoryMockConfiguration
     */
    @Autowired
    private ParticipantNotesSearchRepository mockParticipantNotesSearchRepository;

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

    private MockMvc restParticipantNotesMockMvc;

    private ParticipantNotes participantNotes;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ParticipantNotesResource participantNotesResource = new ParticipantNotesResource(participantNotesService);
        this.restParticipantNotesMockMvc = MockMvcBuilders.standaloneSetup(participantNotesResource)
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
    public static ParticipantNotes createEntity(EntityManager em) {
        ParticipantNotes participantNotes = new ParticipantNotes()
            .notes(DEFAULT_NOTES);
        return participantNotes;
    }

    @Before
    public void initTest() {
        participantNotes = createEntity(em);
    }

    @Test
    @Transactional
    public void createParticipantNotes() throws Exception {
        int databaseSizeBeforeCreate = participantNotesRepository.findAll().size();

        // Create the ParticipantNotes
        ParticipantNotesDTO participantNotesDTO = participantNotesMapper.toDto(participantNotes);
        restParticipantNotesMockMvc.perform(post("/api/participant-notes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(participantNotesDTO)))
            .andExpect(status().isCreated());

        // Validate the ParticipantNotes in the database
        List<ParticipantNotes> participantNotesList = participantNotesRepository.findAll();
        assertThat(participantNotesList).hasSize(databaseSizeBeforeCreate + 1);
        ParticipantNotes testParticipantNotes = participantNotesList.get(participantNotesList.size() - 1);
        assertThat(testParticipantNotes.getNotes()).isEqualTo(DEFAULT_NOTES);

        // Validate the ParticipantNotes in Elasticsearch
        verify(mockParticipantNotesSearchRepository, times(1)).save(testParticipantNotes);
    }

    @Test
    @Transactional
    public void createParticipantNotesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = participantNotesRepository.findAll().size();

        // Create the ParticipantNotes with an existing ID
        participantNotes.setId(1L);
        ParticipantNotesDTO participantNotesDTO = participantNotesMapper.toDto(participantNotes);

        // An entity with an existing ID cannot be created, so this API call must fail
        restParticipantNotesMockMvc.perform(post("/api/participant-notes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(participantNotesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ParticipantNotes in the database
        List<ParticipantNotes> participantNotesList = participantNotesRepository.findAll();
        assertThat(participantNotesList).hasSize(databaseSizeBeforeCreate);

        // Validate the ParticipantNotes in Elasticsearch
        verify(mockParticipantNotesSearchRepository, times(0)).save(participantNotes);
    }

    @Test
    @Transactional
    public void getAllParticipantNotes() throws Exception {
        // Initialize the database
        participantNotesRepository.saveAndFlush(participantNotes);

        // Get all the participantNotesList
        restParticipantNotesMockMvc.perform(get("/api/participant-notes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(participantNotes.getId().intValue())))
            .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES.toString())));
    }
    
    @Test
    @Transactional
    public void getParticipantNotes() throws Exception {
        // Initialize the database
        participantNotesRepository.saveAndFlush(participantNotes);

        // Get the participantNotes
        restParticipantNotesMockMvc.perform(get("/api/participant-notes/{id}", participantNotes.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(participantNotes.getId().intValue()))
            .andExpect(jsonPath("$.notes").value(DEFAULT_NOTES.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingParticipantNotes() throws Exception {
        // Get the participantNotes
        restParticipantNotesMockMvc.perform(get("/api/participant-notes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateParticipantNotes() throws Exception {
        // Initialize the database
        participantNotesRepository.saveAndFlush(participantNotes);

        int databaseSizeBeforeUpdate = participantNotesRepository.findAll().size();

        // Update the participantNotes
        ParticipantNotes updatedParticipantNotes = participantNotesRepository.findById(participantNotes.getId()).get();
        // Disconnect from session so that the updates on updatedParticipantNotes are not directly saved in db
        em.detach(updatedParticipantNotes);
        updatedParticipantNotes
            .notes(UPDATED_NOTES);
        ParticipantNotesDTO participantNotesDTO = participantNotesMapper.toDto(updatedParticipantNotes);

        restParticipantNotesMockMvc.perform(put("/api/participant-notes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(participantNotesDTO)))
            .andExpect(status().isOk());

        // Validate the ParticipantNotes in the database
        List<ParticipantNotes> participantNotesList = participantNotesRepository.findAll();
        assertThat(participantNotesList).hasSize(databaseSizeBeforeUpdate);
        ParticipantNotes testParticipantNotes = participantNotesList.get(participantNotesList.size() - 1);
        assertThat(testParticipantNotes.getNotes()).isEqualTo(UPDATED_NOTES);

        // Validate the ParticipantNotes in Elasticsearch
        verify(mockParticipantNotesSearchRepository, times(1)).save(testParticipantNotes);
    }

    @Test
    @Transactional
    public void updateNonExistingParticipantNotes() throws Exception {
        int databaseSizeBeforeUpdate = participantNotesRepository.findAll().size();

        // Create the ParticipantNotes
        ParticipantNotesDTO participantNotesDTO = participantNotesMapper.toDto(participantNotes);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restParticipantNotesMockMvc.perform(put("/api/participant-notes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(participantNotesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ParticipantNotes in the database
        List<ParticipantNotes> participantNotesList = participantNotesRepository.findAll();
        assertThat(participantNotesList).hasSize(databaseSizeBeforeUpdate);

        // Validate the ParticipantNotes in Elasticsearch
        verify(mockParticipantNotesSearchRepository, times(0)).save(participantNotes);
    }

    @Test
    @Transactional
    public void deleteParticipantNotes() throws Exception {
        // Initialize the database
        participantNotesRepository.saveAndFlush(participantNotes);

        int databaseSizeBeforeDelete = participantNotesRepository.findAll().size();

        // Get the participantNotes
        restParticipantNotesMockMvc.perform(delete("/api/participant-notes/{id}", participantNotes.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ParticipantNotes> participantNotesList = participantNotesRepository.findAll();
        assertThat(participantNotesList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the ParticipantNotes in Elasticsearch
        verify(mockParticipantNotesSearchRepository, times(1)).deleteById(participantNotes.getId());
    }

    @Test
    @Transactional
    public void searchParticipantNotes() throws Exception {
        // Initialize the database
        participantNotesRepository.saveAndFlush(participantNotes);
        when(mockParticipantNotesSearchRepository.search(queryStringQuery("id:" + participantNotes.getId())))
            .thenReturn(Collections.singletonList(participantNotes));
        // Search the participantNotes
        restParticipantNotesMockMvc.perform(get("/api/_search/participant-notes?query=id:" + participantNotes.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(participantNotes.getId().intValue())))
            .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ParticipantNotes.class);
        ParticipantNotes participantNotes1 = new ParticipantNotes();
        participantNotes1.setId(1L);
        ParticipantNotes participantNotes2 = new ParticipantNotes();
        participantNotes2.setId(participantNotes1.getId());
        assertThat(participantNotes1).isEqualTo(participantNotes2);
        participantNotes2.setId(2L);
        assertThat(participantNotes1).isNotEqualTo(participantNotes2);
        participantNotes1.setId(null);
        assertThat(participantNotes1).isNotEqualTo(participantNotes2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ParticipantNotesDTO.class);
        ParticipantNotesDTO participantNotesDTO1 = new ParticipantNotesDTO();
        participantNotesDTO1.setId(1L);
        ParticipantNotesDTO participantNotesDTO2 = new ParticipantNotesDTO();
        assertThat(participantNotesDTO1).isNotEqualTo(participantNotesDTO2);
        participantNotesDTO2.setId(participantNotesDTO1.getId());
        assertThat(participantNotesDTO1).isEqualTo(participantNotesDTO2);
        participantNotesDTO2.setId(2L);
        assertThat(participantNotesDTO1).isNotEqualTo(participantNotesDTO2);
        participantNotesDTO1.setId(null);
        assertThat(participantNotesDTO1).isNotEqualTo(participantNotesDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(participantNotesMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(participantNotesMapper.fromId(null)).isNull();
    }
}

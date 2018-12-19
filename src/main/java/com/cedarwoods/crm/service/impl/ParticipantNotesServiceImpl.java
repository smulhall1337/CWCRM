package com.cedarwoods.crm.service.impl;

import com.cedarwoods.crm.service.ParticipantNotesService;
import com.cedarwoods.crm.domain.ParticipantNotes;
import com.cedarwoods.crm.repository.ParticipantNotesRepository;
import com.cedarwoods.crm.repository.search.ParticipantNotesSearchRepository;
import com.cedarwoods.crm.service.dto.ParticipantNotesDTO;
import com.cedarwoods.crm.service.mapper.ParticipantNotesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing ParticipantNotes.
 */
@Service
@Transactional
public class ParticipantNotesServiceImpl implements ParticipantNotesService {

    private final Logger log = LoggerFactory.getLogger(ParticipantNotesServiceImpl.class);

    private final ParticipantNotesRepository participantNotesRepository;

    private final ParticipantNotesMapper participantNotesMapper;

    private final ParticipantNotesSearchRepository participantNotesSearchRepository;

    public ParticipantNotesServiceImpl(ParticipantNotesRepository participantNotesRepository, ParticipantNotesMapper participantNotesMapper, ParticipantNotesSearchRepository participantNotesSearchRepository) {
        this.participantNotesRepository = participantNotesRepository;
        this.participantNotesMapper = participantNotesMapper;
        this.participantNotesSearchRepository = participantNotesSearchRepository;
    }

    /**
     * Save a participantNotes.
     *
     * @param participantNotesDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ParticipantNotesDTO save(ParticipantNotesDTO participantNotesDTO) {
        log.debug("Request to save ParticipantNotes : {}", participantNotesDTO);

        ParticipantNotes participantNotes = participantNotesMapper.toEntity(participantNotesDTO);
        participantNotes = participantNotesRepository.save(participantNotes);
        ParticipantNotesDTO result = participantNotesMapper.toDto(participantNotes);
        participantNotesSearchRepository.save(participantNotes);
        return result;
    }

    /**
     * Get all the participantNotes.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ParticipantNotesDTO> findAll() {
        log.debug("Request to get all ParticipantNotes");
        return participantNotesRepository.findAll().stream()
            .map(participantNotesMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one participantNotes by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ParticipantNotesDTO> findOne(Long id) {
        log.debug("Request to get ParticipantNotes : {}", id);
        return participantNotesRepository.findById(id)
            .map(participantNotesMapper::toDto);
    }

    /**
     * Delete the participantNotes by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ParticipantNotes : {}", id);
        participantNotesRepository.deleteById(id);
        participantNotesSearchRepository.deleteById(id);
    }

    /**
     * Search for the participantNotes corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ParticipantNotesDTO> search(String query) {
        log.debug("Request to search ParticipantNotes for query {}", query);
        return StreamSupport
            .stream(participantNotesSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(participantNotesMapper::toDto)
            .collect(Collectors.toList());
    }
}

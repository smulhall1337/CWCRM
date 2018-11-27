package com.cedarwoods.crm.service.impl;

import com.cedarwoods.crm.service.ParticipantService;
import com.cedarwoods.crm.domain.Participant;
import com.cedarwoods.crm.repository.ParticipantRepository;
import com.cedarwoods.crm.repository.search.ParticipantSearchRepository;
import com.cedarwoods.crm.service.dto.ParticipantDTO;
import com.cedarwoods.crm.service.mapper.ParticipantMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Participant.
 */
@Service
@Transactional
public class ParticipantServiceImpl implements ParticipantService {

    private final Logger log = LoggerFactory.getLogger(ParticipantServiceImpl.class);

    private final ParticipantRepository participantRepository;

    private final ParticipantMapper participantMapper;

    private final ParticipantSearchRepository participantSearchRepository;

    public ParticipantServiceImpl(ParticipantRepository participantRepository, ParticipantMapper participantMapper, ParticipantSearchRepository participantSearchRepository) {
        this.participantRepository = participantRepository;
        this.participantMapper = participantMapper;
        this.participantSearchRepository = participantSearchRepository;
    }

    /**
     * Save a participant.
     *
     * @param participantDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ParticipantDTO save(ParticipantDTO participantDTO) {
        log.debug("Request to save Participant : {}", participantDTO);

        Participant participant = participantMapper.toEntity(participantDTO);
        participant = participantRepository.save(participant);
        ParticipantDTO result = participantMapper.toDto(participant);
        participantSearchRepository.save(participant);
        return result;
    }

    /**
     * Get all the participants.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ParticipantDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Participants");
        return participantRepository.findAll(pageable)
            .map(participantMapper::toDto);
    }


    /**
     * Get one participant by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ParticipantDTO> findOne(Long id) {
        log.debug("Request to get Participant : {}", id);
        return participantRepository.findById(id)
            .map(participantMapper::toDto);
    }

    /**
     * Delete the participant by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Participant : {}", id);
        participantRepository.deleteById(id);
        participantSearchRepository.deleteById(id);
    }

    /**
     * Search for the participant corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ParticipantDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Participants for query {}", query);
        return participantSearchRepository.search(queryStringQuery(query), pageable)
            .map(participantMapper::toDto);
    }
}

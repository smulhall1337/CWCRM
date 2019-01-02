package com.cedarwoods.crm.service.impl;

import com.cedarwoods.crm.domain.Participant;
import com.cedarwoods.crm.service.ContactHistoryService;
import com.cedarwoods.crm.domain.ContactHistory;
import com.cedarwoods.crm.repository.ContactHistoryRepository;
import com.cedarwoods.crm.repository.search.ContactHistorySearchRepository;
import com.cedarwoods.crm.service.dto.ContactHistoryDTO;
import com.cedarwoods.crm.service.mapper.ContactHistoryMapper;
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
 * Service Implementation for managing ContactHistory.
 */
@Service
@Transactional
public class ContactHistoryServiceImpl implements ContactHistoryService {

    private final Logger log = LoggerFactory.getLogger(ContactHistoryServiceImpl.class);

    private final ContactHistoryRepository contactHistoryRepository;

    private final ContactHistoryMapper contactHistoryMapper;

    private final ContactHistorySearchRepository contactHistorySearchRepository;

    public ContactHistoryServiceImpl(ContactHistoryRepository contactHistoryRepository, ContactHistoryMapper contactHistoryMapper, ContactHistorySearchRepository contactHistorySearchRepository) {
        this.contactHistoryRepository = contactHistoryRepository;
        this.contactHistoryMapper = contactHistoryMapper;
        this.contactHistorySearchRepository = contactHistorySearchRepository;
    }

    /**
     * Save a contactHistory.
     *
     * @param contactHistoryDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ContactHistoryDTO save(ContactHistoryDTO contactHistoryDTO) {
        log.debug("Request to save ContactHistory : {}", contactHistoryDTO);

        ContactHistory contactHistory = contactHistoryMapper.toEntity(contactHistoryDTO);
        contactHistory = contactHistoryRepository.save(contactHistory);
        ContactHistoryDTO result = contactHistoryMapper.toDto(contactHistory);
        contactHistorySearchRepository.save(contactHistory);
        return result;
    }

    /**
     * Get all the contactHistories.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ContactHistoryDTO> findAll() {
        log.debug("Request to get all ContactHistories");
        return contactHistoryRepository.findAll().stream()
            .map(contactHistoryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one contactHistory by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ContactHistoryDTO> findOne(Long id) {
        log.debug("Request to get ContactHistory : {}", id);
        return contactHistoryRepository.findById(id)
            .map(contactHistoryMapper::toDto);
    }

    /**
     * Delete the contactHistory by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ContactHistory : {}", id);
        contactHistoryRepository.deleteById(id);
        contactHistorySearchRepository.deleteById(id);
    }

    /**
     * Search for the contactHistory corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ContactHistoryDTO> search(String query) {
        log.debug("Request to search ContactHistories for query {}", query);
        return StreamSupport
            .stream(contactHistorySearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(contactHistoryMapper::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<ContactHistoryDTO> findAllByParticipantId(Long partId) {
        log.debug("Request to get all ContactHistories By Participant Ids");
        System.out.println("PartID: "+partId);
        return contactHistoryRepository.findAllByParticipantId(partId).stream()
            .map(contactHistoryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }
}

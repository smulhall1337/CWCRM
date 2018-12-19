package com.cedarwoods.crm.service.impl;

import com.cedarwoods.crm.service.ContactTypeService;
import com.cedarwoods.crm.domain.ContactType;
import com.cedarwoods.crm.repository.ContactTypeRepository;
import com.cedarwoods.crm.repository.search.ContactTypeSearchRepository;
import com.cedarwoods.crm.service.dto.ContactTypeDTO;
import com.cedarwoods.crm.service.mapper.ContactTypeMapper;
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
 * Service Implementation for managing ContactType.
 */
@Service
@Transactional
public class ContactTypeServiceImpl implements ContactTypeService {

    private final Logger log = LoggerFactory.getLogger(ContactTypeServiceImpl.class);

    private final ContactTypeRepository contactTypeRepository;

    private final ContactTypeMapper contactTypeMapper;

    private final ContactTypeSearchRepository contactTypeSearchRepository;

    public ContactTypeServiceImpl(ContactTypeRepository contactTypeRepository, ContactTypeMapper contactTypeMapper, ContactTypeSearchRepository contactTypeSearchRepository) {
        this.contactTypeRepository = contactTypeRepository;
        this.contactTypeMapper = contactTypeMapper;
        this.contactTypeSearchRepository = contactTypeSearchRepository;
    }

    /**
     * Save a contactType.
     *
     * @param contactTypeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ContactTypeDTO save(ContactTypeDTO contactTypeDTO) {
        log.debug("Request to save ContactType : {}", contactTypeDTO);

        ContactType contactType = contactTypeMapper.toEntity(contactTypeDTO);
        contactType = contactTypeRepository.save(contactType);
        ContactTypeDTO result = contactTypeMapper.toDto(contactType);
        contactTypeSearchRepository.save(contactType);
        return result;
    }

    /**
     * Get all the contactTypes.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ContactTypeDTO> findAll() {
        log.debug("Request to get all ContactTypes");
        return contactTypeRepository.findAll().stream()
            .map(contactTypeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one contactType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ContactTypeDTO> findOne(Long id) {
        log.debug("Request to get ContactType : {}", id);
        return contactTypeRepository.findById(id)
            .map(contactTypeMapper::toDto);
    }

    /**
     * Delete the contactType by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ContactType : {}", id);
        contactTypeRepository.deleteById(id);
        contactTypeSearchRepository.deleteById(id);
    }

    /**
     * Search for the contactType corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ContactTypeDTO> search(String query) {
        log.debug("Request to search ContactTypes for query {}", query);
        return StreamSupport
            .stream(contactTypeSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(contactTypeMapper::toDto)
            .collect(Collectors.toList());
    }
}

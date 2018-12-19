package com.cedarwoods.crm.service.impl;

import com.cedarwoods.crm.service.PhysicianService;
import com.cedarwoods.crm.domain.Physician;
import com.cedarwoods.crm.repository.PhysicianRepository;
import com.cedarwoods.crm.repository.search.PhysicianSearchRepository;
import com.cedarwoods.crm.service.dto.PhysicianDTO;
import com.cedarwoods.crm.service.mapper.PhysicianMapper;
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
 * Service Implementation for managing Physician.
 */
@Service
@Transactional
public class PhysicianServiceImpl implements PhysicianService {

    private final Logger log = LoggerFactory.getLogger(PhysicianServiceImpl.class);

    private final PhysicianRepository physicianRepository;

    private final PhysicianMapper physicianMapper;

    private final PhysicianSearchRepository physicianSearchRepository;

    public PhysicianServiceImpl(PhysicianRepository physicianRepository, PhysicianMapper physicianMapper, PhysicianSearchRepository physicianSearchRepository) {
        this.physicianRepository = physicianRepository;
        this.physicianMapper = physicianMapper;
        this.physicianSearchRepository = physicianSearchRepository;
    }

    /**
     * Save a physician.
     *
     * @param physicianDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PhysicianDTO save(PhysicianDTO physicianDTO) {
        log.debug("Request to save Physician : {}", physicianDTO);

        Physician physician = physicianMapper.toEntity(physicianDTO);
        physician = physicianRepository.save(physician);
        PhysicianDTO result = physicianMapper.toDto(physician);
        physicianSearchRepository.save(physician);
        return result;
    }

    /**
     * Get all the physicians.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<PhysicianDTO> findAll() {
        log.debug("Request to get all Physicians");
        return physicianRepository.findAll().stream()
            .map(physicianMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one physician by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PhysicianDTO> findOne(Long id) {
        log.debug("Request to get Physician : {}", id);
        return physicianRepository.findById(id)
            .map(physicianMapper::toDto);
    }

    /**
     * Delete the physician by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Physician : {}", id);
        physicianRepository.deleteById(id);
        physicianSearchRepository.deleteById(id);
    }

    /**
     * Search for the physician corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<PhysicianDTO> search(String query) {
        log.debug("Request to search Physicians for query {}", query);
        return StreamSupport
            .stream(physicianSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(physicianMapper::toDto)
            .collect(Collectors.toList());
    }
}

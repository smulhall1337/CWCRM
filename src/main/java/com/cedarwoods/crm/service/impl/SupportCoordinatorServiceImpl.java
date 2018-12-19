package com.cedarwoods.crm.service.impl;

import com.cedarwoods.crm.service.SupportCoordinatorService;
import com.cedarwoods.crm.domain.SupportCoordinator;
import com.cedarwoods.crm.repository.SupportCoordinatorRepository;
import com.cedarwoods.crm.repository.search.SupportCoordinatorSearchRepository;
import com.cedarwoods.crm.service.dto.SupportCoordinatorDTO;
import com.cedarwoods.crm.service.mapper.SupportCoordinatorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing SupportCoordinator.
 */
@Service
@Transactional
public class SupportCoordinatorServiceImpl implements SupportCoordinatorService {

    private final Logger log = LoggerFactory.getLogger(SupportCoordinatorServiceImpl.class);

    private final SupportCoordinatorRepository supportCoordinatorRepository;

    private final SupportCoordinatorMapper supportCoordinatorMapper;

    private final SupportCoordinatorSearchRepository supportCoordinatorSearchRepository;

    public SupportCoordinatorServiceImpl(SupportCoordinatorRepository supportCoordinatorRepository, SupportCoordinatorMapper supportCoordinatorMapper, SupportCoordinatorSearchRepository supportCoordinatorSearchRepository) {
        this.supportCoordinatorRepository = supportCoordinatorRepository;
        this.supportCoordinatorMapper = supportCoordinatorMapper;
        this.supportCoordinatorSearchRepository = supportCoordinatorSearchRepository;
    }

    /**
     * Save a supportCoordinator.
     *
     * @param supportCoordinatorDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SupportCoordinatorDTO save(SupportCoordinatorDTO supportCoordinatorDTO) {
        log.debug("Request to save SupportCoordinator : {}", supportCoordinatorDTO);

        SupportCoordinator supportCoordinator = supportCoordinatorMapper.toEntity(supportCoordinatorDTO);
        supportCoordinator = supportCoordinatorRepository.save(supportCoordinator);
        SupportCoordinatorDTO result = supportCoordinatorMapper.toDto(supportCoordinator);
        supportCoordinatorSearchRepository.save(supportCoordinator);
        return result;
    }

    /**
     * Get all the supportCoordinators.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SupportCoordinatorDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SupportCoordinators");
        return supportCoordinatorRepository.findAll(pageable)
            .map(supportCoordinatorMapper::toDto);
    }


    /**
     * Get one supportCoordinator by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SupportCoordinatorDTO> findOne(Long id) {
        log.debug("Request to get SupportCoordinator : {}", id);
        return supportCoordinatorRepository.findById(id)
            .map(supportCoordinatorMapper::toDto);
    }

    /**
     * Delete the supportCoordinator by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SupportCoordinator : {}", id);
        supportCoordinatorRepository.deleteById(id);
        supportCoordinatorSearchRepository.deleteById(id);
    }

    /**
     * Search for the supportCoordinator corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SupportCoordinatorDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of SupportCoordinators for query {}", query);
        return supportCoordinatorSearchRepository.search(queryStringQuery(query), pageable)
            .map(supportCoordinatorMapper::toDto);
    }
}

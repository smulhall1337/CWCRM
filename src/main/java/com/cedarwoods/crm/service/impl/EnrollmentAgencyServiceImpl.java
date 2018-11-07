package com.cedarwoods.crm.service.impl;

import com.cedarwoods.crm.service.EnrollmentAgencyService;
import com.cedarwoods.crm.domain.EnrollmentAgency;
import com.cedarwoods.crm.repository.EnrollmentAgencyRepository;
import com.cedarwoods.crm.repository.search.EnrollmentAgencySearchRepository;
import com.cedarwoods.crm.service.dto.EnrollmentAgencyDTO;
import com.cedarwoods.crm.service.mapper.EnrollmentAgencyMapper;
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
 * Service Implementation for managing EnrollmentAgency.
 */
@Service
@Transactional
public class EnrollmentAgencyServiceImpl implements EnrollmentAgencyService {

    private final Logger log = LoggerFactory.getLogger(EnrollmentAgencyServiceImpl.class);

    private final EnrollmentAgencyRepository enrollmentAgencyRepository;

    private final EnrollmentAgencyMapper enrollmentAgencyMapper;

    private final EnrollmentAgencySearchRepository enrollmentAgencySearchRepository;

    public EnrollmentAgencyServiceImpl(EnrollmentAgencyRepository enrollmentAgencyRepository, EnrollmentAgencyMapper enrollmentAgencyMapper, EnrollmentAgencySearchRepository enrollmentAgencySearchRepository) {
        this.enrollmentAgencyRepository = enrollmentAgencyRepository;
        this.enrollmentAgencyMapper = enrollmentAgencyMapper;
        this.enrollmentAgencySearchRepository = enrollmentAgencySearchRepository;
    }

    /**
     * Save a enrollmentAgency.
     *
     * @param enrollmentAgencyDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EnrollmentAgencyDTO save(EnrollmentAgencyDTO enrollmentAgencyDTO) {
        log.debug("Request to save EnrollmentAgency : {}", enrollmentAgencyDTO);

        EnrollmentAgency enrollmentAgency = enrollmentAgencyMapper.toEntity(enrollmentAgencyDTO);
        enrollmentAgency = enrollmentAgencyRepository.save(enrollmentAgency);
        EnrollmentAgencyDTO result = enrollmentAgencyMapper.toDto(enrollmentAgency);
        enrollmentAgencySearchRepository.save(enrollmentAgency);
        return result;
    }

    /**
     * Get all the enrollmentAgencies.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<EnrollmentAgencyDTO> findAll() {
        log.debug("Request to get all EnrollmentAgencies");
        return enrollmentAgencyRepository.findAll().stream()
            .map(enrollmentAgencyMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one enrollmentAgency by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EnrollmentAgencyDTO> findOne(Long id) {
        log.debug("Request to get EnrollmentAgency : {}", id);
        return enrollmentAgencyRepository.findById(id)
            .map(enrollmentAgencyMapper::toDto);
    }

    /**
     * Delete the enrollmentAgency by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EnrollmentAgency : {}", id);
        enrollmentAgencyRepository.deleteById(id);
        enrollmentAgencySearchRepository.deleteById(id);
    }

    /**
     * Search for the enrollmentAgency corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<EnrollmentAgencyDTO> search(String query) {
        log.debug("Request to search EnrollmentAgencies for query {}", query);
        return StreamSupport
            .stream(enrollmentAgencySearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(enrollmentAgencyMapper::toDto)
            .collect(Collectors.toList());
    }
}

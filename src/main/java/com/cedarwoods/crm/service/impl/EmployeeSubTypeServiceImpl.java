package com.cedarwoods.crm.service.impl;

import com.cedarwoods.crm.service.EmployeeSubTypeService;
import com.cedarwoods.crm.domain.EmployeeSubType;
import com.cedarwoods.crm.repository.EmployeeSubTypeRepository;
import com.cedarwoods.crm.repository.search.EmployeeSubTypeSearchRepository;
import com.cedarwoods.crm.service.dto.EmployeeSubTypeDTO;
import com.cedarwoods.crm.service.mapper.EmployeeSubTypeMapper;
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
 * Service Implementation for managing EmployeeSubType.
 */
@Service
@Transactional
public class EmployeeSubTypeServiceImpl implements EmployeeSubTypeService {

    private final Logger log = LoggerFactory.getLogger(EmployeeSubTypeServiceImpl.class);

    private final EmployeeSubTypeRepository employeeSubTypeRepository;

    private final EmployeeSubTypeMapper employeeSubTypeMapper;

    private final EmployeeSubTypeSearchRepository employeeSubTypeSearchRepository;

    public EmployeeSubTypeServiceImpl(EmployeeSubTypeRepository employeeSubTypeRepository, EmployeeSubTypeMapper employeeSubTypeMapper, EmployeeSubTypeSearchRepository employeeSubTypeSearchRepository) {
        this.employeeSubTypeRepository = employeeSubTypeRepository;
        this.employeeSubTypeMapper = employeeSubTypeMapper;
        this.employeeSubTypeSearchRepository = employeeSubTypeSearchRepository;
    }

    /**
     * Save a employeeSubType.
     *
     * @param employeeSubTypeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EmployeeSubTypeDTO save(EmployeeSubTypeDTO employeeSubTypeDTO) {
        log.debug("Request to save EmployeeSubType : {}", employeeSubTypeDTO);

        EmployeeSubType employeeSubType = employeeSubTypeMapper.toEntity(employeeSubTypeDTO);
        employeeSubType = employeeSubTypeRepository.save(employeeSubType);
        EmployeeSubTypeDTO result = employeeSubTypeMapper.toDto(employeeSubType);
        employeeSubTypeSearchRepository.save(employeeSubType);
        return result;
    }

    /**
     * Get all the employeeSubTypes.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<EmployeeSubTypeDTO> findAll() {
        log.debug("Request to get all EmployeeSubTypes");
        return employeeSubTypeRepository.findAll().stream()
            .map(employeeSubTypeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one employeeSubType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EmployeeSubTypeDTO> findOne(Long id) {
        log.debug("Request to get EmployeeSubType : {}", id);
        return employeeSubTypeRepository.findById(id)
            .map(employeeSubTypeMapper::toDto);
    }

    /**
     * Delete the employeeSubType by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EmployeeSubType : {}", id);
        employeeSubTypeRepository.deleteById(id);
        employeeSubTypeSearchRepository.deleteById(id);
    }

    /**
     * Search for the employeeSubType corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<EmployeeSubTypeDTO> search(String query) {
        log.debug("Request to search EmployeeSubTypes for query {}", query);
        return StreamSupport
            .stream(employeeSubTypeSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(employeeSubTypeMapper::toDto)
            .collect(Collectors.toList());
    }
}

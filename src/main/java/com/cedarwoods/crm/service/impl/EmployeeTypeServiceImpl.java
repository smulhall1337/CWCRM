package com.cedarwoods.crm.service.impl;

import com.cedarwoods.crm.service.EmployeeTypeService;
import com.cedarwoods.crm.domain.EmployeeType;
import com.cedarwoods.crm.repository.EmployeeTypeRepository;
import com.cedarwoods.crm.repository.search.EmployeeTypeSearchRepository;
import com.cedarwoods.crm.service.dto.EmployeeTypeDTO;
import com.cedarwoods.crm.service.mapper.EmployeeTypeMapper;
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
 * Service Implementation for managing EmployeeType.
 */
@Service
@Transactional
public class EmployeeTypeServiceImpl implements EmployeeTypeService {

    private final Logger log = LoggerFactory.getLogger(EmployeeTypeServiceImpl.class);

    private final EmployeeTypeRepository employeeTypeRepository;

    private final EmployeeTypeMapper employeeTypeMapper;

    private final EmployeeTypeSearchRepository employeeTypeSearchRepository;

    public EmployeeTypeServiceImpl(EmployeeTypeRepository employeeTypeRepository, EmployeeTypeMapper employeeTypeMapper, EmployeeTypeSearchRepository employeeTypeSearchRepository) {
        this.employeeTypeRepository = employeeTypeRepository;
        this.employeeTypeMapper = employeeTypeMapper;
        this.employeeTypeSearchRepository = employeeTypeSearchRepository;
    }

    /**
     * Save a employeeType.
     *
     * @param employeeTypeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EmployeeTypeDTO save(EmployeeTypeDTO employeeTypeDTO) {
        log.debug("Request to save EmployeeType : {}", employeeTypeDTO);

        EmployeeType employeeType = employeeTypeMapper.toEntity(employeeTypeDTO);
        employeeType = employeeTypeRepository.save(employeeType);
        EmployeeTypeDTO result = employeeTypeMapper.toDto(employeeType);
        employeeTypeSearchRepository.save(employeeType);
        return result;
    }

    /**
     * Get all the employeeTypes.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<EmployeeTypeDTO> findAll() {
        log.debug("Request to get all EmployeeTypes");
        return employeeTypeRepository.findAll().stream()
            .map(employeeTypeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one employeeType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EmployeeTypeDTO> findOne(Long id) {
        log.debug("Request to get EmployeeType : {}", id);
        return employeeTypeRepository.findById(id)
            .map(employeeTypeMapper::toDto);
    }

    /**
     * Delete the employeeType by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EmployeeType : {}", id);
        employeeTypeRepository.deleteById(id);
        employeeTypeSearchRepository.deleteById(id);
    }

    /**
     * Search for the employeeType corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<EmployeeTypeDTO> search(String query) {
        log.debug("Request to search EmployeeTypes for query {}", query);
        return StreamSupport
            .stream(employeeTypeSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(employeeTypeMapper::toDto)
            .collect(Collectors.toList());
    }
}

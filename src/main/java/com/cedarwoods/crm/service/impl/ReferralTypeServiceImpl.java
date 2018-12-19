package com.cedarwoods.crm.service.impl;

import com.cedarwoods.crm.service.ReferralTypeService;
import com.cedarwoods.crm.domain.ReferralType;
import com.cedarwoods.crm.repository.ReferralTypeRepository;
import com.cedarwoods.crm.repository.search.ReferralTypeSearchRepository;
import com.cedarwoods.crm.service.dto.ReferralTypeDTO;
import com.cedarwoods.crm.service.mapper.ReferralTypeMapper;
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
 * Service Implementation for managing ReferralType.
 */
@Service
@Transactional
public class ReferralTypeServiceImpl implements ReferralTypeService {

    private final Logger log = LoggerFactory.getLogger(ReferralTypeServiceImpl.class);

    private final ReferralTypeRepository referralTypeRepository;

    private final ReferralTypeMapper referralTypeMapper;

    private final ReferralTypeSearchRepository referralTypeSearchRepository;

    public ReferralTypeServiceImpl(ReferralTypeRepository referralTypeRepository, ReferralTypeMapper referralTypeMapper, ReferralTypeSearchRepository referralTypeSearchRepository) {
        this.referralTypeRepository = referralTypeRepository;
        this.referralTypeMapper = referralTypeMapper;
        this.referralTypeSearchRepository = referralTypeSearchRepository;
    }

    /**
     * Save a referralType.
     *
     * @param referralTypeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ReferralTypeDTO save(ReferralTypeDTO referralTypeDTO) {
        log.debug("Request to save ReferralType : {}", referralTypeDTO);

        ReferralType referralType = referralTypeMapper.toEntity(referralTypeDTO);
        referralType = referralTypeRepository.save(referralType);
        ReferralTypeDTO result = referralTypeMapper.toDto(referralType);
        referralTypeSearchRepository.save(referralType);
        return result;
    }

    /**
     * Get all the referralTypes.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ReferralTypeDTO> findAll() {
        log.debug("Request to get all ReferralTypes");
        return referralTypeRepository.findAll().stream()
            .map(referralTypeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one referralType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ReferralTypeDTO> findOne(Long id) {
        log.debug("Request to get ReferralType : {}", id);
        return referralTypeRepository.findById(id)
            .map(referralTypeMapper::toDto);
    }

    /**
     * Delete the referralType by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ReferralType : {}", id);
        referralTypeRepository.deleteById(id);
        referralTypeSearchRepository.deleteById(id);
    }

    /**
     * Search for the referralType corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ReferralTypeDTO> search(String query) {
        log.debug("Request to search ReferralTypes for query {}", query);
        return StreamSupport
            .stream(referralTypeSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(referralTypeMapper::toDto)
            .collect(Collectors.toList());
    }
}

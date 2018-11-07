package com.cedarwoods.crm.service.impl;

import com.cedarwoods.crm.service.MCOService;
import com.cedarwoods.crm.domain.MCO;
import com.cedarwoods.crm.repository.MCORepository;
import com.cedarwoods.crm.repository.search.MCOSearchRepository;
import com.cedarwoods.crm.service.dto.MCODTO;
import com.cedarwoods.crm.service.mapper.MCOMapper;
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
 * Service Implementation for managing MCO.
 */
@Service
@Transactional
public class MCOServiceImpl implements MCOService {

    private final Logger log = LoggerFactory.getLogger(MCOServiceImpl.class);

    private final MCORepository mCORepository;

    private final MCOMapper mCOMapper;

    private final MCOSearchRepository mCOSearchRepository;

    public MCOServiceImpl(MCORepository mCORepository, MCOMapper mCOMapper, MCOSearchRepository mCOSearchRepository) {
        this.mCORepository = mCORepository;
        this.mCOMapper = mCOMapper;
        this.mCOSearchRepository = mCOSearchRepository;
    }

    /**
     * Save a mCO.
     *
     * @param mCODTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MCODTO save(MCODTO mCODTO) {
        log.debug("Request to save MCO : {}", mCODTO);

        MCO mCO = mCOMapper.toEntity(mCODTO);
        mCO = mCORepository.save(mCO);
        MCODTO result = mCOMapper.toDto(mCO);
        mCOSearchRepository.save(mCO);
        return result;
    }

    /**
     * Get all the mCOS.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<MCODTO> findAll() {
        log.debug("Request to get all MCOS");
        return mCORepository.findAll().stream()
            .map(mCOMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one mCO by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MCODTO> findOne(Long id) {
        log.debug("Request to get MCO : {}", id);
        return mCORepository.findById(id)
            .map(mCOMapper::toDto);
    }

    /**
     * Delete the mCO by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MCO : {}", id);
        mCORepository.deleteById(id);
        mCOSearchRepository.deleteById(id);
    }

    /**
     * Search for the mCO corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<MCODTO> search(String query) {
        log.debug("Request to search MCOS for query {}", query);
        return StreamSupport
            .stream(mCOSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(mCOMapper::toDto)
            .collect(Collectors.toList());
    }
}

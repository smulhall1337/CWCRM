package com.cedarwoods.crm.service.impl;

import com.cedarwoods.crm.service.ReferralService;
import com.cedarwoods.crm.domain.Referral;
import com.cedarwoods.crm.repository.ReferralRepository;
import com.cedarwoods.crm.repository.search.ReferralSearchRepository;
import com.cedarwoods.crm.service.dto.ReferralDTO;
import com.cedarwoods.crm.service.mapper.ReferralMapper;
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
 * Service Implementation for managing Referral.
 */
@Service
@Transactional
public class ReferralServiceImpl implements ReferralService {

    private final Logger log = LoggerFactory.getLogger(ReferralServiceImpl.class);

    private final ReferralRepository referralRepository;

    private final ReferralMapper referralMapper;

    private final ReferralSearchRepository referralSearchRepository;

    public ReferralServiceImpl(ReferralRepository referralRepository, ReferralMapper referralMapper, ReferralSearchRepository referralSearchRepository) {
        this.referralRepository = referralRepository;
        this.referralMapper = referralMapper;
        this.referralSearchRepository = referralSearchRepository;
    }

    /**
     * Save a referral.
     *
     * @param referralDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ReferralDTO save(ReferralDTO referralDTO) {
        log.debug("Request to save Referral : {}", referralDTO);

        Referral referral = referralMapper.toEntity(referralDTO);
        referral = referralRepository.save(referral);
        ReferralDTO result = referralMapper.toDto(referral);
        referralSearchRepository.save(referral);
        return result;
    }

    /**
     * Get all the referrals.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ReferralDTO> findAll() {
        log.debug("Request to get all Referrals");
        return referralRepository.findAll().stream()
            .map(referralMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one referral by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ReferralDTO> findOne(Long id) {
        log.debug("Request to get Referral : {}", id);
        return referralRepository.findById(id)
            .map(referralMapper::toDto);
    }

    /**
     * Delete the referral by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Referral : {}", id);
        referralRepository.deleteById(id);
        referralSearchRepository.deleteById(id);
    }

    /**
     * Search for the referral corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ReferralDTO> search(String query) {
        log.debug("Request to search Referrals for query {}", query);
        return StreamSupport
            .stream(referralSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(referralMapper::toDto)
            .collect(Collectors.toList());
    }
}

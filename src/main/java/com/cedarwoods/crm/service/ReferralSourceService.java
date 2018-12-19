package com.cedarwoods.crm.service;

import com.cedarwoods.crm.service.dto.ReferralSourceDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing ReferralSource.
 */
public interface ReferralSourceService {

    /**
     * Save a referralSource.
     *
     * @param referralSourceDTO the entity to save
     * @return the persisted entity
     */
    ReferralSourceDTO save(ReferralSourceDTO referralSourceDTO);

    /**
     * Get all the referralSources.
     *
     * @return the list of entities
     */
    List<ReferralSourceDTO> findAll();


    /**
     * Get the "id" referralSource.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ReferralSourceDTO> findOne(Long id);

    /**
     * Delete the "id" referralSource.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the referralSource corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<ReferralSourceDTO> search(String query);
}

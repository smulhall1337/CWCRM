package com.cedarwoods.crm.service;

import com.cedarwoods.crm.service.dto.ReferralDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Referral.
 */
public interface ReferralService {

    /**
     * Save a referral.
     *
     * @param referralDTO the entity to save
     * @return the persisted entity
     */
    ReferralDTO save(ReferralDTO referralDTO);

    /**
     * Get all the referrals.
     *
     * @return the list of entities
     */
    List<ReferralDTO> findAll();


    /**
     * Get the "id" referral.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ReferralDTO> findOne(Long id);

    /**
     * Delete the "id" referral.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the referral corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<ReferralDTO> search(String query);
}

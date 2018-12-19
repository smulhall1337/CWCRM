package com.cedarwoods.crm.service;

import com.cedarwoods.crm.service.dto.WaiverDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Waiver.
 */
public interface WaiverService {

    /**
     * Save a waiver.
     *
     * @param waiverDTO the entity to save
     * @return the persisted entity
     */
    WaiverDTO save(WaiverDTO waiverDTO);

    /**
     * Get all the waivers.
     *
     * @return the list of entities
     */
    List<WaiverDTO> findAll();


    /**
     * Get the "id" waiver.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<WaiverDTO> findOne(Long id);

    /**
     * Delete the "id" waiver.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the waiver corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<WaiverDTO> search(String query);
}

package com.cedarwoods.crm.service;

import com.cedarwoods.crm.service.dto.PhysicianDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Physician.
 */
public interface PhysicianService {

    /**
     * Save a physician.
     *
     * @param physicianDTO the entity to save
     * @return the persisted entity
     */
    PhysicianDTO save(PhysicianDTO physicianDTO);

    /**
     * Get all the physicians.
     *
     * @return the list of entities
     */
    List<PhysicianDTO> findAll();


    /**
     * Get the "id" physician.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PhysicianDTO> findOne(Long id);

    /**
     * Delete the "id" physician.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the physician corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<PhysicianDTO> search(String query);
}

package com.cedarwoods.crm.service;

import com.cedarwoods.crm.service.dto.EnrollmentAgencyDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing EnrollmentAgency.
 */
public interface EnrollmentAgencyService {

    /**
     * Save a enrollmentAgency.
     *
     * @param enrollmentAgencyDTO the entity to save
     * @return the persisted entity
     */
    EnrollmentAgencyDTO save(EnrollmentAgencyDTO enrollmentAgencyDTO);

    /**
     * Get all the enrollmentAgencies.
     *
     * @return the list of entities
     */
    List<EnrollmentAgencyDTO> findAll();


    /**
     * Get the "id" enrollmentAgency.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EnrollmentAgencyDTO> findOne(Long id);

    /**
     * Delete the "id" enrollmentAgency.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the enrollmentAgency corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<EnrollmentAgencyDTO> search(String query);
}

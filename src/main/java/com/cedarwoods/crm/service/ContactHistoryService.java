package com.cedarwoods.crm.service;

import com.cedarwoods.crm.service.dto.ContactHistoryDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing ContactHistory.
 */
public interface ContactHistoryService {

    /**
     * Save a contactHistory.
     *
     * @param contactHistoryDTO the entity to save
     * @return the persisted entity
     */
    ContactHistoryDTO save(ContactHistoryDTO contactHistoryDTO);

    /**
     * Get all the contactHistories.
     *
     * @return the list of entities
     */
    List<ContactHistoryDTO> findAll();


    /**
     * Get the "id" contactHistory.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ContactHistoryDTO> findOne(Long id);

    /**
     * Delete the "id" contactHistory.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the contactHistory corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<ContactHistoryDTO> search(String query);
}

package com.cedarwoods.crm.service;

import com.cedarwoods.crm.service.dto.ExtendedUserDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing ExtendedUser.
 */
public interface ExtendedUserService {

    /**
     * Save a extendedUser.
     *
     * @param extendedUserDTO the entity to save
     * @return the persisted entity
     */
    ExtendedUserDTO save(ExtendedUserDTO extendedUserDTO);

    /**
     * Get all the extendedUsers.
     *
     * @return the list of entities
     */
    List<ExtendedUserDTO> findAll();


    /**
     * Get the "id" extendedUser.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ExtendedUserDTO> findOne(Long id);

    /**
     * Delete the "id" extendedUser.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the extendedUser corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<ExtendedUserDTO> search(String query);
}

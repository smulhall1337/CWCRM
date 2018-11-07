package com.cedarwoods.crm.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cedarwoods.crm.service.PhysicianService;
import com.cedarwoods.crm.web.rest.errors.BadRequestAlertException;
import com.cedarwoods.crm.web.rest.util.HeaderUtil;
import com.cedarwoods.crm.service.dto.PhysicianDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing Physician.
 */
@RestController
@RequestMapping("/api")
public class PhysicianResource {

    private final Logger log = LoggerFactory.getLogger(PhysicianResource.class);

    private static final String ENTITY_NAME = "physician";

    private final PhysicianService physicianService;

    public PhysicianResource(PhysicianService physicianService) {
        this.physicianService = physicianService;
    }

    /**
     * POST  /physicians : Create a new physician.
     *
     * @param physicianDTO the physicianDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new physicianDTO, or with status 400 (Bad Request) if the physician has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/physicians")
    @Timed
    public ResponseEntity<PhysicianDTO> createPhysician(@RequestBody PhysicianDTO physicianDTO) throws URISyntaxException {
        log.debug("REST request to save Physician : {}", physicianDTO);
        if (physicianDTO.getId() != null) {
            throw new BadRequestAlertException("A new physician cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PhysicianDTO result = physicianService.save(physicianDTO);
        return ResponseEntity.created(new URI("/api/physicians/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /physicians : Updates an existing physician.
     *
     * @param physicianDTO the physicianDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated physicianDTO,
     * or with status 400 (Bad Request) if the physicianDTO is not valid,
     * or with status 500 (Internal Server Error) if the physicianDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/physicians")
    @Timed
    public ResponseEntity<PhysicianDTO> updatePhysician(@RequestBody PhysicianDTO physicianDTO) throws URISyntaxException {
        log.debug("REST request to update Physician : {}", physicianDTO);
        if (physicianDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PhysicianDTO result = physicianService.save(physicianDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, physicianDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /physicians : get all the physicians.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of physicians in body
     */
    @GetMapping("/physicians")
    @Timed
    public List<PhysicianDTO> getAllPhysicians() {
        log.debug("REST request to get all Physicians");
        return physicianService.findAll();
    }

    /**
     * GET  /physicians/:id : get the "id" physician.
     *
     * @param id the id of the physicianDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the physicianDTO, or with status 404 (Not Found)
     */
    @GetMapping("/physicians/{id}")
    @Timed
    public ResponseEntity<PhysicianDTO> getPhysician(@PathVariable Long id) {
        log.debug("REST request to get Physician : {}", id);
        Optional<PhysicianDTO> physicianDTO = physicianService.findOne(id);
        return ResponseUtil.wrapOrNotFound(physicianDTO);
    }

    /**
     * DELETE  /physicians/:id : delete the "id" physician.
     *
     * @param id the id of the physicianDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/physicians/{id}")
    @Timed
    public ResponseEntity<Void> deletePhysician(@PathVariable Long id) {
        log.debug("REST request to delete Physician : {}", id);
        physicianService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/physicians?query=:query : search for the physician corresponding
     * to the query.
     *
     * @param query the query of the physician search
     * @return the result of the search
     */
    @GetMapping("/_search/physicians")
    @Timed
    public List<PhysicianDTO> searchPhysicians(@RequestParam String query) {
        log.debug("REST request to search Physicians for query {}", query);
        return physicianService.search(query);
    }

}

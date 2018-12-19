package com.cedarwoods.crm.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cedarwoods.crm.service.ReferralService;
import com.cedarwoods.crm.web.rest.errors.BadRequestAlertException;
import com.cedarwoods.crm.web.rest.util.HeaderUtil;
import com.cedarwoods.crm.service.dto.ReferralDTO;
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
 * REST controller for managing Referral.
 */
@RestController
@RequestMapping("/api")
public class ReferralResource {

    private final Logger log = LoggerFactory.getLogger(ReferralResource.class);

    private static final String ENTITY_NAME = "referral";

    private final ReferralService referralService;

    public ReferralResource(ReferralService referralService) {
        this.referralService = referralService;
    }

    /**
     * POST  /referrals : Create a new referral.
     *
     * @param referralDTO the referralDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new referralDTO, or with status 400 (Bad Request) if the referral has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/referrals")
    @Timed
    public ResponseEntity<ReferralDTO> createReferral(@RequestBody ReferralDTO referralDTO) throws URISyntaxException {
        log.debug("REST request to save Referral : {}", referralDTO);
        if (referralDTO.getId() != null) {
            throw new BadRequestAlertException("A new referral cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReferralDTO result = referralService.save(referralDTO);
        return ResponseEntity.created(new URI("/api/referrals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /referrals : Updates an existing referral.
     *
     * @param referralDTO the referralDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated referralDTO,
     * or with status 400 (Bad Request) if the referralDTO is not valid,
     * or with status 500 (Internal Server Error) if the referralDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/referrals")
    @Timed
    public ResponseEntity<ReferralDTO> updateReferral(@RequestBody ReferralDTO referralDTO) throws URISyntaxException {
        log.debug("REST request to update Referral : {}", referralDTO);
        if (referralDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ReferralDTO result = referralService.save(referralDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, referralDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /referrals : get all the referrals.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of referrals in body
     */
    @GetMapping("/referrals")
    @Timed
    public List<ReferralDTO> getAllReferrals() {
        log.debug("REST request to get all Referrals");
        return referralService.findAll();
    }

    /**
     * GET  /referrals/:id : get the "id" referral.
     *
     * @param id the id of the referralDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the referralDTO, or with status 404 (Not Found)
     */
    @GetMapping("/referrals/{id}")
    @Timed
    public ResponseEntity<ReferralDTO> getReferral(@PathVariable Long id) {
        log.debug("REST request to get Referral : {}", id);
        Optional<ReferralDTO> referralDTO = referralService.findOne(id);
        return ResponseUtil.wrapOrNotFound(referralDTO);
    }

    /**
     * DELETE  /referrals/:id : delete the "id" referral.
     *
     * @param id the id of the referralDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/referrals/{id}")
    @Timed
    public ResponseEntity<Void> deleteReferral(@PathVariable Long id) {
        log.debug("REST request to delete Referral : {}", id);
        referralService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/referrals?query=:query : search for the referral corresponding
     * to the query.
     *
     * @param query the query of the referral search
     * @return the result of the search
     */
    @GetMapping("/_search/referrals")
    @Timed
    public List<ReferralDTO> searchReferrals(@RequestParam String query) {
        log.debug("REST request to search Referrals for query {}", query);
        return referralService.search(query);
    }

}

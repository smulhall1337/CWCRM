package com.cedarwoods.crm.repository;

import com.cedarwoods.crm.domain.ContactSubStatus;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ContactSubStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContactSubStatusRepository extends JpaRepository<ContactSubStatus, Long> {

}

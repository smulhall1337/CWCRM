package com.cedarwoods.crm.repository;

import com.cedarwoods.crm.domain.ContactStatus;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ContactStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContactStatusRepository extends JpaRepository<ContactStatus, Long> {

}

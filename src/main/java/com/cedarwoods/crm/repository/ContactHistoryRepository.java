package com.cedarwoods.crm.repository;

import com.cedarwoods.crm.domain.ContactHistory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ContactHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContactHistoryRepository extends JpaRepository<ContactHistory, Long> {

}

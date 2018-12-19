package com.cedarwoods.crm.repository;

import com.cedarwoods.crm.domain.Priority;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Priority entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {

}

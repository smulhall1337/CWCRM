package com.cedarwoods.crm.repository;

import com.cedarwoods.crm.domain.ContactHistory;
import com.cedarwoods.crm.domain.Participant;
import org.hibernate.loader.plan.build.internal.LoadGraphLoadPlanBuildingStrategy;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.StreamSupport;


/**
 * Spring Data  repository for the ContactHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContactHistoryRepository extends JpaRepository<ContactHistory, Long> {

    @Query(value ="SELECT * FROM CONTACT_HISTORY WHERE PARTICIPANT_ID = ?1",
            nativeQuery = true)
    List<ContactHistory> findAllByParticipantId(Long partId);
}

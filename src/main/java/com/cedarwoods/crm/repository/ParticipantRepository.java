package com.cedarwoods.crm.repository;

import com.cedarwoods.crm.domain.Participant;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Participant entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    @Query("select participant from Participant participant where participant.assignedTo.login = ?#{principal.username}")
    List<Participant> findByAssignedToIsCurrentUser();

}

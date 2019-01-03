package com.cedarwoods.crm.repository;

import com.cedarwoods.crm.domain.ParticipantNotes;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ParticipantNotes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ParticipantNotesRepository extends JpaRepository<ParticipantNotes, Long> {

}

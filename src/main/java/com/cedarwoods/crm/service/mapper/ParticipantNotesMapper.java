package com.cedarwoods.crm.service.mapper;

import com.cedarwoods.crm.domain.*;
import com.cedarwoods.crm.service.dto.ParticipantNotesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ParticipantNotes and its DTO ParticipantNotesDTO.
 */
@Mapper(componentModel = "spring", uses = {ParticipantMapper.class, UserMapper.class})
public interface ParticipantNotesMapper extends EntityMapper<ParticipantNotesDTO, ParticipantNotes> {

    @Mapping(source = "participant.id", target = "participantId")
    @Mapping(source = "participant.firstName", target = "participantFirstName")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.firstName", target = "userFirstName")
    ParticipantNotesDTO toDto(ParticipantNotes participantNotes);

    @Mapping(source = "participantId", target = "participant")
    @Mapping(source = "userId", target = "user")
    ParticipantNotes toEntity(ParticipantNotesDTO participantNotesDTO);

    default ParticipantNotes fromId(Long id) {
        if (id == null) {
            return null;
        }
        ParticipantNotes participantNotes = new ParticipantNotes();
        participantNotes.setId(id);
        return participantNotes;
    }
}

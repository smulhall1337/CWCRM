package com.cedarwoods.crm.service.mapper;

import com.cedarwoods.crm.domain.*;
import com.cedarwoods.crm.service.dto.ParticipantDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Participant and its DTO ParticipantDTO.
 */
@Mapper(componentModel = "spring", uses = {ContactStatusMapper.class, ContactSubStatusMapper.class, WaiverMapper.class, MCOMapper.class, SupportCoordinatorMapper.class, PhysicianMapper.class})
public interface ParticipantMapper extends EntityMapper<ParticipantDTO, Participant> {

    @Mapping(source = "contactStatus.id", target = "contactStatusId")
    @Mapping(source = "contactSubStatus.id", target = "contactSubStatusId")
    @Mapping(source = "contactSubStatus.name", target = "contactSubStatusName")
    @Mapping(source = "waiver.id", target = "waiverId")
    @Mapping(source = "waiver.name", target = "waiverName")
    @Mapping(source = "mco.id", target = "mcoId")
    @Mapping(source = "mco.name", target = "mcoName")
    @Mapping(source = "supportCoordinator.id", target = "supportCoordinatorId")
    @Mapping(source = "supportCoordinator.firstName", target = "supportCoordinatorFirstName")
    @Mapping(source = "primaryPhysician.id", target = "primaryPhysicianId")
    @Mapping(source = "primaryPhysician.firstName", target = "primaryPhysicianFirstName")
    ParticipantDTO toDto(Participant participant);

    @Mapping(source = "contactStatusId", target = "contactStatus")
    @Mapping(source = "contactSubStatusId", target = "contactSubStatus")
    @Mapping(source = "waiverId", target = "waiver")
    @Mapping(source = "mcoId", target = "mco")
    @Mapping(source = "supportCoordinatorId", target = "supportCoordinator")
    @Mapping(source = "primaryPhysicianId", target = "primaryPhysician")
    @Mapping(target = "referrals", ignore = true)
    Participant toEntity(ParticipantDTO participantDTO);

    default Participant fromId(Long id) {
        if (id == null) {
            return null;
        }
        Participant participant = new Participant();
        participant.setId(id);
        return participant;
    }
}

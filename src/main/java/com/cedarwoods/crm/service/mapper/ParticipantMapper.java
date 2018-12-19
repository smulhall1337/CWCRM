package com.cedarwoods.crm.service.mapper;

import com.cedarwoods.crm.domain.*;
import com.cedarwoods.crm.service.dto.ParticipantDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Participant and its DTO ParticipantDTO.
 */
@Mapper(componentModel = "spring", uses = {ContactStatusMapper.class, ContactSubStatusMapper.class, MCOMapper.class, ReferralTypeMapper.class, ReferralSourceMapper.class, UserMapper.class})
public interface ParticipantMapper extends EntityMapper<ParticipantDTO, Participant> {

    @Mapping(source = "contactStatus.id", target = "contactStatusId")
    @Mapping(source = "contactSubStatus.id", target = "contactSubStatusId")
    @Mapping(source = "contactSubStatus.name", target = "contactSubStatusName")
    @Mapping(source = "mco.id", target = "mcoId")
    @Mapping(source = "mco.name", target = "mcoName")
    @Mapping(source = "referralType.id", target = "referralTypeId")
    @Mapping(source = "referralType.name", target = "referralTypeName")
    @Mapping(source = "referralSource.id", target = "referralSourceId")
    @Mapping(source = "referralSource.name", target = "referralSourceName")
    @Mapping(source = "assignedTo.id", target = "assignedToId")
    ParticipantDTO toDto(Participant participant);

    @Mapping(source = "contactStatusId", target = "contactStatus")
    @Mapping(source = "contactSubStatusId", target = "contactSubStatus")
    @Mapping(source = "mcoId", target = "mco")
    @Mapping(source = "referralTypeId", target = "referralType")
    @Mapping(source = "referralSourceId", target = "referralSource")
    @Mapping(source = "assignedToId", target = "assignedTo")
    @Mapping(target = "action", ignore = true)
    @Mapping(target = "contactHistory", ignore = true)
    @Mapping(target = "participantNotes", ignore = true)
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

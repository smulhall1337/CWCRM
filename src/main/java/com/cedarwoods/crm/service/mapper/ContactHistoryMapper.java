package com.cedarwoods.crm.service.mapper;

import com.cedarwoods.crm.domain.*;
import com.cedarwoods.crm.service.dto.ContactHistoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ContactHistory and its DTO ContactHistoryDTO.
 */
@Mapper(componentModel = "spring", uses = {ParticipantMapper.class, UserMapper.class, ContactTypeMapper.class})
public interface ContactHistoryMapper extends EntityMapper<ContactHistoryDTO, ContactHistory> {

    @Mapping(source = "participant.id", target = "participantId")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "contactType.id", target = "contactTypeId")
    @Mapping(source = "contactType.name", target = "contactTypeName")
    ContactHistoryDTO toDto(ContactHistory contactHistory);

    @Mapping(source = "participantId", target = "participant")
    @Mapping(source = "userId", target = "user")
    @Mapping(source = "contactTypeId", target = "contactType")
    ContactHistory toEntity(ContactHistoryDTO contactHistoryDTO);

    default ContactHistory fromId(Long id) {
        if (id == null) {
            return null;
        }
        ContactHistory contactHistory = new ContactHistory();
        contactHistory.setId(id);
        return contactHistory;
    }
}

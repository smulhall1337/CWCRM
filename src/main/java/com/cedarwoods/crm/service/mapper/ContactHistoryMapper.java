package com.cedarwoods.crm.service.mapper;

import com.cedarwoods.crm.domain.*;
import com.cedarwoods.crm.service.dto.ContactHistoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ContactHistory and its DTO ContactHistoryDTO.
 */
@Mapper(componentModel = "spring", uses = {ContactTypeMapper.class, ParticipantMapper.class, UserMapper.class})
public interface ContactHistoryMapper extends EntityMapper<ContactHistoryDTO, ContactHistory> {

    @Mapping(source = "contactType.id", target = "contactTypeId")
    @Mapping(source = "contactType.name", target = "contactTypeName")
    @Mapping(source = "participant.id", target = "participantId")
    @Mapping(source = "user.id", target = "userId")
    ContactHistoryDTO toDto(ContactHistory contactHistory);

    @Mapping(source = "contactTypeId", target = "contactType")
    @Mapping(source = "participantId", target = "participant")
    @Mapping(source = "userId", target = "user")
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

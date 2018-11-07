package com.cedarwoods.crm.service.mapper;

import com.cedarwoods.crm.domain.*;
import com.cedarwoods.crm.service.dto.ContactStatusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ContactStatus and its DTO ContactStatusDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ContactStatusMapper extends EntityMapper<ContactStatusDTO, ContactStatus> {


    @Mapping(target = "participant", ignore = true)
    ContactStatus toEntity(ContactStatusDTO contactStatusDTO);

    default ContactStatus fromId(Long id) {
        if (id == null) {
            return null;
        }
        ContactStatus contactStatus = new ContactStatus();
        contactStatus.setId(id);
        return contactStatus;
    }
}

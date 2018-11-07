package com.cedarwoods.crm.service.mapper;

import com.cedarwoods.crm.domain.*;
import com.cedarwoods.crm.service.dto.ContactSubStatusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ContactSubStatus and its DTO ContactSubStatusDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ContactSubStatusMapper extends EntityMapper<ContactSubStatusDTO, ContactSubStatus> {



    default ContactSubStatus fromId(Long id) {
        if (id == null) {
            return null;
        }
        ContactSubStatus contactSubStatus = new ContactSubStatus();
        contactSubStatus.setId(id);
        return contactSubStatus;
    }
}

package com.cedarwoods.crm.service.mapper;

import com.cedarwoods.crm.domain.*;
import com.cedarwoods.crm.service.dto.WaiverDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Waiver and its DTO WaiverDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WaiverMapper extends EntityMapper<WaiverDTO, Waiver> {



    default Waiver fromId(Long id) {
        if (id == null) {
            return null;
        }
        Waiver waiver = new Waiver();
        waiver.setId(id);
        return waiver;
    }
}

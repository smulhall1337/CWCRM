package com.cedarwoods.crm.service.mapper;

import com.cedarwoods.crm.domain.*;
import com.cedarwoods.crm.service.dto.PhysicianDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Physician and its DTO PhysicianDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PhysicianMapper extends EntityMapper<PhysicianDTO, Physician> {



    default Physician fromId(Long id) {
        if (id == null) {
            return null;
        }
        Physician physician = new Physician();
        physician.setId(id);
        return physician;
    }
}

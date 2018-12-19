package com.cedarwoods.crm.service.mapper;

import com.cedarwoods.crm.domain.*;
import com.cedarwoods.crm.service.dto.EmployeeSubTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EmployeeSubType and its DTO EmployeeSubTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EmployeeSubTypeMapper extends EntityMapper<EmployeeSubTypeDTO, EmployeeSubType> {



    default EmployeeSubType fromId(Long id) {
        if (id == null) {
            return null;
        }
        EmployeeSubType employeeSubType = new EmployeeSubType();
        employeeSubType.setId(id);
        return employeeSubType;
    }
}

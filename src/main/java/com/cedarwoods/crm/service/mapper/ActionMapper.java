package com.cedarwoods.crm.service.mapper;

import com.cedarwoods.crm.domain.*;
import com.cedarwoods.crm.service.dto.ActionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Action and its DTO ActionDTO.
 */
@Mapper(componentModel = "spring", uses = {SupportCoordinatorMapper.class, ParticipantMapper.class, PriorityMapper.class})
public interface ActionMapper extends EntityMapper<ActionDTO, Action> {

    @Mapping(source = "assignedTo.id", target = "assignedToId")
    @Mapping(source = "assignedTo.firstName", target = "assignedToFirstName")
    @Mapping(source = "participant.id", target = "participantId")
    @Mapping(source = "participant.firstName", target = "participantFirstName")
    @Mapping(source = "priority.id", target = "priorityId")
    @Mapping(source = "priority.name", target = "priorityName")
    ActionDTO toDto(Action action);

    @Mapping(source = "assignedToId", target = "assignedTo")
    @Mapping(source = "participantId", target = "participant")
    @Mapping(source = "priorityId", target = "priority")
    Action toEntity(ActionDTO actionDTO);

    default Action fromId(Long id) {
        if (id == null) {
            return null;
        }
        Action action = new Action();
        action.setId(id);
        return action;
    }
}

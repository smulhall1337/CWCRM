package com.cedarwoods.crm.service.mapper;

import com.cedarwoods.crm.domain.*;
import com.cedarwoods.crm.service.dto.ActionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Action and its DTO ActionDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, ParticipantMapper.class})
public interface ActionMapper extends EntityMapper<ActionDTO, Action> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "participant.id", target = "participantId")
    ActionDTO toDto(Action action);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "participantId", target = "participant")
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

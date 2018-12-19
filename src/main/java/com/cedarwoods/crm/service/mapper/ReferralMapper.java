package com.cedarwoods.crm.service.mapper;

import com.cedarwoods.crm.domain.*;
import com.cedarwoods.crm.service.dto.ReferralDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Referral and its DTO ReferralDTO.
 */
@Mapper(componentModel = "spring", uses = {ParticipantMapper.class})
public interface ReferralMapper extends EntityMapper<ReferralDTO, Referral> {

    @Mapping(source = "participant.id", target = "participantId")
    ReferralDTO toDto(Referral referral);

    @Mapping(source = "participantId", target = "participant")
    Referral toEntity(ReferralDTO referralDTO);

    default Referral fromId(Long id) {
        if (id == null) {
            return null;
        }
        Referral referral = new Referral();
        referral.setId(id);
        return referral;
    }
}

package com.cedarwoods.crm.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Referral entity.
 */
public class ReferralDTO implements Serializable {

    private Long id;

    private String name;

    private Long participantId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ReferralDTO referralDTO = (ReferralDTO) o;
        if (referralDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), referralDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ReferralDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", participant=" + getParticipantId() +
            "}";
    }
}

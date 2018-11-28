package com.cedarwoods.crm.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Action entity.
 */
public class ActionDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    private Long userId;

    private Long participantId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

        ActionDTO actionDTO = (ActionDTO) o;
        if (actionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), actionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ActionDTO{" +
            "id=" + getId() +
            ", user=" + getUserId() +
            ", participant=" + getParticipantId() +
            "}";
    }
}

package com.cedarwoods.crm.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the ParticipantNotes entity.
 */
public class ParticipantNotesDTO implements Serializable {

    private Long id;

    @Lob
    private String notes;

    private Long participantId;

    private String participantFirstName;

    private Long userId;

    private String userFirstName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }

    public String getParticipantFirstName() {
        return participantFirstName;
    }

    public void setParticipantFirstName(String participantFirstName) {
        this.participantFirstName = participantFirstName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ParticipantNotesDTO participantNotesDTO = (ParticipantNotesDTO) o;
        if (participantNotesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), participantNotesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ParticipantNotesDTO{" +
            "id=" + getId() +
            ", notes='" + getNotes() + "'" +
            ", participant=" + getParticipantId() +
            ", participant='" + getParticipantFirstName() + "'" +
            ", user=" + getUserId() +
            ", user='" + getUserFirstName() + "'" +
            "}";
    }
}

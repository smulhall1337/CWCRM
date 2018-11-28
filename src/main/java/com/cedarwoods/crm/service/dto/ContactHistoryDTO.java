package com.cedarwoods.crm.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the ContactHistory entity.
 */
public class ContactHistoryDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    private LocalDate date;

    @Lob
    private String notes;

    private Long contactTypeId;

    private String contactTypeName;

    private Long participantId;

    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getContactTypeId() {
        return contactTypeId;
    }

    public void setContactTypeId(Long contactTypeId) {
        this.contactTypeId = contactTypeId;
    }

    public String getContactTypeName() {
        return contactTypeName;
    }

    public void setContactTypeName(String contactTypeName) {
        this.contactTypeName = contactTypeName;
    }

    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ContactHistoryDTO contactHistoryDTO = (ContactHistoryDTO) o;
        if (contactHistoryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), contactHistoryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ContactHistoryDTO{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", notes='" + getNotes() + "'" +
            ", contactType=" + getContactTypeId() +
            ", contactType='" + getContactTypeName() + "'" +
            ", participant=" + getParticipantId() +
            ", user=" + getUserId() +
            "}";
    }
}

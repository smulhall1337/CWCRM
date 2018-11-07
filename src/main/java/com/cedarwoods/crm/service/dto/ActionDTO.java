package com.cedarwoods.crm.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Action entity.
 */
public class ActionDTO implements Serializable {

    private Long id;

    private LocalDate dueDate;

    private Long assignedToId;

    private String assignedToFirstName;

    private Long participantId;

    private String participantFirstName;

    private Long priorityId;

    private String priorityName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Long getAssignedToId() {
        return assignedToId;
    }

    public void setAssignedToId(Long supportCoordinatorId) {
        this.assignedToId = supportCoordinatorId;
    }

    public String getAssignedToFirstName() {
        return assignedToFirstName;
    }

    public void setAssignedToFirstName(String supportCoordinatorFirstName) {
        this.assignedToFirstName = supportCoordinatorFirstName;
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

    public Long getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(Long priorityId) {
        this.priorityId = priorityId;
    }

    public String getPriorityName() {
        return priorityName;
    }

    public void setPriorityName(String priorityName) {
        this.priorityName = priorityName;
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
            ", dueDate='" + getDueDate() + "'" +
            ", assignedTo=" + getAssignedToId() +
            ", assignedTo='" + getAssignedToFirstName() + "'" +
            ", participant=" + getParticipantId() +
            ", participant='" + getParticipantFirstName() + "'" +
            ", priority=" + getPriorityId() +
            ", priority='" + getPriorityName() + "'" +
            "}";
    }
}

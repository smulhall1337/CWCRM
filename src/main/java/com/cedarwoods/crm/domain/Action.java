package com.cedarwoods.crm.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Action.
 */
@Entity
@Table(name = "action")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "action")
public class Action implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @OneToOne    @JoinColumn(unique = true)
    private SupportCoordinator assignedTo;

    @OneToOne    @JoinColumn(unique = true)
    private Participant participant;

    @OneToOne    @JoinColumn(unique = true)
    private Priority priority;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public Action dueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public SupportCoordinator getAssignedTo() {
        return assignedTo;
    }

    public Action assignedTo(SupportCoordinator supportCoordinator) {
        this.assignedTo = supportCoordinator;
        return this;
    }

    public void setAssignedTo(SupportCoordinator supportCoordinator) {
        this.assignedTo = supportCoordinator;
    }

    public Participant getParticipant() {
        return participant;
    }

    public Action participant(Participant participant) {
        this.participant = participant;
        return this;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public Priority getPriority() {
        return priority;
    }

    public Action priority(Priority priority) {
        this.priority = priority;
        return this;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Action action = (Action) o;
        if (action.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), action.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Action{" +
            "id=" + getId() +
            ", dueDate='" + getDueDate() + "'" +
            "}";
    }
}

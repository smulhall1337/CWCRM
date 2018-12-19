package com.cedarwoods.crm.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A ContactHistory.
 */
@Entity
@Table(name = "contact_history")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "contacthistory")
public class ContactHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "jhi_date")
    private LocalDate date;

    @Lob
    @Column(name = "notes")
    private String notes;

    @OneToOne    @JoinColumn(unique = true)
    private Participant participant;

    @OneToOne    @JoinColumn(unique = true)
    private User user;

    @OneToOne    @JoinColumn(unique = true)
    private ContactType contactType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public ContactHistory date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public ContactHistory notes(String notes) {
        this.notes = notes;
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Participant getParticipant() {
        return participant;
    }

    public ContactHistory participant(Participant participant) {
        this.participant = participant;
        return this;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public User getUser() {
        return user;
    }

    public ContactHistory user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public ContactHistory contactType(ContactType contactType) {
        this.contactType = contactType;
        return this;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
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
        ContactHistory contactHistory = (ContactHistory) o;
        if (contactHistory.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), contactHistory.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ContactHistory{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", notes='" + getNotes() + "'" +
            "}";
    }
}

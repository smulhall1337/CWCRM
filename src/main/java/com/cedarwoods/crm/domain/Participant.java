package com.cedarwoods.crm.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Participant.
 */
@Entity
@Table(name = "participant")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "participant")
public class Participant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotNull
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @Column(name = "address_1")
    private String address1;

    @Column(name = "address_2")
    private String address2;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @Column(name = "dob")
    private String dob;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "zip")
    private String zip;

    @Column(name = "m_an_number")
    private Long mANNumber;

    @OneToOne    @JoinColumn(unique = true)
    private ContactStatus contactStatus;

    @OneToOne    @JoinColumn(unique = true)
    private ContactSubStatus contactSubStatus;

    @OneToOne    @JoinColumn(unique = true)
    private Waiver waiver;

    @ManyToOne
    @JsonIgnoreProperties("")
    private User supportCoordinator;

    @OneToOne(mappedBy = "participant")
    @JsonIgnore
    private Action action;

    @OneToOne(mappedBy = "participant")
    @JsonIgnore
    private ContactHistory contactHistory;

    @OneToMany(mappedBy = "participant")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ParticipantNotes> participantNotes = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public Participant firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Participant lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public Participant registrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
        return this;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getAddress1() {
        return address1;
    }

    public Participant address1(String address1) {
        this.address1 = address1;
        return this;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public Participant address2(String address2) {
        this.address2 = address2;
        return this;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public Participant city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public Participant state(String state) {
        this.state = state;
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public Participant country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDob() {
        return dob;
    }

    public Participant dob(String dob) {
        this.dob = dob;
        return this;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public Participant phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public Participant email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getZip() {
        return zip;
    }

    public Participant zip(String zip) {
        this.zip = zip;
        return this;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Long getmANNumber() {
        return mANNumber;
    }

    public Participant mANNumber(Long mANNumber) {
        this.mANNumber = mANNumber;
        return this;
    }

    public void setmANNumber(Long mANNumber) {
        this.mANNumber = mANNumber;
    }

    public ContactStatus getContactStatus() {
        return contactStatus;
    }

    public Participant contactStatus(ContactStatus contactStatus) {
        this.contactStatus = contactStatus;
        return this;
    }

    public void setContactStatus(ContactStatus contactStatus) {
        this.contactStatus = contactStatus;
    }

    public ContactSubStatus getContactSubStatus() {
        return contactSubStatus;
    }

    public Participant contactSubStatus(ContactSubStatus contactSubStatus) {
        this.contactSubStatus = contactSubStatus;
        return this;
    }

    public void setContactSubStatus(ContactSubStatus contactSubStatus) {
        this.contactSubStatus = contactSubStatus;
    }

    public Waiver getWaiver() {
        return waiver;
    }

    public Participant waiver(Waiver waiver) {
        this.waiver = waiver;
        return this;
    }

    public void setWaiver(Waiver waiver) {
        this.waiver = waiver;
    }

    public User getSupportCoordinator() {
        return supportCoordinator;
    }

    public Participant supportCoordinator(User user) {
        this.supportCoordinator = user;
        return this;
    }

    public void setSupportCoordinator(User user) {
        this.supportCoordinator = user;
    }

    public Action getAction() {
        return action;
    }

    public Participant action(Action action) {
        this.action = action;
        return this;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public ContactHistory getContactHistory() {
        return contactHistory;
    }

    public Participant contactHistory(ContactHistory contactHistory) {
        this.contactHistory = contactHistory;
        return this;
    }

    public void setContactHistory(ContactHistory contactHistory) {
        this.contactHistory = contactHistory;
    }

    public Set<ParticipantNotes> getParticipantNotes() {
        return participantNotes;
    }

    public Participant participantNotes(Set<ParticipantNotes> participantNotes) {
        this.participantNotes = participantNotes;
        return this;
    }

    public Participant addParticipantNotes(ParticipantNotes participantNotes) {
        this.participantNotes.add(participantNotes);
        participantNotes.setParticipant(this);
        return this;
    }

    public Participant removeParticipantNotes(ParticipantNotes participantNotes) {
        this.participantNotes.remove(participantNotes);
        participantNotes.setParticipant(null);
        return this;
    }

    public void setParticipantNotes(Set<ParticipantNotes> participantNotes) {
        this.participantNotes = participantNotes;
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
        Participant participant = (Participant) o;
        if (participant.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), participant.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Participant{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", registrationDate='" + getRegistrationDate() + "'" +
            ", address1='" + getAddress1() + "'" +
            ", address2='" + getAddress2() + "'" +
            ", city='" + getCity() + "'" +
            ", state='" + getState() + "'" +
            ", country='" + getCountry() + "'" +
            ", dob='" + getDob() + "'" +
            ", phone='" + getPhone() + "'" +
            ", email='" + getEmail() + "'" +
            ", zip='" + getZip() + "'" +
            ", mANNumber=" + getmANNumber() +
            "}";
    }
}

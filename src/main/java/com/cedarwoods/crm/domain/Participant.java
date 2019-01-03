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

    @Column(name = "middle_initial")
    private String middleInitial;

    @NotNull
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "title")
    private String title;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "primary_phone")
    private String primaryPhone;

    @Column(name = "primary_phone_type")
    private String primaryPhoneType;

    @Column(name = "secondary_phone")
    private String secondaryPhone;

    @Column(name = "secondary_phone_type")
    private String secondaryPhoneType;

    @Column(name = "email")
    private String email;

    @Column(name = "zip")
    private String zip;

    @Column(name = "medicare_id_number")
    private String medicareIdNumber;

    @Column(name = "medicaid_id_number")
    private String medicaidIdNumber;

    @Column(name = "gender")
    private String gender;

    @OneToOne    @JoinColumn(unique = true)
    private ContactStatus contactStatus;

    @OneToOne    @JoinColumn(unique = true)
    private ContactSubStatus contactSubStatus;

    @OneToOne    @JoinColumn(unique = true)
    private MCO mco;

    @OneToOne    @JoinColumn(unique = true)
    private ReferralType referralType;

    @OneToOne    @JoinColumn(unique = true)
    private ReferralSource referralSource;

    @OneToMany(mappedBy = "participant")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ParticipantNotes> participantNotes = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("")
    private User assignedTo;

    @OneToOne(mappedBy = "participant")
    @JsonIgnore
    private Action action;

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

    public String getMiddleInitial() {
        return middleInitial;
    }

    public Participant middleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
        return this;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
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

    public String getTitle() {
        return title;
    }

    public Participant title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getAddress() {
        return address;
    }

    public Participant address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public LocalDate getDob() {
        return dob;
    }

    public Participant dob(LocalDate dob) {
        this.dob = dob;
        return this;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getPrimaryPhone() {
        return primaryPhone;
    }

    public Participant primaryPhone(String primaryPhone) {
        this.primaryPhone = primaryPhone;
        return this;
    }

    public void setPrimaryPhone(String primaryPhone) {
        this.primaryPhone = primaryPhone;
    }

    public String getPrimaryPhoneType() {
        return primaryPhoneType;
    }

    public Participant primaryPhoneType(String primaryPhoneType) {
        this.primaryPhoneType = primaryPhoneType;
        return this;
    }

    public void setPrimaryPhoneType(String primaryPhoneType) {
        this.primaryPhoneType = primaryPhoneType;
    }

    public String getSecondaryPhone() {
        return secondaryPhone;
    }

    public Participant secondaryPhone(String secondaryPhone) {
        this.secondaryPhone = secondaryPhone;
        return this;
    }

    public void setSecondaryPhone(String secondaryPhone) {
        this.secondaryPhone = secondaryPhone;
    }

    public String getSecondaryPhoneType() {
        return secondaryPhoneType;
    }

    public Participant secondaryPhoneType(String secondaryPhoneType) {
        this.secondaryPhoneType = secondaryPhoneType;
        return this;
    }

    public void setSecondaryPhoneType(String secondaryPhoneType) {
        this.secondaryPhoneType = secondaryPhoneType;
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

    public String getMedicareIdNumber() {
        return medicareIdNumber;
    }

    public Participant medicareIdNumber(String medicareIdNumber) {
        this.medicareIdNumber = medicareIdNumber;
        return this;
    }

    public void setMedicareIdNumber(String medicareIdNumber) {
        this.medicareIdNumber = medicareIdNumber;
    }

    public String getMedicaidIdNumber() {
        return medicaidIdNumber;
    }

    public Participant medicaidIdNumber(String medicaidIdNumber) {
        this.medicaidIdNumber = medicaidIdNumber;
        return this;
    }

    public void setMedicaidIdNumber(String medicaidIdNumber) {
        this.medicaidIdNumber = medicaidIdNumber;
    }

    public String getGender() {
        return gender;
    }

    public Participant gender(String gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public MCO getMco() {
        return mco;
    }

    public Participant mco(MCO mCO) {
        this.mco = mCO;
        return this;
    }

    public void setMco(MCO mCO) {
        this.mco = mCO;
    }

    public ReferralType getReferralType() {
        return referralType;
    }

    public Participant referralType(ReferralType referralType) {
        this.referralType = referralType;
        return this;
    }

    public void setReferralType(ReferralType referralType) {
        this.referralType = referralType;
    }

    public ReferralSource getReferralSource() {
        return referralSource;
    }

    public Participant referralSource(ReferralSource referralSource) {
        this.referralSource = referralSource;
        return this;
    }

    public void setReferralSource(ReferralSource referralSource) {
        this.referralSource = referralSource;
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

    public User getAssignedTo() {
        return assignedTo;
    }

    public Participant assignedTo(User user) {
        this.assignedTo = user;
        return this;
    }

    public void setAssignedTo(User user) {
        this.assignedTo = user;
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
            ", middleInitial='" + getMiddleInitial() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", title='" + getTitle() + "'" +
            ", registrationDate='" + getRegistrationDate() + "'" +
            ", address='" + getAddress() + "'" +
            ", city='" + getCity() + "'" +
            ", state='" + getState() + "'" +
            ", country='" + getCountry() + "'" +
            ", dob='" + getDob() + "'" +
            ", primaryPhone='" + getPrimaryPhone() + "'" +
            ", primaryPhoneType='" + getPrimaryPhoneType() + "'" +
            ", secondaryPhone='" + getSecondaryPhone() + "'" +
            ", secondaryPhoneType='" + getSecondaryPhoneType() + "'" +
            ", email='" + getEmail() + "'" +
            ", zip='" + getZip() + "'" +
            ", medicareIdNumber='" + getMedicareIdNumber() + "'" +
            ", medicaidIdNumber='" + getMedicaidIdNumber() + "'" +
            ", gender='" + getGender() + "'" +
            "}";
    }
}

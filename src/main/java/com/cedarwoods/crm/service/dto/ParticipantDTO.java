package com.cedarwoods.crm.service.dto;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the Participant entity.
 */
public class ParticipantDTO implements Serializable {

    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    private LocalDate registrationDate;

    private String address1;

    private String address2;

    private String city;

    private String state;

    private String country;

    private String dob;

    private String phone;

    private String email;

    private String zip;

    private Long manNumber;

    private Boolean deceased;

    private ZonedDateTime created;

    private ZonedDateTime updated;

    private Boolean isActive;

    @Lob
    private String altContactInfo;

    private Long contactStatusId;

    private Long contactSubStatusId;

    private String contactSubStatusName;

    private Long waiverId;

    private String waiverName;

    private Long mcoId;

    private String mcoName;

    private Long supportCoordinatorId;

    private String supportCoordinatorFirstName;

    private Long primaryPhysicianId;

    private String primaryPhysicianFirstName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Long getManNumber() {
        return manNumber;
    }

    public void setManNumber(Long manNumber) {
        this.manNumber = manNumber;
    }

    public Boolean isDeceased() {
        return deceased;
    }

    public void setDeceased(Boolean deceased) {
        this.deceased = deceased;
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public void setCreated(ZonedDateTime created) {
        this.created = created;
    }

    public ZonedDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(ZonedDateTime updated) {
        this.updated = updated;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getAltContactInfo() {
        return altContactInfo;
    }

    public void setAltContactInfo(String altContactInfo) {
        this.altContactInfo = altContactInfo;
    }

    public Long getContactStatusId() {
        return contactStatusId;
    }

    public void setContactStatusId(Long contactStatusId) {
        this.contactStatusId = contactStatusId;
    }

    public Long getContactSubStatusId() {
        return contactSubStatusId;
    }

    public void setContactSubStatusId(Long contactSubStatusId) {
        this.contactSubStatusId = contactSubStatusId;
    }

    public String getContactSubStatusName() {
        return contactSubStatusName;
    }

    public void setContactSubStatusName(String contactSubStatusName) {
        this.contactSubStatusName = contactSubStatusName;
    }

    public Long getWaiverId() {
        return waiverId;
    }

    public void setWaiverId(Long waiverId) {
        this.waiverId = waiverId;
    }

    public String getWaiverName() {
        return waiverName;
    }

    public void setWaiverName(String waiverName) {
        this.waiverName = waiverName;
    }

    public Long getMcoId() {
        return mcoId;
    }

    public void setMcoId(Long mCOId) {
        this.mcoId = mCOId;
    }

    public String getMcoName() {
        return mcoName;
    }

    public void setMcoName(String mCOName) {
        this.mcoName = mCOName;
    }

    public Long getSupportCoordinatorId() {
        return supportCoordinatorId;
    }

    public void setSupportCoordinatorId(Long supportCoordinatorId) {
        this.supportCoordinatorId = supportCoordinatorId;
    }

    public String getSupportCoordinatorFirstName() {
        return supportCoordinatorFirstName;
    }

    public void setSupportCoordinatorFirstName(String supportCoordinatorFirstName) {
        this.supportCoordinatorFirstName = supportCoordinatorFirstName;
    }

    public Long getPrimaryPhysicianId() {
        return primaryPhysicianId;
    }

    public void setPrimaryPhysicianId(Long physicianId) {
        this.primaryPhysicianId = physicianId;
    }

    public String getPrimaryPhysicianFirstName() {
        return primaryPhysicianFirstName;
    }

    public void setPrimaryPhysicianFirstName(String physicianFirstName) {
        this.primaryPhysicianFirstName = physicianFirstName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ParticipantDTO participantDTO = (ParticipantDTO) o;
        if (participantDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), participantDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ParticipantDTO{" +
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
            ", manNumber=" + getManNumber() +
            ", deceased='" + isDeceased() + "'" +
            ", created='" + getCreated() + "'" +
            ", updated='" + getUpdated() + "'" +
            ", isActive='" + isIsActive() + "'" +
            ", altContactInfo='" + getAltContactInfo() + "'" +
            ", contactStatus=" + getContactStatusId() +
            ", contactSubStatus=" + getContactSubStatusId() +
            ", contactSubStatus='" + getContactSubStatusName() + "'" +
            ", waiver=" + getWaiverId() +
            ", waiver='" + getWaiverName() + "'" +
            ", mco=" + getMcoId() +
            ", mco='" + getMcoName() + "'" +
            ", supportCoordinator=" + getSupportCoordinatorId() +
            ", supportCoordinator='" + getSupportCoordinatorFirstName() + "'" +
            ", primaryPhysician=" + getPrimaryPhysicianId() +
            ", primaryPhysician='" + getPrimaryPhysicianFirstName() + "'" +
            "}";
    }
}

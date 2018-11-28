package com.cedarwoods.crm.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Participant entity.
 */
public class ParticipantDTO extends AbstractAuditingDTO implements Serializable {

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

    private Long mANNumber;

    private Long contactStatusId;

    private Long contactSubStatusId;

    private String contactSubStatusName;

    private Long waiverId;

    private String waiverName;

    private Long supportCoordinatorId;

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

    public Long getmANNumber() {
        return mANNumber;
    }

    public void setmANNumber(Long mANNumber) {
        this.mANNumber = mANNumber;
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

    public Long getSupportCoordinatorId() {
        return supportCoordinatorId;
    }

    public void setSupportCoordinatorId(Long userId) {
        this.supportCoordinatorId = userId;
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
            ", mANNumber=" + getmANNumber() +
            ", contactStatus=" + getContactStatusId() +
            ", contactSubStatus=" + getContactSubStatusId() +
            ", contactSubStatus='" + getContactSubStatusName() + "'" +
            ", waiver=" + getWaiverId() +
            ", waiver='" + getWaiverName() + "'" +
            ", supportCoordinator=" + getSupportCoordinatorId() +
            "}";
    }
}

package com.cedarwoods.crm.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Physician.
 */
@Entity
@Table(name = "physician")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "physician")
public class Physician implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "fax")
    private String fax;

    @Column(name = "address")
    private String address;

    @Lob
    @Column(name = "notes")
    private String notes;

    @Column(name = "disability")
    private String disability;

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

    public Physician firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Physician lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public Physician phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public Physician fax(String fax) {
        this.fax = fax;
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAddress() {
        return address;
    }

    public Physician address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNotes() {
        return notes;
    }

    public Physician notes(String notes) {
        this.notes = notes;
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDisability() {
        return disability;
    }

    public Physician disability(String disability) {
        this.disability = disability;
        return this;
    }

    public void setDisability(String disability) {
        this.disability = disability;
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
        Physician physician = (Physician) o;
        if (physician.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), physician.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Physician{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", phone='" + getPhone() + "'" +
            ", fax='" + getFax() + "'" +
            ", address='" + getAddress() + "'" +
            ", notes='" + getNotes() + "'" +
            ", disability='" + getDisability() + "'" +
            "}";
    }
}

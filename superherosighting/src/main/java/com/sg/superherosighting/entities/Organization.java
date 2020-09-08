/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting.entities;

import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author Minul
 */
@Entity(name = "organization")
public class Organization {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "organizationid")
    private int id;

    @Column(nullable = false)
    @NotBlank(message = "Organization name cannot be empty.")
    @Size(max = 45, message = "Organization name must be less than 45 characters.")
    private String name;

    @Column
    private String description;

    @Column
    private String address;

    @Column(name = "contactinfo")
    private String contactInfo;

    @ManyToMany(mappedBy = "organizations")
    private List<Superhero> superheros;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public List<Superhero> getSuperheros() {
        return superheros;
    }

    public void setSuperheros(List<Superhero> superheros) {
        this.superheros = superheros;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.id;
        hash = 47 * hash + Objects.hashCode(this.name);
        hash = 47 * hash + Objects.hashCode(this.description);
        hash = 47 * hash + Objects.hashCode(this.address);
        hash = 47 * hash + Objects.hashCode(this.contactInfo);
        hash = 47 * hash + Objects.hashCode(this.superheros);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Organization other = (Organization) obj;
        return true;
    }

}

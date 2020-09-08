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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author Minul
 */
@Entity
public class Superhero {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "superheroid")
    private int id;

    @Column(nullable = false)
    @NotBlank(message = "Superhero name cannot be empty.")
    @Size(max = 45, message = "Superhero name must be less than 45 characters.")
    private String name;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "superpowerid")
    private Superpower superpower;

    @ManyToMany
    @JoinTable(name = "superhero_organization",
            joinColumns = {
                @JoinColumn(name = "superheroid")},
            inverseJoinColumns = {
                @JoinColumn(name = "organizationid")})
    private List<Organization> organizations;

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

    public Superpower getSuperpower() {
        return superpower;
    }

    public void setSuperpower(Superpower superpower) {
        this.superpower = superpower;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.id;
        hash = 23 * hash + Objects.hashCode(this.name);
        hash = 23 * hash + Objects.hashCode(this.description);
        hash = 23 * hash + Objects.hashCode(this.superpower);
        hash = 23 * hash + Objects.hashCode(this.organizations);
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
        final Superhero other = (Superhero) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.superpower, other.superpower)) {
            return false;
        }
        if (!Objects.equals(this.organizations, other.organizations)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Superhero{" + "id=" + id + ", name=" + name + ", description=" + description + ", superpower=" + superpower + ", organizations=" + organizations + '}';
    }

}

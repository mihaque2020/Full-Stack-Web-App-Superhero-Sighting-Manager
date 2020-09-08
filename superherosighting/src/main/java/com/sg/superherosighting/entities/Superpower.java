/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting.entities;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author Minul
 */
@Entity
public class Superpower {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "superpowerid")
    private int id;
    
    @Column(nullable = false)
    @NotBlank(message = "Superpower name cannot be empty.")
    @Size(max = 45, message = "Superpower name must be less than 45 characters.")
    private String power;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + this.id;
        hash = 19 * hash + Objects.hashCode(this.power);
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
        final Superpower other = (Superpower) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.power, other.power)) {
            return false;
        }
        return true;
    }
    
    
}

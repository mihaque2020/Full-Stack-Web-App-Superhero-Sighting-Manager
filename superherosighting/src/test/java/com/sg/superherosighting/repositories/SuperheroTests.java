/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting.repositories;

import com.sg.superherosighting.entities.Superhero;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

/**
 *
 * @author Minul
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class SuperheroTests {

    @Autowired
    private SuperheroRepository superheroRepo;

    @Test
    @Order(1)
    public void testCreateSuperhero() {
        // Arrange
        Superhero superhero = new Superhero();
        superhero.setName("Superman");
        superhero.setDescription("Man of Steel");

        // Act
        Superhero savedSuperhero = superheroRepo.save(superhero);

        // Assert
        assertNotNull(savedSuperhero);
    }

    @Test
    @Order(2)
    public void testFindProductById() {
        // Arrange
        Superhero superhero = new Superhero();
        superhero.setName("Batman");
        superhero.setDescription("The Dark Knight");

        // Act
        Superhero savedSuperhero = superheroRepo.save(superhero);
        Optional<Superhero> retrievedSuperhero = superheroRepo.findById(savedSuperhero.getId());

        // Assert
        assertThat(retrievedSuperhero.get()).isEqualTo(savedSuperhero);
    }
    
    @Test
    @Order(3)
    public void testUpdateSuperhero() {
        String superheroName = "Frozone";
        Superhero editedSuperhero =  new Superhero();
        editedSuperhero.setName(superheroName);
        editedSuperhero.setId(2);
        
        Superhero updatedSuperhero = superheroRepo.save(editedSuperhero);
        Optional<Superhero> retrievedSuperhero = superheroRepo.findById(2);
        
        assertThat(retrievedSuperhero.get()).isEqualTo(editedSuperhero);
    }
    
    @Test
    @Order(4)
    public void testListSuperheros() {
        Superhero newSuperhero = new Superhero();
        newSuperhero.setName("Batman");
        newSuperhero.setDescription("The Dark Knight");
        superheroRepo.save(newSuperhero);
        
        List<Superhero> superheros = superheroRepo.findAll();
        
        for (Superhero superhero : superheros) {
            System.out.println(superhero);
        }
        
        assertThat(superheros).size().isGreaterThan(0);
    }
}

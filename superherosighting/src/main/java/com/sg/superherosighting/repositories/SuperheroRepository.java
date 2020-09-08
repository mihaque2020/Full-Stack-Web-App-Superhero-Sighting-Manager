/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting.repositories;

import com.sg.superherosighting.entities.Superhero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 *
 * @author Minul
 */
@Repository
public interface SuperheroRepository extends JpaRepository<Superhero, Integer>{
    
}

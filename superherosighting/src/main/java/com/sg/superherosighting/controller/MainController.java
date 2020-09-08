/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting.controller;

import com.sg.superherosighting.entities.Location;
import com.sg.superherosighting.entities.Organization;
import com.sg.superherosighting.entities.Sighting;
import com.sg.superherosighting.entities.Superhero;
import com.sg.superherosighting.entities.Superpower;
import com.sg.superherosighting.repositories.LocationRepository;
import com.sg.superherosighting.repositories.OrganizationRepository;
import com.sg.superherosighting.repositories.SightingRepository;
import com.sg.superherosighting.repositories.SuperheroRepository;
import com.sg.superherosighting.repositories.SuperpowerRepository;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Minul
 */
@Controller
public class MainController {

    @Autowired
    LocationRepository locations;

    @Autowired
    SightingRepository sightings;

    @Autowired
    SuperheroRepository superheros;

    @GetMapping("/")
    public String organizations(Model model) {

        List<Sighting> sightingsList = sightings.findAll();
        List<Superhero> superheroList = superheros.findAll();
        List<Location> locationList = locations.findAll();

        model.addAttribute("sightings", sightingsList);
        model.addAttribute("superheros", superheroList);
        model.addAttribute("locations", locationList);

        return "index";
    }

}

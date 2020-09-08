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
import com.sg.superherosighting.repositories.SightingRepository;
import com.sg.superherosighting.repositories.SuperheroRepository;
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
public class SightingController {
    
    @Autowired
    SightingRepository sightings;
    
    @Autowired
    SuperheroRepository superheros;
    
    @Autowired
    LocationRepository locations;
    
    @GetMapping("/sightings")
    public String sightings(Model model) {
        List<Sighting> sightingsList = sightings.findAll();
        List<Superhero> superheroList = superheros.findAll();
        List<Location> locationList = locations.findAll();
        
        model.addAttribute("sightings", sightingsList);
        model.addAttribute("superheros", superheroList);
        model.addAttribute("locations", locationList);

        return "sightings";
    }

    @PostMapping("/addSighting")
    @Transactional
    public String addSighting(Sighting sighting, HttpServletRequest request) {
        String superheroId = request.getParameter("superheroId");
        String locationId = request.getParameter("locationId");
        
        sighting.setSuperhero(superheros.findById(Integer.parseInt(superheroId)).get());
        sighting.setLocation(locations.findById(Integer.parseInt(locationId)).get());
        
        sightings.save(sighting);

        return "redirect:/sightings";
    }

    @GetMapping("/sightingDetail")
    public String sightingDetail(Integer id, Model model) {
        Sighting sighting = sightings.findById(id).orElse(null);
        model.addAttribute("sighting", sighting);

        return "sightingDetail";
    }

    @GetMapping("deleteSighting")
    @Transactional
    public String deleteSighting(Integer id) {
        sightings.deleteById(id);
        return "redirect:/sightings";
    }

    @GetMapping("editSighting")
    public String editSighting(Integer id, Model model) {
        Optional<Sighting> sighting = sightings.findById(id);        
        List<Superhero> superheroList = superheros.findAll();
        List<Location> locationList = locations.findAll();
        
        model.addAttribute("sighting", sighting.get());
        model.addAttribute("superheros", superheroList);
        model.addAttribute("locations", locationList);

        return "editSighting";
    }

    @PostMapping("editSighting")
    @Transactional
    public String performEditSighting(Sighting sighting, HttpServletRequest request) {
        String superheroId = request.getParameter("superheroId");
        String locationId = request.getParameter("locationId");
        
        sighting.setSuperhero(superheros.findById(Integer.parseInt(superheroId)).get());
        sighting.setLocation(locations.findById(Integer.parseInt(locationId)).get());
        
        sightings.save(sighting);
        
        return "redirect:/sightings";
    }
}

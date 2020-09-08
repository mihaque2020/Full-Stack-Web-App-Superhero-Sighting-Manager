/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting.controller;

import com.sg.superherosighting.entities.Location;
import com.sg.superherosighting.repositories.LocationRepository;
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
public class LocationController {

    @Autowired
    LocationRepository locations;

    @GetMapping("/locations")
    public String locations(Model model) {

        List<Location> locationList = locations.findAll();

        model.addAttribute("locations", locationList);

        return "locations";
    }

    @PostMapping("/addLocation")
    @Transactional
    public String addLocation(Location location) {
        locations.save(location);
        return "redirect:/locations";
    }

    @GetMapping("/deleteLocation")
    @Transactional
    public String deleteLocation(Integer id) {
        locations.deleteById(id);
        return "redirect:/locations";
    }

    @GetMapping("/editLocation")
    public String editLocation(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Optional<Location> location = locations.findById(id);

        model.addAttribute("location", location.get());

        return "editLocation";
    }

    @PostMapping("/editLocation")
    @Transactional
    public String performEditLocation(Location location, HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        location.setId(id);

        locations.save(location);

        return "redirect:/locations";
    }
}

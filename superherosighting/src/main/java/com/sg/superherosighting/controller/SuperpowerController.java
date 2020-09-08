/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting.controller;

import com.sg.superherosighting.entities.Superpower;
import com.sg.superherosighting.repositories.SuperpowerRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
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
public class SuperpowerController {

    @Autowired
    SuperpowerRepository superpowers;
    
     Set<ConstraintViolation<Superpower>> violations = new HashSet<>();
    
    @GetMapping("/superpowers")
    public String superpowers(Model model) {

        List<Superpower> superpowerList = superpowers.findAll();

        model.addAttribute("superpowers", superpowerList);
        model.addAttribute("errors", violations);
        
        return "superpowers";
    }

    @PostMapping("/addSuperpower")
//    @Transactional
    public String addSuperpower(Superpower superpower) {
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(superpower);
        if (violations.isEmpty()) {
            superpowers.save(superpower);
        }
        return "redirect:/superpowers";
    }

    @GetMapping("/deleteSuperpower")
    @Transactional
    public String deleteSuperpower(Integer id) {
        superpowers.deleteById(id);
        return "redirect:/superpowers";
    }

    @GetMapping("/editSuperpower")
    public String editSuperpower(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Optional<Superpower> superpower = superpowers.findById(id);

        model.addAttribute("superpower", superpower.get());

        return "editSuperpower";
    }

    @PostMapping("/editSuperpower")
    @Transactional
    public String performEditSuperpower(Superpower superpower, HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        superpower.setId(id);

        superpowers.save(superpower);

        return "redirect:/superpowers";
    }
}

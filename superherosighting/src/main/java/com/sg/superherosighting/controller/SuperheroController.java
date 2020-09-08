/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting.controller;

import com.sg.superherosighting.entities.Organization;
import com.sg.superherosighting.entities.Superhero;
import com.sg.superherosighting.entities.Superpower;
import com.sg.superherosighting.repositories.OrganizationRepository;
import com.sg.superherosighting.repositories.SuperheroRepository;
import com.sg.superherosighting.repositories.SuperpowerRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Minul
 */
@Controller
public class SuperheroController {

    @Autowired
    SuperheroRepository superheros;

    @Autowired
    SuperpowerRepository superpowers;

    @Autowired
    OrganizationRepository organizations;

    Set<ConstraintViolation<Superhero>> violations = new HashSet<>();

    @GetMapping("/superheros")
    public String superheros(Model model) {
        List<Superhero> superheroList = superheros.findAll();
        List<Superpower> superpowerList = superpowers.findAll();
        List<Organization> organizationList = organizations.findAll();

        model.addAttribute("superheros", superheroList);
        model.addAttribute("superpowers", superpowerList);
        model.addAttribute("organizations", organizationList);
        model.addAttribute("errors", violations);

        return "superheros";
    }

    @PostMapping("/addSuperhero")
    @Transactional
    public String addSuperhero(Superhero superhero, HttpServletRequest request) {
        String superpowerId = request.getParameter("superpowerId");
        String[] organizationIds = request.getParameterValues("organizationId");

        List<Organization> organizationList = new ArrayList<>();
        for (String organizationId : organizationIds) {
            organizationList.add(organizations.findById(Integer.parseInt(organizationId)).get());
        }

        superhero.setSuperpower(superpowers.findById(Integer.parseInt(superpowerId)).get());
        superhero.setOrganizations(organizationList);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(superhero);
        if (violations.isEmpty()) {
            superheros.save(superhero);
        }
        return "redirect:/superheros";
    }

    @GetMapping("/superheroDetail")
    public String superheroDetail(Integer id, Model model) {
        Superhero superhero = superheros.findById(id).orElse(null);
        model.addAttribute("superhero", superhero);
        return "superheroDetail";
    }

    @GetMapping("deleteSuperhero")
    @Transactional
    public String deleteSuperhero(Integer id) {
        superheros.deleteById(id);
        return "redirect:/superheros";
    }

    @GetMapping("editSuperhero")
    public String editSuperhero(Integer id, Model model) {
        Optional<Superhero> superhero = superheros.findById(id);
        List<Superpower> superpowerList = superpowers.findAll();
        List<Organization> organizationList = organizations.findAll();

        model.addAttribute("superhero", superhero.get());
        model.addAttribute("superpowers", superpowerList);
        model.addAttribute("organizations", organizationList);

        return "editSuperhero";
    }

    @PostMapping("editSuperhero")
    @Transactional
    public String performEditSuperhero(@Valid Superhero superhero, BindingResult result, HttpServletRequest request, Model model) {
        String superpowerId = request.getParameter("superpowerId");
        String[] organizationIds = request.getParameterValues("organizationId");

        List<Organization> organizationList = new ArrayList<>();
        if (organizationIds != null) {
            for (String organizationId : organizationIds) {
                organizationList.add(organizations.findById(Integer.parseInt(organizationId)).get());
            }
        } else {
            FieldError error = new FieldError("superhero", "organizationList", "Must include one organization/none");
            result.addError(error);
        }

        superhero.setSuperpower(superpowers.findById(Integer.parseInt(superpowerId)).get());
        superhero.setOrganizations(organizationList);

        if (result.hasErrors()) {
            model.addAttribute("superpowers", superpowers.findAll());
            model.addAttribute("organizations", organizations.findAll());
            model.addAttribute("superhero", superhero);
            return "editSuperhero";
        }

        superheros.save(superhero);

        return "redirect:/superheros";
    }

}

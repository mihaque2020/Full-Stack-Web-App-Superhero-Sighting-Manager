/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting.controller;

import com.sg.superherosighting.entities.Organization;
import com.sg.superherosighting.repositories.OrganizationRepository;
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
public class OrganizationController {

    @Autowired
    OrganizationRepository organizations;

    Set<ConstraintViolation<Organization>> violations = new HashSet<>();

    @GetMapping("/organizations")
    public String organizations(Model model) {

        List<Organization> organizationList = organizations.findAll();

        model.addAttribute("organizations", organizationList);
        model.addAttribute("errors", violations);

        return "organizations";
    }

    @PostMapping("/addOrganization")
    @Transactional
    public String addOrganizaion(Organization organization) {
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(organization);
        if (violations.isEmpty()) {
            organizations.save(organization);
        }
        return "redirect:/organizations";
    }

    @GetMapping("/deleteOrganization")
    @Transactional
    public String deleteOrganization(Integer id) {
        organizations.deleteById(id);
        return "redirect:/organizations";
    }

    @GetMapping("/editOrganization")
    public String editOrganization(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Optional<Organization> organization = organizations.findById(id);

        model.addAttribute("organization", organization.get());

        return "editOrganization";
    }

    @PostMapping("/editOrganization")
    @Transactional
    public String performEditOrganization(Organization organization, HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        organization.setId(id);

        organizations.save(organization);

        return "redirect:/organizations";
    }

}

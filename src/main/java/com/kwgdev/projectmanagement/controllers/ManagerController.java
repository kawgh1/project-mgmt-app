package com.kwgdev.projectmanagement.controllers;

import com.kwgdev.projectmanagement.dao.EmployeeRepository;
import com.kwgdev.projectmanagement.dao.ManagerRepository;
import com.kwgdev.projectmanagement.entities.Employee;
import com.kwgdev.projectmanagement.entities.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/managers")
public class ManagerController {

    @Autowired
    ManagerRepository managerRepo;

    @GetMapping
    public String displayManagers(Model model) {
        List<Manager> managers = managerRepo.findAll();
        model.addAttribute("managers", managers);
        return "managers/list-managers";

    }

    @GetMapping("/new")
    public String displayManagerForm(Model model) { // Model is used to bind the Java object (Employee) to the HTML Form


        Manager aManager = new Manager();
        // bind an empty Project object to the HTML form
        model.addAttribute("manager", aManager);

        return "managers/new-manager";
    }

    @PostMapping("/save")
    public String createManager(Manager manager, Model model) {
        // this will handle saving the new employee to the database
        managerRepo.save(manager);

        // use a redirect to new to prevent duplicate submissions
        // always use redirect after saving data
        return "redirect:/managers";
    }

}

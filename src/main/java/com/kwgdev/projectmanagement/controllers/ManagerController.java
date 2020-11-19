package com.kwgdev.projectmanagement.controllers;

import com.kwgdev.projectmanagement.entities.Manager;
import com.kwgdev.projectmanagement.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/managers")
public class ManagerController {

//    @Autowired
//    ManagerRepository managerRepo;
    // abstraction layer to keep Controllers separated from Database operations
    @Autowired
    private ManagerService managerService;

    @GetMapping
    public String displayManagers(Model model) {
        List<Manager> managers = managerService.findAll();
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
    public String createManager(@Valid Manager manager, Model model) {
        // this will handle saving the new employee to the database
        managerService.save(manager);

        // use a redirect to new to prevent duplicate submissions
        // always use redirect after saving data
        return "redirect:/managers";
    }

    @GetMapping("/update")
    public String displayManagerUpdateForm(@RequestParam("mgrId") long theMgrId, Model model) {

        // display manager
        Manager theMgr = managerService.findManagerById(theMgrId);
        model.addAttribute("manager", theMgr);

        return "managers/update-manager";
    }

    @GetMapping("/delete")
    public String deleteManager(@RequestParam("mgrId") long theMgrId) {

        Manager theMgr = managerService.findManagerById(theMgrId);
        managerService.delete(theMgr);

        return "redirect:/managers";
    }

}

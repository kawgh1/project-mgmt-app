package com.kwgdev.projectmanagement.controllers;

import com.kwgdev.projectmanagement.dao.EmployeeRepository;
import com.kwgdev.projectmanagement.dao.ManagerRepository;
import com.kwgdev.projectmanagement.dao.ProjectRepository;
import com.kwgdev.projectmanagement.dto.EmployeeProject;
import com.kwgdev.projectmanagement.entities.Manager;
import com.kwgdev.projectmanagement.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    ProjectRepository projectRepo;

    @Autowired
    ManagerRepository managerRepo;

    @Autowired
    EmployeeRepository employeeRepo;

    @GetMapping("/")
    public String displayHome(Model model) {
        // Projects
        List<Project> projects = projectRepo.findAll();

        model.addAttribute("projectsList", projects);

        // Managers
        List<Manager> managers = managerRepo.findAll();

        model.addAttribute("managersList", managers);

        // Employees
//        List<Employee> employees = employeeRepo.findAll();
        List<EmployeeProject> employeesProjectCount = employeeRepo.employeeProjects();

        model.addAttribute("employeesListProjectCount", employeesProjectCount);
        return "main/home";

    }
}

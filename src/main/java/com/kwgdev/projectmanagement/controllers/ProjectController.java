package com.kwgdev.projectmanagement.controllers;

import com.kwgdev.projectmanagement.dao.EmployeeRepository;
import com.kwgdev.projectmanagement.dao.LocationRepository;
import com.kwgdev.projectmanagement.dao.ManagerRepository;
import com.kwgdev.projectmanagement.dao.ProjectRepository;
import com.kwgdev.projectmanagement.entities.Employee;
import com.kwgdev.projectmanagement.entities.Location;
import com.kwgdev.projectmanagement.entities.Manager;
import com.kwgdev.projectmanagement.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    ProjectRepository projectRepo;

    @Autowired
    EmployeeRepository employeeRepo;

    @Autowired
    ManagerRepository managerRepo;

    @Autowired
    LocationRepository locationRepo;

    @GetMapping
    public String displayProjects(Model model) {
        List<Project> projects = projectRepo.findAll();
        model.addAttribute("projects", projects);
        return "projects/list-projects";

    }

    @GetMapping("/new")
    public String displayProjectForm(Model model) { // Model is used to bind the Java object (Project) to the HTML Form


        Project aProject = new Project();
        List<Employee> employees = employeeRepo.findAll();
        List<Manager> managers = managerRepo.findAll();
        List<Location> locations = locationRepo.findAll();
        // bind an empty Project object to the HTML form
        model.addAttribute("project", aProject);
        model.addAttribute("allEmployees", employees);
        model.addAttribute("allManagers", managers);
        model.addAttribute("allLocations", locations);

        return "projects/new-project";
    }

    @PostMapping("/save")
    public String createProject(Project project, @RequestParam List<Long> employees,
                                @RequestParam Manager manager, @RequestParam Location location,
                                Model model) {
        // this will handle saving the new project to the database
    projectRepo.save(project);



    Iterable<Employee> projectEmployees = employeeRepo.findAllById(employees);

    for(Employee employee : projectEmployees) {
        employee.setProject(project);
        employee.setLocation(location);
        employeeRepo.save(employee);
    }

    manager.addProject(project);
    manager.addLocation(location);
    managerRepo.save(manager);

    location.addProject(project);
    location.addManager(manager);
    locationRepo.save(location);

    // use a redirect to new to prevent duplicate submissions
        // always use redirect after saving data
    return "redirect:/projects";
    }


}

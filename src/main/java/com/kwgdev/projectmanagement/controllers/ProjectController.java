package com.kwgdev.projectmanagement.controllers;

import com.kwgdev.projectmanagement.entities.Employee;
import com.kwgdev.projectmanagement.entities.Manager;
import com.kwgdev.projectmanagement.entities.Project;
import com.kwgdev.projectmanagement.service.EmployeeService;
import com.kwgdev.projectmanagement.service.ManagerService;
import com.kwgdev.projectmanagement.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

//    @Autowired
//    ProjectRepository projectRepo;
    // abstraction layer to keep Controllers separated from Database operations
    @Autowired
    private ProjectService projectService;

//    @Autowired
//    EmployeeRepository employeeRepo;
    @Autowired
    private EmployeeService employeeService;

//    @Autowired
//    ManagerRepository managerRepo;
    @Autowired
    private ManagerService managerService;


    @GetMapping
    public String displayProjects(Model model) {
        List<Project> projects = projectService.findAll();
        model.addAttribute("projects", projects);
        return "projects/list-projects";

    }

    @GetMapping("/new")
    public String displayProjectForm(Model model) { // Model is used to bind the Java object (Project) to the HTML Form


        Project aProject = new Project();
        List<Employee> employees = employeeService.findAll();
        List<Manager> managers = managerService.findAll();
        // bind an empty Project object to the HTML form
        model.addAttribute("project", aProject);
        model.addAttribute("allEmployees", employees);
        model.addAttribute("allManagers", managers);

        return "projects/new-project";
    }

    @PostMapping("/save-new")
    public String createProject(@Valid Project project, Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "projects/new-project";
        }
        // this will handle saving the new project to the database
        projectService.save(project);

    // use a redirect to new to prevent duplicate submissions
        // always use redirect after saving data
    return "redirect:/projects";
    }

    @GetMapping("/update")
    public String displayProjectUpdateForm(@RequestParam("proId") long theProId, Model model) {

        // display project
        Project thePro = projectService.findByProjectId(theProId);
        model.addAttribute("project", thePro);

        // display all managers
        List<Manager> managers = managerService.findAll();
        model.addAttribute("allManagers", managers);

        // display all employees
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("allEmployees", employees);

        return "projects/update-project";
    }

    @PostMapping("/save-update")
    public String updateProject(@Valid Project project, Errors errors, Model model) {


        // display all managers
        List<Manager> managers = managerService.findAll();
        model.addAttribute("allManagers", managers);

        // display all employees
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("allEmployees", employees);

        if (errors.hasErrors()) {
            return "projects/new-project";
        }
        // this will handle saving the new project to the database
        projectService.save(project);

        // use a redirect to new to prevent duplicate submissions
        // always use redirect after saving data
        return "redirect:/projects";
    }

    @GetMapping("/delete")
    public String deleteProject(@RequestParam("proId") long theProId) {

        Project thePro = projectService.findByProjectId(theProId);
        projectService.delete(thePro);

        return "redirect:/projects";
    }


//    // Function to remove duplicates from an ArrayList
//    public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list) {
//
//        // Create a new ArrayList
//        ArrayList<T> newList = new ArrayList<T>();
//        // Traverse through the first list
//        for (T element : list) {
//            // If this element is not present in newList
//            // then add it
//            if (!newList.contains(element)) {
//                newList.add(element);
//            }
//        }
//        // return the new list
//        return newList;
//    }

}

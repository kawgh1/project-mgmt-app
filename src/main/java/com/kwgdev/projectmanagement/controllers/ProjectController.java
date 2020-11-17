package com.kwgdev.projectmanagement.controllers;

import com.kwgdev.projectmanagement.dao.EmployeeRepository;
import com.kwgdev.projectmanagement.dao.ManagerRepository;
import com.kwgdev.projectmanagement.dao.ProjectRepository;
import com.kwgdev.projectmanagement.entities.Employee;
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
        // bind an empty Project object to the HTML form
        model.addAttribute("project", aProject);
        model.addAttribute("allEmployees", employees);
        model.addAttribute("allManagers", managers);

        return "projects/new-project";
    }

    @PostMapping("/save")
    public String createProject(Project project, Model model) {
        // this will handle saving the new project to the database
    projectRepo.save(project);

    // use a redirect to new to prevent duplicate submissions
        // always use redirect after saving data
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

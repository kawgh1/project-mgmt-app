package com.kwgdev.projectmanagement.controllers;

import com.kwgdev.projectmanagement.dao.ProjectRepository;
import com.kwgdev.projectmanagement.entities.Location;
import com.kwgdev.projectmanagement.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    ProjectRepository projectRepo;

    @GetMapping
    public String displayProjects(Model model) {
        List<Project> projects = projectRepo.findAll();
        model.addAttribute("projects", projects);
        return "projects/list-projects";

    }

    @GetMapping("/new")
    public String displayProjectForm(Model model) { // Model is used to bind the Java object (Project) to the HTML Form


        Project aProject = new Project();
        // bind an empty Project object to the HTML form
        model.addAttribute("project", aProject);

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


}

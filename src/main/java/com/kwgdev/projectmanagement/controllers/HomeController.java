package com.kwgdev.projectmanagement.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kwgdev.projectmanagement.dao.EmployeeRepository;
import com.kwgdev.projectmanagement.dao.ManagerRepository;
import com.kwgdev.projectmanagement.dao.ProjectRepository;
import com.kwgdev.projectmanagement.dto.ChartData;
import com.kwgdev.projectmanagement.dto.EmployeeProject;
import com.kwgdev.projectmanagement.dto.ManagerProject;
import com.kwgdev.projectmanagement.entities.Manager;
import com.kwgdev.projectmanagement.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    // wired in from application.properties for demo purposes
    @Value("${version}")
    private String version;

    @Autowired
    ProjectRepository projectRepo;

    @Autowired
    ManagerRepository managerRepo;

    @Autowired
    EmployeeRepository employeeRepo;

    @GetMapping("/")
    public String displayHome(Model model) throws JsonProcessingException {

//        Map<String, Object> map = new HashMap<>();
        model.addAttribute("versionNumber", version);

        // Projects
        List<Project> projects = projectRepo.findAll();
        model.addAttribute("projectsList", projects);

        // Managers
//        List<Manager> managers = managerRepo.findAll();
//        model.addAttribute("managersList", managers);

        // dto chart data
        List<ChartData> projectData = projectRepo.getProjectStatus();
        List<ChartData> employeeData = employeeRepo.getEmployeeStatus();
        List<ChartData> managerData = managerRepo.getManagerStatus();

        // convert projectData object into JSON structure for us in javascript
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(projectData);

        ObjectMapper objectMapper1 = new ObjectMapper();
        String jsonString1 = objectMapper1.writeValueAsString(employeeData);

        ObjectMapper objectMapper2 = new ObjectMapper();
        String jsonString2 = objectMapper2.writeValueAsString(managerData);
        // JSON String will look like [["NOTSTARTED", 1, "INPROGRESS", 2, "COMPLETED", 3]]

        // sned ChartData JSON data to model to display in our HTML templates
        model.addAttribute("projectStatusCount", jsonString);
        model.addAttribute("employeeStatusCount", jsonString1);
        model.addAttribute("managerStatusCount", jsonString2);

        // Employees
//        List<Employee> employees = employeeRepo.findAll();
        // we are querying the database for employees
        // -> this shows "Project Count" in Employee table on home.html
        // nothing to do with the pie charts
        List<EmployeeProject> employeesProjectCount = employeeRepo.employeeProjects();
        model.addAttribute("employeesListProjectCount", employeesProjectCount);

        List<ManagerProject> managersProjectCount = managerRepo.managerProjects();
        model.addAttribute("managersListProjectCount", managersProjectCount);


        return "main/home";

    }
}

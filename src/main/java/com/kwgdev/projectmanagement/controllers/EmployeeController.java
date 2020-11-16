package com.kwgdev.projectmanagement.controllers;

import com.kwgdev.projectmanagement.dao.EmployeeRepository;
import com.kwgdev.projectmanagement.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepo;

    @GetMapping
    public String displayEmployees(Model model) {
        List<Employee> employees = employeeRepo.findAll();
        model.addAttribute("employees", employees);
        return "employees/list-employees";

    }

    @GetMapping("/new")
    public String displayEmployeeForm(Model model) { // Model is used to bind the Java object (Employee) to the HTML Form


        Employee aEmployee = new Employee();
        // bind an empty Project object to the HTML form
        model.addAttribute("employee", aEmployee);

        return "employees/new-employee";
    }

    @PostMapping("/save")
    public String createEmployee(Employee employee, Model model) {
        // this will handle saving the new employee to the database
        employeeRepo.save(employee);

        // use a redirect to new to prevent duplicate submissions
        // always use redirect after saving data
        return "redirect:/employees";
    }


}


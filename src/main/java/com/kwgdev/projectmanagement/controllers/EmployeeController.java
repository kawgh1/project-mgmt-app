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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepo;

    @Autowired
    ManagerRepository   managerRepo;

    @GetMapping
    public String displayEmployees(Model model) {
        List<Employee> employees = employeeRepo.findAll();
        model.addAttribute("employees", employees);
        return "employees/list-employees";

    }

    @GetMapping("/new")
    public String displayEmployeeForm(Model model) { // Model is used to bind the Java object (Employee) to the HTML Form


        Employee aEmployee = new Employee();
        List<Manager> managers = managerRepo.findAll();
        // bind an empty Project object to the HTML form
        model.addAttribute("employee", aEmployee);
        model.addAttribute("allManagers", managers);

        return "employees/new-employee";
    }

    @PostMapping("/save")
    public String createEmployee(Employee employee, @RequestParam Manager manager, Model model) {
        // this will handle saving the new employee to the database
        employeeRepo.save(employee);

        manager.addEmployee(employee);
        managerRepo.save(manager);

        // use a redirect to new to prevent duplicate submissions
        // always use redirect after saving data
        return "redirect:/employees";
    }


}


package com.kwgdev.projectmanagement.controllers;

import com.kwgdev.projectmanagement.entities.Employee;
import com.kwgdev.projectmanagement.entities.Manager;
import com.kwgdev.projectmanagement.service.EmployeeService;
import com.kwgdev.projectmanagement.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

//    @Autowired
//    EmployeeRepository employeeRepo;
    // abstraction layer to keep Controllers separated from Database operations
    @Autowired
    private EmployeeService employeeService;

//    @Autowired
//    ManagerRepository   managerRepo;
    @Autowired
    private ManagerService managerService;

    @GetMapping
    public String displayEmployees(Model model) {
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        return "employees/list-employees";

    }

    @GetMapping("/new")
    public String displayEmployeeForm(Model model) { // Model is used to bind the Java object (Employee) to the HTML Form


        Employee aEmployee = new Employee();
        List<Manager> managers = managerService.findAll();
        // bind an empty Project object to the HTML form
        model.addAttribute("employee", aEmployee);
        model.addAttribute("allManagers", managers);

        return "employees/new-employee";
    }

    @PostMapping("/save-new")
    public String createEmployee(@Valid Employee employee, Errors errors, @RequestParam Manager manager, Model model ) {

        List<Manager> managers = managerService.findAll();
        model.addAttribute("allManagers", managers);
        // validation
        if(errors.hasErrors()) {
            return "employees/new-employee";
        }


        // this will handle saving the new employee to the database
        employeeService.save(employee);

        manager.addEmployee(employee);
        managerService.save(manager);

        // use a redirect to new to prevent duplicate submissions
        // always use redirect after saving data
        return "redirect:/employees";
    }

    @GetMapping("/update")
    public String displayEmployeeUpdateForm(@RequestParam("empId") long theEmpId, Model model) {

        // display employee
        Employee theEmp = employeeService.findByEmployeeId(theEmpId);
        model.addAttribute("employee", theEmp); // send Employee data to page

        // display all managers
        List<Manager> managers = managerService.findAll();
        model.addAttribute("allManagers", managers);

        return "employees/update-employee";
    }

    @PostMapping("/save-update")
    public String updateEmployee(@Valid Employee employee, Errors errors, @RequestParam Manager manager, Model model ) {

        List<Manager> managers = managerService.findAll();
        model.addAttribute("allManagers", managers);
        // validation
        if(errors.hasErrors()) {
            return "employees/update-employee";
        }


        // this will handle saving the new employee to the database
        employeeService.save(employee);

        manager.addEmployee(employee);
        managerService.save(manager);

        // use a redirect to new to prevent duplicate submissions
        // always use redirect after saving data
        return "redirect:/employees";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("empId") long theEmpId) {

        Employee theEmp = employeeService.findByEmployeeId(theEmpId);
        employeeService.delete(theEmp);

        return "redirect:/employees";
    }


}


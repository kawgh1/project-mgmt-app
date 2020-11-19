package com.kwgdev.projectmanagement.service;

import com.kwgdev.projectmanagement.dao.EmployeeRepository;
import com.kwgdev.projectmanagement.dto.ChartData;
import com.kwgdev.projectmanagement.dto.EmployeeProject;
import com.kwgdev.projectmanagement.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// service bean
@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepo;

    public Employee save (Employee employee) {
        return employeeRepo.save(employee);
    }

    public List<Employee> findAll() {
        return employeeRepo.findAll();
    }

    public Employee findByEmployeeId(long theEmpId) { return employeeRepo.findByEmployeeId(theEmpId);}

    public List<EmployeeProject> getEmployeeProjects() {
        return employeeRepo.getEmployeeProjects();
    }

    public List<ChartData> getEmployeeStatus() {
        return employeeRepo.getEmployeeStatus();
    }


    public void delete(Employee theEmp) {
        employeeRepo.delete(theEmp);
    }

}

package com.kwgdev.projectmanagement.dao;

import com.kwgdev.projectmanagement.dto.ChartData;
import com.kwgdev.projectmanagement.dto.EmployeeProject;
import com.kwgdev.projectmanagement.entities.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Override
    List<Employee> findAll(); // default CrudRepository returns a type iterable, but we need a list

    @Query(nativeQuery=true, value="SELECT e.first_name as firstName, e.last_name as lastName, COUNT(pe.employee_id) as projectCount " +
            "FROM employee e left join project_employee pe ON pe.employee_id = e.employee_id GROUP BY e.first_name, e.last_name ORDER BY 3 DESC")
    public List<EmployeeProject> employeeProjects();

    @Query(nativeQuery = true, value="SELECT e.first_name, e.last_name as label, COUNT(pe.employee_id) as value " +
            "FROM employee e left join project_employee pe ON pe.employee_id = e.employee_id GROUP BY e.first_name, e.last_name ORDER BY 3 DESC")
    public List<ChartData> getEmployeeStatus();
}

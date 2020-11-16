package com.kwgdev.projectmanagement.dao;

import com.kwgdev.projectmanagement.entities.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Override
    List<Employee> findAll(); // default CrudRepository returns a type iterable, but we need a list
}

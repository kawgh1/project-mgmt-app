package com.kwgdev.projectmanagement.dao;

import com.kwgdev.projectmanagement.entities.Manager;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ManagerRepository extends CrudRepository<Manager, Long> {

    @Override
    List<Manager> findAll(); // default CrudRepository returns a type iterable, but we need a list
}

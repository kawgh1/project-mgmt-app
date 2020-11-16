package com.kwgdev.projectmanagement.dao;

import com.kwgdev.projectmanagement.entities.Project;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, Long> {

    @Override
    List<Project> findAll(); // default CrudRepository returns a type iterable, but we need a list
}

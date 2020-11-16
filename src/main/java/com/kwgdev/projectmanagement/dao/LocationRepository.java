package com.kwgdev.projectmanagement.dao;

import com.kwgdev.projectmanagement.entities.Location;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LocationRepository extends CrudRepository<Location, Long> {

    @Override
    List<Location> findAll(); // default CrudRepository returns a type iterable, but we need a list
}

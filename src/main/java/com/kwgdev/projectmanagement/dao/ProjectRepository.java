package com.kwgdev.projectmanagement.dao;

import com.kwgdev.projectmanagement.dto.ChartData;
import com.kwgdev.projectmanagement.entities.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

// this line creates a Spring Data REST API at host/api-projects
@RepositoryRestResource(collectionResourceRel = "api-projects", path="api-projects")
public interface ProjectRepository extends CrudRepository<Project, Long> {

    @Override
    List<Project> findAll(); // default CrudRepository returns a type iterable, but we need a list

    @Query(nativeQuery = true, value="SELECT stage as label, COUNT(*) as value FROM project GROUP BY stage")
    public List<ChartData> getProjectStatus();


    Project findByProjectId(long theProId);
}

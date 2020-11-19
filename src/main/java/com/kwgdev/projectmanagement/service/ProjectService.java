package com.kwgdev.projectmanagement.service;

import com.kwgdev.projectmanagement.dao.ProjectRepository;
import com.kwgdev.projectmanagement.dto.ChartData;
import com.kwgdev.projectmanagement.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

// service bean
@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepo;

    public Project save(Project project) {
        return projectRepo.save(project);
    }

    public List<Project> findAll() {
        return projectRepo.findAll();
    };

    public List<ChartData> getProjectStatus() {
        return projectRepo.getProjectStatus();
    }
}

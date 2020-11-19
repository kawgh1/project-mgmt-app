package com.kwgdev.projectmanagement.service;

import com.kwgdev.projectmanagement.dao.ManagerRepository;
import com.kwgdev.projectmanagement.dto.ChartData;
import com.kwgdev.projectmanagement.dto.ManagerProject;
import com.kwgdev.projectmanagement.entities.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

//service bean
@Service
public class ManagerService {

    @Autowired
    ManagerRepository managerRepo;

    public Manager save(Manager manager) {
        return managerRepo.save(manager);
    }

    public List<Manager> findAll() {
        return managerRepo.findAll();
    }

    public List<ManagerProject> getManagerProjects() {
        return managerRepo.getManagerProjects();
    }

    public List<ChartData> getManagerStatus() {
        return managerRepo.getManagerStatus();
    }
}

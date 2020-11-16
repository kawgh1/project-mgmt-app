package com.kwgdev.projectmanagement.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long projectId;
    private String name;

    private String stage; // NOTSTARTED, COMPLETED, INPROGRESS

    private String description;

    @OneToMany(mappedBy = "project")
    private List<Employee> employees;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;

    // This empty instance is used for Thymeleaf HTML form binding
    public Project() {

    }

    public Project(String name, String stage, String description, Manager manager) {
        this.name = name;
        this.stage = stage;
        this.description = description;
        this.manager = manager;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}

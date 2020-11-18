package com.kwgdev.projectmanagement.dao;

import com.kwgdev.projectmanagement.dto.ChartData;
import com.kwgdev.projectmanagement.dto.ManagerProject;
import com.kwgdev.projectmanagement.entities.Manager;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ManagerRepository extends CrudRepository<Manager, Long> {

    @Override
    List<Manager> findAll(); // default CrudRepository returns a type iterable, but we need a list

    @Query(nativeQuery=true, value="SELECT m.first_name as firstName, m.last_name as lastName, COUNT(pm.manager_id) as projectCount " +
            "FROM manager m left join project_manager pm ON pm.manager_id = m.manager_id GROUP BY m.first_name, m.last_name ORDER BY 3 DESC")
    public List<ManagerProject> managerProjects();

    @Query(nativeQuery = true, value="SELECT m.first_name, m.last_name as label, COUNT(pm.manager_id) as value " +
            "FROM manager m left join project_manager pm ON pm.manager_id = m.manager_id GROUP BY m.first_name, m.last_name ORDER BY 3 DESC")
    public List<ChartData> getManagerStatus();
}

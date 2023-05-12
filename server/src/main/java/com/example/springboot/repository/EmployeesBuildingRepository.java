package com.example.springboot.repository;


import com.example.springboot.model.EmployeesBuilding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeesBuildingRepository extends JpaRepository<EmployeesBuilding, Integer> {

    @Query(value = "SELECT * FROM employees_building WHERE employeeID = ?1 LIMIT 1", nativeQuery = true)
    EmployeesBuilding findBuildingEmployeeByID(int employeeID);

    List<EmployeesBuilding> findBuildingEmployeeByNameContaining(String name);
}
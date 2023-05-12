package com.example.springboot.repository;

import com.example.springboot.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SalaryRepository  extends JpaRepository<Salary, Integer> {
    @Query(value = "SELECT * FROM salary WHERE salaryID = ?1 LIMIT 1", nativeQuery = true)
    Salary getSalaryByID(int salaryID);

}

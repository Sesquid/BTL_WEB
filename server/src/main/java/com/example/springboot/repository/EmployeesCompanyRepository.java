package com.example.springboot.repository;

import com.example.springboot.model.Company;
import com.example.springboot.model.EmployeesCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeesCompanyRepository extends JpaRepository<EmployeesCompany, String> {
    @Query(value = "SELECT * FROM employees_company", nativeQuery = true)
    List<EmployeesCompany> findAll();

    @Query(value = "SELECT * FROM employees_company WHERE company_id = ?1", nativeQuery = true)
    List<EmployeesCompany> findAllByCompanyID(String companyID);

}

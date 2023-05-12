package com.example.springboot.repository;

import com.example.springboot.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    @Query(value = "SELECT * FROM company", nativeQuery = true)
    List<Company> findAll();


}

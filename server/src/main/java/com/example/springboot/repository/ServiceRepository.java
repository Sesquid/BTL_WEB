package com.example.springboot.repository;

import com.example.springboot.model.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Integer> {

    @Query(value = "SELECT * FROM service WHERE serviceID = ?1 LIMIT 1", nativeQuery = true)
    ServiceEntity findServiceByID(int serviceID);
    // tim nhung dich vu bat buoc
    List<ServiceEntity> findServicesByRequired(int isRequired);

    List<ServiceEntity> findServiceByNameContaining(String name);
}

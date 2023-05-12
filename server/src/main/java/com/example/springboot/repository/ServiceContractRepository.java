package com.example.springboot.repository;

import com.example.springboot.model.ServiceContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceContractRepository extends JpaRepository<ServiceContract, Integer> {

    @Query(value = "SELECT * FROM service_contract WHERE service_ContractID = ?1 LIMIT 1", nativeQuery = true)
    ServiceContract getServiceContractByID(int serviceContractID);


    List<ServiceContract> getServiceContractByCompany_companyID(Integer companyId);
}

package com.example.springboot.repository;

import com.example.springboot.model.Contract;
import com.example.springboot.model.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Integer> {
    @Query(value = "SELECT * FROM contract WHERE contractID = ?1 LIMIT 1", nativeQuery = true)
    Contract findContractById(int contractID);

    // tu tao cau lenh sql va no tu chay luon
    // sytax: getContractByCompany_(truong id trung vs truong id khai bao)
    List<Contract> getContractByCompany_companyID(int companyID);

    // tu tao cau lenh sql va no tu chay luon
    // cu phap tuong tu ben tren
    List<Contract> getContractByFloor_floorID(int companyID);
}

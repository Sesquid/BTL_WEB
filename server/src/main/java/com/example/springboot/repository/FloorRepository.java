package com.example.springboot.repository;


import com.example.springboot.DTO.FloorDTO;
import com.example.springboot.model.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FloorRepository extends JpaRepository<Floor, String> {
    @Query(value = "SELECT * FROM floor WHERE floorID = ?1 LIMIT 1", nativeQuery = true)
    Floor findByFloorId(int floorID);

//    @Query(value = "INSERT INTO floor VALUES (name = ?, pricePerM2 = ?, area = ?)", nativeQuery = true)
//    Floor saveFloor(Floor floor);
}

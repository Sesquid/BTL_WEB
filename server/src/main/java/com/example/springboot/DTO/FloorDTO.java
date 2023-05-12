package com.example.springboot.DTO;

import com.example.springboot.model.Floor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FloorDTO {
    private int floorID;

    private String name;

    private double pricePerM2;

    private double area;

    public FloorDTO(Floor floor){
        this.setFloorID(floor.getFloorID());
        this.setName(floor.getName());
        this.setPricePerM2(floor.getPricePerM2());
        this.setArea(floor.getArea());
    }
}

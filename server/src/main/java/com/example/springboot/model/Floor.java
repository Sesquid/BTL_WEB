package com.example.springboot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="floor")
public class Floor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int floorID;

    private String name;

    private double pricePerM2;

    private double area;
}

package com.example.springboot.controller;

import com.example.springboot.common.BaseResponse;
import com.example.springboot.model.EmployeesBuilding;
import com.example.springboot.service.EmployeesBuildingService;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/building-employee")
public class EmployeesBuildingController {

    @Autowired
    EmployeesBuildingService buildingEmployeeService;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllBuildingEmployee(){
        try {
            JsonObject res = buildingEmployeeService.getAllBuildingEmployee();
            return ResponseEntity.ok().body(res.toString());
        }
        catch (Exception e){
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }

    @GetMapping("/get-employee/{id}")
    public ResponseEntity<?> getBuildingEmployeeByID(@PathVariable int id){
        try {
            JsonObject res = buildingEmployeeService.getBuildingEmployeeByID(id);
            return ResponseEntity.ok().body(res.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }

    @GetMapping("get-employee/name")
    public ResponseEntity<?> getBuildingEmployeeByNameContaining(@RequestParam String name){
        try {
            JsonObject res = buildingEmployeeService.getBuildingEmployeeByNameContaining(name);
            return ResponseEntity.ok().body(res.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBuildingEmployee(@RequestParam int salaryID, @RequestBody EmployeesBuilding buildingEmployee){
        try {
            JsonObject res = buildingEmployeeService.create(buildingEmployee, salaryID);
            return ResponseEntity.ok().body(res.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateBuildingEmployee(@PathVariable int id, @RequestParam int salaryID, @RequestBody EmployeesBuilding buildingEmployee){
        try {
            JsonObject res = buildingEmployeeService.update(buildingEmployee, id, salaryID);
            return ResponseEntity.ok().body(res.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteBuildingEmployee(@PathVariable int id){
        try {
            JsonObject res = buildingEmployeeService.delete(id);
            return ResponseEntity.ok().body(res.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }

}
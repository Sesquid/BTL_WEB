package com.example.springboot.controller;

import com.example.springboot.common.BaseResponse;
import com.example.springboot.model.Salary;
import com.example.springboot.service.SalaryService;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/salary")
public class SalaryController {

    @Autowired
    SalaryService salaryService;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllSalary(){
        try {
            JsonObject res = salaryService.getAllSalary();
            return ResponseEntity.ok().body(res.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }

    @GetMapping("/get-salary/{id}")
    public ResponseEntity<?> getSalaryByID(@PathVariable int id){
        try {
            JsonObject res = salaryService.getSalaryByID(id);
            return ResponseEntity.ok().body(res.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Salary salary){
        try {
            JsonObject res = salaryService.create(salary);
            return ResponseEntity.ok().body(res.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody Salary salary, @PathVariable int id){
        try {
            JsonObject res = salaryService.update(salary, id);
            return ResponseEntity.ok().body(res.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        try {
            JsonObject res = salaryService.delete(id);
            return ResponseEntity.ok().body(res.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }
}

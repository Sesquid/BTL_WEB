package com.example.springboot.controller;

import com.example.springboot.DTO.ServiceContractDTO;
import com.example.springboot.common.BaseResponse;
import com.example.springboot.model.ServiceContract;
import com.example.springboot.service.ServiceContractService;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/service-contract")
public class ServiceContractController {

    @Autowired
    ServiceContractService serviceContractService;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllServiceContract(){
        try {
            JsonObject res = serviceContractService.getAllServiceContract();
            return ResponseEntity.ok().body(res.toString());
        }
        catch (Exception e){
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }

    @GetMapping("/get-contractService/{id}")
    public ResponseEntity<?> getAllServiceContractByID(@PathVariable int id){
        try {
            JsonObject res = serviceContractService.getServiceContractByID(id);
            return ResponseEntity.ok().body(res.toString());
        }
        catch (Exception e){
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ServiceContract serviceContract, @RequestParam int serviceID, @RequestParam int companyID){
        try {
            JsonObject res = serviceContractService.create(serviceContract, serviceID, companyID);
            return ResponseEntity.ok().body(res.toString());
        }
        catch (Exception e){
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody ServiceContract serviceContract, @PathVariable int id){
        try {
            JsonObject res = serviceContractService.update(serviceContract, id);
            return ResponseEntity.ok().body(res.toString());
        }
        catch (Exception e){
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        try {
            JsonObject res = serviceContractService.delete(id);
            return ResponseEntity.ok().body(res.toString());
        }
        catch (Exception e){
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }

    // lay ra tat ca cac hop dong dich vu cua cong ty

    @GetMapping("/get-all-service/company/{id}")
    public ResponseEntity<?> getServicesByName(@PathVariable int id){
        try {
            JsonObject  res = serviceContractService.getAllServiceContractOfCompany(id);
            return ResponseEntity.ok().body(res.toString());
        }
        catch (Exception e){
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }

}

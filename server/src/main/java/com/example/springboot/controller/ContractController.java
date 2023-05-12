package com.example.springboot.controller;

import com.example.springboot.common.BaseResponse;
import com.example.springboot.model.Contract;
import com.example.springboot.service.ContractService;
import com.example.springboot.service.FloorService;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/contract")
public class ContractController {

    @Autowired
    ContractService contractService;

    @Autowired
    FloorService floorService;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllContract() {
        try {
            JsonObject res = contractService.getAllContract();
            return ResponseEntity.ok().body(res.toString());
        }
        catch (Exception e){
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }

    @GetMapping("/get-contract/{id}")
    public ResponseEntity<?> getContractById(@PathVariable int id) {
        try {
            JsonObject res = contractService.getContractById(id);
            return ResponseEntity.ok().body(res.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createContract(@RequestParam int companyID, @RequestParam int floorID, @RequestBody Contract contract){
        try {
            double restArea = floorService.getTheRestArea(floorID);
            if (contract.getRentedArea() <= restArea){
                JsonObject res = contractService.create(contract, companyID, floorID);
                return ResponseEntity.ok().body(res.toString());
            }

            JsonObject res = BaseResponse.createFullMessageResponse(2, "The rest area is not enough to rent");
            return ResponseEntity.ok().body(res.toString());

        }
        catch (Exception e) {
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateContract(@PathVariable int id, @RequestBody Contract contract){
        try {
            JsonObject res = contractService.update(contract, id);
            return ResponseEntity.ok().body(res.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteContract(@PathVariable int id){
        try {
            JsonObject res = contractService.delete(id);
            return ResponseEntity.ok().body(res.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }

    @GetMapping("/get-contracts/companyid/{id}")
    public ResponseEntity<?> getContractsByCompanyID(@PathVariable int id){
        try {
            JsonObject res = contractService.getContractsByCompanyIDApi(id);
            return ResponseEntity.ok().body(res.toString());
        }
        catch (Exception e){
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }

    @GetMapping("/get-contracts/floorid/{id}")
    public ResponseEntity<?> getContractsByFloorID(@PathVariable int id){
        try {
            JsonObject res = contractService.getContractsByFloorIDApi(id);
            return ResponseEntity.ok().body(res.toString());
        }
        catch (Exception e){
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }

}

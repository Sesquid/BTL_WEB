package com.example.springboot.controller;

import com.example.springboot.common.BaseResponse;
import com.example.springboot.controller.request.FloorRequest;
import com.example.springboot.model.ServiceEntity;
import com.example.springboot.service.ServiceService;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/service")
public class ServiceController {

    @Autowired
    ServiceService serviceService;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllService(){
        try {
            JsonObject res = serviceService.getAllService();
            return ResponseEntity.ok().body(res.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }

    @GetMapping("/get-service/{id}")
    public ResponseEntity<?> getServiceById(@PathVariable int id){
        try {
            JsonObject res = serviceService.getServiceByID(id);
            return ResponseEntity.ok().body(res.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createService(@RequestBody ServiceEntity service){
        try {
            JsonObject res = serviceService.create(service);
            return ResponseEntity.ok().body(res.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateService(@PathVariable int id, @RequestBody ServiceEntity service){
        try {
            JsonObject res = serviceService.update(service, id);
            return ResponseEntity.ok().body(res.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteService(@PathVariable int id){
        try {
            JsonObject res = serviceService.delete(id);
            return ResponseEntity.ok().body(res.toString());
        } catch (Exception e){
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }

    // lay ra cac dich vu co chua tu hoac chu muon tim
    @GetMapping("/get-services/name")
    public ResponseEntity<?> getServicesByName(@RequestParam String name){
        try {
            JsonObject  res = serviceService.findServicesByName(name);
            return ResponseEntity.ok().body(res.toString());
        }
        catch (Exception e){
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }


    //
}

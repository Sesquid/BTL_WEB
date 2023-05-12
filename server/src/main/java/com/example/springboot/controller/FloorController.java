package com.example.springboot.controller;

import com.example.springboot.DTO.FloorDTO;
import com.example.springboot.common.BaseResponse;
import com.example.springboot.controller.request.FloorRequest;
import com.example.springboot.model.Floor;
import com.example.springboot.service.FloorService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.swagger.v3.core.util.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/floor")
public class FloorController {
    @Autowired
    FloorService floorService;

    @Autowired
    Gson gson;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllFloor(){
        try {
            JsonObject res = floorService.getAllFloor();
            return ResponseEntity.ok().body(res.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }

    @GetMapping("/get-floor/{id}")
    public ResponseEntity<?> getFloorById(@PathVariable int id){
        try {
            JsonObject res = floorService.getFloorById(id);
            return ResponseEntity.ok().body(res.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createFloor(@RequestBody FloorRequest floor){
        try {
            JsonObject res = floorService.create(floor);
            return ResponseEntity.ok().body(res.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateFloor(@PathVariable int id ,@RequestBody FloorRequest floor){
        try {
            JsonObject res = floorService.update(floor, id);
            return ResponseEntity.ok().body(res.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteFloor(@PathVariable int id){
        try {
            JsonObject res = floorService.delete(id);
            return ResponseEntity.ok().body(res.toString());
        } catch (Exception e){
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }

    // con phan kiem soat dien tich con lai cua tang nua
    @GetMapping("/available-area/{id}")
    public ResponseEntity<?> getTheRestArea(@PathVariable int id){
        try {
            double rest = floorService.getTheRestArea(id);
            JsonObject res = BaseResponse.createFullMessageResponse(0, "success", gson.toJsonTree(rest));
            return ResponseEntity.ok().body(res.toString());
        } catch (Exception e){
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }
    // nhieu truong du lieu thi dung request body
    // 1 truong du lieu thi dung request param
}

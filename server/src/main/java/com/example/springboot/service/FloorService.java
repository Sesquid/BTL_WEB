package com.example.springboot.service;

import com.example.springboot.DTO.FloorDTO;
import com.example.springboot.common.BaseResponse;
import com.example.springboot.controller.request.FloorRequest;
import com.example.springboot.model.Floor;
import com.example.springboot.repository.FloorRepository;
import com.example.springboot.util.json.GsonUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FloorService {

    @Autowired
    FloorRepository floorRepository;

    @Autowired
    ContractService contractService;

    @Autowired
    Gson gson;


     public JsonObject getAllFloor(){
         try {
             List<Floor> list = floorRepository.findAll();
             List<FloorDTO> listDTO = new ArrayList<>();
             for(Floor floor : list){
                 listDTO.add(new FloorDTO(floor));
             }
             return BaseResponse.createFullMessageResponse(0, "success", GsonUtil.toJsonArray(gson.toJson(listDTO)));
         } catch (Exception e){
             return BaseResponse.createFullMessageResponse(1, "system_error");
         }
     }

     public JsonObject getFloorById(int id){
         try {
            Floor floor = floorRepository.findByFloorId(id);
            if(floor == null){
                return BaseResponse.createFullMessageResponse(10, "Floor is null");
            }
            FloorDTO floorDTO = new FloorDTO(floor);
             return BaseResponse.createFullMessageResponse(0, "success", gson.toJsonTree(floorDTO));
         } catch (Exception e){
             e.printStackTrace();
             return BaseResponse.createFullMessageResponse(1, "system_error");
         }
     }

    public JsonObject create(FloorRequest floor){
        try {

            Floor tmp = new Floor();
            tmp.setName(floor.getName());
            tmp.setPricePerM2(Double.parseDouble(floor.getPricePerM2()));
            tmp.setArea(Double.parseDouble(floor.getArea()));

            floorRepository.save(tmp);
            FloorDTO floorDTO = new FloorDTO(tmp);
            return BaseResponse.createFullMessageResponse(0, "success", gson.toJsonTree(floorDTO));
        }
        catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.createFullMessageResponse(1, "system_error");
        }
    }

    public JsonObject update(FloorRequest floor, int id){
         try {
             Floor floorOld = floorRepository.findByFloorId(id);
             if(floorOld == null){
                 return BaseResponse.createFullMessageResponse(10, "Floor is null");
             }
             Floor floorNew = new Floor();
             floorNew.setName(floor.getName());
             floorNew.setPricePerM2(Double.parseDouble(floor.getPricePerM2()));
             floorNew.setArea(Double.parseDouble(floor.getArea()));

             floorOld.setName(floorNew.getName());
             floorOld.setPricePerM2(floorNew.getPricePerM2());
             floorOld.setArea(floorNew.getArea());

             floorRepository.save(floorOld);

             FloorDTO floorDTO = new FloorDTO(floorOld);
             return BaseResponse.createFullMessageResponse(0, "success", gson.toJsonTree(floorDTO));
         } catch (Exception e){
             e.printStackTrace();
             return BaseResponse.createFullMessageResponse(1, "system_error");
         }
    }

    public JsonObject delete(int id){
        try {
            Floor floor = floorRepository.findByFloorId(id);
            if(floor == null){
                return BaseResponse.createFullMessageResponse(10, "Floor is null");
            }
            floorRepository.delete(floor);
            FloorDTO floorDTO = new FloorDTO(floor);
            return BaseResponse.createFullMessageResponse(0, "success", gson.toJsonTree(floorDTO));
        } catch (Exception e){
            e.printStackTrace();
            return BaseResponse.createFullMessageResponse(1, "system_error");
        }

    }

    public double getTheRestArea(int floorID){
         Floor floor = floorRepository.findByFloorId(floorID);
         double totalArea = floor.getArea();
         double rentedArea = contractService.getSumofRentedFloorArea(floorID);
         return totalArea - rentedArea;
    }
}

package com.example.springboot.service;

import com.example.springboot.DTO.ContractDTO;
import com.example.springboot.DTO.FloorDTO;
import com.example.springboot.DTO.ServiceDTO;
import com.example.springboot.common.BaseResponse;
import com.example.springboot.controller.request.FloorRequest;
import com.example.springboot.model.Company;
import com.example.springboot.model.Contract;
import com.example.springboot.model.Floor;
import com.example.springboot.model.ServiceEntity;
import com.example.springboot.repository.ServiceRepository;
import com.example.springboot.util.json.GsonUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceService {
    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    Gson gson;

    public JsonObject getAllService(){
        try {
            List<ServiceEntity> list = serviceRepository.findAll();
            List<ServiceDTO> listDTO = new ArrayList<>();
            for(ServiceEntity x : list){
                listDTO.add(new ServiceDTO(x));
            }
            return BaseResponse.createFullMessageResponse(0, "success", GsonUtil.toJsonArray(gson.toJson(listDTO)));
        }
        catch (Exception e){
            e.printStackTrace();
            return BaseResponse.createFullMessageResponse(1, "system_error");
        }
    }

    public JsonObject getServiceByID(int id){
        try {
            ServiceEntity service = serviceRepository.findServiceByID(id);
            if(service == null){
                return BaseResponse.createFullMessageResponse(10, "Service is null");
            }
            ServiceDTO serviceDTO = new ServiceDTO(service);
            return BaseResponse.createFullMessageResponse(0, "success", gson.toJsonTree(serviceDTO));
        } catch (Exception e){
            e.printStackTrace();
            return BaseResponse.createFullMessageResponse(1, "system_error");
        }
    }

    public JsonObject create(ServiceEntity service){
        try {
            serviceRepository.save(service);
            ServiceDTO serviceDTO = new ServiceDTO(service);
            return BaseResponse.createFullMessageResponse(0, "success", gson.toJsonTree(serviceDTO));
        }
        catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.createFullMessageResponse(1, "system_error");
        }
    }

    public JsonObject update(ServiceEntity service, int id){
        try {
            ServiceEntity serviceOld = serviceRepository.findServiceByID(id);
            if(serviceOld == null){
                return BaseResponse.createFullMessageResponse(10, "Service is null");
            }

            serviceOld.setName(service.getName());
            serviceOld.setType(service.getType());
            serviceOld.setRequired(service.getRequired());
            serviceOld.setPrice(service.getPrice());

            serviceRepository.save(serviceOld);

            ServiceDTO serviceDTO = new ServiceDTO(serviceOld);
            return BaseResponse.createFullMessageResponse(0, "success", gson.toJsonTree(serviceDTO));
        } catch (Exception e){
            e.printStackTrace();
            return BaseResponse.createFullMessageResponse(1, "system_error");
        }
    }

    public JsonObject delete(int id){
        try {
            ServiceEntity service = serviceRepository.findServiceByID(id);
            if(service == null){
                return BaseResponse.createFullMessageResponse(10, "Service is null");
            }
            serviceRepository.delete(service);
            ServiceDTO serviceDTO = new ServiceDTO(service);
            return BaseResponse.createFullMessageResponse(0, "success", gson.toJsonTree(serviceDTO));
        } catch (Exception e){
            e.printStackTrace();
            return BaseResponse.createFullMessageResponse(1, "system_error");
        }
    }

    public JsonObject findServicesByName(String name){
        try {
            List<ServiceEntity> list = serviceRepository.findServiceByNameContaining(name);
            List<ServiceDTO> listDTO = new ArrayList<>();
            for(ServiceEntity service : list){
                listDTO.add(new ServiceDTO(service));
            }
            return BaseResponse.createFullMessageResponse(0, "success", GsonUtil.toJsonArray(gson.toJson(listDTO)));
        }
        catch (Exception e){
            e.printStackTrace();
            return BaseResponse.createFullMessageResponse(1, "system_error");
        }
    }



}

package com.example.springboot.service;

import com.example.springboot.DTO.SalaryDTO;
import com.example.springboot.common.BaseResponse;
import com.example.springboot.model.Salary;
import com.example.springboot.repository.SalaryRepository;
import com.example.springboot.util.json.GsonUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SalaryService {
    @Autowired
    SalaryRepository salaryRepository;

    @Autowired
    Gson gson;

    public JsonObject getAllSalary(){
        try {
            List<Salary> list = salaryRepository.findAll();
            List<SalaryDTO> listDTO =  new ArrayList<>();
            for(Salary x : list){
                listDTO.add(new SalaryDTO(x));
            }
            return BaseResponse.createFullMessageResponse(0, "success", GsonUtil.toJsonArray(gson.toJson(listDTO)));
        }
        catch (Exception e){
            e.printStackTrace();
            return BaseResponse.createFullMessageResponse(1, "system_error");
        }
    }

    public JsonObject getSalaryByID(int salaryID){
        try {
            Salary salary = salaryRepository.getSalaryByID(salaryID);
            if(salary == null){
                return BaseResponse.createFullMessageResponse(1, "Salary is null");
            }
            SalaryDTO salaryDTO = new SalaryDTO(salary);
            return BaseResponse.createFullMessageResponse(0, "success", gson.toJsonTree(salaryDTO));
        }
        catch (Exception e){
            e.printStackTrace();
            return BaseResponse.createFullMessageResponse(1, "system_error");
        }
    }

    public JsonObject create(Salary salary){
        try {
            salaryRepository.save(salary);
            SalaryDTO salaryDTO = new SalaryDTO(salary);
            return BaseResponse.createFullMessageResponse(0, "success", gson.toJsonTree(salaryDTO));
        }
        catch (Exception e){
            e.printStackTrace();
            return BaseResponse.createFullMessageResponse(1, "system_error");
        }
    }

    public JsonObject update(Salary salary, int salaryID){
        try {
            Salary salaryOld = salaryRepository.getSalaryByID(salaryID);
            if(salaryOld ==  null){
                return BaseResponse.createFullMessageResponse(1, "Salary is null");
            }

            salaryOld.setLevel(salary.getLevel());
            salaryOld.setSalary(salary.getSalary());

            salaryRepository.save(salaryOld);

            SalaryDTO salaryDTO = new SalaryDTO(salaryOld);
            return BaseResponse.createFullMessageResponse(0, "success", gson.toJsonTree(salaryDTO));
        }
        catch (Exception e){
            e.printStackTrace();
            return BaseResponse.createFullMessageResponse(1, "system_error");
        }
    }

    public JsonObject delete(int salaryID){
        try {
            Salary salary = salaryRepository.getSalaryByID(salaryID);
            if(salary == null){
                return BaseResponse.createFullMessageResponse(1, "Salary is null");
            }
            salaryRepository.delete(salary);
            SalaryDTO salaryDTO = new SalaryDTO(salary);
            return BaseResponse.createFullMessageResponse(0, "success", gson.toJsonTree(salaryDTO));
        }
        catch (Exception e){
            e.printStackTrace();
            return BaseResponse.createFullMessageResponse(1, "system_error");
        }
    }
}

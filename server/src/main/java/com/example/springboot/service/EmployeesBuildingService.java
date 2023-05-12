package com.example.springboot.service;

import com.example.springboot.DTO.EmployeesBuildingDTO;
import com.example.springboot.common.BaseResponse;
import com.example.springboot.model.EmployeesBuilding;

import com.example.springboot.model.Salary;
import com.example.springboot.repository.EmployeesBuildingRepository;
import com.example.springboot.repository.SalaryRepository;
import com.example.springboot.util.json.GsonUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeesBuildingService {
    @Autowired
    EmployeesBuildingRepository employeesBuildingRepository;

    @Autowired
    SalaryRepository salaryRepository;

    @Autowired
    Gson gson;

    public JsonObject getAllBuildingEmployee(){
        try {
            List<EmployeesBuilding> list = employeesBuildingRepository.findAll();
            List<EmployeesBuildingDTO> listDTO = new ArrayList<>();
            for(EmployeesBuilding x : list){
                listDTO.add(new EmployeesBuildingDTO(x));
            }
            return BaseResponse.createFullMessageResponse(0, "success", GsonUtil.toJsonArray(gson.toJson(listDTO)));
        }
        catch (Exception e){
            e.printStackTrace();
            return BaseResponse.createFullMessageResponse(1, "System_error");
        }
    }

    public JsonObject getBuildingEmployeeByID(int employeeID) {
        try {
            EmployeesBuilding buildingEmployee = employeesBuildingRepository.findBuildingEmployeeByID(employeeID);
            if(buildingEmployee == null){
                return BaseResponse.createFullMessageResponse(10, "Employee is null");
            }
            EmployeesBuildingDTO employeesBuildingDTO = new EmployeesBuildingDTO(buildingEmployee);
            return BaseResponse.createFullMessageResponse(0, "success", gson.toJsonTree(employeesBuildingDTO));
        } catch (Exception e){
            e.printStackTrace();
            return BaseResponse.createFullMessageResponse(1, "system_error");
        }
    }

    // them tim nhan vien bang ten

    public JsonObject getBuildingEmployeeByNameContaining(String name) {
        try {
            List<EmployeesBuilding> list = employeesBuildingRepository.findBuildingEmployeeByNameContaining(name);
            List<EmployeesBuildingDTO> listDTO = new ArrayList<>();
            for(EmployeesBuilding x : list){
                listDTO.add(new EmployeesBuildingDTO(x));
            }
            return BaseResponse.createFullMessageResponse(0, "success", GsonUtil.toJsonArray(gson.toJson(listDTO)));
        } catch (Exception e){
            e.printStackTrace();
            return BaseResponse.createFullMessageResponse(1, "system_error");
        }
    }


    public JsonObject create(EmployeesBuilding buildingEmployee, int salaryID) {
        try {
            Salary salary = salaryRepository.getSalaryByID(salaryID);
            if(salary == null){
                return BaseResponse.createFullMessageResponse(10, "Salary is null");
            }
            buildingEmployee.setSalary(salary);
            employeesBuildingRepository.save(buildingEmployee);
            EmployeesBuildingDTO employeesBuildingDTO = new EmployeesBuildingDTO(buildingEmployee);
            return BaseResponse.createFullMessageResponse(0, "success", gson.toJsonTree(employeesBuildingDTO));
        } catch (Exception e){
            e.printStackTrace();
            return BaseResponse.createFullMessageResponse(1, "system_error");
        }
    }

    public JsonObject update(EmployeesBuilding buildingEmployee, int employeeID, int salaryID) {
        try {
            Salary salary = salaryRepository.getSalaryByID(salaryID);
            if(salary == null){
                return BaseResponse.createFullMessageResponse(10, "Salary is null");
            }

            EmployeesBuilding buildingEmployeeOld = employeesBuildingRepository.findBuildingEmployeeByID(employeeID);
            if(buildingEmployeeOld == null){
                return BaseResponse.createFullMessageResponse(10, "Employee is null");
            }

            buildingEmployeeOld.setDOB(buildingEmployee.getDOB());
            buildingEmployeeOld.setAddress(buildingEmployee.getAddress());
            buildingEmployeeOld.setPhoneNumber(buildingEmployeeOld.getPhoneNumber());
            buildingEmployeeOld.setName(buildingEmployee.getName());
            buildingEmployeeOld.setPosition(buildingEmployee.getPosition());
            buildingEmployeeOld.setSalary(salary);

            employeesBuildingRepository.save(buildingEmployeeOld);

            EmployeesBuildingDTO employeesBuildingDTO = new EmployeesBuildingDTO(buildingEmployeeOld);
            return BaseResponse.createFullMessageResponse(0, "success", gson.toJsonTree(employeesBuildingDTO));
        } catch (Exception e){
            e.printStackTrace();
            return BaseResponse.createFullMessageResponse(1, "system_error");
        }
    }

    public JsonObject delete(int employeeID) {
        try {
            EmployeesBuilding buildingEmployee = employeesBuildingRepository.findBuildingEmployeeByID(employeeID);
            if(buildingEmployee == null){
                return BaseResponse.createFullMessageResponse(10, "Salary is null");
            }

            employeesBuildingRepository.delete(buildingEmployee);

            EmployeesBuildingDTO employeesBuildingDTO = new EmployeesBuildingDTO(buildingEmployee);
            return BaseResponse.createFullMessageResponse(0, "success", gson.toJsonTree(employeesBuildingDTO));
        } catch (Exception e){
            e.printStackTrace();
            return BaseResponse.createFullMessageResponse(1, "system_error");
        }
    }


}
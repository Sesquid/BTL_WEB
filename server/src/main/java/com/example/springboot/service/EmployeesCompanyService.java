package com.example.springboot.service;

import com.example.springboot.DTO.employees_company.EmployeesCompanyDTO;
import com.example.springboot.DTO.employees_company.EmployeesCompanyDeleteDTO;
import com.example.springboot.common.BaseResponse;
import com.example.springboot.model.Company;
import com.example.springboot.model.EmployeesCompany;
import com.example.springboot.repository.EmployeesCompanyRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeesCompanyService {
    @Autowired
    CompanyService companyService;
    @Autowired
    EmployeesCompanyRepository employeesCompanyRepository;
    @Autowired
    Gson gson;
    public JsonObject createEmployee(EmployeesCompanyDTO employeesCompanyDTO) {
        try {
            if(!companyService.isExistCompany(employeesCompanyDTO.getCompanyID())) {
                return BaseResponse.createFullMessageResponse(10, "company_not_exist");
            }
            if(isExistEmployeeCompany(employeesCompanyDTO.getEmployeeID())) {
                return BaseResponse.createFullMessageResponse(11, "employee_already_exist");
            }
            Company company = new Company();
            company.setCompanyID(employeesCompanyDTO.getCompanyID());
            EmployeesCompany employeesCompany = new EmployeesCompany();
            employeesCompany.setCompany(company);
            employeesCompany.setEmployeeID(employeesCompanyDTO.getEmployeeID());
            employeesCompany.setEmployeeName(employeesCompanyDTO.getEmployeeName());
            employeesCompany.setDate(employeesCompanyDTO.getDate());
            employeesCompany.setPhoneNumber(employeesCompanyDTO.getPhoneNumber());
            employeesCompany = employeesCompanyRepository.save(employeesCompany);
            employeesCompanyDTO = new EmployeesCompanyDTO(employeesCompany);
            return BaseResponse.createFullMessageResponse(0, "success", gson.toJsonTree(employeesCompanyDTO));
        }
        catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.createFullMessageResponse(1, "system_error");
        }
    }
    public JsonObject deleteEmployee(EmployeesCompanyDeleteDTO employeesCompanyDeleteDTO) {
        try {
            if(!isExistEmployeeCompany(employeesCompanyDeleteDTO.getEmployeeID())) {
                return BaseResponse.createFullMessageResponse(11, "employee_not_exist");
            }
            employeesCompanyRepository.deleteById(employeesCompanyDeleteDTO.getEmployeeID());
            return BaseResponse.createFullMessageResponse(0, "success");
        }
        catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.createFullMessageResponse(1, "system_error");
        }
    }
    public JsonObject updateEmployee(EmployeesCompanyDTO employeesCompanyDTO) {
        try {
            if(!companyService.isExistCompany(employeesCompanyDTO.getCompanyID())) {
                return BaseResponse.createFullMessageResponse(10, "company_not_exist");
            }
            if(!isExistEmployeeCompany(employeesCompanyDTO.getEmployeeID())) {
                return BaseResponse.createFullMessageResponse(11, "employee_not_exist");
            }
            Company company = new Company();
            company.setCompanyID(employeesCompanyDTO.getCompanyID());
            EmployeesCompany employeesCompany = new EmployeesCompany();
            employeesCompany.setCompany(company);
            employeesCompany.setEmployeeID(employeesCompanyDTO.getEmployeeID());
            employeesCompany.setEmployeeName(employeesCompanyDTO.getEmployeeName());
            employeesCompany.setDate(employeesCompanyDTO.getDate());
            employeesCompany.setPhoneNumber(employeesCompanyDTO.getPhoneNumber());
            employeesCompany = employeesCompanyRepository.save(employeesCompany);
            employeesCompanyDTO = new EmployeesCompanyDTO(employeesCompany);
            return BaseResponse.createFullMessageResponse(0, "success", gson.toJsonTree(employeesCompanyDTO));
        }
        catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.createFullMessageResponse(1, "system_error");
        }
    }

    public JsonObject getAllEmployees() {
        try {
            List<EmployeesCompany> employeesCompanyList = employeesCompanyRepository.findAll();
            List<EmployeesCompanyDTO> employeesCompanyDTOList = new ArrayList<>();
            for(EmployeesCompany e : employeesCompanyList) {
                employeesCompanyDTOList.add(new EmployeesCompanyDTO(e));
            }
            return BaseResponse.createFullMessageResponse(0, "success", gson.toJsonTree(employeesCompanyDTOList));
        }
        catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.createFullMessageResponse(1, "system_error");
        }
    }

    public JsonObject getAllEmployeesByCompanyID(String companyID) {
        try {
            List<EmployeesCompany> employeesCompanyList = employeesCompanyRepository.findAllByCompanyID(companyID);
            List<EmployeesCompanyDTO> employeesCompanyDTOList = new ArrayList<>();
            for(EmployeesCompany e : employeesCompanyList) {
                employeesCompanyDTOList.add(new EmployeesCompanyDTO(e));
            }
            return BaseResponse.createFullMessageResponse(0, "success", gson.toJsonTree(employeesCompanyDTOList));
        }
        catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.createFullMessageResponse(1, "system_error");
        }
    }

    public boolean isExistEmployeeCompany(String employeeId) {
        try {
            return employeesCompanyRepository.findById(employeeId).isPresent();
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

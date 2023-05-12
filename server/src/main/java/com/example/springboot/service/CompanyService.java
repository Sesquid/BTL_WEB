package com.example.springboot.service;

import com.example.springboot.DTO.company.CompanyCreateDTO;
import com.example.springboot.DTO.company.CompanyDeleteDTO;
import com.example.springboot.common.BaseResponse;
import com.example.springboot.model.Company;
import com.example.springboot.DTO.company.CompanyDTO;
import com.example.springboot.repository.CompanyRepository;
import com.example.springboot.util.json.GsonUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    Gson gson;
    public JsonObject getAllCompany() {
        try {
            List<Company> companyList = companyRepository.findAll();
            List<CompanyDTO> companyDTOList = new ArrayList<>();
            for (Company company : companyList) {
                companyDTOList.add(new CompanyDTO(company));
            }
            return BaseResponse.createFullMessageResponse(0, "success", GsonUtil.toJsonArray(gson.toJson(companyDTOList)));
        }
        catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.createFullMessageResponse(1, "system_error");
        }
    }

    public JsonObject createCompany(CompanyCreateDTO companyCreateDTO) {
        try {
            Company company = new Company();
            company.setCompanyID(null);
            company.setCompanyName(companyCreateDTO.getCompanyName());
            company.setTaxCode(companyCreateDTO.getTaxCode());
            company.setPhoneNumber(companyCreateDTO.getPhoneNumber());
            company = companyRepository.save(company);
            CompanyDTO companyDTO = new CompanyDTO(company);
            return BaseResponse.createFullMessageResponse(0, "success", gson.toJsonTree(companyDTO));
        }
        catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.createFullMessageResponse(1, "system_error");
        }
    }

    public JsonObject updateCompany(Company company) {
        try {
            if(!companyRepository.findById(company.getCompanyID()).isPresent()) {
                return BaseResponse.createFullMessageResponse(10, "company_not_exist");
            }
            company = companyRepository.save(company);
            CompanyDTO companyDTO = new CompanyDTO(company);
            return BaseResponse.createFullMessageResponse(0, "success", gson.toJsonTree(companyDTO));
        }
        catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.createFullMessageResponse(1, "system_error");
        }
    }

    public JsonObject deleteCompany(CompanyDeleteDTO companyDeleteDTO) {
        try {
            if(!companyRepository.findById(companyDeleteDTO.getCompanyID()).isPresent()) {
                return BaseResponse.createFullMessageResponse(10, "company_not_exist");
            }
            companyRepository.deleteById(companyDeleteDTO.getCompanyID());
            return BaseResponse.createFullMessageResponse(0, "success");
        }
        catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.createFullMessageResponse(1, "system_error");
        }
    }

    public boolean isExistCompany(Integer companyId) {
        try {
            return companyRepository.findById(companyId).isPresent();
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

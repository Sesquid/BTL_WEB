package com.example.springboot.service;

import com.example.springboot.DTO.ContractDTO;
import com.example.springboot.DTO.ServiceContractDTO;
import com.example.springboot.common.BaseResponse;
import com.example.springboot.model.Company;
import com.example.springboot.model.Contract;
import com.example.springboot.model.ServiceContract;
import com.example.springboot.model.ServiceEntity;
import com.example.springboot.repository.CompanyRepository;
import com.example.springboot.repository.ContractRepository;
import com.example.springboot.repository.ServiceContractRepository;
import com.example.springboot.repository.ServiceRepository;
import com.example.springboot.util.json.GsonUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.swagger.v3.core.util.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceContractService {

    @Autowired
    ServiceContractRepository serviceContractRepository;

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    Gson gson;

     public JsonObject getAllServiceContract(){
         try {
             List<ServiceContract> list = serviceContractRepository.findAll();
             List<ServiceContractDTO> listDTO = new ArrayList<>();
             for(ServiceContract x : list){
                 listDTO.add(new ServiceContractDTO(x));
             }
             return BaseResponse.createFullMessageResponse(0, "success", GsonUtil.toJsonArray(gson.toJson(listDTO)));
         }
         catch (Exception e){
             e.printStackTrace();
             return BaseResponse.createFullMessageResponse(1, "system_error");
         }
     }

     public JsonObject getServiceContractByID(int serviceContractID){
         try {
             ServiceContract serviceContract = serviceContractRepository.getServiceContractByID(serviceContractID);
             if(serviceContract == null){
                 return BaseResponse.createFullMessageResponse(10, "Service contract is null");
             }
             ServiceContractDTO serviceContractDTO = new ServiceContractDTO(serviceContract);
             return BaseResponse.createFullMessageResponse(0, "success", gson.toJsonTree(serviceContractDTO));
         }
         catch (Exception e){
             e.printStackTrace();
             return BaseResponse.createFullMessageResponse(1, "system_error");
         }
     }

     public JsonObject create(ServiceContract serviceContract, int serviceID, int companyID){
         try {
             ServiceEntity serviceEntity = serviceRepository.findServiceByID(serviceID);
             if(serviceEntity == null){
                 return BaseResponse.createFullMessageResponse(10, "Service is null");
             }

             Optional<Company> companyOptional = companyRepository.findById(companyID);
             if(companyOptional == null){
                 return BaseResponse.createFullMessageResponse(10, "Company is null");
             }

             Company company = new Company();
             company.setCompanyID(companyOptional.get().getCompanyID());
             company.setTaxCode(companyOptional.get().getTaxCode());
             company.setPhoneNumber(companyOptional.get().getPhoneNumber());
             company.setEmployeesCompany(companyOptional.get().getEmployeesCompany());
             company.setCompanyName(companyOptional.get().getCompanyName());

             serviceContract.setService(serviceEntity);
             serviceContract.setCompany(company);

             serviceContractRepository.save(serviceContract);

             ServiceContractDTO serviceContractDTO = new ServiceContractDTO(serviceContract);
             return BaseResponse.createFullMessageResponse(0, "success", gson.toJsonTree(serviceContractDTO));
         }
         catch (Exception e){
             e.printStackTrace();
             return BaseResponse.createFullMessageResponse(1, "system_error");
         }
     }

    public JsonObject update(ServiceContract serviceContract, int id){
        try {
            ServiceContract serviceContractOld = serviceContractRepository.getServiceContractByID(id);
            if(serviceContractOld == null){
                return BaseResponse.createFullMessageResponse(10, "Service contract is null");
            }

            serviceContractOld.setDescription(serviceContract.getDescription());
            serviceContractOld.setStartDate(serviceContract.getStartDate());

            serviceContractRepository.save(serviceContractOld);

            ServiceContractDTO serviceContractOldDTO = new ServiceContractDTO(serviceContractOld);
            return BaseResponse.createFullMessageResponse(0, "success", gson.toJsonTree(serviceContractOldDTO));
        } catch (Exception e){
            e.printStackTrace();
            return BaseResponse.createFullMessageResponse(1, "system_error");
        }
    }

    public JsonObject delete(int id){
        try {
            ServiceContract serviceContract = serviceContractRepository.getServiceContractByID(id);
            if(serviceContract == null){
                return BaseResponse.createFullMessageResponse(10, "Service contract is null");
            }

            serviceContractRepository.delete(serviceContract);

            ServiceContractDTO serviceContractDTO = new ServiceContractDTO(serviceContract);
            return BaseResponse.createFullMessageResponse(0, "success", gson.toJsonTree(serviceContractDTO));
        } catch (Exception e){
            e.printStackTrace();
            return BaseResponse.createFullMessageResponse(1, "system_error");
        }
    }

    public JsonObject getAllServiceContractOfCompany(int companyID){
        try {
            List<ServiceContract> list = serviceContractRepository.getServiceContractByCompany_companyID(companyID);
            List<ServiceContractDTO> listDTO = new ArrayList<>();
            for(ServiceContract x : list){
                listDTO.add(new ServiceContractDTO(x));
            }
            return BaseResponse.createFullMessageResponse(0, "success", GsonUtil.toJsonArray(gson.toJson(listDTO)));
        } catch (Exception e){
            e.printStackTrace();
            return BaseResponse.createFullMessageResponse(1, "system_error");
        }
    }

}

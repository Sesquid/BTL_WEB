package com.example.springboot.service;

import com.example.springboot.DTO.ContractDTO;
import com.example.springboot.common.BaseResponse;
import com.example.springboot.model.Company;
import com.example.springboot.model.Contract;
import com.example.springboot.model.Floor;
import com.example.springboot.repository.CompanyRepository;
import com.example.springboot.repository.ContractRepository;
import com.example.springboot.repository.FloorRepository;
import com.example.springboot.util.json.GsonUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContractService {
    @Autowired
    ContractRepository contractRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    FloorRepository floorRepository;

    @Autowired
    Gson gson;

    public JsonObject getAllContract(){
        try {
            List<Contract> list = contractRepository.findAll();
            List<ContractDTO> listDTO = new ArrayList<>();
            for(Contract contract : list){
                listDTO.add(new ContractDTO(contract));
            }
            return BaseResponse.createFullMessageResponse(0, "success", GsonUtil.toJsonArray(gson.toJson(listDTO)));
        } catch (Exception e){
            e.printStackTrace();
            return BaseResponse.createFullMessageResponse(1, "system_error");
        }
    }

    public JsonObject getContractById(int id){
        try {
            Contract contract = contractRepository.findContractById(id);
            if(contract == null){
                return BaseResponse.createFullMessageResponse(10, "Contract is null");
            }
            ContractDTO contractDTO = new ContractDTO(contract);
            return BaseResponse.createFullMessageResponse(0, "success", gson.toJsonTree(contractDTO));
        } catch (Exception e){
            e.printStackTrace();
            return BaseResponse.createFullMessageResponse(1, "system_error");
        }
    }

    // ham de lay ra list contract khi biet companyID api tra ve json object
    public JsonObject getContractsByCompanyIDApi(int companyID){
        try {
//            List<Contract> contract = this.getContractsByCompanyID(companyID);
            List<Contract> contract = contractRepository.getContractByCompany_companyID(companyID);
            List<ContractDTO> contractDTO = new ArrayList<>();
            for(Contract x : contract){
                contractDTO.add(new ContractDTO(x));
            }
            return BaseResponse.createFullMessageResponse(0, "success", GsonUtil.toJsonArray(gson.toJson(contractDTO)));
        } catch (Exception e){
            e.printStackTrace();
            return BaseResponse.createFullMessageResponse(1, "system_error");
        }
    }

    public JsonObject getContractsByFloorIDApi(int floorID){
        try {
//            List<Contract> contract = this.getContractsByFloorID(floorID);
            List<Contract> contract = contractRepository.getContractByFloor_floorID(floorID);
            List<ContractDTO> contractDTO = new ArrayList<>();
            for(Contract x : contract){
                contractDTO.add(new ContractDTO(x));
            }
            return BaseResponse.createFullMessageResponse(0, "success", GsonUtil.toJsonArray(gson.toJson(contractDTO)));
        } catch (Exception e){
            e.printStackTrace();
            return BaseResponse.createFullMessageResponse(1, "system_error");
        }
    }

    public JsonObject create(Contract contract, int companyID, int floorID){
        try {

            Optional<Company> com = companyRepository.findById(companyID);
            Company company = new Company();
            company.setCompanyID(com.get().getCompanyID());

            Floor floor = floorRepository.findByFloorId(floorID);
            floor.setFloorID(floorID);

            contract.setCompany(company);
            contract.setFloor(floor);

            contractRepository.save(contract);


            ContractDTO contractDTO = new ContractDTO(contract);
            return BaseResponse.createFullMessageResponse(0, "success", gson.toJsonTree(contractDTO));
        }
        catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.createFullMessageResponse(1, "system_error");
        }
    }

    public JsonObject update(Contract contract, int id){
        try {
            Contract contractOld = contractRepository.findContractById(id);
            if(contractOld == null){
                return BaseResponse.createFullMessageResponse(10, "Contract is null");
            }

            contractOld.setDescription(contract.getDescription());
            contractOld.setIsCanceled(contract.getIsCanceled());
            contractOld.setExpiredDate(contract.getExpiredDate());
            contractOld.setRentedDate(contract.getRentedDate());
            contractOld.setRentedArea(contract.getRentedArea());


            contractRepository.save(contractOld);

            ContractDTO contractDTO = new ContractDTO(contractOld);
            return BaseResponse.createFullMessageResponse(0, "success", gson.toJsonTree(contractDTO));
        } catch (Exception e){
            e.printStackTrace();
            return BaseResponse.createFullMessageResponse(1, "system_error");
        }
    }

    public JsonObject delete(int id){
        try {
            Contract contract = contractRepository.findContractById(id);
            if(contract == null){
                return BaseResponse.createFullMessageResponse(10, "Contract is null");
            }
            contractRepository.delete(contract);
            ContractDTO contractDTO = new ContractDTO(contract);
            return BaseResponse.createFullMessageResponse(0, "success", gson.toJsonTree(contractDTO));
        } catch (Exception e){
            e.printStackTrace();
            return BaseResponse.createFullMessageResponse(1, "system_error");
        }
    }

    // lay ra tong dien tich cong ty do da thue
    public double getSumOfRentedCompanyArea(int companyID) {
        double result = 0;
//        List<Contract> list = this.getContractsByCompanyID(companyID);
        List<Contract> list = contractRepository.getContractByCompany_companyID(companyID);
        for(Contract x : list){
            result += x.getRentedArea();
        }
        return result;
    }

    // lay ra tong dien tich da thue cua tang do
    public double getSumofRentedFloorArea(int floorID){
        double result = 0;
//        List<Contract> list = this.getContractsByFloorID(floorID);
        List<Contract> list = contractRepository.getContractByFloor_floorID(floorID);
        for(Contract x : list){
            result += x.getRentedArea();
        }
        return result;
    }





}

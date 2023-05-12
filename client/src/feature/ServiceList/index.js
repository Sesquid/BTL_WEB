import React, { useEffect, useState } from 'react'
import { useDispatch, useSelector } from 'react-redux';
import { allCompanies, allRegService, allService, tokenSelector } from '../../service/selector';
import { useNavigate, useParams } from 'react-router-dom';


import { deleteRegService, fetchRegService } from '../../service/registeredServiceSlice';
import ServiceContractModal from '../../component/ServiceContractModal';
import { fetchService } from '../../service/serviceSlice';
import { fetchCompanies } from '../../service/companyListSlice';
import axios from 'axios';

const ServiceList = () => {

  const dispatch = useDispatch();
  const navigate = useNavigate();

  const param = useParams();
  const id = param.companyID;

  const services = useSelector(allService);
  const companies = useSelector(allCompanies)
  const regServices = useSelector(allRegService)
  const token = useSelector(tokenSelector);
  const [addModal, setAddModal] = useState({ status: false, isAdding: true });
  const [companyName, setCompanyName] = useState("");
  const [newServiceContract, setNewServiceContract] = useState({

    serviceContractID: "",
    startDate: "",
    description: "",
    companyDTO: {
      companyID: id,
      companyName: "",
      taxCode: "",
      phoneNumber: ""
    },
    serviceDTO: {
      serviceID: "",
      name: "",
      type: "",
      price: "",
      required: ""
    }
  })


  useEffect(() => {

    if (!token) {
      navigate("/intro")
    }
    else {
      dispatch(fetchService());
      dispatch(fetchRegService(id))
    }
  }, [token])

  useEffect(() => {
    for (let i = 0; i < companies.length; i++) {
      if (companies[i].companyID == id) setCompanyName(companies[i].companyName)
    }
  }, [companies])

  useEffect(() => {
    setNewServiceContract({ ...newServiceContract, companyDTO: { ...newServiceContract.companyDTO, companyName: companyName } })
  }, [companyName])

  const handleEditBtn = (serviceID, serviceName) => {

    // console.log(serviceID)
    setNewServiceContract({
      ...newServiceContract, serviceDTO: {
        ...newServiceContract.serviceDTO, serviceID: serviceID
        , name: serviceName
      }
    });
    setAddModal({ status: true, isAdding: true });
  }


  return (
    <div className='container'>
      <ServiceContractModal
        isShow={addModal}
        newServiceContract={newServiceContract}
      />
      <div className='row'>
        <div className='table_header'>
          <div style={{ padding: "0px" }}>Danh sách các dịch vụ đã đăng ký của công ty {companyName}</div>

        </div>
      </div>
      <table className="table table-bordered" style={{ width: "1000px", margin: "20px auto" }}>

        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope='col'>Tên dịch vụ</th>
            <th scope="col">Loại dịch vụ</th>
            <th scope="col">Giá</th>
            <th scope='col'>Đăng ký</th>
          </tr>
        </thead>
        <tbody>
          {services.map((service, index) => {
            return (
              <tr key={service.serviceID}>
                <th scope="row">{index + 1}</th>
                <td>{service.name}</td>
                <td>{service.type}</td>
                <td>{service.price}</td>
                <td>
                  <button type="button" className="btn btn-success"
                    onClick={() => handleEditBtn(service.serviceID, service.name)}
                    disabled={regServices.filter(service1 => (service1.companyDTO.companyID === parseInt(id) &&
                      service1.serviceDTO.serviceID === service.serviceID)).length > 0}
                  >Đăng ký</button>
                </td>
              </tr>
            )
          })}

        </tbody>
      </table>
    </div>
  )
}

export default ServiceList;
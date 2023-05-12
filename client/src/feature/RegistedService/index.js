import React, { useEffect, useState } from 'react'
import { useDispatch, useSelector } from 'react-redux';
import { allCompanies, allRegService, tokenSelector } from '../../service/selector';
import { useNavigate, useParams } from 'react-router-dom';


import { deleteRegService, fetchRegService } from '../../service/registeredServiceSlice';
import ServiceContractModal from '../../component/ServiceContractModal';

const RegistedService = () => {

  const dispatch = useDispatch();
  const navigate = useNavigate();

  const param = useParams();
  const id = param.companyID;

  const allRegServices = useSelector(allRegService);
  const token = useSelector(tokenSelector);
  const companies = useSelector(allCompanies)
  const [addModal, setAddModal] = useState({ status: false, isAdding: true });
  const [companyName, setCompanyName] = useState("");
  const [newServiceContract, setNewServiceContract] = useState({

    serviceContractID: "",
    startDate: "",
    description: "",
    companyDTO: {
      companyID: "",
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
      dispatch(fetchRegService(id));
      
    }
  }, [token])


  useEffect(() => {
    for (let i = 0; i < companies.length; i++) {
      // console.log("yes")
      if (companies[i].companyID == id) setCompanyName(companies[i].companyName)
    }
  }, [companies])

  const handleAddBtn = () => {
    navigate("/services/companyID/" + id)
  }



  const handleDeleteBtn = (id) => {
    dispatch(deleteRegService(id));
  }

  const handleEditBtn = (contract) => {
    setNewServiceContract(contract);
    setAddModal({ status: true, isAdding: false });
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
          <div>
            <button type="button" className="btn btn-success"
              onClick={(handleAddBtn)}
            >Đăng ký thêm dịch vụ</button>
          </div>
        </div>
      </div>
      <table className="table table-bordered" style={{ width: "1000px", margin: "20px auto" }}>

        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope='col'>Tên dịch vụ</th>
            <th scope="col">Ngày bắt đầu</th>
            <th scope="col">Mô tả</th>
            <th scope='col'>Sửa</th>
            <th scope='col'>Xoá</th>
          </tr>
        </thead>
        <tbody>
          {allRegServices.length ? allRegServices.map((contract, index) => {
            return (
              <tr key={contract.serviceContractID}>
                <th scope="row">{index + 1}</th>
                <td>{contract.serviceDTO.name}</td>
                <td>{contract.startDate}</td>
                <td>{contract.description}</td>
                <td>
                  <button type="button" className="btn btn-secondary"
                    onClick={() => handleEditBtn(contract)}
                  >Edit</button>
                </td>
                <td>
                  <button type="button" className="btn btn-danger"
                    onClick={() => handleDeleteBtn(contract.serviceContractID)}
                    disabled={contract.serviceDTO.required === 1 ? true : false}
                  >Huỷ</button>
                </td>
              </tr>
            )
          }) : <tr><th>Khong co hop dong nao</th></tr>}

        </tbody>
      </table>
    </div>
  )
}

export default RegistedService;
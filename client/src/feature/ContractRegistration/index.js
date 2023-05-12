import React, { useEffect, useState } from 'react'
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate, useParams } from 'react-router-dom';
import { allCompanies, tokenSelector } from '../../service/selector';
import { deleteCompany, fetchCompanies } from '../../service/companyListSlice';
import CompanyListModal from "../../component/CompanyListModal";
import ContractListModal from '../../component/ContractListModal';
import { param } from 'jquery';

const ContractRegistration = () => {

  const companies = useSelector(allCompanies)
  const token = useSelector(tokenSelector);

  const navigate = useNavigate();
  const dispatch = useDispatch();

  const [contractParam, setContractParam] = useState({ companyID: "", floorID: "" })
  const [newContract, setNewContract] = useState({
    contractID: "",
    rentedDate: "",
    expiredDate: "",
    rentedArea: "",
    description: "",
    isCanceled: "",
    companyDTO: {
      companyID: "",
      companyName: "",
      taxCode: "",
      phoneNumber: ""
    },
    floorDTO: {
      floorID: "",
      name: "",
      pricePerM2: "",
      area: ""
    }
  })
  const [remainCompany, setRemainCompany] = useState([]);
  const [addModal, setAddModal] = useState({ status: false, isAdding: false });
  const [q, setQ] = useState("")

  const param = useParams();

  useEffect(() => {

    if (!token) {
      navigate("/intro")
    }
    else {
      dispatch(fetchCompanies())
    }
  }, [token])

  useEffect(() => {
    setRemainCompany(companies);
  }, [companies])

  useEffect(() => {
    setRemainCompany(companies.filter((company) => company.companyName.toLowerCase().includes(q.toLowerCase())))
  }, [q])

  const handleViewBtn = (item) => {
    const floorID = param.floorID
    setNewContract({
      contractID: "",
      rentedDate: "",
      expiredDate: "",
      rentedArea: "",
      description: "",
      isCanceled: 0,
      companyDTO: {
        companyID: item.companyID,
        companyName: item.companyName,
        taxCode: "",
        phoneNumber: ""
      },
      floorDTO: {
        floorID: floorID,
        name: "",
        pricePerM2: "",
        area: ""
      }
    });
    setAddModal({ status: true, isAdding: true });

  }

  const handleSearchInput = (e) => {
    setQ(e.target.value);
  }

  if (token)
    return (
      <div className='container'>
        <ContractListModal
          isShow={addModal}
          newContract={newContract}
        />
        <div className='row'>
          <div className='table_header'>
            <div style={{ padding: "0px" }}>Danh sách các công ty</div>
            <div>
              <input
                style={{
                  width: "300px", fontSize: "1.23rem", margin: "0 10px",
                  border: "none", outline: "solid 3px  rgba(121, 166, 232, 0.7)"
                }}
                value={q}
                onChange={(e) => handleSearchInput(e)}

              ></input>
              <button type="button" className="btn btn-success" disabled
              >Tìm kiếm</button>
            </div>

          </div>
        </div>
        <table className="table table-bordered" style={{ width: "1000px", margin: "20px auto" }}>

          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Name</th>
              <th scope="col">Tax Code</th>
              <th scope='col'>Phone</th>
              <th scope='col'>Đăng ký</th>
            </tr>
          </thead>
          <tbody>

            {remainCompany.map((item, index) => {
              return (
                <tr key={item.companyID}>
                  <th scope="row">{index + 1}</th>
                  <td>{item.companyName}</td>
                  <td>{item.taxCode}</td>
                  <td>{item.phoneNumber}</td>
                  <td>
                    <button type="button" className="btn btn-primary"
                      onClick={() => handleViewBtn(item)}
                    >Đăng ký hợp đồng</button>
                  </td>

                </tr>
              )
            })}

          </tbody>
        </table>
      </div>
    )
}

export default ContractRegistration;
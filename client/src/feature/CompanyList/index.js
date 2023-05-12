import "./style.css"
import React, { useEffect, useState } from 'react'
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { allCompanies, tokenSelector } from '../../service/selector';
import { deleteCompany, fetchCompanies } from '../../service/companyListSlice';
import CompanyListModal from "../../component/CompanyListModal";

const CompanyList = () => {

  const companies = useSelector(allCompanies)
  const token = useSelector(tokenSelector);

  const navigate = useNavigate();
  const dispatch = useDispatch();

  const [remainCompany, setRemainCompany] = useState([]);
  const [addModal, setAddModal] = useState({ status: false, isAdding: false });
  const [newCompany, setNewCompany] = useState({ companyName: "", taxCode: "", phoneNumber: "" })
  const [q, setQ] = useState("")


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

  const handleViewBtn = (id) => {
    // console.log(id)
    navigate("/company/" + id)
  }

  const handleDeleteBtn = (item) => {
    dispatch(deleteCompany(item))
  }

  const handleAddBtn = () => {
    setNewCompany({ companyName: "", taxCode: "", phoneNumber: "" })
    setAddModal({ status: true, isAdding: true });
  }

  const handleEditBtn = (company) => {
    setNewCompany(company)
    setAddModal({ status: true, isAdding: false });
  }

  const handleSearchInput = (e) => {
    setQ(e.target.value);
  }

  if (token)
    return (
      <div className='container'>
        <CompanyListModal
          isShow={addModal}
          newCompany={newCompany}
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
              <button type="button" className="btn btn-success"
                onClick={(handleAddBtn)} disabled
              >Tìm kiếm</button>
            </div>
            <div>
              <button type="button" className="btn btn-success"
                onClick={(handleAddBtn)}
              >Thêm công ty</button>
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
              <th scope='col'>View</th>
              <th scope='col'>Edit</th>
              <th scope='col'>Delete</th>
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
                      onClick={() => handleViewBtn(item.companyID)}
                    >View</button>
                  </td>
                  <td>
                    <button type="button" className="btn btn-secondary"
                      onClick={() => handleEditBtn(item)}
                    >Edit</button>
                  </td>
                  <td>
                    <button type="button" className="btn btn-danger"
                      onClick={() => handleDeleteBtn(item)}
                    >Delete</button>
                  </td>
                </tr>
              )
            })}

          </tbody>
        </table>
      </div>
    )
}

export default CompanyList;
import React, { useEffect, useState } from 'react'
import { useDispatch, useSelector } from 'react-redux';
import { allEmployeesCompany, tokenSelector } from '../../service/selector';

import { useNavigate, useParams } from 'react-router-dom';
import { deleteEmployeeCompany, fetchEmployeesCompany } from '../../service/employeesCompanySlice';
import EmployeeCompanyModal from '../../component/EmployeeCompanyModal';

const Company = () => {

  const param = useParams();
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const employees = useSelector(allEmployeesCompany);
  const token = useSelector(tokenSelector);

  const [addModal, setAddModal] = useState({ status: false, isAdding: true });
  const [newEmployee, setNewEmployee] = useState({})



  useEffect(() => {
    if (!token) {
      navigate("/intro")
    }
    else {
      dispatch(fetchEmployeesCompany(param.companyID));
    }
  }, [token])

  const handleAddBtn = () => {
    setNewEmployee({
      employeeID: "",
      employeeName: "",
      date: "",
      phoneNumber: "",
      companyID: ""
    });
    setAddModal({ status: true, isAdding: true });
  }

  const handleDeleteBtn = (employee) => {
    dispatch(deleteEmployeeCompany(employee));
  }

  const handleEditBtn = (employee) => {
    setNewEmployee(employee);
    setAddModal({ status: true, isAdding: false });
  }

  return (
    <div className='container'>
      <EmployeeCompanyModal
        isShow={addModal}
        newEmployee={newEmployee}
      />
      <div className='row'>
        <div className='table_header'>
          <div style={{ padding: "0px" }}>Danh sách nhân viên</div>
          <div>
            <button type="button" className="btn btn-success"
              onClick={(handleAddBtn)}
            >Thêm nhân viên</button>
          </div>
        </div>
      </div>
      <table className="table table-bordered" style={{ width: "1000px", margin: "20px auto" }}>

        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">ID</th>
            <th scope="col">Date</th>
            <th scope='col'>Phone</th>
            <th scope='col'>Edit</th>
            <th scope='col'>Delete</th>
          </tr>
        </thead>
        <tbody>

          {employees.map((employee, index) => {
            return (
              <tr key={employee.employeeID}>
                <th scope="row">{index + 1}</th>
                <td>{employee.employeeName}</td>
                <td>{employee.employeeID}</td>
                <td>{employee.date}</td>
                <td>{employee.phoneNumber}</td>
                <td>
                  <button type="button" className="btn btn-secondary"
                    onClick={() => handleEditBtn(employee)}
                  >Edit</button>
                </td>
                <td>
                  <button type="button" className="btn btn-danger"
                    onClick={() => handleDeleteBtn(employee)}
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

export default Company;
import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { tokenSelector } from "../../service/selector";
import {
  deleteEmployeeBuilding,
  fetchEmployeesBuilding,
} from "../../service/employeesBuildingSlice";
import EmployeeBuildingModal from "../../component/EmployeeBuildingModal";

const EmployeeBuilding = () => {
  const employeesBuilding = useSelector(
    state => state.employeesBuilding.employeesBuilding
  );
  const token = useSelector(tokenSelector);

  const navigate = useNavigate();
  const dispatch = useDispatch();

  const [remainEmployeeBuildingCompany, setRemainEmployeeBuildingCompany] =
    useState([]);
  const [addModal, setAddModal] = useState({ status: false, isAdding: false });
  const [newEmployeeBuilding, setNewEmployeeBuilding] = useState({
    name: "",
    address: "",
    phoneNumber: "",
    salary: "",
    date: "",
    position: "",
    salaryID: "",
  });

  useEffect(() => {
    dispatch(fetchEmployeesBuilding());
    if (!token) {
      navigate("/intro");
    }
  }, [token]);

  useEffect(() => {
    setRemainEmployeeBuildingCompany(employeesBuilding);
  }, [employeesBuilding]);

  const handleDeleteBtn = item => {
    dispatch(deleteEmployeeBuilding(item));
  };

  const handleAddBtn = () => {
    setNewEmployeeBuilding({
      name: "",
      address: "",
      phoneNumber: "",
      position: "",
      salary: "",
      date: "",
      salaryID: "",
    });
    setAddModal({ status: true, isAdding: true });
  };

  const handleEditBtn = item => {
    setNewEmployeeBuilding({ ...item, date: item.DOB });
    setAddModal({ status: true, isAdding: false });
  };

  if (token)
    return (
      <div className="container">
        <EmployeeBuildingModal
          isShow={addModal}
          newEmployee={newEmployeeBuilding}
        />
        <div className="row">
          <div className="table_header">
            <div style={{ padding: "0px" }}>Thông tin nhân viên tòa nhà</div>
            <div>
              <button
                type="button"
                className="btn btn-success"
                onClick={handleAddBtn}>
                Thêm nhân viên mới
              </button>
            </div>
          </div>
        </div>
        <table
          className="table table-bordered"
          style={{ width: "1000px", margin: "20px auto" }}>
          <thead>
            <tr>
              <th scope="col">Id</th>
              <th scope="col">Name</th>
              <th scope="col">Ngày sinh</th>
              <th scope="col">Địa chỉ</th>
              <th scope="col">Số điện thoại</th>
              <th scope="col">Vị trí</th>
              <th scope="col">Id salary</th>
              <th scope="col">Edit</th>
              <th scope="col">Delete</th>
            </tr>
          </thead>
          <tbody>
            {remainEmployeeBuildingCompany.map((item, index) => {
              return (
                <tr key={item.employeeID}>
                  <th scope="row">{index + 1}</th>
                  <td>{item.name}</td>
                  <td>{item.DOB.substring(0, 12)}</td>
                  <td>{item.address}</td>
                  <td>{item.phoneNumber}</td>
                  <td>{item.position}</td>
                  <td>{item.salaryID}</td>
                  <td>
                    <button
                      type="button"
                      className="btn btn-secondary"
                      onClick={() => handleEditBtn(item)}>
                      Edit
                    </button>
                  </td>
                  <td>
                    <button
                      type="button"
                      className="btn btn-danger"
                      onClick={() => handleDeleteBtn(item)}>
                      Delete
                    </button>
                  </td>
                </tr>
              );
            })}
          </tbody>
        </table>
      </div>
    );
};

export default EmployeeBuilding;

import React, { useEffect, useState } from "react";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import Modal from "react-bootstrap/Modal";
import { useDispatch } from "react-redux";
import { useParams } from "react-router-dom";
import {
  createEmployeeBuilding,
  updateEmployeeBuilding,
} from "../../service/employeesBuildingSlice";

const EmployeeBuildingModal = props => {
  //state
  const [show, setShow] = useState(props.isShow);
  const [newEmployee, setNewEmployee] = useState(props.newEmployee);
  const [currentEmployee, setCurrentEmployee] = useState(props.newEmployee);

  //dispatch
  const dispatch = useDispatch();
  const param = useParams();
  //useEffect
  useEffect(() => {
    setShow(props.isShow);
    setCurrentEmployee(props.newEmployee);

    if (!props.isShow.isAdding) {
      setNewEmployee(props.newEmployee);
    }
  }, [props.isShow]);

  //handle event
  const handleClose = () => {
    setShow({ ...show, status: false });
    setCurrentEmployee({});
  };

  const handleNameInput = e => {
    setCurrentEmployee({ ...currentEmployee, name: e.target.value });
    if (e.target.value.length > 0) {
      setNewEmployee({ ...newEmployee, name: e.target.value });
    }
  };

  const handleDateInput = e => {
    setCurrentEmployee({ ...currentEmployee, date: e.target.value });
    setNewEmployee({ ...newEmployee, date: e.target.value });
  };

  const handleAddressInput = e => {
    setCurrentEmployee({ ...currentEmployee, address: e.target.value });
    setNewEmployee({ ...newEmployee, address: e.target.value });
  };

  const handlePhoneInput = e => {
    setCurrentEmployee({ ...currentEmployee, phoneNumber: e.target.value });
    setNewEmployee({ ...newEmployee, phoneNumber: e.target.value });
  };

  const handlePositionInput = e => {
    setCurrentEmployee({ ...currentEmployee, position: e.target.value });
    setNewEmployee({ ...newEmployee, position: e.target.value });
  };

  const handleSalaryIDInput = e => {
    setCurrentEmployee({ ...currentEmployee, salaryID: e.target.value });
    setNewEmployee({ ...newEmployee, salaryID: e.target.value });
  };

  const handleSaveBtn = () => {
    show.isAdding
      ? dispatch(createEmployeeBuilding(newEmployee))
      : dispatch(updateEmployeeBuilding(newEmployee));
    handleClose();
  };

  return (
    <Modal show={show.status} onHide={handleClose}>
      <Modal.Header closeButton>
        <Modal.Title>
          {show.isAdding ? "Thêm nhân viên tòa nhà" : "Sửa nhân viên tòa nhà"}
        </Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form>
          <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
            <Form.Label>Tên nhân viên</Form.Label>
            <Form.Control
              type="email"
              autoFocus
              onChange={e => handleNameInput(e)}
              value={currentEmployee.name}
            />
          </Form.Group>
          <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
            <Form.Label>Date</Form.Label>
            <Form.Control
              type="email"
              onChange={e => handleDateInput(e)}
              value={currentEmployee.date}
            />
          </Form.Group>
          <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
            <Form.Label>Địa chỉ</Form.Label>
            <Form.Control
              type="email"
              onChange={e => handleAddressInput(e)}
              value={currentEmployee.address}
            />
          </Form.Group>
          <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
            <Form.Label>Số điện thoại</Form.Label>
            <Form.Control
              type="email"
              onChange={e => handlePhoneInput(e)}
              value={currentEmployee.phoneNumber}
            />
          </Form.Group>
          <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
            <Form.Label>Vị trí</Form.Label>
            <Form.Control
              type="email"
              onChange={e => handlePositionInput(e)}
              value={currentEmployee.position}
            />
          </Form.Group>
          <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
            <Form.Label>Id salary</Form.Label>
            <Form.Control
              type="email"
              onChange={e => handleSalaryIDInput(e)}
              value={currentEmployee.salaryID}
            />
          </Form.Group>
        </Form>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={handleClose}>
          Huỷ
        </Button>
        <Button variant="primary" onClick={handleSaveBtn}>
          {show.isAdding ? "Thêm" : "Cập nhật"}
        </Button>
      </Modal.Footer>
    </Modal>
  );
};

export default EmployeeBuildingModal;

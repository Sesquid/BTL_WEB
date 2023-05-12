import React, { useEffect, useState } from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Modal from 'react-bootstrap/Modal';
import { useDispatch, useSelector } from 'react-redux';
import { allEmployeesCompany } from '../../service/selector';
import { isNumeric } from '../../util/checkNumber';
import { createEmployeeCompany, updateEmployeeCompany } from '../../service/employeesCompanySlice';
import { useParams } from 'react-router-dom';

const EmployeeCompanyModal = (props) => {

  //state
  const [show, setShow] = useState(props.isShow);
  const [validation, setValidation] = useState()
  const [newEmployee, setNewEmployee] = useState(props.newEmployee);
  const [currentEmployee, setCurrentEmployee] = useState(props.newEmployee);

  //dispatch
  const dispatch = useDispatch();
  const allEmployee = useSelector(allEmployeesCompany)
  const param = useParams();
  //useEffect
  useEffect(() => {
    setShow(props.isShow)
    setCurrentEmployee(props.newEmployee)

    if (!props.isShow.isAdding) {
      setNewEmployee(props.newEmployee)

    }
  }, [props.isShow])

  //handle event
  const handleClose = () => {
    setShow({ ...show, status: false });
    setCurrentEmployee({})
  }

  const handleNameInput = (e) => {
    console.log(param.companyID)
    setCurrentEmployee({
      ...currentEmployee,
      employeeName: e.target.value
    })
    if (e.target.value.length > 0) {
      setNewEmployee({
        ...newEmployee, employeeName: e.target.value,
        companyID: param.companyID
      });
    }
  }

  const handleIDInput = (e) => {
    setCurrentEmployee({ ...currentEmployee, employeeID: e.target.value })
    setNewEmployee({ ...newEmployee, employeeID: e.target.value })

  }

  const handleDateInput = (e) => {
    setCurrentEmployee({ ...currentEmployee, date: e.target.value })
    setNewEmployee({ ...newEmployee, date: e.target.value })

  }

  const handlePhoneInput = (e) => {
    setCurrentEmployee({ ...currentEmployee, phoneNumber: e.target.value })
    setNewEmployee({ ...newEmployee, phoneNumber: e.target.value })
  }

  const handleSaveBtn = () => {
    setNewEmployee({ ...newEmployee, companyID: param.companyID })
    show.isAdding ? dispatch(createEmployeeCompany(newEmployee))
      : dispatch(updateEmployeeCompany(newEmployee))
    // console.log(newEmployee)
    handleClose();
  }

  return (
    <Modal show={show.status} onHide={handleClose}>
      <Modal.Header closeButton>
        <Modal.Title>{show.isAdding ? "Thêm nhân viên" : "Sửa nhân viên"}</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form>
          <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
            <Form.Label>Tên nhân viên</Form.Label>
            <Form.Control
              type="email"
              autoFocus
              onChange={(e) => handleNameInput(e)}
              value={currentEmployee.employeeName}
            />
          </Form.Group>
          <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
            <Form.Label>Mã nhân viên</Form.Label>
            <Form.Control
              type="email"
              onChange={(e) => handleIDInput(e)}
              value={currentEmployee.employeeID}
              readOnly={!show.isAdding}
            />
          </Form.Group>
          <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
            <Form.Label>Date</Form.Label>
            <Form.Control
              type="email"
              onChange={(e) => handleDateInput(e)}
              value={currentEmployee.date}
              readOnly={show.isAdding ? false : true}
              placeholder='yyyy-MM-dd'
            />
          </Form.Group>
          <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
            <Form.Label>Phone</Form.Label>
            <Form.Control
              type="email"
              onChange={(e) => handlePhoneInput(e)}
              value={currentEmployee.phoneNumber}
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
}

export default EmployeeCompanyModal;
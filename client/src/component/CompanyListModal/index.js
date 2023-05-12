import React, { useEffect, useState } from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Modal from 'react-bootstrap/Modal';
import { useDispatch, useSelector } from 'react-redux';
import { allCompanies } from '../../service/selector';
import { isPhoneNumber, isNumeric } from '../../util/checkNumber';
import { createCompany, updateCompany } from '../../service/companyListSlice';

//chua co update va delete

const CompanyListModal = (props) => {


  //state
  const [show, setShow] = useState(props.isShow);
  const [validation, setValidation] = useState({ companyName: "Không được để trống", taxCode: "Không được để trống", phoneNumber: "Không được để trống" })
  const [newCompany, setNewCompany] = useState(props.newCompany);
  const [currentCompany, setCurrentCompany] = useState(newCompany);


  //selector
  const companies = useSelector(allCompanies);

  //dispatch
  const dispatch = useDispatch();

  //useEffect
  useEffect(() => {
    setShow(props.isShow)
    setCurrentCompany(props.newCompany)

    if (props.isShow.status && !props.isShow.isAdding) {
      setValidation({ ...validation, companyName: "OK", taxCode: "OK", phoneNumber: "OK" });
      setNewCompany(props.newCompany)
    }

  }, [props.isShow])

  //handle event
  const handleClose = () => {
    setShow({ ...show, status: false });
    setValidation({ companyName: "Không được để trống", taxCode: "Không được để trống", phoneNumber: "Không được để trống" })
    setCurrentCompany({})
  }

  const handleNameInput = (e) => {

    setCurrentCompany({ ...currentCompany, companyName: e.target.value })
    let exist = false;
    for (let i = 0; i < companies.length; i++) {
      if (companies[i].companyName.toLowerCase() === e.target.value.toLowerCase()) {
        exist = true;
      }
    }
    if (e.target.value.length > 0) {
      if (exist === true) setValidation({ ...validation, companyName: "Tên đã tồn tại!" })
      else {
        setValidation({ ...validation, companyName: "OK" });
        setNewCompany({ ...newCompany, companyName: e.target.value });
      }
    }
    else setValidation({ ...validation, companyName: "Không được để trống!" })
  }

  const handleAreaInput = (e) => {
    setCurrentCompany({ ...currentCompany, taxCode: e.target.value })
    if (isNumeric(e.target.value)) {
      setNewCompany({ ...newCompany, taxCode: e.target.value })
      setValidation({ ...validation, taxCode: "OK" })
    }
    else {
      setValidation({ ...validation, taxCode: "Nhập số dương" })
    }
  }

  const handlePriceInput = (e) => {
    setCurrentCompany({ ...currentCompany, phoneNumber: e.target.value })
    if (isPhoneNumber(e.target.value)) {
      setNewCompany({ ...newCompany, phoneNumber: e.target.value })
      setValidation({ ...validation, phoneNumber: "OK" })
    }
    else {
      setValidation({ ...validation, phoneNumber: "Nhập số điện thoại" })
    }
  }

  const handleSaveBtn = () => {
    let enabled = true;
    if (validation.companyName !== "OK" || validation.taxCode !== "OK" || validation.phoneNumber !== "OK")
      enabled = false;
    if (enabled === true) {
      show.isAdding ? dispatch(createCompany(newCompany)) : dispatch(updateCompany(newCompany))
    }
    else {
      alert("Điền thông tin hợp lệ!")
    }
    handleClose();
  }

  return (
    <Modal show={show.status} onHide={handleClose}>
      <Modal.Header closeButton>
        <Modal.Title>{show.isAdding ? "Thêm công ty" : "Sửa công ty"}</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form>
          <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
            <Form.Label>Tên công ty</Form.Label>
            <Form.Control
              type="text"
              placeholder="Tên công ty..."
              autoFocus
              onChange={(e) => handleNameInput(e)}
              value={currentCompany.companyName}
            />
            <span>{validation.companyName}</span>
          </Form.Group>
          <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
            <Form.Label>Tax code</Form.Label>
            <Form.Control
              type="text"
              placeholder="Tax code..."
              onChange={(e) => handleAreaInput(e)}
              value={currentCompany.taxCode}
            />
            <span>{validation.taxCode}</span>
          </Form.Group>
          <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
            <Form.Label>Phone</Form.Label>
            <Form.Control
              type="text"
              placeholder="Phone number..."
              onChange={(e) => handlePriceInput(e)}
              value={currentCompany.phoneNumber}
            />
          </Form.Group>
          <span>{validation.phoneNumber}</span>
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

export default CompanyListModal;
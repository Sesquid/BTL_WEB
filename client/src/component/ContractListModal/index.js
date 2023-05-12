import React, { useEffect, useState } from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Modal from 'react-bootstrap/Modal';
import { useDispatch, useSelector } from 'react-redux';
import { allContract } from '../../service/selector';
import { createContract, updateContract } from '../../service/contractListSlice';
import { convertToDateForm, isValidate } from '../../util/checkNumber';
import axios from 'axios';
import { useParams } from 'react-router-dom';

const ContractListModal = (props) => {

  // console.log(props.newContract)
  //state
  const [show, setShow] = useState(props.isShow);
  const [validation, setValidation] = useState("OK")
  const [newContract, setNewContract] = useState(props.newContract);
  const [currentContract, setCurrentContract] = useState(props.newContract);
  const [avaiArea, setAvaiArea] = useState(0);
  const [remainArea, setRemainArea] = useState(avaiArea)

  //selector
  const contracts = useSelector(allContract);

  //dispatch
  const dispatch = useDispatch();

  const param = useParams();




  //useEffect
  useEffect(() => {
    

    setShow(props.isShow)
    setCurrentContract(props.newContract)
    setNewContract(props.newContract)
    if (!props.isShow.isAdding) {
      setValidation("OK");
      setNewContract(props.newContract)
      const floorID = param.floorID
      axios.get(`http://localhost:8080/api/floor/available-area/${floorID}`)
        .then(res => {
          setAvaiArea(res.data.data)
        })
    }

  }, [props.isShow])



  useEffect(() => {
    setRemainArea(avaiArea)
  }, [avaiArea])
  //handle event
  const handleClose = () => {
    setShow({ ...show, status: false });
    setValidation("OK")
    setCurrentContract({
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
  }

  const handleNameInput = (e) => {

    setNewContract({
      ...currentContract, companyDTO: { ...currentContract.companyDTO, companyName: e.target.value }
    })
    if (e.target.value.length > 0) {
      setNewContract({
        ...newContract, companyDTO: { ...newContract.companyDTO, companyName: e.target.value }
      })
    }
  }

  const handleAreaInput = (e, available_area) => {
    console.log(newContract)
    setCurrentContract({ ...currentContract, rentedArea: e.target.value })

    if (isValidate(available_area, e.target.value)) {

      setNewContract({ ...newContract, rentedArea: parseFloat(e.target.value) })
      setValidation("OK")
    }
    else {
      setValidation("Nhập diện tích nhỏ hơn hoặc bằng số diện tích còn lại")
    }
  }

  const handleExpiredDateInput = (e) => {
    setCurrentContract({ ...currentContract, expiredDate: e.target.value })
    setNewContract({
      ...newContract, expiredDate: convertToDateForm(e.target.value),
      rentedDate: convertToDateForm(newContract.rentedDate)
    })
  }

  const handleRentedDateInput = (e) => {
    setCurrentContract({ ...currentContract, rentedDate: e.target.value })
    setNewContract({ ...newContract, rentedDate: convertToDateForm(e.target.value) })
  }

  const handleDescriptionInput = (e) => {
    setCurrentContract({ ...currentContract, description: e.target.value })
    setNewContract({ ...newContract, description: e.target.value })
  }

  const handleSaveBtn = (newContract) => {
    let enabled = true;
    if (validation !== "OK")
      enabled = false;
    if (enabled === true) {
      show.isAdding ? dispatch(createContract(newContract)) : dispatch(updateContract(newContract))
      // console.log((newContract))
    }
    else {
      alert("Điền thông tin hợp lệ!")
    }
    handleClose();
  }

  return (
    <Modal show={show.status} onHide={handleClose} >
      <Modal.Header closeButton>
        <Modal.Title>{show.isAdding ? "Đăng ký mặt bằng" : "Gia hạn hợp đồng"}</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <div>{show.isAdding ? "Diện tích còn lại là " + remainArea : ""}</div>
        <Form>
          <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
            <Form.Label>Tên công ty</Form.Label>
            <Form.Control
              type="text"
              value={currentContract.companyDTO.companyName}
              onChange={(e) => handleNameInput(e)}
              readOnly
            />

          </Form.Group>
          <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
            <Form.Label>Rented area</Form.Label>
            <Form.Control
              type="text"
              value={currentContract.rentedArea}
              readOnly={!show.isAdding ? true : false}
              onChange={(e) => handleAreaInput(e, remainArea)}
            />
          </Form.Group>
          <div>{show.isAdding ? validation : ""}</div>
          <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
            <Form.Label>Rented date</Form.Label>
            <Form.Control
              type="text"
              value={currentContract.rentedDate}
              readOnly={!show.isAdding ? true : false}
              onChange={(e) => handleRentedDateInput(e)}
              placeholder="MM-dd-yyyy"
            />
          </Form.Group>

          <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
            <Form.Label>Ngày hết hạn</Form.Label>
            <Form.Control
              type="text"
              onChange={(e) => handleExpiredDateInput(e)}
              value={currentContract.expiredDate}
              placeholder="MM-dd-yyyy"
            />
          </Form.Group>

          <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
            <Form.Label>Description</Form.Label>
            <Form.Control
              type="text"
              value={currentContract.description}
              readOnly={!show.isAdding ? true : false}
              onChange={(e) => handleDescriptionInput(e)}
            />
          </Form.Group>
        </Form>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={handleClose}>
          Huỷ
        </Button>
        <Button variant="primary" onClick={() => handleSaveBtn(newContract)}>
          {show.isAdding ? "Đăng ký" : "Gia hạn"}
        </Button>
      </Modal.Footer>
    </Modal >
  );
}

export default ContractListModal;
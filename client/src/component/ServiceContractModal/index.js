import React, { useEffect, useState } from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Modal from 'react-bootstrap/Modal';
import { useDispatch, useSelector } from 'react-redux';
import { convertToDateForm, convertToDateForm3, isValidate } from '../../util/checkNumber';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import { allRegService } from '../../service/selector';
import { createRegService, updateRegService } from '../../service/registeredServiceSlice';

const ServiceContractModal = (props) => {

  //state
  const [show, setShow] = useState(props.isShow);
  const [newServiceContract, setNewServiceContract] = useState(props.newServiceContract);
  const [currentServiceContract, setCurrentServiceContract] = useState(props.newServiceContract);
  const [avaiArea, setAvaiArea] = useState(0);
  const [remainArea, setRemainArea] = useState(avaiArea)

  //selector
  const serviceContracts = useSelector(allRegService);

  //dispatch
  const dispatch = useDispatch();





  //useEffect
  useEffect(() => {
    setShow(props.isShow)
    setCurrentServiceContract(props.newServiceContract)
    setNewServiceContract(props.newServiceContract)
    if (!props.isShow.isAdding) {
      setNewServiceContract(props.newServiceContract)

    }

  }, [props.isShow])


  //handle event
  const handleClose = () => {
    setShow({ ...show, status: false });
    setCurrentServiceContract({

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
  }

  const handleStartDateInput = (e) => {
    setCurrentServiceContract({ ...currentServiceContract, startDate: e.target.value })
    setNewServiceContract({ ...newServiceContract, startDate: convertToDateForm3(e.target.value) })

  }

  const handleDescriptionInput = (e) => {
    setCurrentServiceContract({ ...currentServiceContract, description: e.target.value })
    setNewServiceContract({ ...newServiceContract, description: e.target.value })
  }

  const handleSaveBtn = (newServiceContract) => {

    setNewServiceContract({ ...newServiceContract })

    show.isAdding ? dispatch(createRegService(newServiceContract)) : dispatch(updateRegService(newServiceContract))
    handleClose();
  }

  return (
    <Modal show={show.status} onHide={handleClose} >
      <Modal.Header closeButton>
        <Modal.Title>{show.isAdding ? "Đăng ký dịch vụ" : "Sửa dịch vụ"}</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form>
          <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
            <Form.Label>Tên công ty</Form.Label>
            <Form.Control
              type="text"
              value={currentServiceContract.companyDTO.companyName}
              readOnly
            />

          </Form.Group>
          <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
            <Form.Label>Tên dịch vụ</Form.Label>
            <Form.Control
              type="text"
              value={currentServiceContract.serviceDTO.name}
              readOnly
            />
          </Form.Group>
          <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
            <Form.Label>Ngày bắt đầu</Form.Label>
            <Form.Control
              type="text"
              value={currentServiceContract.startDate}
              readOnly={!show.isAdding ? true : false}
              onChange={(e) => handleStartDateInput(e)}
              placeholder="MM-dd-yyyy"
            />
          </Form.Group>

          <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
            <Form.Label>Description</Form.Label>
            <Form.Control
              type="text"
              value={currentServiceContract.description}
              onChange={(e) => handleDescriptionInput(e)}
            />
          </Form.Group>
        </Form>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={handleClose}>
          Huỷ
        </Button>
        <Button variant="primary" onClick={() => handleSaveBtn(newServiceContract)}>
          {show.isAdding ? "Đăng ký" : "Sửa"}
        </Button>
      </Modal.Footer>
    </Modal >
  );
}

export default ServiceContractModal;
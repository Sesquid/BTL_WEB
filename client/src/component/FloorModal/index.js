import React, { useEffect, useState } from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Modal from 'react-bootstrap/Modal';
import { useDispatch, useSelector } from 'react-redux';
import { allFloors } from '../../service/selector';
import { isNumeric } from '../../util/checkNumber';
import { createFloor, updateFloor } from '../../service/floorListSlice';

const FloorModal = (props) => {

  //state
  const [show, setShow] = useState(props.isShow);
  const [validation, setValidation] = useState({ name: "Không được để trống", area: "Không được để trống", pricePerM2: "Không được để trống" })
  const [newFloor, setNewFloor] = useState(props.newFloor);
  const [currentFloor, setCurrentFloor] = useState(newFloor);
  

  //selector
  const floors = useSelector(allFloors);

  //dispatch
  const dispatch = useDispatch();

  //useEffect
  useEffect(() => {

    setShow(props.isShow)
    setCurrentFloor(props.newFloor)

    if (!props.isShow.isAdding) {
      setValidation({ ...validation, name: "OK", area: "OK", pricePerM2: "OK" });
      setNewFloor(props.newFloor)
    }
    
  }, [props.isShow])

  //handle event
  const handleClose = () => {
    setShow({ ...show, status: false });
    setValidation({ name: "Không được để trống", area: "Không được để trống", pricePerM2: "Không được để trống" })
    setCurrentFloor({})
  }

  const handleNameInput = (e) => {

    setCurrentFloor({ ...currentFloor, name: e.target.value })
    let exist = false;
    for (let i = 0; i < floors.length; i++) {
      if (floors[i].name.toLowerCase() === e.target.value.toLowerCase()) {
        exist = true;
      }
    }
    if (e.target.value.length > 0) {
      if (exist === true) setValidation({ ...validation, name: "Tên đã tồn tại!" })
      else {
        setValidation({ ...validation, name: "OK" });
        setNewFloor({ ...newFloor, name: e.target.value });
      }
    }
    else setValidation({ ...validation, name: "Không được để trống!" })
  }

  const handleAreaInput = (e) => {
    setCurrentFloor({ ...currentFloor, area: e.target.value })
    if (isNumeric(e.target.value)) {
      setNewFloor({ ...newFloor, area: parseFloat(e.target.value) })
      setValidation({ ...validation, area: "OK" })
    }
    else {
      setValidation({ ...validation, area: "Nhập số dương" })
    }
  }

  const handlePriceInput = (e) => {
    setCurrentFloor({ ...currentFloor, pricePerM2: e.target.value })
    if (isNumeric(e.target.value)) {
      setNewFloor({ ...newFloor, pricePerM2: parseFloat(e.target.value) })
      setValidation({ ...validation, pricePerM2: "OK" })
    }
    else {
      setValidation({ ...validation, pricePerM2: "Nhập số dương" })
    }
  }

  const handleSaveBtn = () => {
    let enabled = true;
    if (validation.name !== "OK" || validation.area !== "OK" || validation.pricePerM2 !== "OK")
      enabled = false;
    if (enabled === true) {
      show.isAdding ? dispatch(createFloor(newFloor)) : dispatch(updateFloor(newFloor))
    }
    else {
      alert("Điền thông tin hợp lệ!")
    }
    handleClose();
  }

  return (
    <Modal show={show.status} onHide={handleClose}>
      <Modal.Header closeButton>
        <Modal.Title>{show.isAdding ? "Thêm tầng mới" : "Sửa tầng"}</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form>
          <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
            <Form.Label>Tên tầng</Form.Label>
            <Form.Control
              type="email"
              placeholder="Tên tầng..."
              autoFocus
              onChange={(e) => handleNameInput(e)}
              value={currentFloor.name}
              readOnly={!show.isAdding}
            />
            <span>{validation.name}</span>
          </Form.Group>
          <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
            <Form.Label>Diện tích</Form.Label>
            <Form.Control
              type="email"
              placeholder="Diện tích..."
              onChange={(e) => handleAreaInput(e)}
              value={currentFloor.area}
              readOnly={!show.isAdding}
            />
            <span>{validation.area}</span>
          </Form.Group>
          <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
            <Form.Label>Giá mỗi m2</Form.Label>
            <Form.Control
              type="email"
              placeholder="Giá mỗi m2..."
              onChange={(e) => handlePriceInput(e)}
              value={currentFloor.pricePerM2}
            />
          </Form.Group>
          <span>{validation.pricePerM2}</span>
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

export default FloorModal;
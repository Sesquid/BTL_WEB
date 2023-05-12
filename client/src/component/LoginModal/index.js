import React, { useState } from 'react'
import { tokenSelector } from '../../service/selector';
import { useDispatch, useSelector } from 'react-redux';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Modal from 'react-bootstrap/Modal';
import { fetchUser } from '../../service/authSlice';
const LoginModal = () => {

  //state
  const [user, setUser] = useState({ username: "", password: "" })
  const [validation, setValidation] = useState({ username: "Không được để trống", password: "Không được để trống" })

  //selector
  const token = useSelector(tokenSelector);

  //dispatch
  const dispatch = useDispatch();


  //handle event
  const handleUsernameInput = (e) => {
    setUser({ ...user, username: e.target.value })
    if (e.target.value.length <= 0) {
      setValidation({ ...validation, username: "Không được để trống" })
    }
  }

  const handlePasswordInput = (e) => {
    setUser({ ...user, password: e.target.value })
    if (e.target.value.length <= 0) {
      setValidation({ ...validation, password: "Không được để trống" })
    }
  }

  const handleLogBtn = () => {
    dispatch(fetchUser(user));
  }


  
  return (
    <Modal show={token ? false : true}>
      <Modal.Header >
        <Modal.Title>Đăng nhập</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form>
          <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
            <Form.Label>Username</Form.Label>
            <Form.Control
              type="email"
              placeholder="Username..."
              autoFocus
              onChange={(e) => handleUsernameInput(e)}
              value={user.username}
            />
            <span>{validation.username}</span>
          </Form.Group>
          <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
            <Form.Label>Password</Form.Label>
            <Form.Control
              type="email"
              placeholder="Password..."
              onChange={(e) => handlePasswordInput(e)}
              value={user.password}
            />
            <span>{validation.password}</span>
          </Form.Group>

        </Form>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="primary" onClick={handleLogBtn}>
          Đăng nhập
        </Button>
      </Modal.Footer>
    </Modal>
  );
}

export default LoginModal;
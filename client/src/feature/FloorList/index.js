import React, { useEffect, useState } from 'react'
import { fetchFloors, deleteFloor } from '../../service/floorListSlice';
import { useDispatch, useSelector } from 'react-redux';
import { allFloors, tokenSelector } from '../../service/selector';
import FloorModal from '../../component/FloorModal';
import { useNavigate } from 'react-router-dom';

const FloorList = () => {

  const dispatch = useDispatch();
  const navigate = useNavigate();

  const floors = useSelector(allFloors);
  const token = useSelector(tokenSelector);

  const [addModal, setAddModal] = useState({ status: false, isAdding: true });
  const [newFloor, setNewFloor] = useState({ name: "", area: "", pricePerM2: "" })


  useEffect(() => {
    if (!token) {
      navigate("/intro")
    }
    else {
      dispatch(fetchFloors());
    }
  }, [token])

  const handleAddBtn = () => {
    setNewFloor({ name: "", area: "", pricePerM2: "" });
    setAddModal({ status: true, isAdding: true });
  }

  const handleDeleteBtn = (id) => {
    dispatch(deleteFloor(id));
  }

  const handleEditBtn = (floor) => {
    setNewFloor(floor);
    setAddModal({ status: true, isAdding: false });
  }

  const handleViewBtn = (id) => {
    navigate("/floor/" + id)
  }

  return (
    <div className='container'>
      <FloorModal
        isShow={addModal}
        newFloor={newFloor}
      />
      <div className='row'>
        <div className='table_header'>
          <div style={{ padding: "0px" }}>Danh sách các tầng</div>
          <div>
            <button type="button" className="btn btn-success"
              onClick={(handleAddBtn)}
            >Thêm tầng</button>
          </div>
        </div>
      </div>
      <table className="table table-bordered" style={{ width: "1000px", margin: "20px auto" }}>

        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">Area</th>
            <th scope='col'>Price/m2/month</th>
            <th scope='col'>View</th>
            <th scope='col'>Edit</th>
            <th scope='col'>Delete</th>
          </tr>
        </thead>
        <tbody>

          {floors.map((floor, index) => {
            return (
              <tr key={floor.floorID}>
                <th scope="row">{index + 1}</th>
                <td>{floor.name}</td>
                <td>{floor.area}</td>
                <td>{floor.pricePerM2}</td>
                <td>
                  <button type="button" className="btn btn-primary"
                    onClick={() => handleViewBtn(floor.floorID)}
                  >View</button>
                </td>
                <td>
                  <button type="button" className="btn btn-secondary"
                    onClick={() => handleEditBtn(floor)}
                  >Edit</button>
                </td>
                <td>
                  <button type="button" className="btn btn-danger"
                    onClick={() => handleDeleteBtn(floor.floorID)}
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

export default FloorList;
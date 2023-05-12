import React, { useEffect, useState } from 'react'
import { fetchFloors, deleteFloor } from '../../service/floorListSlice';
import { useDispatch, useSelector } from 'react-redux';
import { contractsSelector, floorsSelector, tokenSelector } from '../../service/selector';
import { useNavigate, useParams } from 'react-router-dom';
import { deleteContract, fetchAllContract } from '../../service/contractListSlice';
import ContractListModal from '../../component/ContractListModal';

const ContracList = () => {

  const dispatch = useDispatch();
  const navigate = useNavigate();
  const param = useParams();

  const floorID = param.floorID;

  const allContract = useSelector(contractsSelector);
  const token = useSelector(tokenSelector);

  const [reamainContract, setRemainContract] = useState([]);
  const [addModal, setAddModal] = useState({ status: false, isAdding: true });

  const [newContract, setNewContract] = useState({
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


  useEffect(() => {

    if (!token) {
      navigate("/intro")
    }
    else {
      dispatch(fetchAllContract(floorID));
    }
  }, [token])

  useEffect(() => {
    setRemainContract(allContract);
  }, [allContract])

  const handleAddBtn = () => {
    navigate("/registration-contract/" + floorID)
  }



  const handleDeleteBtn = (id) => {
    dispatch(deleteContract(id));
  }

  const handleEditBtn = (contract) => {
    setNewContract(contract);
    setAddModal({ status: true, isAdding: false });
  }


  return (
    <div className='container'>
      <ContractListModal
        isShow={addModal}
        newContract={newContract}
      />
      <div className='row'>
        <div className='table_header'>
          <div style={{ padding: "0px" }}>Danh sách các mặt bằng</div>
          <div>
            <button type="button" className="btn btn-success"
              onClick={(handleAddBtn)}
            >Đăng ký mặt bằng</button>
          </div>
        </div>
      </div>
      <table className="table table-bordered" style={{ width: "1000px", margin: "20px auto" }}>

        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope='col'>Tên công ty</th>
            <th scope="col">Ngày thuê</th>
            <th scope="col">Ngày hết hạn</th>
            <th scope='col'>Diện tích thuê</th>
            <th scope='col'>Mô tả</th>
            <th scope='col'>Gia hạn</th>
            <th scope='col'>Xoá</th>
          </tr>
        </thead>
        <tbody>
          {reamainContract.length ? reamainContract.map((contract, index) => {
            return (
              <tr key={contract.contractID}>
                <th scope="row">{index + 1}</th>
                <td>{contract.companyDTO.companyName}</td>
                <td>{contract.rentedDate}</td>
                <td>{contract.expiredDate}</td>
                <td>{contract.rentedArea}</td>
                <td>{contract.description}</td>
                <td>
                  <button type="button" className="btn btn-secondary"
                    onClick={() => handleEditBtn(contract)}
                  >Edit</button>
                </td>
                <td>
                  <button type="button" className="btn btn-danger"
                    onClick={() => handleDeleteBtn(contract.contractID)}
                  >Delete</button>
                </td>
              </tr>
            )
          }) : <tr><th>Khong co hop dong nao</th></tr>}

        </tbody>
      </table>
    </div>
  )
}

export default ContracList;
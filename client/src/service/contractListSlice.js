import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";

export const fetchAllContract = createAsyncThunk('contracts/fetchAllContract', async (id) => {
  const res = await axios({
    method: "get",
    url: "http://localhost:8080/api/contract/get-contracts/floorid/" + id,
  })
  console.log(res)
  return res.data
})

export const createContract = createAsyncThunk('contracts/createContract', async (newContract) => {
  
  const res = await axios({
    method: "post",
    url: "http://localhost:8080/api/contract/create",
    params: {
      companyID: newContract.companyDTO.companyID,
      floorID: newContract.floorDTO.floorID
    },
    data: newContract
  })
  console.log(JSON.stringify(newContract))
  return res.data
})

export const deleteContract = createAsyncThunk('contracts/deleteContract', async (id) => {
  const res = await axios({
    method: "post",
    url: `http://localhost:8080/api/contract/delete/${id}`,
  })
  console.log(res.data)
  return res.data;
})

export const updateContract = createAsyncThunk('contracts/updateContract', async (newContract) => {
  const id = newContract.contractID
  const res = await axios({
    method: "post",
    url: `http://localhost:8080/api/contract/update/${id}`,
    data: newContract
  })
  return res.data
})

export const contractListSlice = createSlice({
  name: 'contracts',
  initialState: {
    contracts: []
  },
  reducers: {

  },
  extraReducers: builder => {
    builder
      .addCase(fetchAllContract.fulfilled, (state, action) => {
        state.contracts = action.payload.data;
      })
      .addCase(createContract.fulfilled, (state, action) => {
        state.contracts.push(action.payload.data)
      })
      .addCase(deleteContract.fulfilled, (state, action) => {
        state.contracts = state.contracts.filter((contract) =>
          contract.contractID !== action.payload.data.contractID)
      })
      .addCase(updateContract.fulfilled, (state, action) => {
        for (let i = 0; i < state.contracts.length; i++) {
          if (state.contracts[i].contractID === action.payload.data.contractID) {
            state.contracts[i] = action.payload.data
          }
        }
      })
  }
})
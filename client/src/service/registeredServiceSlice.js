import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import { convertToDateForm3 } from "../util/checkNumber";

export const fetchRegService = createAsyncThunk('services/fetchServices', async (id) => {
  const res = await axios({
    method: "get",
    url: "http://localhost:8080/api/service-contract/get-all-service/company/" + id,
  })
  return res.data
})

export const createRegService = createAsyncThunk('services/createService', async (newServiceContract) => {
  const res = await axios({
    method: "post",
    url: "http://localhost:8080/api/service-contract/create",
    params: {
      serviceID: newServiceContract.serviceDTO.serviceID,
      companyID: newServiceContract.companyDTO.companyID
    },
    data: newServiceContract
  })
  return res.data
})

export const deleteRegService = createAsyncThunk('services/deleteService', async (id) => {
  const res = await axios({
    method: "post",
    url: "http://localhost:8080/api/service-contract/delete/" + id,
  })
  return res.data
})

export const updateRegService = createAsyncThunk('services/updateService', async (newServiceContract) => {

  const res = await axios({
    method: "post",
    url: "http://localhost:8080/api/service-contract/update/" + newServiceContract.serviceContractID,
    data: newServiceContract
  })
  console.log(res.data)
  return res.data
})


export const registServiceSlice = createSlice({
  name: "services",
  initialState: {
    services: []
  },
  reducers: {

  },
  extraReducers: builder => {
    builder
      .addCase(fetchRegService.fulfilled, (state, action) => {
        state.services = action.payload.data
        for (let i = 0; i < state.services.length; i++) {
          state.services[i].startDate = convertToDateForm3(state.services[i].startDate)
        }
      })
      .addCase(createRegService.fulfilled, (state, action) => {
        state.services.push(action.payload.data)
      })
      .addCase(deleteRegService.fulfilled, (state, action) => {
        state.services = state.services.filter((service) =>
          service.serviceContractID !== action.payload.data.serviceContractID
        )
      })
      .addCase(updateRegService.fulfilled, (state, action) => {
        for (let i = 0; i < state.services.length; i++) {
          if (state.services[i].serviceContractID === action.payload.data.serviceContractID) {
            state.services[i] = action.payload.data
          }
        }
      })
  }
})
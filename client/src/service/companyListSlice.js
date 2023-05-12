import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";


export const fetchCompanies = createAsyncThunk('companies/fetchCompanies', async () => {
  const token = localStorage.getItem("token")
  if (token) {
    const response = await axios({
      method: "get",
      url: "http://localhost:8080/api/company/get-all",
      headers: {
        Authorization: "Bearer " + token
      }
    })
    return response.data;
  }

})

export const createCompany = createAsyncThunk('companies/createCompany', async (newCompany) => {
  // console.log(newCompany)
  const token = localStorage.getItem("token")
  const res = await axios({
    method: "post",
    url: "http://localhost:8080/api/company/create",
    headers: {
      Authorization: "Bearer " + token
    },
    data: newCompany
  })
  return res.data;
})

export const deleteCompany = createAsyncThunk('companies/deleteCompany', async (item) => {
  const token = localStorage.getItem("token")
  
  const response = await axios({
    method: "post",
    url: "http://localhost:8080/api/company/delete",
    headers: {
      Authorization: "Bearer " + token
    },
    data: item
  })
  return {
    resData: response.data,
    id: item.companyID
  }
})

export const updateCompany = createAsyncThunk('companies/updateCompany', async (item) => {
  const token = localStorage.getItem("token")
  const response = await axios({
    method: "post",
    url: "http://localhost:8080/api/company/update",
    headers: {
      Authorization: "Bearer " + token
    },
    data: item
  })
  return {
    resData: response.data,
    id: item.companyID
  }
})


export const companyListSlice = createSlice({
  name: 'companies',
  initialState: {
    companies: []
  },
  reducers: {

  },
  extraReducers: builder => {
    builder
      .addCase(fetchCompanies.fulfilled, (state, action) => {
        state.companies = action.payload.data
      })
      .addCase(createCompany.fulfilled, (state, action) => {
        state.companies.push(action.payload.data)
      })
      .addCase(deleteCompany.fulfilled, (state, action) => {
        console.log(action.payload.resData)
        const id = action.payload.id
        state.companies = state.companies.filter((company) => company.companyID !== id)
      })
      .addCase(updateCompany.fulfilled, (state, action) => {
        const id = action.payload.id
        for (let i = 0; i < state.companies.length; i++) {
          if (state.companies[i].companyID === id) {
            state.companies[i] = action.payload.resData.data;
          }
        }
      })
  }
})
import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import { convertToDateForm2 } from "../util/checkNumber";


export const fetchEmployeesCompany = createAsyncThunk('employeesCompany/fetchEmployeesCompany',

  async (id) => {
    const token = localStorage.getItem("token")
    const res = await axios({
      method: "get",
      url: "http://localhost:8080/api/employees-company/get-by-companyid",
      headers: {
        Authorization: "Bearer " + token,
      },
      params: {
        companyID: id
      }
    })
    return res.data
  }
)

export const createEmployeeCompany = createAsyncThunk('employeesCompany/createEmployeeCompany',
  async (newEmployee) => {
    console.log(newEmployee)
    const token = localStorage.getItem("token")
    const res = await axios({
      method: "post",
      url: "http://localhost:8080/api/employees-company/create",
      headers: {
        Authorization: "Bearer " + token,
      },
      data: newEmployee
    })
    return res.data
  }
)

export const deleteEmployeeCompany = createAsyncThunk('employeesCompany/deleteEmployeeCompany',
  async (employee) => {
    const token = localStorage.getItem("token")
    const res = await axios({
      method: "post",
      url: "http://localhost:8080/api/employees-company/delete",
      headers: {
        Authorization: "Bearer " + token,
      },
      data: employee
    })
    return [res.data, employee.employeeID]
  })

export const updateEmployeeCompany = createAsyncThunk('employeesCompany/update', async (newEmployee) => {
  const token = localStorage.getItem("token")
  const res = await axios({
    method: "post",
    url: "http://localhost:8080/api/employees-company/update",
    headers: {
      Authorization: "Bearer " + token,
    },
    data: newEmployee
  })
  return res.data
})

export const employeesCompanySlice = createSlice({
  name: "employeesCompany",
  initialState: {
    employeesCompany: []
  },
  reducers: {
    
  },
  extraReducers: builder => {
    builder
      .addCase(fetchEmployeesCompany.fulfilled, (state, action) => {
        state.employeesCompany = action.payload.data
        for (let i = 0; i < state.employeesCompany.length; i++) {
          state.employeesCompany[i].date = convertToDateForm2(state.employeesCompany[i].date)
        }
      })
      .addCase(createEmployeeCompany.fulfilled, (state, action) => {
        
        console.log(JSON.stringify(action.payload.data))
        state.employeesCompany.push(action.payload.data)
      })
      .addCase(deleteEmployeeCompany.fulfilled, (state, action) => {
        console.log(action.payload)
        state.employeesCompany = state.employeesCompany.filter((employee) =>
          employee.employeeID !== action.payload[1])
      })
      .addCase(updateEmployeeCompany.fulfilled, (state, action) => {
        const id = action.payload.data.employeeID;
        for (let i = 0; i < state.employeesCompany.length; i++) {
          if (state.employeesCompany[i].employeeID === id) {
            state.employeesCompany[i] = action.payload.data
            state.employeesCompany[i].date = convertToDateForm2(state.employeesCompany[i].date)
          }
        }
      })
  }
})
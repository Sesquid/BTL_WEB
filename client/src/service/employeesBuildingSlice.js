import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";

export const fetchEmployeesBuilding = createAsyncThunk(
  "employeesBuilding/fetchEmployeesBuilding",
  async () => {
    const token = localStorage.getItem("token");
    const res = await axios({
      method: "get",
      url: "http://localhost:8080/api/building-employee/get-all",
      headers: {
        Authorization: "Bearer " + token,
      },
    });
    return res.data;
  }
);

export const createEmployeeBuilding = createAsyncThunk(
  "employeesBuilding/createEmployeeCompany",
  async newEmployee => {
    const token = localStorage.getItem("token");
    const data = {
      ...newEmployee,
      dob: newEmployee.date,
      salary: null,
    };
    const res = await axios({
      method: "post",
      url: `http://localhost:8080/api/building-employee/create?salaryID=${newEmployee.salaryID}`,
      headers: {
        Authorization: "Bearer " + token,
      },
      data: data,
    });
    return res.data;
  }
);

export const deleteEmployeeBuilding = createAsyncThunk(
  "employeesBuilding/deleteEmployeeCompany",
  async employee => {
    const token = localStorage.getItem("token");
    const res = await axios({
      method: "post",
      url: `http://localhost:8080/api/building-employee/delete/${employee.employeeID}`,
      headers: {
        Authorization: "Bearer " + token,
      },
      data: employee,
    });
    return res.data;
  }
);

export const updateEmployeeBuilding = createAsyncThunk(
  "employeesBuilding/update",
  async newEmployee => {
    const token = localStorage.getItem("token");
    const data = {
      ...newEmployee,
      dob: newEmployee.date,
      DOB: null,
      salary: null,
    };
    const res = await axios({
      method: "post",
      url: `http://localhost:8080/api/building-employee/update/${newEmployee.employeeID}?salaryID=${newEmployee.salaryID}`,
      headers: {
        Authorization: "Bearer " + token,
      },
      data: data,
    });
    return res.data;
  }
);

export const employeesBuildingSlice = createSlice({
  name: "employeesBuilding",
  initialState: {
    employeesBuilding: [],
  },
  reducers: {},
  extraReducers: builder => {
    builder
      .addCase(fetchEmployeesBuilding.fulfilled, (state, action) => {
        state.employeesBuilding = action.payload.data;
      })
      .addCase(createEmployeeBuilding.fulfilled, (state, action) => {
        state.employeesBuilding.push(action.payload.data);
      })
      .addCase(deleteEmployeeBuilding.fulfilled, (state, action) => {
        state.employeesBuilding = state.employeesBuilding.filter(
          employee => employee.employeeID !== action.payload.data.employeeID
        );
      })
      .addCase(updateEmployeeBuilding.fulfilled, (state, action) => {
        const id = action.payload.data.employeeID;
        for (let i = 0; i < state.employeesBuilding.length; i++) {
          if (state.employeesBuilding[i].employeeID === id) {
            state.employeesBuilding[i] = action.payload.data;
          }
        }
      });
  },
});

import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";

export const fetchService = createAsyncThunk('services/fetchService', async () => {
  const res = await axios({
    method: "get",
    url: "http://localhost:8080/api/service/get-all"
  })
  return res.data
})



export const serviceSlice = createSlice({
  name: "services",
  initialState: {
    services: []
  },
  reducers: {

  },
  extraReducers: builder => {
    builder
      .addCase(fetchService.fulfilled, (state, action) => {
        // console.log(action.payload.data)
        state.services = action.payload.data
      })

  }
})
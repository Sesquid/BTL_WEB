import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";

export const fetchFloors = createAsyncThunk('floors/fetchFloors', async () => {
  const response = await axios
    .get("http://localhost:8080/api/floor/get-all")
    .catch(err => console.log("ERR: ", err))
  // console.log(response.data)
  return response.data;
})


export const createFloor = createAsyncThunk('floors/creatFloor', async (newFloor) => {
  const res = await axios({
    method: "post",
    url: "http://localhost:8080/api/floor/create",
    headers: {},
    data: newFloor
  })
  // console.log(res);
  return res.data;
})

export const deleteFloor = createAsyncThunk('floors/deleteFloor', async (id) => {
  const res = await axios({
    method: "post",
    url: `http://localhost:8080/api/floor/delete/${id}`
  })
  // console.log(res);
  return res.data;
})

export const updateFloor = createAsyncThunk('floors/updateFloor', async (currentFloor) => {
  const id = currentFloor.floorID;
  const res = await axios({
    method: "post",
    url: `http://localhost:8080/api/floor/update/${id}`,
    data: currentFloor
  })
  return res.data;
})

export const floorListSlice = createSlice({
  name: 'floors',
  initialState: {
    floors: []
  },
  reducers: {

  },
  extraReducers: builder => {
    builder
      .addCase(fetchFloors.fulfilled, (state, action) => {
        state.floors = action.payload.data
      })

      .addCase(createFloor.fulfilled, (state, action) => {
        state.floors.push(action.payload.data)
      })

      .addCase(deleteFloor.fulfilled, (state, action) => {
        state.floors = state.floors.filter((floor) => floor.floorID !== action.payload.data.floorID)
      })
      
      .addCase(updateFloor.fulfilled, (state, action) => {
        for (let i = 0; i < state.floors.length; i++) {
          if (state.floors[i].floorID === action.payload.data.floorID) {
            state.floors[i] = action.payload.data;
          }
        }
      })
  }

})
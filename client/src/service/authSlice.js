import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";

export const fetchUser = createAsyncThunk('auth/fetchUser', async (user) => {
  const res = await axios({
    method: "post",
    url: 'http://localhost:8080/api/login',
    data: user
  })
  return res.data;
})

export const authSlice = createSlice({
  name: "auth",
  initialState: {
    token: localStorage.getItem("token") ? localStorage.getItem("token") : false
  },
  reducers: {
    login(state, action) {
      
    },
    logout(state, action) {
      state.token = null
      localStorage.removeItem("token")
    }
  },
  extraReducers: builder => {
    builder
      .addCase(fetchUser.fulfilled, (state, action) => {
        // if()
        if (action.payload.error !== 0) {
          alert("Sai tài khoản hoặc mật khẩu")
        }
        else {
          localStorage.setItem("token", action.payload.data.token)
          state.token = action.payload.data.token
        }

      })
  }
})
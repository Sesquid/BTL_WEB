import { configureStore } from "@reduxjs/toolkit";
import { floorListSlice } from "./floorListSlice";
import { authSlice } from "./authSlice";
import { companyListSlice } from "./companyListSlice";
import { contractListSlice } from "./contractListSlice";
import { employeesCompanySlice } from "./employeesCompanySlice";
import { registServiceSlice } from "./registeredServiceSlice";
import { serviceSlice } from "./serviceSlice";

import { employeesBuildingSlice } from "./employeesBuildingSlice";
const store = configureStore({
  reducer: {
    companies: companyListSlice.reducer,
    floors: floorListSlice.reducer,
    auth: authSlice.reducer,
    contracts: contractListSlice.reducer,
    employeesCompany: employeesCompanySlice.reducer,  
    employeesBuilding: employeesBuildingSlice.reducer,
    registedServices: registServiceSlice.reducer,
    services: serviceSlice.reducer
  }
})
export default store;
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import Header from './component/Header';
import Intro from './feature/Intro';
import { Provider } from 'react-redux';
import store from './service/store';
import CompanyList from './feature/CompanyList';
import Company from './feature/Company';
import FloorList from './feature/FloorList';
import ContracList from './feature/ContractList';
import ContractRegistration from './feature/ContractRegistration';
import EmployeeBuilding from './feature/EmployeesBuilding'
import ServiceRegistration from './feature/ServiceRegistration';
import RegistedService from './feature/RegistedService';
import ServiceList from './feature/ServiceList';

function App() {
  return (
    <Provider store={store}>
      <BrowserRouter>
        <div className="App">
          <Header></Header>
          <Routes>
            <Route path='' element={<Intro></Intro>}></Route>
            <Route path='/intro' element={<Intro></Intro>}></Route>
            <Route path='/companies' element={<CompanyList></CompanyList>}></Route>
            <Route path='/floors' element={<FloorList></FloorList>}></Route>
            <Route path='/company/:companyID' element={<Company></Company>}></Route>
            <Route path='/floor/:floorID' element={<ContracList></ContracList>}></Route>
            <Route path='/registration-contract/:floorID' element={<ContractRegistration></ContractRegistration>}></Route>
            <Route path='/employees-building' element={<EmployeeBuilding></EmployeeBuilding>}></Route>
            <Route path='/registration-service/companies' element={<ServiceRegistration></ServiceRegistration>}></Route>
            <Route path='/registration-service/registed/:companyID'
              element={<RegistedService></RegistedService>}
            ></Route>
            <Route path='/services/companyID/:companyID' element={<ServiceList></ServiceList>}></Route>
          </Routes>
        </div>
      </BrowserRouter>
    </Provider>
  );
}

export default App;

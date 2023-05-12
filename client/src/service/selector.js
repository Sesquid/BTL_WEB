import { createSelector } from "@reduxjs/toolkit";

export const companiesSelector = state => state.companies.companies;
export const floorsSelector = state => state.floors.floors;
export const tokSelector = state => state.auth.token;
export const contractsSelector = state => state.contracts.contracts;
export const employeesCompanySelector = state =>
  state.employeesCompany.employeesCompany;
export const registedServiceSelector = state => state.registedServices.services
export const servicesSelector = state => state.services.services

export const allService = createSelector(servicesSelector, (services,) => {
  // console.log(services)
  return services;
})

export const allRegService = createSelector(registedServiceSelector, (services) => {
  return services;
})

export const allEmployeesCompany = createSelector(
  employeesCompanySelector,
  employeesCompany => {
    return employeesCompany;
  }
);

export const allContract = createSelector(contractsSelector, contracts => {
  return contracts;
});

export const tokenSelector = createSelector(tokSelector, token => {
  return token;
});

export const allFloors = createSelector(floorsSelector, floors => {
  return floors;
});

export const allCompanies = createSelector(companiesSelector, companies => {
  return companies;
});

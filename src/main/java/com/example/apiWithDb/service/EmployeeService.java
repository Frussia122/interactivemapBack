package com.example.apiWithDb.service;

import java.util.List;

public interface EmployeeService {

    public String createEmployee(com.example.apiWithDb.entities.Employee employee);
    public String updateEmployee(com.example.apiWithDb.entities.Employee employee);
    public String deleteEmployee(String employeeId);
    public com.example.apiWithDb.entities.Employee getEmployee(String employeeId);
    public List<com.example.apiWithDb.entities.Employee> getAllEmployees();
}

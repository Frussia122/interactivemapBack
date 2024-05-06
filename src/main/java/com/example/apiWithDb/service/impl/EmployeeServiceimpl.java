package com.example.apiWithDb.service.impl;

import com.example.apiWithDb.exception.EmployeeNotFoundException;
import com.example.apiWithDb.repository.employeeRepository;
import com.example.apiWithDb.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceimpl implements EmployeeService {

    employeeRepository employeeRepository;

    public EmployeeServiceimpl(employeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public String createEmployee(com.example.apiWithDb.entities.Employee employee) {
        employeeRepository.save(employee);
        return "Success";
    }

    @Override
    public String updateEmployee(com.example.apiWithDb.entities.Employee employee) {
        employeeRepository.save(employee);
        return "Success";
    }

    @Override
    public String deleteEmployee(String employeeId) {
        if(employeeRepository.findById(employeeId).isEmpty())
            throw new EmployeeNotFoundException("Запрошенный работник не существует!");
        employeeRepository.deleteById(employeeId);
        return "Success";
    }

    @Override
    public com.example.apiWithDb.entities.Employee getEmployee(String employeeId) {
        if(employeeRepository.findById(employeeId).isEmpty())
            throw new EmployeeNotFoundException("Запрошенный работник не существует!");
        return employeeRepository.findById(employeeId).get();
    }

    @Override
    public List<com.example.apiWithDb.entities.Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}

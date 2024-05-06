package com.example.apiWithDb.repository;

import com.example.apiWithDb.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface employeeRepository extends JpaRepository<Employee, String> {

}

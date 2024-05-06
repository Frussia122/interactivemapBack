package com.example.apiWithDb.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="employeeInfo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    private String employeeId;
    private String employeeName;
    private String employeeSecName;
    private String employeePhoto;
}
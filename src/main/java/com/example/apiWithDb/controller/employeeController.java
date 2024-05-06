package com.example.apiWithDb.controller;


import com.example.apiWithDb.response.ResponseHandler;
import com.example.apiWithDb.service.EmployeeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
@SecurityRequirement(name = "bearerAuth")
public class employeeController {

    public EmployeeService employeeService;

    public employeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    public ResponseEntity<Object> getEmployeeDetails()
    {
        return ResponseHandler.responseBuilder("Запрошенные данные предоставлены", HttpStatus.OK, employeeService.getAllEmployees());
    }

    @GetMapping("{employeeId}")
    public ResponseEntity<Object> getEmployeeDetails(@PathVariable("employeeId") String employeeId)
    {
        return ResponseHandler.responseBuilder("Запрошенные данные предоставлены", HttpStatus.OK, employeeService.getEmployee(employeeId));
    }

    @PostMapping
    public String CreateEmployeeDetails(@RequestBody com.example.apiWithDb.entities.Employee employee)
    {
        employeeService.createEmployee(employee);
        return "Employee Created!";
    }

    @PutMapping
    public String UpdateEmployeeDetails(@RequestBody com.example.apiWithDb.entities.Employee employee)
    {
        employeeService.updateEmployee(employee);
        return "Employee Updated!";
    }

    @DeleteMapping("{employeeId}")
    public String deleteEmployee(@PathVariable("employeeId") String employeeId)
    {
        employeeService.deleteEmployee(employeeId);
        return "Employee Deleted!";
    }


}

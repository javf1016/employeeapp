package com.example.employeeapp.service;

import com.example.employeeapp.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    Employee getEmployeeById(int id);
}

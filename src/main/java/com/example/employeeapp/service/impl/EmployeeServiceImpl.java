package com.example.employeeapp.service.impl;

import com.example.employeeapp.model.Employee;
import com.example.employeeapp.model.ResponseWrapper;
import com.example.employeeapp.model.ResponseWrapperEmployee;
import com.example.employeeapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final String EXTERNAL_API_URL = "http://dummy.restapiexample.com/api/v1/";

    private final RestTemplate restTemplate;

    @Autowired
    public EmployeeServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Employee> getAllEmployees() {
        String url = EXTERNAL_API_URL + "employees";
        ResponseEntity<ResponseWrapper> response = restTemplate.getForEntity(url, ResponseWrapper.class);
        List<Employee> employees = response.getBody().getData();

        for (Employee employee : employees) {
            double annualSalary = calculateAnnualSalary(employee.getEmployeeSalary());
            employee.setEmployee_anual_salary(annualSalary);
        }

        return employees;
    }

    @Override
    public Employee getEmployeeById(int id) {
        String url = EXTERNAL_API_URL + "employee/" + id;
        ResponseEntity<ResponseWrapperEmployee> response = restTemplate.getForEntity(url, ResponseWrapperEmployee.class);
        Employee employee = response.getBody().getData();
        double annualSalary = calculateAnnualSalary(employee.getEmployeeSalary());
        employee.setEmployee_anual_salary(annualSalary);

        return employee;
    }

    private double calculateAnnualSalary(double monthlySalary) {
        return monthlySalary * 12;
    }
}

package com.example.employeeapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private int id;
    @JsonProperty("employee_name")
    private String employeeName;
    @JsonProperty("employee_salary")
    private double employeeSalary;
    @JsonProperty("employee_age")
    private int employeeAge;
    private String profileImage;
    private double employee_anual_salary; // Campo para el salario anual
}

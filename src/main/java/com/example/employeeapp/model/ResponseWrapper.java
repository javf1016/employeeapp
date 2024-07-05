package com.example.employeeapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseWrapper {
    private String status;
    @JsonProperty("data")
    private List<Employee> data;
    private String message;
}
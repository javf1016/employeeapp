package com.example.employeeapp;

import com.example.employeeapp.model.Employee;
import com.example.employeeapp.model.ResponseWrapper;
import com.example.employeeapp.model.ResponseWrapperEmployee;
import com.example.employeeapp.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EmployeeServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllEmployees_Success() {
        // Mock de la respuesta exitosa del servidor
        Employee employee1 = new Employee(1, "John Doe", 5000.0, 30, "profile.jpg", 0);
        Employee employee2 = new Employee(2, "Jane Smith", 6000, 30, "",0);
        List<Employee> mockEmployees = Arrays.asList(employee1, employee2);
        ResponseWrapper mockResponseWrapper = new ResponseWrapper();
        mockResponseWrapper.setData(mockEmployees);

        ResponseEntity<ResponseWrapper> mockResponseEntity = new ResponseEntity<>(mockResponseWrapper, HttpStatus.OK);

        // Configuración del mock de RestTemplate
        when(restTemplate.getForEntity(anyString(), eq(ResponseWrapper.class))).thenReturn(mockResponseEntity);

        List<Employee> employees = employeeService.getAllEmployees();

        // Verificar resultados
        assertNotNull(employees);
        assertEquals(2, employees.size());
        assertEquals(employee1.getId(), employees.get(0).getId());
        assertEquals(employee2.getEmployeeName(), employees.get(1).getEmployeeName());
    }

    @Test
    public void testGetEmployeeById_Success() {
        // Mock de la respuesta exitosa del servidor para un empleado específico
        int employeeId = 1;
        Employee mockEmployee = new Employee(1, "John Doe", 5000.0, 30, "profile.jpg", 0);
        ResponseWrapperEmployee mockResponseWrapper = new ResponseWrapperEmployee();
        mockResponseWrapper.setData(mockEmployee);

        ResponseEntity<ResponseWrapperEmployee> mockResponseEntity = new ResponseEntity<>(mockResponseWrapper, HttpStatus.OK);

        // Configuración del mock de RestTemplate
        when(restTemplate.getForEntity(anyString(), eq(ResponseWrapperEmployee.class))).thenReturn(mockResponseEntity);

        Employee employee = employeeService.getEmployeeById(employeeId);

        // Verificar resultados
        assertNotNull(employee);
        assertEquals(employeeId, employee.getId());
        assertEquals("John Doe", employee.getEmployeeName());
    }

    @Test
    public void testGetAllEmployees_ServerError() {
        // Mock de respuesta de error interno del servidor
        ResponseEntity<ResponseWrapper> mockResponseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        // Configuración del mock de RestTemplate
        when(restTemplate.getForEntity(anyString(), eq(ResponseWrapper.class))).thenReturn(mockResponseEntity);

        assertThrows(RuntimeException.class, () -> employeeService.getAllEmployees());
    }

}

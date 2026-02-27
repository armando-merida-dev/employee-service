package com.invex.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.invex.dto.EmployeeRequest;
import com.invex.dto.EmployeeResponse;
import com.invex.service.EmployeeService;
import com.invex.utils.Constants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
@DisplayName("EmployeeController Unit Tests")
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Should return all employees")
    void shouldReturnAllEmployees() throws Exception {

        // Arrange
        EmployeeResponse response = new EmployeeResponse();
        response.setId(1L);
        response.setFullName("Antonio");

        when(service.findAll()).thenReturn(List.of(response));

        // Act
        var result = mockMvc.perform(get(Constants.ROOT_MAPPING));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].fullName").value("Antonio"));

        verify(service).findAll();
    }

    @Test
    @DisplayName("Should return employee by id")
    void shouldReturnEmployeeById() throws Exception {

        // Arrange
        Long id = 1L;
        EmployeeResponse response = new EmployeeResponse();
        response.setId(id);
        response.setFullName("Antonio");

        when(service.findById(id)).thenReturn(response);

        // Act
        var result = mockMvc.perform(get(Constants.ROOT_MAPPING + "/" + id));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.fullName").value("Antonio"));

        verify(service).findById(id);
    }

    @Test
    @DisplayName("Should create employees")
    void shouldCreateEmployees() throws Exception {

        // Arrange
        EmployeeRequest request = new EmployeeRequest();
        request.setFirstName("Antonio");
        request.setLastName("Lopez");
        request.setSecondLastName("Martinez");
        request.setAge(30);
        request.setGender("M");
        request.setBirthDate(LocalDate.of(1995, 5, 10));
        request.setPosition("Developer");
        request.setActive(true);

        EmployeeResponse response = new EmployeeResponse();
        response.setId(1L);
        response.setFullName("Antonio Lopez Martinez");

        when(service.saveAll(anyList())).thenReturn(List.of(response));

        // Act
        var result = mockMvc.perform(post(Constants.ROOT_MAPPING)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(List.of(request))));

        // Assert
        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].fullName")
                        .value("Antonio Lopez Martinez"));

        verify(service).saveAll(anyList());
    }

    @Test
    @DisplayName("Should update employee")
    void shouldUpdateEmployee() throws Exception {

        // Arrange
        Long id = 1L;
        EmployeeRequest request = new EmployeeRequest();
        request.setFirstName("Updated");

        EmployeeResponse response = new EmployeeResponse();
        response.setId(id);
        response.setFullName("Updated");

        when(service.update(eq(id), any(EmployeeRequest.class)))
                .thenReturn(response);

        // Act
        var result = mockMvc.perform(put(Constants.ROOT_MAPPING + "/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.fullName").value("Updated"));

        verify(service).update(eq(id), any(EmployeeRequest.class));
    }

    @Test
    @DisplayName("Should delete employee")
    void shouldDeleteEmployee() throws Exception {

        // Arrange
        Long id = 1L;
        doNothing().when(service).delete(id);

        // Act
        var result = mockMvc.perform(delete(Constants.ROOT_MAPPING + "/" + id));

        // Assert
        result.andExpect(status().isNoContent());

        verify(service).delete(id);
    }

    @Test
    @DisplayName("Should search employees by name")
    void shouldSearchEmployeesByName() throws Exception {

        // Arrange
        String name = "Ant";

        EmployeeResponse response = new EmployeeResponse();
        response.setId(1L);
        response.setFullName("Antonio");

        when(service.searchByName(name)).thenReturn(List.of(response));

        // Act
        var result = mockMvc.perform(get(Constants.ROOT_MAPPING + "/search")
                .param("name", name));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].fullName").value("Antonio"));

        verify(service).searchByName(name);
    }
}
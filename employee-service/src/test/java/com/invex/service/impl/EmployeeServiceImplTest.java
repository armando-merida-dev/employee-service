package com.invex.service.impl;

import com.invex.dto.EmployeeRequest;
import com.invex.dto.EmployeeResponse;
import com.invex.entity.Employee;
import com.invex.exception.ResourceNotFoundException;
import com.invex.mapper.EmployeeMapper;
import com.invex.repository.EmployeeRepository;
import com.invex.utils.Constants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("EmployeeServiceImpl Unit Tests")
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository repository;

    @Mock
    private EmployeeMapper mapper;

    @InjectMocks
    private EmployeeServiceImpl service;

    @Test
    @DisplayName("Should return all employees when repository contains data")
    void shouldReturnAllEmployees() {

        // Arrange
        Employee employee = Employee.builder().id(1L).firstName("Juan").build();
        EmployeeResponse response = new EmployeeResponse();

        when(repository.findAll()).thenReturn(List.of(employee));
        when(mapper.toResponse(employee)).thenReturn(response);

        // Act
        List<EmployeeResponse> result = service.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(repository, times(1)).findAll();
        verify(mapper, times(1)).toResponse(employee);
    }

    @Test
    @DisplayName("Should return employee by id when employee exists")
    void shouldReturnEmployeeByIdWhenExists() {

        // Arrange
        Long id = 1L;
        Employee employee = Employee.builder().id(id).build();
        EmployeeResponse response = new EmployeeResponse();

        when(repository.findById(id)).thenReturn(Optional.of(employee));
        when(mapper.toResponse(employee)).thenReturn(response);

        // Act
        EmployeeResponse result = service.findById(id);

        // Assert
        assertNotNull(result);
        verify(repository).findById(id);
        verify(mapper).toResponse(employee);
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when employee does not exist")
    void shouldThrowExceptionWhenEmployeeNotFound() {

        // Arrange
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        // Act + Assert
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> service.findById(id)
        );

        assertEquals(Constants.OUTPUT_NOT_FOUND, exception.getMessage());
        verify(repository).findById(id);
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Should save all employees successfully")
    void shouldSaveAllEmployees() {

        // Arrange
        EmployeeRequest request = new EmployeeRequest();
        Employee employee = Employee.builder().build();
        EmployeeResponse response = new EmployeeResponse();

        when(mapper.toEntity(request)).thenReturn(employee);
        when(repository.saveAll(List.of(employee))).thenReturn(List.of(employee));
        when(mapper.toResponse(employee)).thenReturn(response);

        // Act
        List<EmployeeResponse> result = service.saveAll(List.of(request));

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(mapper).toEntity(request);
        verify(repository).saveAll(anyList());
        verify(mapper).toResponse(employee);
    }

    @Test
    @DisplayName("Should update employee when employee exists")
    void shouldUpdateEmployeeWhenExists() {

        // Arrange
        Long id = 1L;
        EmployeeRequest request = new EmployeeRequest();
        request.setFirstName("Carlos");
        request.setLastName("Lopez");

        Employee existing = Employee.builder()
                .id(id)
                .firstName("Old")
                .lastName("Name")
                .build();

        EmployeeResponse response = new EmployeeResponse();

        when(repository.findById(id)).thenReturn(Optional.of(existing));
        when(repository.save(existing)).thenReturn(existing);
        when(mapper.toResponse(existing)).thenReturn(response);

        // Act
        EmployeeResponse result = service.update(id, request);

        // Assert
        assertNotNull(result);
        assertEquals("Carlos", existing.getFirstName());
        assertEquals("Lopez", existing.getLastName());
        verify(repository).findById(id);
        verify(repository).save(existing);
        verify(mapper).toResponse(existing);
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when updating non-existing employee")
    void shouldThrowExceptionWhenUpdatingNonExistingEmployee() {

        // Arrange
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(ResourceNotFoundException.class,
                () -> service.update(id, new EmployeeRequest()));

        verify(repository).findById(id);
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Should delete employee when employee exists")
    void shouldDeleteEmployeeWhenExists() {

        // Arrange
        Long id = 1L;
        when(repository.existsById(id)).thenReturn(true);

        // Act
        service.delete(id);

        // Assert
        verify(repository).existsById(id);
        verify(repository).deleteById(id);
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when deleting non-existing employee")
    void shouldThrowExceptionWhenDeletingNonExistingEmployee() {

        // Arrange
        Long id = 1L;
        when(repository.existsById(id)).thenReturn(false);

        // Act + Assert
        assertThrows(ResourceNotFoundException.class,
                () -> service.delete(id));

        verify(repository).existsById(id);
        verify(repository, never()).deleteById(any());
    }

    @Test
    @DisplayName("Should search employees by name ignoring case")
    void shouldSearchEmployeesByName() {

        // Arrange
        String name = "juan";
        Employee employee = Employee.builder().build();
        EmployeeResponse response = new EmployeeResponse();

        when(repository.findByFirstNameContainingIgnoreCase(name))
                .thenReturn(List.of(employee));
        when(mapper.toResponse(employee)).thenReturn(response);

        // Act
        List<EmployeeResponse> result = service.searchByName(name);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(repository).findByFirstNameContainingIgnoreCase(name);
        verify(mapper).toResponse(employee);
    }
}
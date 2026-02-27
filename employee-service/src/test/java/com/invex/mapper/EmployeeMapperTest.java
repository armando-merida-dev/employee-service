package com.invex.mapper;

import com.invex.dto.EmployeeRequest;
import com.invex.dto.EmployeeResponse;
import com.invex.entity.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeMapperTest {

    private final EmployeeMapper mapper = new EmployeeMapper();

    @Test
    @DisplayName("Should map EmployeeRequest to Employee entity")
    void shouldMapRequestToEntity() {

        // Arrange
        EmployeeRequest request = new EmployeeRequest();
        request.setFirstName("Antonio");
        request.setMiddleName("Luis");
        request.setLastName("Escobedo");
        request.setSecondLastName("Martinez");
        request.setAge(30);
        request.setGender("M");
        request.setBirthDate(LocalDate.of(1995, 5, 10));
        request.setPosition("Developer");
        request.setActive(true);

        // Act
        Employee result = mapper.toEntity(request);

        // Assert
        assertNotNull(result);
        assertEquals("Antonio", result.getFirstName());
        assertEquals("Luis", result.getMiddleName());
        assertEquals("Escobedo", result.getLastName());
        assertEquals("Martinez", result.getSecondLastName());
        assertEquals(30, result.getAge());
        assertEquals("M", result.getGender());
        assertEquals(LocalDate.of(1995, 5, 10), result.getBirthDate());
        assertEquals("Developer", result.getPosition());
        assertTrue(result.getActive());
    }

    @Test
    @DisplayName("Should map Employee entity to EmployeeResponse with fullName")
    void shouldMapEntityToResponse() {

        // Arrange
        Employee employee = Employee.builder()
                .id(1L)
                .firstName("Antonio")
                .middleName("Luis")
                .lastName("Escobedo")
                .secondLastName("Martinez")
                .age(30)
                .position("Developer")
                .active(true)
                .createdAt(LocalDateTime.now())
                .build();

        // Act
        EmployeeResponse result = mapper.toResponse(employee);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Antonio Luis Escobedo Martinez", result.getFullName());
        assertEquals(30, result.getAge());
        assertEquals("Developer", result.getPosition());
        assertTrue(result.getActive());
        assertNotNull(result.getCreatedAt());
    }

    @Test
    @DisplayName("Should map Employee entity to EmployeeResponse without middleName and secondLastName")
    void shouldMapEntityToResponseWithoutOptionalNames() {

        // Arrange
        Employee employee = Employee.builder()
                .id(2L)
                .firstName("Antonio")
                .middleName(null)
                .lastName("Escobedo")
                .secondLastName(null)
                .age(30)
                .position("Developer")
                .active(true)
                .createdAt(LocalDateTime.now())
                .build();

        // Act
        EmployeeResponse result = mapper.toResponse(employee);

        // Assert
        assertNotNull(result);
        assertEquals("Antonio  Escobedo", result.getFullName());
    }
}
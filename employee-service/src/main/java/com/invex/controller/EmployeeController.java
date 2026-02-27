package com.invex.controller;

import com.invex.dto.*;
import com.invex.service.EmployeeService;
import com.invex.utils.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(
        name = "Employee Management",
        description = "CRUD operations for managing employees"
)
@RestController
@RequestMapping(Constants.ROOT_MAPPING)
@Validated
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @Operation(
            summary = "Retrieve all employees",
            description = "Returns a complete list of registered employees in the system"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved employee list"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(
            summary = "Retrieve employee by ID",
            description = "Returns detailed information of a specific employee based on the provided ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee found"),
            @ApiResponse(responseCode = "404", description = "Employee not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(Constants.INPUT_PARAM_ID)
    public ResponseEntity<EmployeeResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(
            summary = "Create a new employee",
            description = "Registers a new employee in the system with the provided information"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<List<EmployeeResponse>> create(
            @Valid @RequestBody List<EmployeeRequest> requests) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.saveAll(requests));
    }

    @Operation(
            summary = "Update an existing employee",
            description = "Updates the information of an existing employee identified by ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee updated successfully"),
            @ApiResponse(responseCode = "404", description = "Employee not found"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping(Constants.INPUT_PARAM_ID)
    public ResponseEntity<EmployeeResponse> update(
            @PathVariable Long id,
            @RequestBody EmployeeRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @Operation(
            summary = "Delete employee",
            description = "Removes an employee from the system based on the provided ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Employee deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Employee not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping(Constants.INPUT_PARAM_ID)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Search employees by name",
            description = "Returns a list of employees that match the provided name parameter"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employees retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid search parameter"),
            @ApiResponse(responseCode = "404", description = "No employees found with the given name"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(Constants.INPUT_PARAM_SEARCH)
    public ResponseEntity<List<EmployeeResponse>> search(@RequestParam String name) {
        return ResponseEntity.ok(service.searchByName(name));
    }
}

package com.invex.controller;

import com.invex.dto.*;
import com.invex.service.EmployeeService;
import com.invex.utils.Constants;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Constants.ROOT_MAPPING)
@Validated
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(Constants.INPUT_PARAM_ID)
    public ResponseEntity<EmployeeResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<List<EmployeeResponse>> create(
            @Valid @RequestBody List<EmployeeRequest> requests) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.saveAll(requests));
    }

    @PutMapping(Constants.INPUT_PARAM_ID)
    public ResponseEntity<EmployeeResponse> update(
            @PathVariable Long id,
            @RequestBody EmployeeRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping(Constants.INPUT_PARAM_ID)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(Constants.INPUT_PARAM_SEARCH)
    public ResponseEntity<List<EmployeeResponse>> search(@RequestParam String name) {
        return ResponseEntity.ok(service.searchByName(name));
    }
}

package com.invex.service;

import com.invex.dto.*;

import java.util.List;

public interface EmployeeService {

    List<EmployeeResponse> findAll();

    EmployeeResponse findById(Long id);

    List<EmployeeResponse> saveAll(List<EmployeeRequest> requests);

    EmployeeResponse update(Long id, EmployeeRequest request);

    void delete(Long id);

    List<EmployeeResponse> searchByName(String name);
}

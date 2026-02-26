package com.invex.service.impl;

import com.invex.dto.*;
import com.invex.entity.Employee;
import com.invex.exception.ResourceNotFoundException;
import com.invex.mapper.EmployeeMapper;
import com.invex.repository.EmployeeRepository;
import com.invex.service.EmployeeService;
import com.invex.utils.Constants;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;

    public EmployeeServiceImpl(EmployeeRepository repository, EmployeeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<EmployeeResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public EmployeeResponse findById(Long id) {
        Employee e = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Constants.OUTPUT_NOT_FOUND));
        return mapper.toResponse(e);
    }

    public List<EmployeeResponse> saveAll(List<EmployeeRequest> requests) {
        List<Employee> list = requests.stream()
                .map(mapper::toEntity)
                .collect(Collectors.toList());
        return repository.saveAll(list)
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public EmployeeResponse update(Long id, EmployeeRequest request) {
        Employee e = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Constants.OUTPUT_NOT_FOUND));

        if (request.getFirstName() != null) e.setFirstName(request.getFirstName());
        if (request.getLastName() != null) e.setLastName(request.getLastName());
        if (request.getPosition() != null) e.setPosition(request.getPosition());
        if (request.getActive() != null) e.setActive(request.getActive());

        return mapper.toResponse(repository.save(e));
    }

    public void delete(Long id) {
        if (!repository.existsById(id))
            throw new ResourceNotFoundException(Constants.OUTPUT_NOT_FOUND);
        repository.deleteById(id);
    }

    public List<EmployeeResponse> searchByName(String name) {
        return repository.findByFirstNameContainingIgnoreCase(name)
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }
}

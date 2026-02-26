package com.invex.mapper;

import com.invex.dto.*;
import com.invex.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee toEntity(EmployeeRequest request) {
        Employee e = new Employee();
        e.setFirstName(request.getFirstName());
        e.setMiddleName(request.getMiddleName());
        e.setLastName(request.getLastName());
        e.setSecondLastName(request.getSecondLastName());
        e.setAge(request.getAge());
        e.setGender(request.getGender());
        e.setBirthDate(request.getBirthDate());
        e.setPosition(request.getPosition());
        e.setActive(request.getActive());
        return e;
    }

    public EmployeeResponse toResponse(Employee e) {
        String fullName = String.join(" ",
                e.getFirstName(),
                e.getMiddleName() == null ? "" : e.getMiddleName(),
                e.getLastName(),
                e.getSecondLastName() == null ? "" : e.getSecondLastName()
        ).trim();

        return new EmployeeResponse(
                e.getId(),
                fullName,
                e.getAge(),
                e.getPosition(),
                e.getActive(),
                e.getCreatedAt()
        );
    }
}

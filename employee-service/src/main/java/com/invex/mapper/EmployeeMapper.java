package com.invex.mapper;

import com.invex.dto.*;
import com.invex.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee toEntity(EmployeeRequest request) {
        return Employee.builder()
                .firstName(request.getFirstName())
                .middleName(request.getMiddleName())
                .lastName(request.getLastName())
                .secondLastName(request.getSecondLastName())
                .age(request.getAge())
                .gender(request.getGender())
                .birthDate(request.getBirthDate())
                .position(request.getPosition())
                .active(request.getActive())
                .build();
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

package com.invex.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class EmployeeResponse {

    private Long id;
    private String fullName;
    private Integer age;
    private String position;
    private Boolean active;
    private LocalDateTime createdAt;
}

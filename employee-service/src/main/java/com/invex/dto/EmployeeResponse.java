package com.invex.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Schema(description = "Represents the employee information returned by the API")
public class EmployeeResponse {

    @Schema(description = "Unique identifier of the employee", example = "1")
    private Long id;

    @Schema(description = "Employee full name", example = "Armando Armando Merida Merida")
    private String fullName;

    @Schema(description = "Employee age", example = "40")
    private Integer age;

    @Schema(description = "Employee job position", example = "Backend Developer")
    private String position;

    @Schema(description = "Indicates whether the employee is active", example = "true")
    private Boolean active;

    @Schema(description = "Date and time when the employee was created", example = "2026-02-26T18:30:00")
    private LocalDateTime createdAt;
}
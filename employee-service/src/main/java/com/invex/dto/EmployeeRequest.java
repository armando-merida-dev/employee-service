package com.invex.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Schema(description = "Represents the request payload to create or update an employee")
public class EmployeeRequest {

    @NotBlank(message = "The firstName is required")
    @Schema(description = "Employee first name", example = "Armando", required = true)
    private String firstName;

    @Schema(description = "Employee middle name", example = "Armando")
    private String middleName;

    @NotBlank(message = "The lastName is required")
    @Schema(description = "Employee last name", example = "Merida", required = true)
    private String lastName;

    @NotBlank(message = "The secondLastName is required")
    @Schema(description = "Employee second last name", example = "Merida", required = true)
    private String secondLastName;

    @NotNull(message = "The age is required")
    @Min(18)
    @Schema(description = "Employee age", example = "40", minimum = "18", required = true)
    private Integer age;

    @NotBlank(message = "The gender is required")
    @Schema(description = "Employee gender", example = "F", required = true)
    private String gender;

    @NotNull(message = "The birthDate is required")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Schema(description = "Employee birth date in format dd-MM-yyyy", example = "15-08-1994", required = true)
    private LocalDate birthDate;

    @NotBlank(message = "The position is required")
    @Schema(description = "Employee job position", example = "Backend Developer", required = true)
    private String position;

    @NotNull(message = "The active is required")
    @Schema(description = "Indicates whether the employee is active", example = "true", required = true)
    private Boolean active;
}
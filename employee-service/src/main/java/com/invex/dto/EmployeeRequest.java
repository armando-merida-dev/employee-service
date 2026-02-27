package com.invex.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class EmployeeRequest {

    @NotBlank
    private String firstName;

    private String middleName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String secondLastName;

    @Min(18)
    @NotNull
    private Integer age;

    @NotBlank
    private String gender;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull
    private LocalDate birthDate;

    @NotBlank
    private String position;

    @NotNull
    private Boolean active;
}

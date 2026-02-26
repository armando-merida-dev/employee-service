package com.invex.dto;

import java.time.LocalDateTime;

public class EmployeeResponse {

    private Long id;
    private String fullName;
    private Integer age;
    private String position;
    private Boolean active;
    private LocalDateTime createdAt;

    public EmployeeResponse(Long id, String fullName, Integer age,
                            String position, Boolean active,
                            LocalDateTime createdAt) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.position = position;
        this.active = active;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public Integer getAge() {
        return age;
    }

    public String getPosition() {
        return position;
    }

    public Boolean getActive() {
        return active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}

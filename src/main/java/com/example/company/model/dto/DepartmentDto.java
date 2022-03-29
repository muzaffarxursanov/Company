package com.example.company.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
    @NotNull(message = "Department name is empty")
    private String departmentName;
    @NotNull(message = "Company id is empty")
    private Long CompanyId;
}

package com.example.company.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDto {
    @NotNull(message = "Worker name is empty")
    private String name;
    @NotNull(message = "Phone number is empty")
    private String phoneNumber;
    @NotNull(message = "Address id is empty")
    private Long addressId;
    @NotNull(message = "Department id is empty")
    private Long departmentId;
}

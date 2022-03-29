package com.example.company.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {
    @NotNull(message = "Corporation name is empty")
    private String corporationName;
    @NotNull(message = "Director name is empty")
    private String directorName;
    @NotNull(message = "Address id is empty")
    private Long addressId;
}

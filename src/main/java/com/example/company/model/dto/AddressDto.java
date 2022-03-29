package com.example.company.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    @NotNull(message = "Street name is empty")
    private String street;
    @NotNull(message = "Home number is empty")
    private String homeNumber;
}

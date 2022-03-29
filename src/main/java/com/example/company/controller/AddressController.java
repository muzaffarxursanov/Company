package com.example.company.controller;

import com.example.company.model.ApiResponse;
import com.example.company.model.dto.AddressDto;
import com.example.company.model.entity.Address;
import com.example.company.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    @Autowired
    AddressService addressService;

    @GetMapping
    public ResponseEntity<List<Address>> getAddresses(){
        return ResponseEntity.ok(addressService.getAddresses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddress(@PathVariable Long id){
        return ResponseEntity.ok(addressService.getAddress(id));
    }

    @PostMapping
    public ResponseEntity<Address> addAddress(@RequestBody AddressDto addressDto){
        return ResponseEntity.ok(addressService.addAddress(addressDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable Long id){
        ApiResponse apiResponse = addressService.deleteAddress(id);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse.getMassage());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editAddress(@PathVariable Long id, @RequestBody AddressDto addressDto){
        ApiResponse apiResponse = addressService.editAddress(id, addressDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(apiResponse.getMassage());
    }
}

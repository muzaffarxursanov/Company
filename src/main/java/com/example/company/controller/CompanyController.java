package com.example.company.controller;

import com.example.company.model.ApiResponse;
import com.example.company.model.dto.CompanyDto;
import com.example.company.model.entity.Company;
import com.example.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<Company>> getCompanies(){
        return ResponseEntity.ok(companyService.getCompanies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable Long id){
        return ResponseEntity.ok(companyService.getCompany(id));
    }

    @PostMapping
    public ResponseEntity<?> addCompany(@RequestBody CompanyDto companyDto){
        ApiResponse apiResponse = companyService.addCompany(companyDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.OK:HttpStatus.CONFLICT)
                .body(apiResponse.getMassage());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable Long id){
        ApiResponse apiResponse = companyService.deleteCompany(id);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT)
                .body(apiResponse.getMassage());
    }

    @PutMapping("/{id}")
    public ResponseEntity editCompany(@PathVariable Long id, @RequestBody CompanyDto companyDto){
        ApiResponse apiResponse = companyService.editCompany(id, companyDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT)
                .body(apiResponse.getMassage());
    }
}

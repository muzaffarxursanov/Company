package com.example.company.service;

import com.example.company.model.ApiResponse;
import com.example.company.model.dto.CompanyDto;
import com.example.company.model.entity.Address;
import com.example.company.model.entity.Company;
import com.example.company.repository.AddressRepository;
import com.example.company.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    AddressRepository addressRepository;

    public List<Company> getCompanies(){
        return companyRepository.findAll();
    }

    public Company getCompany(Long id){
        Optional<Company> optionalCompany = companyRepository.findById(id);
        return optionalCompany.orElse(null);
    }

    public ApiResponse addCompany(CompanyDto companyDto){
        Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddressId());
        if (!optionalAddress.isPresent())
            return new ApiResponse("Address not found", false);
        Company company=new Company();
        company.setCorporationName(companyDto.getCorporationName());
        company.setDirectorName(companyDto.getDirectorName());
        company.setAddress(optionalAddress.get());
        companyRepository.save(company);
        return new ApiResponse("Company is saved", true);
    }

    public ApiResponse deleteCompany(Long id){
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent())
            return new ApiResponse("Bunday company mavjud emas", false);
        companyRepository.deleteById(id);
        return new ApiResponse("Company id deleted", true);
    }

    public ApiResponse editCompany(Long id, CompanyDto companyDto){
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent())
            return new ApiResponse("Company not found", false);
        Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddressId());
        if (!optionalAddress.isPresent())
            return new ApiResponse("Address not found", false);
        Company company = optionalCompany.get();
        company.setCorporationName(companyDto.getCorporationName());
        company.setDirectorName(companyDto.getDirectorName());
        company.setAddress(optionalAddress.get());
        companyRepository.save(company);
        return new ApiResponse("Company is edited", true);
    }
}

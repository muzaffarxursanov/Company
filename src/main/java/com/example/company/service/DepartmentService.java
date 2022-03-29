package com.example.company.service;

import com.example.company.model.ApiResponse;
import com.example.company.model.dto.DepartmentDto;
import com.example.company.model.entity.Company;
import com.example.company.model.entity.Department;
import com.example.company.repository.CompanyRepository;
import com.example.company.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    CompanyRepository companyRepository;

    public List<Department> getDepartments(){
        return departmentRepository.findAll();
    }

    public Department getDepartment(Long id){
        return departmentRepository.findById(id).orElse(null);
    }

    public ApiResponse addDepartment(DepartmentDto departmentDto){
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (!optionalCompany.isPresent())
            return new ApiResponse("Company not found", false);
        Department department=new Department();
        department.setName(departmentDto.getDepartmentName());
        department.setCompany(optionalCompany.get());
        departmentRepository.save(department);
        return new ApiResponse("Department is added", true);
    }

    public ApiResponse deleteDepartment(Long id){
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent())
            return new ApiResponse("Department not found", false);
        departmentRepository.delete(optionalDepartment.get());
        return new ApiResponse("Department is deleted", true);
    }

    public ApiResponse editDepartment(Long id, DepartmentDto departmentDto){
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (!optionalCompany.isPresent())
            return new ApiResponse("Company not found", false);
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent())
            return new ApiResponse("Department is not found", false);
        Department department = optionalDepartment.get();
        department.setName(departmentDto.getDepartmentName());
        department.setCompany(optionalCompany.get());
        departmentRepository.save(department);
        return new ApiResponse("Department is edited", true);
    }
}

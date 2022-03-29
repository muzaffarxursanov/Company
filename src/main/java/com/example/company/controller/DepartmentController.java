package com.example.company.controller;

import com.example.company.model.ApiResponse;
import com.example.company.model.dto.DepartmentDto;
import com.example.company.model.entity.Department;
import com.example.company.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<Department>> getDepartments(){
        return ResponseEntity.ok(departmentService.getDepartments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable Long id){
        return ResponseEntity.ok(departmentService.getDepartment(id));
    }

    @PostMapping
    public ResponseEntity<?> addDepartment(@RequestBody DepartmentDto departmentDto){
        ApiResponse apiResponse = departmentService.addDepartment(departmentDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.OK:HttpStatus.CONFLICT)
                .body(apiResponse.getMassage());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable Long id){
        ApiResponse apiResponse = departmentService.deleteDepartment(id);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT)
                .body(apiResponse.getMassage());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editDepartment(@PathVariable Long id, @RequestBody DepartmentDto departmentDto){
        ApiResponse apiResponse = departmentService.editDepartment(id, departmentDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT)
                .body(apiResponse.getMassage());
    }
}

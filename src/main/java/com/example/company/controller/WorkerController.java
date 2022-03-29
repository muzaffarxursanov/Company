package com.example.company.controller;

import com.example.company.model.ApiResponse;
import com.example.company.model.dto.WorkerDto;
import com.example.company.model.entity.Worker;
import com.example.company.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/worker")
public class WorkerController {
    @Autowired
    WorkerService workerService;

    @GetMapping
    public ResponseEntity<List<Worker>> getWorkers(){
        return ResponseEntity.ok(workerService.getWorkers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Worker> getWorker(@PathVariable Long id){
        return ResponseEntity.ok(workerService.getWorker(id));
    }

    @PostMapping
    public ResponseEntity<?> addWorker(@RequestBody WorkerDto workerDto){
        ApiResponse apiResponse = workerService.addWorker(workerDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.OK:HttpStatus.CONFLICT)
                .body(apiResponse.getMassage());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWorker(@PathVariable Long id){
        ApiResponse apiResponse = workerService.deleteWorker(id);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT)
                .body(apiResponse.getMassage());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editWorker(@PathVariable Long id, @RequestBody WorkerDto workerDto){
        ApiResponse apiResponse = workerService.editWorker(id, workerDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT)
                .body(apiResponse.getMassage());
    }
}

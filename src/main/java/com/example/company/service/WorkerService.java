package com.example.company.service;

import com.example.company.model.ApiResponse;
import com.example.company.model.dto.WorkerDto;
import com.example.company.model.entity.Address;
import com.example.company.model.entity.Department;
import com.example.company.model.entity.Worker;
import com.example.company.repository.AddressRepository;
import com.example.company.repository.DepartmentRepository;
import com.example.company.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {
    @Autowired
    WorkerRepository workerRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    public List<Worker> getWorkers(){
        return workerRepository.findAll();
    }

    public Worker getWorker(Long id){
        return workerRepository.findById(id).orElse(null);
    }

    public ApiResponse addWorker(WorkerDto workerDto){
        Optional<Address> optionalAddress = addressRepository.findById(workerDto.getAddressId());
        if (!optionalAddress.isPresent())
            return new ApiResponse("Address not found", false);
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if (!optionalDepartment.isPresent())
            return new ApiResponse("Department not found", false);
        Worker worker=new Worker();
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setAddress(optionalAddress.get());
        worker.setDepartment(optionalDepartment.get());
        workerRepository.save(worker);
        return new ApiResponse("Worker is added", true);
    }

    public ApiResponse deleteWorker(Long id){
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (!optionalWorker.isPresent())
            return new ApiResponse("Worker is not found", false);
        workerRepository.delete(optionalWorker.get());
        return new ApiResponse("Worker is deleted", true);
    }

    public ApiResponse editWorker(Long id, WorkerDto workerDto){
        Optional<Address> optionalAddress = addressRepository.findById(workerDto.getAddressId());
        if (!optionalAddress.isPresent())
            return new ApiResponse("Address not found", false);
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if (!optionalDepartment.isPresent())
            return new ApiResponse("Department is not found", false);
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (!optionalWorker.isPresent())
            return new ApiResponse("Worker is not found", false);
        Worker worker = optionalWorker.get();
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setAddress(optionalAddress.get());
        worker.setDepartment(optionalDepartment.get());
        workerRepository.save(worker);
        return new ApiResponse("Worker is edited", true);
    }
}

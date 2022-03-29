package com.example.company.service;

import com.example.company.model.ApiResponse;
import com.example.company.model.dto.AddressDto;
import com.example.company.model.entity.Address;
import com.example.company.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

    public List<Address> getAddresses(){
        return addressRepository.findAll();
    }

    public Address getAddress(Long id){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        return optionalAddress.orElseGet(null);
    }

    public Address addAddress(AddressDto addressDto){
        Address address=new Address();
        address.setStreet(addressDto.getStreet());
        address.setHomeNumber(addressDto.getHomeNumber());
        return addressRepository.save(address);
    }

    public ApiResponse deleteAddress(Long id){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent())
            return new ApiResponse("Bu idli address mavjud emas", false);
        Address address = optionalAddress.get();
        addressRepository.deleteById(address.getId());
        return new ApiResponse("Address o'chirildi", true);
    }

    public ApiResponse editAddress(Long id, AddressDto addressDto){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent())
            return new ApiResponse("Bu idli address mavjud emas", false);
        Address address = optionalAddress.get();
        address.setStreet(addressDto.getStreet());
        address.setHomeNumber(addressDto.getHomeNumber());
        addressRepository.save(address);
        return new ApiResponse("Address o'zgartirildi", true);
    }
}

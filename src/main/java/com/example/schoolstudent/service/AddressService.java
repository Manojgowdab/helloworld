package com.example.schoolstudent.service;

import com.example.schoolstudent.dto.AddressDto;
import com.example.schoolstudent.entity.Address;
import com.example.schoolstudent.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public List<AddressDto> getAddressDetails() {
        List<Address> addresses = addressRepository.findAll();
        List<AddressDto> addressDtos = new ArrayList<>();
        for (Address address : addresses) {
            AddressDto addressDto = new AddressDto();
            addressDto.setAddressId(address.getAddressId());
            addressDto.setStreet(address.getStreet());
            addressDto.setCity(address.getCity());
            addressDto.setState(address.getState());
            addressDto.setZipcode(address.getZipcode());
            addressDtos.add(addressDto);

        }
        return addressDtos;
    }

    public void deleteAddressById(Long addressId) {
        if (addressRepository.existsById(addressId)) {
            addressRepository.deleteById(addressId);
        } else {
            throw new RuntimeException("Address not found with id: " + addressId);
        }
    }

    }

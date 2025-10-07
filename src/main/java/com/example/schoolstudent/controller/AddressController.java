package com.example.schoolstudent.controller;


import com.example.schoolstudent.dto.AddressDto;
import com.example.schoolstudent.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping
    //get method
    public List<AddressDto> getAddressDetails()
    {
        return addressService.getAddressDetails();
    }
}

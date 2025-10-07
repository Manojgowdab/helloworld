package com.example.schoolstudent.repository;

import com.example.schoolstudent.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}

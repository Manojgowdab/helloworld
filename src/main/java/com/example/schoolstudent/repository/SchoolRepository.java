package com.example.schoolstudent.repository;

import com.example.schoolstudent.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Long> {
}

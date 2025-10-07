package com.example.schoolstudent.controller;

import com.example.schoolstudent.dto.SchoolDto;
import com.example.schoolstudent.entity.School;
import com.example.schoolstudent.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/schools")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @PostMapping
    public SchoolDto saveSchoolDetails(@RequestBody SchoolDto schoolDto) {
        return schoolService.saveSchoolDetails(schoolDto);
    }

    @PutMapping("/{id}")
    public SchoolDto updateSchool(@PathVariable Long id, @RequestBody SchoolDto schoolDto) {
        return schoolService.updateSchool(id, schoolDto);
    }

    @DeleteMapping("/school/{id}")
    public void deleteSchool(@PathVariable Long id) {
        schoolService.deleteSchoolById(id);
    }
}


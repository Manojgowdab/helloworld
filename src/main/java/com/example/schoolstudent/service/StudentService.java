package com.example.schoolstudent.service;

import com.example.schoolstudent.dto.AddressDto;
import com.example.schoolstudent.dto.SchoolDto;
import com.example.schoolstudent.dto.StudentDto;
import com.example.schoolstudent.entity.Address;
import com.example.schoolstudent.entity.School;
import com.example.schoolstudent.entity.Student;
import com.example.schoolstudent.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Make sure this is public
    public List<StudentDto> getStudentDetails() {
        List<Student> students = studentRepository.findAll();
        List<StudentDto> dtoList = new ArrayList<>();
        for (Student s : students) {
            dtoList.add(entityToDto(s));
        }
        return dtoList;
    }

    private StudentDto entityToDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setStudentId(student.getStudentId());
        studentDto.setStudentName(student.getStudentName());
        studentDto.setStudentClass(student.getStudentClass());
        studentDto.setStudentEmailId(student.getStudentEmailId());

        // Map student address
        if (student.getAddress() != null) {
            AddressDto addrDto = new AddressDto();
            addrDto.setAddressId(student.getAddress().getAddressId());
            addrDto.setStreet(student.getAddress().getStreet());
            addrDto.setCity(student.getAddress().getCity());
            addrDto.setState(student.getAddress().getState());
            addrDto.setZipcode(student.getAddress().getZipcode());
            studentDto.setAddressDto(addrDto);
        }

        // Map school info
        if (student.getSchool() != null) {
            School school = student.getSchool();
            SchoolDto schoolDto = new SchoolDto();
            schoolDto.setSchoolId(school.getSchoolId());
            schoolDto.setSchoolName(school.getSchoolName());
            schoolDto.setSchoolContactNumber(school.getSchoolContactNumber());
            schoolDto.setSchoolEmailId(school.getSchoolEmailId());

            // Map school address
            if (school.getAddress() != null) {
                AddressDto schoolAddr = new AddressDto();
                schoolAddr.setAddressId(school.getAddress().getAddressId());
                schoolAddr.setStreet(school.getAddress().getStreet());
                schoolAddr.setCity(school.getAddress().getCity());
                schoolAddr.setState(school.getAddress().getState());
                schoolAddr.setZipcode(school.getAddress().getZipcode());
                schoolDto.setAddressDto(schoolAddr);
            }

            // Do NOT map studentDto list here to avoid infinite recursion
            studentDto.setSchoolDto(schoolDto);
        }

        return studentDto;
    }

    public void deleteStudentById(Long studentId) {
        if (studentRepository.existsById(studentId)) {
            studentRepository.deleteById(studentId);
        } else {
            throw new RuntimeException("Student not found with id: " + studentId);
        }
    }
}

package com.example.schoolstudent.dto;

import com.example.schoolstudent.entity.Address;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private Long studentId;
    private String studentName;
    private String studentClass;
    private String studentEmailId;
    private AddressDto addressDto;
    private SchoolDto schoolDto;

    public AddressDto getAddressDto() {
        return addressDto;
    }

    public void setAddressDto(AddressDto addressDto) {
        this.addressDto = addressDto;
    }

    public SchoolDto getSchoolDto() {
        return schoolDto;
    }

    public void setSchoolDto(SchoolDto schoolDto) {
        this.schoolDto = schoolDto;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public String getStudentEmailId() {
        return studentEmailId;
    }

    public void setStudentEmailId(String studentEmailId) {
        this.studentEmailId = studentEmailId;
    }
}

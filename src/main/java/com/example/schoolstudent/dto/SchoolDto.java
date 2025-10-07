package com.example.schoolstudent.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SchoolDto {
    private Long schoolId;
    private String schoolName;
    private Long schoolContactNumber;
    private String schoolEmailId;
    private AddressDto addressDto;
    private List<StudentDto> studentDto;

    public List<StudentDto> getStudentDto() {
        return studentDto;
    }

    public void setStudentDto(List<StudentDto> studentDto) {
        this.studentDto = studentDto;
    }

    public AddressDto getAddressDto() {
        return addressDto;
    }

    public void setAddressDto(AddressDto addressDto) {
        this.addressDto = addressDto;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public Long getSchoolContactNumber() {
        return schoolContactNumber;
    }

    public void setSchoolContactNumber(Long schoolContactNumber) {
        this.schoolContactNumber = schoolContactNumber;
    }

    public String getSchoolEmailId() {
        return schoolEmailId;
    }

    public void setSchoolEmailId(String schoolEmailId) {
        this.schoolEmailId = schoolEmailId;
    }
}

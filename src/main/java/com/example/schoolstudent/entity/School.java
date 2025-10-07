package com.example.schoolstudent.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "school")
public class School
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long schoolId;
    private String schoolName;
    private Long schoolContactNumber;
    private String schoolEmailId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "school")
    private List<Student> student;

    public List<Student> getStudent() {
        return student;
    }

    public void setStudent(List<Student> student) {
        this.student = student;
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
package com.example.schoolstudent.service;

import com.example.schoolstudent.dto.AddressDto;
import com.example.schoolstudent.dto.SchoolDto;
import com.example.schoolstudent.dto.StudentDto;
import com.example.schoolstudent.entity.Address;
import com.example.schoolstudent.entity.School;
import com.example.schoolstudent.entity.Student;
import com.example.schoolstudent.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    public SchoolDto saveSchoolDetails(SchoolDto schoolDto) {
        School school = convertDtoToEntity(schoolDto);
        School saved = schoolRepository.save(school);
        return entityToDto(saved);
    }

    private School convertDtoToEntity(SchoolDto schoolDto) {
        School school = new School();
        school.setSchoolName(schoolDto.getSchoolName());
        school.setSchoolEmailId(schoolDto.getSchoolEmailId());
        school.setSchoolContactNumber(schoolDto.getSchoolContactNumber());

        if (schoolDto.getAddressDto() != null) {
            Address address = new Address();
            address.setStreet(schoolDto.getAddressDto().getStreet());
            address.setCity(schoolDto.getAddressDto().getCity());
            address.setState(schoolDto.getAddressDto().getState());
            address.setZipcode(schoolDto.getAddressDto().getZipcode());
            address.setSchool(school);
            school.setAddress(address);
        }

        if (schoolDto.getStudentDto() != null) {
            List<Student> students = new ArrayList<>();
            for (StudentDto studentDto : schoolDto.getStudentDto()) {
                Student student = getStudentFromDto(studentDto, school);
                students.add(student);
            }
            school.setStudent(students);
        }

        return school;
    }

    private static Student getStudentFromDto(StudentDto studentDto, School school) {
        Student student = new Student();
        student.setStudentName(studentDto.getStudentName());
        student.setStudentClass(studentDto.getStudentClass());
        student.setStudentEmailId(studentDto.getStudentEmailId());

        if (studentDto.getAddressDto() != null) {
            Address address = new Address();
            address.setStreet(studentDto.getAddressDto().getStreet());
            address.setCity(studentDto.getAddressDto().getCity());
            address.setState(studentDto.getAddressDto().getState());
            address.setZipcode(studentDto.getAddressDto().getZipcode());
            address.setStudent(student);
            student.setAddress(address);
        }

        student.setSchool(school);
        return student;
    }

    private SchoolDto entityToDto(School school) {
        SchoolDto schoolDto = new SchoolDto();
        schoolDto.setSchoolId(school.getSchoolId());
        schoolDto.setSchoolName(school.getSchoolName());
        schoolDto.setSchoolContactNumber(school.getSchoolContactNumber());
        schoolDto.setSchoolEmailId(school.getSchoolEmailId());

        if (school.getAddress() != null) {
            AddressDto addressDto = new AddressDto();
            addressDto.setAddressId(school.getAddress().getAddressId());
            addressDto.setStreet(school.getAddress().getStreet());
            addressDto.setCity(school.getAddress().getCity());
            addressDto.setState(school.getAddress().getState());
            addressDto.setZipcode(school.getAddress().getZipcode());
            schoolDto.setAddressDto(addressDto);
        }

        if (school.getStudent() != null) {
            List<StudentDto> studentDtos = new ArrayList<>();
            for (Student s : school.getStudent()) {
                StudentDto studentDto = new StudentDto();
                studentDto.setStudentId(s.getStudentId());
                studentDto.setStudentName(s.getStudentName());
                studentDto.setStudentClass(s.getStudentClass());
                studentDto.setStudentEmailId(s.getStudentEmailId());

                if (s.getAddress() != null) {
                    AddressDto studentAddress = new AddressDto();
                    studentAddress.setAddressId(s.getAddress().getAddressId());
                    studentAddress.setStreet(s.getAddress().getStreet());
                    studentAddress.setCity(s.getAddress().getCity());
                    studentAddress.setState(s.getAddress().getState());
                    studentAddress.setZipcode(s.getAddress().getZipcode());
                    studentDto.setAddressDto(studentAddress);
                }

                studentDtos.add(studentDto);
            }
            schoolDto.setStudentDto(studentDtos);
        }

        return schoolDto;
    }

    public SchoolDto updateSchool(Long schoolId, SchoolDto schoolDto) {
        School existingSchool = schoolRepository.findById(schoolId)
                .orElseThrow(() -> new RuntimeException("School not found with id " + schoolId));

        if (schoolDto.getSchoolName() != null) {
            existingSchool.setSchoolName(schoolDto.getSchoolName());
        }
        if (schoolDto.getSchoolEmailId() != null) {
            existingSchool.setSchoolEmailId(schoolDto.getSchoolEmailId());
        }
        if (schoolDto.getSchoolContactNumber() != null) {
            existingSchool.setSchoolContactNumber(schoolDto.getSchoolContactNumber());
        }

        if (schoolDto.getAddressDto() != null) {
            AddressDto addressDto = schoolDto.getAddressDto();
            Address address = existingSchool.getAddress();
            if (address == null) {
                address = new Address();
                address.setSchool(existingSchool);
            }
            if (addressDto.getStreet() != null) address.setStreet(addressDto.getStreet());
            if (addressDto.getCity() != null) address.setCity(addressDto.getCity());
            if (addressDto.getState() != null) address.setState(addressDto.getState());
            if (addressDto.getZipcode() != null) address.setZipcode(addressDto.getZipcode());

            existingSchool.setAddress(address);
        }

        // ✅ Handle students (add new + update existing)
        if (schoolDto.getStudentDto() != null) {
            List<Student> existingStudents = existingSchool.getStudent();
            if (existingStudents == null) {
                existingStudents = new ArrayList<>();
            }

            for (StudentDto studentDto : schoolDto.getStudentDto()) {
                if (studentDto.getStudentId() == null) {
                    // case 1: New student
                    Student student = new Student();
                    student.setStudentName(studentDto.getStudentName());
                    student.setStudentClass(studentDto.getStudentClass());
                    student.setStudentEmailId(studentDto.getStudentEmailId());

                    if (studentDto.getAddressDto() != null) {
                        Address studentAddress = new Address();
                        studentAddress.setStreet(studentDto.getAddressDto().getStreet());
                        studentAddress.setCity(studentDto.getAddressDto().getCity());
                        studentAddress.setState(studentDto.getAddressDto().getState());
                        studentAddress.setZipcode(studentDto.getAddressDto().getZipcode());
                        studentAddress.setStudent(student);
                        student.setAddress(studentAddress);
                    }

                    student.setSchool(existingSchool);
                    existingStudents.add(student);

                } else {
                    // case 2: Update existing student
                    for (Student existingStudent : existingStudents) {
                        if (existingStudent.getStudentId().equals(studentDto.getStudentId())) {
                            if (studentDto.getStudentName() != null) {
                                existingStudent.setStudentName(studentDto.getStudentName());
                            }
                            if (studentDto.getStudentClass() != null) {
                                existingStudent.setStudentClass(studentDto.getStudentClass());
                            }
                            if (studentDto.getStudentEmailId() != null) {
                                existingStudent.setStudentEmailId(studentDto.getStudentEmailId());
                            }

                            if (studentDto.getAddressDto() != null) {
                                Address studentAddress = existingStudent.getAddress();
                                if (studentAddress == null) {
                                    studentAddress = new Address();
                                    studentAddress.setStudent(existingStudent);
                                }
                                if (studentDto.getAddressDto().getStreet() != null)
                                    studentAddress.setStreet(studentDto.getAddressDto().getStreet());
                                if (studentDto.getAddressDto().getCity() != null)
                                    studentAddress.setCity(studentDto.getAddressDto().getCity());
                                if (studentDto.getAddressDto().getState() != null)
                                    studentAddress.setState(studentDto.getAddressDto().getState());
                                if (studentDto.getAddressDto().getZipcode() != null)
                                    studentAddress.setZipcode(studentDto.getAddressDto().getZipcode());

                                existingStudent.setAddress(studentAddress);
                            }
                        }
                    }
                }
            }

            existingSchool.setStudent(existingStudents);
        }

        School updated = schoolRepository.save(existingSchool);
        return entityToDto(updated);
    }

    public void deleteSchoolById(Long schoolId) {
        if (schoolRepository.existsById(schoolId)) {
            schoolRepository.deleteById(schoolId);
        } else {
            throw new RuntimeException("School not found with id: " + schoolId);
        }
    }


}



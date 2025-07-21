package com.likelion.seminar.student.dto;

import java.time.LocalDate;

public class StudentDTO {
    private Long studentID;
    private String name;
    private LocalDate dateOfBirth;

    public StudentDTO(Long studentID, String name, LocalDate dateOfBirth) {
        this.studentID = studentID;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getStudentID() {
        return studentID;
    }

    public void setStudentID(Long studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


}

package com.likelion.seminar.student.service;

import com.likelion.seminar.student.dto.StudentDTO;
import com.likelion.seminar.student.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void createStudent(StudentDTO student) {
        studentRepository.save(student);
    }

    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<StudentDTO> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public void updateStudent(Long id, StudentDTO student) {
        studentRepository.update(id, student);
    }

    public void deleteStudent(Long id) {
        studentRepository.delete(id);
    }
}

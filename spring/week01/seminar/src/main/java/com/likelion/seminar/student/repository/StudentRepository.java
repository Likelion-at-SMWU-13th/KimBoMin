package com.likelion.seminar.student.repository;

import com.likelion.seminar.student.dto.StudentDTO;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class StudentRepository {

    private final Map<Long, StudentDTO> studentMap = new HashMap<>();

    public void save(StudentDTO student) {
        studentMap.put(student.getStudentID(), student);
    }

    public List<StudentDTO> findAll() {
        return new ArrayList<>(studentMap.values());
    }

    public Optional<StudentDTO> findById(Long studentID) {
        return Optional.ofNullable(studentMap.get(studentID));
    }

    public void update(Long studentID, StudentDTO student) {
        studentMap.put(studentID, student);
    }

    public void delete(Long studentID) {
        studentMap.remove(studentID);
    }
}

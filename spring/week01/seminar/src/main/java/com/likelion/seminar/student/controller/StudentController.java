package com.likelion.seminar.student.controller;

import com.likelion.seminar.student.dto.StudentDTO;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    private Map<Long, StudentDTO> studentDB = new HashMap<>();

    @PostMapping
    public String createStudent(@RequestBody StudentDTO student) {
        studentDB.put(student.getStudentID(), student);
        return "학생 등록 완료";
    }

    @GetMapping
    public List<StudentDTO> getAllStudents() {
        return new ArrayList<>(studentDB.values());
    }

    @GetMapping("/{id}")
    public StudentDTO getStudentById(@PathVariable Long id) {
        return studentDB.get(id);
    }

    @PutMapping("/{id}")
    public String updateStudent(@PathVariable Long id, @RequestBody StudentDTO student) {
        studentDB.put(id, student);
        return "학생 정보 수정 완료";
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentDB.remove(id);
        return "학생 삭제 완료";
    }
}

package com.example.springboottutorial.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @PostMapping
    public void registerStudent(@RequestBody Student student) {
        studentService.addStudent(student);
        System.out.println(student.toString());
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudentById(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
    }


    @PutMapping(path = "{studentId}")
    public void editStudent(
            @PathVariable("studentId") Long id,
            @RequestParam(value = "name", required = false) Optional<String> name,
            @RequestParam(value = "email", required = false) Optional<String> email
    ) {
        studentService.editStudent(id, name.orElse(""), email.orElse(""));
    }

}

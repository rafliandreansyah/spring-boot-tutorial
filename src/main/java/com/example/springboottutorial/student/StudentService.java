package com.example.springboottutorial.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        Optional<Student> studentData = studentRepository.findStudentByEmail(student.getEmail());
        if (studentData.isPresent()) {
            throw new IllegalArgumentException("email already exist");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty()) {
            throw new IllegalArgumentException("student not found");
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public void editStudent(Long id, String name, String email) {

        Student student = studentRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("student not found");
        });

        if (name != null && name.length() > 0 && !Objects.equals(name, student.getName())) {
            student.setName(name);
        }

        if (email != null && email.length() > 0 && !Objects.equals(email, student.getEmail())) {
            Optional<Student> studentData = studentRepository.findStudentByEmail(email);
            if (studentData.isPresent()) {
                throw new IllegalArgumentException("email already taken");
            }
            student.setEmail(email);
        }

    }
}

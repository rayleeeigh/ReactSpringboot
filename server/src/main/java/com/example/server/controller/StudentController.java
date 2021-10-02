package com.example.server.controller;

import com.example.server.model.Student;
import com.example.server.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")

public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public void addStudent(@RequestBody Student student){
        studentService.saveStudent(student);
        System.out.println("Successfully Added New Student");
    }
    @GetMapping("/getAll")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }
}

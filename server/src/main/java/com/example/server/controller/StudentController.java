package com.example.server.controller;
import com.example.server.exception.StudentNotFoundException;
import com.example.server.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.server.service.StudentService;


import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public void addStudent(@RequestBody Student student){
        studentService.saveStudent(student);
    }

    @GetMapping("/view/{id}")
    public Student getStudentById(@PathVariable("id") Integer id){
        Student student = studentService.findById(id).orElseThrow(()->new StudentNotFoundException("Student with "+id+" is not found!"));
        return student;
    }



    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable Integer id){
        studentService.removeStudent(id);
    }

    @GetMapping("/view")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @PutMapping("/update/{id}")
    public void updateStudent(@PathVariable Integer id, @RequestBody Student student){studentService.updateStudent(id,student);}

}

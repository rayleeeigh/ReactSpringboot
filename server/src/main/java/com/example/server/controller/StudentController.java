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

    @GetMapping("/viewStudent/{name}")
    public List<Student> viewStudents(@RequestParam String name){
        List<Student> student = studentService.searchStudent(name);
        return student;
    }

    @PutMapping("/addInstructor/{instructorID}/students/{studentID}")
    public Student assignInstructorToStudent(
            @PathVariable int instructorID,
            @PathVariable int studentID
    ){
        System.out.println(instructorID);
        return studentService.assignInstructor(instructorID,studentID);
    }

    @PutMapping("/enroll/{subjectID}/students/{studentID}")
    public Student enrollStudentSubject(
            @PathVariable int subjectID,
            @PathVariable int studentID
    ){
        return studentService.enrollStudent(subjectID,studentID);
    }

    @PutMapping("/contact/{contactID}/students/{studentID}")
    public Contact assignContactStudent(
            @PathVariable int contactID,
            @PathVariable int studentID
    ){
        return studentService.assignContact(contactID,studentID);
    }
}

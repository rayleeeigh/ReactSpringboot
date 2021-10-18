package com.example.server.controller;

import com.example.server.model.Instructor;
import com.example.server.model.Subject;
import com.example.server.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subject")
@CrossOrigin
public class SubjectController {
    @Autowired
    private SubjectService subjectService;


    @PostMapping("/add")
    public void addSubject(@RequestBody Subject subject){ //@RequestBody binds the HTTP request body to the handler method paramater "student"
        subjectService.saveSubject(subject);
    }

    @GetMapping("/view")
    public List<Subject> getAllSubjects(){
        return subjectService.getAllSubjects();
    }

    //this function will enroll the student to a subject.//
    @PutMapping("/enroll/{subjectID}/students/{studentID}")
    public Subject enrollStudentSubject(
            @PathVariable int subjectID,
            @PathVariable int studentID
    ){
        return subjectService.enrollStudent(subjectID,studentID);
    }

    //this function will find all subjects from a specific student//
    @GetMapping("/allSubjects/student/{studentID}")
    public List<Subject> getAllSubjectsFromStudent(@PathVariable int studentID){
        return subjectService.findAllSubjectFromStudent(studentID);
    }

    //this function will find all subjects that have not yet been enrolled by a student//
    @GetMapping("/allNotSubjects/student/{studentID}")
    public List<Subject> getAllNotSubjectsFromStudent(@PathVariable int studentID){
        return subjectService.findAllNotSubjectFromStudent(studentID);
    }

    @GetMapping("/viewSubject/{name}")
    public List<Subject> viewSubject(@RequestParam String name){
        List<Subject> subjects = subjectService.searchSubject(name);
        return subjects;
    }
}

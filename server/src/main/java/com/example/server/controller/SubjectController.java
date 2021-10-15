package com.example.server.controller;

import com.example.server.model.Subject;
import com.example.server.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subject")
@CrossOrigin
public class SubjectController {

    @PutMapping("/enroll/{subjectID}/students/{studentID}")
    public Subject enrollStudentSubject(
            @PathVariable int subjectID,
            @PathVariable int studentID
    ){
        return subjectService.enrollStudent(subjectID,studentID);
    }

    @GetMapping("/getAllSubjects/{studentID}")
    public List<Subject> getAllSubjectsStudent(@PathVariable int studentID){
        return subjectService.getEnrolledSubject(studentID);
    }
}

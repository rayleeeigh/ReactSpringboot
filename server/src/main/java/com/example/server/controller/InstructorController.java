package com.example.server.controller;

import com.example.server.exception.InstructorNotFoundException;
import com.example.server.model.*;
import com.example.server.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructor")
@CrossOrigin
public class InstructorController {
    @Autowired
    private InstructorService instructorService;

    @PostMapping("/add")
    public void addInstructor(@RequestBody Instructor instructor){
        instructorService.saveInstructor(instructor);
    }

    @GetMapping("/view")
    public List<Instructor> getAllInstructorsDB(){
        return instructorService.getAllInstructors();
    }

    @DeleteMapping("/delete/{instructorID}")
    public String deleteInstructorDB(@PathVariable Integer instructorID){
        return instructorService.deleteInstructor(instructorID);
    }

    @GetMapping("/view/{id}")
    public Instructor getInstructorById(@PathVariable("id") Integer id){
        Instructor instructor = instructorService.findById(id).orElseThrow(()->new InstructorNotFoundException("Instructor with "+id+" is not found!"));
        return instructor;
    }

    //this function will assign an existing student to an existing instructor.//
    @PutMapping("/assignInstructor/{instructorId}/student/{studentId}")
    public Instructor assignStudentToInstructor(@PathVariable Integer instructorId, @PathVariable Integer studentId){
        return instructorService.assignStudent(instructorId,studentId);
    }

    //this function will assign a newly created student to an instructor.//
    @PutMapping("/assignInstructor/{instructorId}")
    public Instructor assignStudentsToInstructor(@PathVariable Integer instructorId, @RequestBody Student student){
        return instructorService.assignCreatedStudent(instructorId,student);
    }

    //this function will remove the assigned instructor from the student//
    @PutMapping("/deleteInstructor/{instructorId}/student/{studentId}")
    public String deleteInstructor(@PathVariable Integer instructorId,@PathVariable Integer studentId){
        return instructorService.deleteInstructorFromStudents(instructorId,studentId);
    }
}

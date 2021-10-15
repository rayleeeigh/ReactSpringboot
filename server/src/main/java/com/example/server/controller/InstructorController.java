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
    public List<Instructor> getAllInstructors(){
        return instructorService.getAllInstructors();
    }

    @GetMapping("/view/{id}")
    public Instructor getInstructorById(@PathVariable("id") Integer id){
        Instructor instructor = instructorService.findById(id).orElseThrow(()->new InstructorNotFoundException("Instructor with "+id+" is not found!"));
        return instructor;
    }

    @PutMapping("/assignInstructor/{instructorId}/student/{studentId}")
    public Instructor assignStudentToInstructor(@PathVariable Integer instructorId, @PathVariable Integer studentId){
        return instructorService.assignStudent(instructorId,studentId);
    }

    @PutMapping("/assignInstructor/{instructorId}")
    public Instructor assignStudentsToInstructor(@PathVariable Integer instructorId, @RequestBody Student student){
        return instructorService.assignCreatedStudent(instructorId,student);
    }

    @DeleteMapping("/deleteInstructor/{instructorId}")
    public String deleteInstructor(@PathVariable Integer instructorId){
        return instructorService.deleteInstructor(instructorId);
    }

}

package com.example.server.controller;

import com.example.server.exception.InstructorNotFoundException;
import com.example.server.model.Instructor;
import com.example.server.service.InstructorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class InstructorController {
    @Autowired
    private InstructorService instructorService;
    @GetMapping(value = "/instructors")
    public List<Instructor> getAllInstructors(){
        return instructorService.getAllInstructors();
    }
    @PostMapping(value = "/instructors")
    public void saveInstructor(@RequestBody Instructor instructor){
        instructorService.saveInstructor(instructor);
    }

    @DeleteMapping(value = "/instructors/{id}")
    public void deleteInstructor(@PathVariable Integer id){
        instructorService.removeInstructorById(id);
    }
}

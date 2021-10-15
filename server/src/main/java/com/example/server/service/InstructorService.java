package com.example.server.service;

import com.example.server.model.Instructor;
import com.example.server.model.Student;

import java.util.List;
import java.util.Optional;

public interface InstructorService {
    public Instructor saveInstructor(Instructor instructor);
    public List<Instructor> getAllInstructors();
    Optional<Instructor> findById(Integer id);
    public Instructor assignStudent(Integer id, Integer studentId);
    public Instructor assignCreatedStudent(Integer id, Student student);
    public String deleteInstructor(Integer id);
}

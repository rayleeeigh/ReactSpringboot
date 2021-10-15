package com.example.server.service;

import com.example.server.model.Instructor;

import java.util.List;
import java.util.Optional;

public interface InstructorService {
    public List<Instructor>getAllInstructors();
    public void saveInstructor(Instructor instructor);
    Optional<Instructor> findByEmail(String email);
    Optional<Instructor> findById(Integer id);
    public void removeInstructorById(Integer id);
}

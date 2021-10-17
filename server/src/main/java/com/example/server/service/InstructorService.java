package com.example.server.service;

import com.example.server.model.Instructor;
import com.example.server.model.Student;

import java.util.List;
import java.util.Optional;

public interface InstructorService {
    public Instructor saveInstructor(Instructor instructor);
    public List<Instructor> getAllInstructors();
    Optional<Instructor> findById(Integer id);
    public String deleteInstructor(Integer instructorID);

    //this function will assign an existing student to an existing instructor.//
    public Instructor assignStudent(Integer id, Integer studentId);

    //this function will assign a newly created student to an instructor.//
    public Instructor assignCreatedStudent(Integer id, Student student);

    //this function will remove the assigned instructor from the student//
    public String deleteInstructorFromStudents(Integer instructorID, Integer studentID);
}

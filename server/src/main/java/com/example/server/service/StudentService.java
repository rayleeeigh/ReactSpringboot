package com.example.server.service;

import com.example.server.model.Student;
import com.example.server.model.Subject;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    public Student saveStudent(Student student);
    Optional<Student> findById(Integer id);
    Optional<Student> findByEmail(String email);
    public List<Student> getAllStudents();
    public void removeStudent(Integer id);
    public Student updateStudent(Integer id,Student student);
    public List<Student> searchStudent(String name);
    public Student addContact(Integer contactID,Integer studentID);
    public Student assignInstructor(Integer instructorID, Integer studentID);
    public Student assign(Integer instructorID,Student student);

    public Subject enrollStudent(Integer subjectID, Integer studentID);
}

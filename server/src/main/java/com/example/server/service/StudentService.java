package com.example.server.service;

import com.example.server.model.Student;


import java.util.List;
import java.util.Optional;

public interface StudentService {
    public void saveStudent(Student student);
    Optional<Student> findById(Integer id);
    Optional<Student> findByEmail(String email);
    public List<Student> getAllStudents();
    public void removeStudent(Integer id);
    public void updateStudent(Integer id,Student student);
}

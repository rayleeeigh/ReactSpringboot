package com.example.server.service;

import com.example.server.model.Student;

import java.util.List;

public interface StudentService {
    public Student saveStudent(Student student);
    public List<Student> getAllStudents();
    public void removeStudent(Integer id);
}

package com.example.server.service;

import com.example.server.model.Student;
import com.example.server.model.Subject;

import java.util.List;
import java.util.Optional;

public interface SubjectService {
    public Subject saveSubject(Subject subject);
    Optional<Subject> findById(Integer id);
    public List<Subject> getAllSubjects();
    public void removeSubject(Integer id);
    public Subject updateSubject(Integer id,Subject subject);
}

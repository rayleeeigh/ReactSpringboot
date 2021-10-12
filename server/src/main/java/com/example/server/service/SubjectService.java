package com.example.server.service;

import com.example.server.model.Student;
import com.example.server.model.Subject;

import java.util.List;

public interface SubjectService {
    public Subject saveSubject(Subject subject);
    public Subject findById(Integer id);
    public List<Subject> getAllSubjects();
    public void removeSubject(Integer id);
    public Subject updateSubject(Integer id,Subject subject);
    public Subject enrollStudent(Integer subjectID, Integer studentID);
}

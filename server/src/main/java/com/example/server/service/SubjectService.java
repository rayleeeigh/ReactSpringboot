package com.example.server.service;

import com.example.server.model.Subject;

import java.util.List;

public interface SubjectService {
    public List<Subject> getAllSubjects();
    public void saveSubject(Subject subject);
    public void removeSubject(Integer id);
}

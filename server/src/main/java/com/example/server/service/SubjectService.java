package com.example.server.service;

import com.example.server.model.Subject;

import java.util.List;

public interface SubjectService {
    public Subject saveSubject(Subject subject);
    public Subject findById(Integer id);
    public List<Subject> getAllSubjects();
    public void removeSubject(Integer id);
    public Subject updateSubject(Integer id,Subject subject);

    //this function will enroll the student to a subject.//
    public Subject enrollStudent(Integer subjectID, Integer studentID);

    //this function will find all subjects from a specific student//
    public List<Subject> findAllSubjectFromStudent(Integer studentID);
}

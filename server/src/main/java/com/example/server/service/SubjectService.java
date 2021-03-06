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
    public List<Subject> searchSubject(String name);

    //this function will enroll the student to a subject.//
    public Subject enrollStudent(Integer subjectID, Integer studentID);

    //this function will find all subjects from a specific student//
    public List<Subject> findAllSubjectFromStudent(Integer studentID);

    //this function will find all subjects that have not yet been enrolled by a student//
    public List<Subject> findAllNotSubjectFromStudent(Integer studentID);
}

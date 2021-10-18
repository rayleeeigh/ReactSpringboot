package com.example.server.service;

import com.example.server.model.Student;
import com.example.server.model.Subject;
import com.example.server.repository.StudentRepository;
import com.example.server.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService{
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Subject saveSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public Subject findById(Integer id) {
        return subjectRepository.findById(id).orElse(null);
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public void removeSubject(Integer id) {
        Subject subject = subjectRepository.findById(id).get();
        List<Student> students = studentRepository.findAll();
        for(Student stud:students){
            if(stud.getSubjects().contains(subject)){
                stud.getSubjects().remove(subject);
            }
        }
        subjectRepository.deleteById(id);
    }

    @Override
    public Subject updateSubject(Integer id, Subject subject) {
        return null;
    }

    @Override
    public Subject enrollStudent(Integer subjectID, Integer studentID){
        Subject subject = subjectRepository.findById(subjectID).get();
        Student student = studentRepository.findById(studentID).get();
        student.enrollStud(subject);
        return subjectRepository.save(subject);
    }

    @Override
    public List<Subject> findAllSubjectFromStudent(Integer studentID){
        return subjectRepository.getSubjects(studentID);
    }

    @Override
    public List<Subject> findAllNotSubjectFromStudent(Integer studentID){
        List<Subject> subjects=subjectRepository.findAll();
        List<Subject> subjects1 = new ArrayList<>();
        Student student = studentRepository.findById(studentID).get();
        for (Subject sub:subjects){
            if(!student.getSubjects().contains(sub)){
                subjects1.add(sub);
            }
        }
        return subjects1;
    }
}

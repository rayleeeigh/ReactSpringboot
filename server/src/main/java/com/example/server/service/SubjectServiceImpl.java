package com.example.server.service;

import com.example.server.model.Student;
import com.example.server.model.Subject;
import com.example.server.repository.StudentRepository;
import com.example.server.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
//        subject.enrollStudent(student);
        return subjectRepository.save(subject);
    }
}

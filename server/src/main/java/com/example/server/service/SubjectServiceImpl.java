package com.example.server.service;

import com.example.server.model.Subject;
import com.example.server.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService{
    @Autowired
    private SubjectRepository subjectRepository;


    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public void saveSubject(Subject subject) {
        Subject newSub = new Subject();
        newSub.setName(subject.getName());
        Subject savedSub = subjectRepository.save(newSub);
        if(subjectRepository.findById(savedSub.getId()).isPresent()){
            System.out.println("Successfully Created Subject!");
        }else{
            System.out.println("Failed to Create New Subject");
        }
    }

    @Override
    public void removeSubject(Integer id) {
        if(subjectRepository.findById(id).isPresent()){
            subjectRepository.deleteById(id);
            if(subjectRepository.findById(id).isPresent()){
                System.out.println("Failed to delete specific subject");
            }else{
                System.out.println("Successfully deleted subject!");
            }
        }else{
            System.out.println("No records found");
        }
    }

    @Override
    public Subject enrollStudent(Integer subjectID, Integer studentID){
        Subject subject = subjectRepository.findById(subjectID).get();
        Student student = studentRepository.findById(studentID).get();
        student.enrollStud(subject);
        return subjectRepository.save(subject);
    }

    @Override
    public List<Subject> getEnrolledSubject(Integer studentID){
        return subjectRepository.getSubjects(studentID);
    }
}

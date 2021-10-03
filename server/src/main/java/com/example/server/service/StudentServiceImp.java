package com.example.server.service;

import com.example.server.model.Student;
import com.example.server.repository.StudentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImp implements StudentService{

    @Autowired
    private StudentRepository studRepo;

    @Override
    public Student saveStudent(Student student){return studRepo.save(student);}

    @Override
    public List<Student> getAllStuds(){return studRepo.findAll();}
}

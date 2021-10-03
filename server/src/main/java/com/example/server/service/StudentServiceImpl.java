package com.example.server.service;

import com.example.server.model.Student;
import com.example.server.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void removeStudent(Integer id) {
        studentRepository.deleteById(id);
    }

    @Override
    public void updateStudent(Integer id,Student student){
        if(studentRepository.findById(id).isPresent()==true){
            studentRepository.save(student);
        }
    }
}

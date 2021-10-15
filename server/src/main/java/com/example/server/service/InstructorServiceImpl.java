package com.example.server.service;

import com.example.server.model.Instructor;
import com.example.server.model.Student;
import com.example.server.repository.InstructorRepository;
import com.example.server.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorServiceImpl implements InstructorService {
    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Instructor> getAllInstructors(){
       return instructorRepository.findAll();
   }

    @Override
    public Optional<Instructor> findByEmail(String email){
        return instructorRepository.findByEmail(email);
    }

    @Override
    public Optional<Instructor> findById(Integer id){
        return instructorRepository.findById(id);
    }

    @Override
    public Instructor assignStudent(Integer id, Integer studentId){
        Instructor instructor=instructorRepository.findById(id).get();
        Student student = studentRepository.findById(studentId).get();
        student.setInstructorId(id);
        studentRepository.save(student);
        instructor.getStudents().add(student);
        instructorRepository.save(instructor);
        return instructor;
    }

    @Override
    public Instructor assignCreatedStudent(Integer id, Student student){
        Instructor instructor=instructorRepository.findById(id).get();
        student.setInstructorId(id);
        studentRepository.save(student);
        instructor.getStudents().add(student);
        instructorRepository.save(instructor);
        return instructor;
    }

    @Override
    public String deleteInstructor(Integer id){
        List<Student> students = studentRepository.findInstructor(id);
        for(Student studs:students){
            studs.setInstructorId(null);
            studentRepository.save(studs);
        }
        instructorRepository.deleteById(id);

        return "Success";
    }
}

package com.example.server.service;

import com.example.server.model.Contact;
import com.example.server.model.Instructor;
import com.example.server.model.Student;
import com.example.server.repository.ContactRepository;
import com.example.server.repository.InstructorRepository;
import com.example.server.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@NoArgsConstructor
@Service
public class InstructorServiceImpl implements InstructorService{
    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Instructor saveInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    @Override
    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    @Override
    public Optional<Instructor> findById(Integer id){
        return instructorRepository.findById(id);
    }

    @Override
    public String deleteInstructor(Integer instructorID){
        Instructor instructor = instructorRepository.findById(instructorID).get();
        instructor.setStudents(null);
        instructorRepository.save(instructor);
        instructorRepository.deleteById(instructorID);
        return "Success";
    }

    @Override
    public Instructor assignStudent(Integer id, Integer studentId){
        Instructor instructor=instructorRepository.findById(id).get();
        Student student = studentRepository.findById(studentId).get();
        instructor.getStudents().add(student);
        instructorRepository.save(instructor);
        return instructor;
    }

    @Override
    public Instructor assignCreatedStudent(Integer id, Student student){
        Instructor instructor=instructorRepository.findById(id).get();
        instructor.getStudents().add(student);
        instructorRepository.save(instructor);
        return instructor;
    }

    @Override
    public String deleteInstructorFromStudents(Integer instructorID, Integer studentID){
        Instructor instructor = instructorRepository.findById(instructorID).get();
        Student student = studentRepository.findById(studentID).get();

        instructor.removeStudentFromSubject(student);
        instructorRepository.save(instructor);
        return "Success";
    }

}

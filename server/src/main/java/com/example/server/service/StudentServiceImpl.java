package com.example.server.service;

import com.example.server.model.Student;
import com.example.server.repository.ContactRepository;
import com.example.server.repository.StudentRepository;
import com.example.server.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service //specifies the  intent that the annotated class is a business class.
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Override
    public void saveStudent(Student student) {
        Student stud = new Student();
        stud.setFirstName(student.getFirstName());
        stud.setLastName(student.getLastName());
        stud.setEmail(student.getEmail());
        stud.setCourse(student.getCourse());
        stud.setYear(student.getYear());
        stud.setContact(student.getContact());
        stud.setSubjects(student.getSubjects());

        Student savedStudent = studentRepository.save(stud);
        if(studentRepository.findById(savedStudent.getId()).isPresent()){
            System.out.println("Successfully created student");
        }else{
            System.out.println("Failed to create new student record");
        }
    }

    @Override
    public Optional<Student> findById(Integer id){
        return studentRepository.findById(id);
    }

    @Override
    public Optional<Student> findByEmail(String email){
        return studentRepository.findByEmail(email);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void removeStudent(Integer id) {
        if(studentRepository.findById(id).isPresent()){
            studentRepository.deleteById(id);
            if(studentRepository.findById(id).isPresent()){
                System.out.println("Failed to delete student");
            }else{
                System.out.println("Successfully deleted student!");
            }
        }else{
            System.out.println("No records found");
        }
    }

    @Override
    public void updateStudent(Integer id,Student student){
        if(studentRepository.findById(id).isPresent()){
            Student stud = studentRepository.findById(id).get();
            stud.setFirstName(student.getFirstName());
            stud.setLastName(student.getLastName());
            stud.setEmail(student.getEmail());
            stud.setCourse(student.getCourse());
            stud.setYear(student.getYear());
            stud.setSubjects(student.getSubjects());

            contactRepository.deleteById(student.getContact().getId());
            stud.setContact(student.getContact());
            Student savedStudent = studentRepository.save(stud);
            if(studentRepository.findById(savedStudent.getId()).isPresent()){
                System.out.println("Successfully Updated Student!");
            }else{
                System.out.println("Failed to Update Student!");
            }
        }else{
            System.out.println("No records found");
        }
    }
}

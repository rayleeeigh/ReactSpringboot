package com.example.server.service;

import com.example.server.model.*;
import com.example.server.repository.ContactRepository;
import com.example.server.repository.InstructorRepository;
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
    private InstructorRepository instructorRepository;

    @Autowired
    private SubjectRepository subjectRepository;


    private int flag=0;

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Optional<Student> findById(Integer id){
        return studentRepository.findById(id);
    }

    @Override
    public Optional<Student> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void removeStudent(Integer id) {
//        studentRepository.deleteStudentSubject(id);
//        Student student = studentRepository.findById(id).get();
        studentRepository.deleteById(id);
    }

    @Override
    public Student updateStudent(Integer id,Student student){
        System.out.println(student);
        Student oldStud = studentRepository.findById(id).orElse(student);
        oldStud.setFirstName(student.getFirstName());
        oldStud.setLastName(student.getLastName());
        oldStud.setEmail(student.getEmail());
        oldStud.setCourse(student.getCourse());
        oldStud.setYear(student.getYear());
        studentRepository.save(oldStud);
        return oldStud;
    }

    @Override
    public List<Student> searchStudent(String name){
        return studentRepository.searchStudents(name);
    }

    @Override
    public Student addContact(Integer contactID,Integer studentID){
        Student student = studentRepository.findById(studentID).get();
        Contact contact = contactRepository.findById(contactID).get();
        student.addContactToStudent(contact);
        contact.setStudentId(studentID);
        studentRepository.save(student);
        contactRepository.save(contact);
        return student;
    }

    @Override
    public Student assignInstructor(Integer instructorID, Integer studentID){
        Student student = studentRepository.findById(studentID).get();
        Instructor instructor = instructorRepository.findById(instructorID).get();
        student.setInstructorId(instructorID);
        instructor.getStudents().add(student);
        studentRepository.save(student);

        return student;
    }



    @Override
    public Student enrollStudent(Integer subjectID, Integer studentID){
        Subject subject = subjectRepository.findById(subjectID).get();
        Student student = studentRepository.findById(studentID).get();
        student.enrollStud(subject);
        return studentRepository.save(student);
    }

    @Override
    public Contact assignContact(Integer contactID, Integer studentID){
        Student student = studentRepository.findById(studentID).get();
        Contact contact = contactRepository.findById(contactID).get();

        List<Student> studs= studentRepository.findAll();
        for(Student st : studs){
            if(st.getContact()==null){
                flag=0;
            }else if(st.getContact().getId() == contactID){
                flag=1;
                break;
            }else{
                flag=0;
            }
        }

        if(flag==0){
            student.setContact(contact);
            contact.setStudentId(studentID);
            contactRepository.save(contact);
            studentRepository.save(student);
        }

        return contact;
    }

    @Override
    public String deleteInstructor(Integer studentId){
        return "Success";
    }

}

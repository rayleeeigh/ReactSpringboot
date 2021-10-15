package com.example.server.service;

import com.example.server.model.*;
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


    private int flag=0;

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
<<<<<<< HEAD
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
=======
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
>>>>>>> c63429b53c64e27a3d9dda7270411d0bc45ffc5a
            }else{
                System.out.println("Failed to Update Student!");
            }
        }else{
            System.out.println("No records found");
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

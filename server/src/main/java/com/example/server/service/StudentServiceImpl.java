package com.example.server.service;

import com.example.server.model.Contact;
import com.example.server.model.Instructor;
import com.example.server.model.Student;
import com.example.server.model.Subject;
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

    private boolean Contactflag;

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
        return studentRepository.getAllUsers();
    }

    @Override
    public void removeStudent(Integer id) {
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
//        student.setInstructorId(instructorID);
        instructor.getStudents().add(student);
//        studentRepository.save(student);
        instructorRepository.save(instructor);
        return student;
    }



    @Override
    public Student enrollStudent(Integer subjectID, Integer studentID){
        Subject subject = subjectRepository.findById(subjectID).get();
        Student student = studentRepository.findById(studentID).get();
        student.enrollStud(subject);
//        subject.enrollStudent(student);
        return studentRepository.save(student);
    }

    @Override
    public Contact assignContact(Integer contactID, Integer studentID){
        Student student = studentRepository.findById(studentID).get();
        Contact contact = contactRepository.findById(contactID).get();

        List<Student> studs= studentRepository.findAll();
        for(Student st : studs){
            if(st.getContact()==null){
                Contactflag=false;
            }else if(st.getContact().getId() == contactID){
                Contactflag=true;
                break;
            }else{
                Contactflag=false;
            }
        }
        if(Contactflag==false){
            student.setContact(contact);
            contact.setStudentId(studentID);
            contactRepository.save(contact);
            studentRepository.save(student);
        }

        return contact;
    }

    @Override
    public List<Student> getAllStudentsFromSubject(Integer subjectID){
        return studentRepository.getStudent(subjectID);
    }

    @Override
    public String removeSubjectFromStuds(Integer subjectID,Integer studentID){
        Subject subject = subjectRepository.findById(subjectID).get();
        Student student = studentRepository.findById(studentID).get();

        student.getSubjects().remove(subject);
        studentRepository.save(student);
        return "Success";

    }

    @Override
    public String removeContactFromStudent(Integer contactID, Integer studentID){
        Contact contact = contactRepository.findById(contactID).get();
        Student student = studentRepository.findById(studentID).get();

        student.setContact(null);
        contact.setStudentId(null);
        studentRepository.save(student);
        contactRepository.save(contact);

        return "Success";
    }
}

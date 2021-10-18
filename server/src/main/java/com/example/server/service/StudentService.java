package com.example.server.service;

import com.example.server.model.Contact;
import com.example.server.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    public Student saveStudent(Student student);
    Optional<Student> findById(Integer id);
    Optional<Student> findByEmail(String email);
    public List<Student> getAllStudents();
    public void removeStudent(Integer id);
    public Student updateStudent(Integer id,Student student);
    public List<Student> searchStudent(String name);
    public Student addContact(Integer contactID,Integer studentID);
    public Student assignInstructor(Integer instructorID, Integer studentID);
    public Student enrollStudent(Integer subjectID, Integer studentID);

    //this function will assign an existing contact for a student//
    Contact assignContact(Integer contactID, Integer studentID);

    //this function will get all the students from a specific subject//
    public List<Student> getAllStudentsFromSubject(Integer subjectID);

    //this function will remove a subject from the student's list of subjects//
    public String removeSubjectFromStuds(Integer subjectID, Integer studentID);

    //this function will remove a contact from a student//
    public String removeContactFromStudent(Integer contactID, Integer studentID);

    //USING ENTITY MANAGER
    public List<Student> getAllStudentByEntityManager( );

    //CRITERIA API QUERY
    public Student getAllStudentUsingCriteria(Integer studentID);

    //DERIVED QUERY METHOD
    public List<Student> findStudentByName(String name );
}

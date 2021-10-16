package com.example.server.service;

import com.example.server.model.Contact;
import com.example.server.model.Student;
import com.example.server.repository.ContactRepository;
import com.example.server.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService{
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Contact saveContact(Contact contact){
        return contactRepository.save(contact);
    }

    @Override
    public List<Contact> getContact(){
        return contactRepository.findAll();
    }

    @Override
    public String deleteContact(Integer contactID){
        Contact contact = contactRepository.findById(contactID).get();
        if(contact.getStudentId() != null){
            Student student = studentRepository.findById(contact.getStudentId()).get();
            student.setContact(null);
            studentRepository.save(student);
        }
        contactRepository.deleteById(contactID);
        return "Successfully Deleted";
    }

    @Override
    public Contact updateContact(Contact contact){
        Contact contact1=contactRepository.findById(contact.getId()).orElse(null);
        contact1.setContact(contact.getFirstname(),contact.getFirstname(),contact.getNumber(),contact.getAddress());
        contactRepository.save(contact1);
        return contact1;
    }

    @Override
    public Contact getContactStudent(Integer studentId){
        return contactRepository.getStudentContact(studentId);
    }
}

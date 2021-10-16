package com.example.server.service;

import com.example.server.model.Contact;

import java.util.List;


public interface ContactService {
     public Contact saveContact(Contact contact);
     public List<Contact> getContact();
     public String deleteContact(Integer contactID);
     public Contact updateContact(Contact contact);

     // This Function would get all the contacts from a specific student id//
     public Contact getContactStudent(Integer studentId);
}

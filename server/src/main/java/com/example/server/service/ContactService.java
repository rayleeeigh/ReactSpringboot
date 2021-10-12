package com.example.server.service;

import com.example.server.model.Contact;

import java.util.List;

public interface ContactService {
     Contact saveContact(Contact contact);
     List<Contact> getContact();
     String deleteContact(Integer contactID);
     Contact updateContact(Contact contact);
}

package com.example.server.service;

import com.example.server.model.Contact;
import com.example.server.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ContactServiceImpl implements ContactService{
    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Contact saveContact(Contact contact){return contactRepository.save(contact);}
    @Override
    public List<Contact> getContact(){
        return contactRepository.findAll();
    }
    @Override
    public String deleteContact(Integer contactID){
        contactRepository.deleteById(contactID);
        return "Successfully Deleted";
    }
    @Override
    public Contact updateContact(Contact contact){
        Contact contact1=contactRepository.findById(contact.getContact_id()).orElse(null);
        contact1.setContact(contact.getContact_guardian_fname(),contact.getContact_guardian_lname(),contact.getContact_number(),contact.getContact_address_city());
        contactRepository.save(contact1);
        return contact1;
    }
}

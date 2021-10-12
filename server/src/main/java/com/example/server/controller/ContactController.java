package com.example.server.controller;

import com.example.server.model.*;
import com.example.server.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/contact")
@CrossOrigin
public class ContactController {
    @Autowired
    private ContactService contactService;

    @GetMapping("/view")
    List<Contact> getContacts(){return contactService.getContact();}

    @PostMapping("/add")
    Contact createContact(@RequestBody Contact contact){
        return contactService.saveContact(contact);
    }

    @DeleteMapping("/delete/{contactID}")
    public String deleteContacts(@PathVariable Integer contactID){
        return contactService.deleteContact(contactID);
    }

    @PutMapping("/update")
    public Contact updateContacts(@RequestBody Contact contact){
        return contactService.updateContact(contact);
    }


}

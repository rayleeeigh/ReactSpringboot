package com.example.server.repository;

import com.example.server.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.server.model.Contact;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Integer> {

}

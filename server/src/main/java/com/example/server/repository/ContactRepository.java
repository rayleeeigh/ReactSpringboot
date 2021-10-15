package com.example.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.server.model.Contact;


@Repository
public interface ContactRepository extends JpaRepository<Contact,Integer> {

}

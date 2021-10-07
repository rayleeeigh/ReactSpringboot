package com.example.server.repository;

import com.example.server.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository //indicates that the underlying interface is a repository and tells Spring to register it as a bean in the context during component scan.
public interface StudentRepository extends JpaRepository<Student,Integer>{
    //Query Method
    Optional<Student> findByEmail(String email); // generate a SELECT query that will retrieve a Student from the database based on the given email.
}

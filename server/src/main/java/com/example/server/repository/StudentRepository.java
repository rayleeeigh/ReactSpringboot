package com.example.server.repository;

import com.example.server.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    //Query Method
    Optional<Student> findByEmail(String email);
}

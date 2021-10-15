package com.example.server.repository;

import com.example.server.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor,Integer> {
    Optional<Instructor>findByEmail(String email);
}

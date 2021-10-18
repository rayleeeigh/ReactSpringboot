package com.example.server.repository;

import com.example.server.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InstructorRepository extends JpaRepository<Instructor,Integer> {
    @Query("" +
            "SELECT CASE WHEN COUNT(i) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM Instructor i " +
            "WHERE i.email = ?1"
    )
    Boolean selectExistsEmail(String email);

    @Query("Select p from Instructor p where p.firstName like %?1%")
    List<Instructor> searchInstructors(String name);
}

package com.example.server.repository;

import com.example.server.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    @Query("Select p from Student p where p.firstName like %?1%")
    List<Student> searchStudents(String name);

    @Query("SELECT u FROM Student u ")
    List<Student> getAllUsers();

    @Query(
            value = "SELECT * FROM Student u INNER JOIN student_enrolled s ON u.id=s.student_id",
            nativeQuery = true
    )
    Student getStudent();

    @Query("SELECT p FROM Student p WHERE p.firstName LIKE %?1%")
    List<Student> search(String keyword);
}

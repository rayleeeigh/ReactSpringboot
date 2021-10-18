package com.example.server.repository;

import com.example.server.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    //DERIVED QUERY
    List<Student> findByFirstName(String firstName);

    @Query("Select p from Student p where p.firstName like %?1%")
    List<Student> searchStudents(String name);

    @Query("SELECT u FROM Student u ")
    List<Student> getAllUsers();

    @Query(value = "SELECT * FROM Student u INNER JOIN student_enrolled s ON u.student_id=s.student_id AND s.subject_id=:subjectID", nativeQuery = true)
    List<Student> getStudent(int subjectID);

    @Query("SELECT p FROM Student p WHERE p.firstName LIKE %?1%")
    List<Student> search(String keyword);

    @Query(value = "SELECT * FROM Student s INNER JOIN instructor_students u ON u.students_student_id=s.student_id AND u.instructor_id=:instructorId", nativeQuery = true)
    List<Student> getInstructorStudent(int instructorId);

//    List<Student> findByNameEquals(String Name);
}

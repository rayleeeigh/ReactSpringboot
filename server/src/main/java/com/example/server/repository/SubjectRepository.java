package com.example.server.repository;

import com.example.server.model.Instructor;
import com.example.server.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject,Integer> {
    @Query(value = "SELECT * FROM Subject u INNER JOIN student_enrolled s ON u.subject_id=s.subject_id AND s.student_id=:studentId ", nativeQuery = true)
    List<Subject> getSubjects(int studentId);

    @Query(value = "SELECT * FROM Subject u FULL OUTER JOIN student_enrolled s ON u.subject_id=s.subject_id AND s.student_id=:studentId ", nativeQuery = true)
    List<Subject> getNotSubjects(int studentId);

    @Query("Select p from Subject p where p.name like %?1%")
    List<Subject> searchSubjects(String name);
}

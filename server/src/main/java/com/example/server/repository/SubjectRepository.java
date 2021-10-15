package com.example.server.repository;

import com.example.server.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject,Integer> {
    @Query(
            value = "SELECT * FROM Subject u INNER JOIN student_enrolled s ON u.subject_id=s.subject_id AND s.student_id=:studentId ",
            nativeQuery = true
    )
    List<Subject> getSubjects(int studentId);


}

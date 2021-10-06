package com.example.server.crudtests;

import com.example.server.repository.StudentRepository;
import com.example.server.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
public class studentViewTest {

    @Autowired
    private StudentRepository studentRepository;
    private List<Student> studentList;
    private int x=0;
    @Test
    void viewAllStudentTest(){
        studentList.addAll(studentRepository.findAll());
    }
}

package com.example.server.crudtests;

import com.example.server.repository.StudentRepository;
import com.example.server.model.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.JUnitException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class studentCreationTest {
    @Autowired
    private StudentRepository studentRepository;


    @Test
     void saveStudentTest() throws JUnitException {
//        System.out.println("successfully inserted");
//        Student student = new Student();
//        student.setName("Arnan");
//        student.setYear(2);
//        student.setCourse("cs");
//        student.setEmail("arnani@yh.com");
//        studentRepository.save(student);
//        System.out.println(student.getId());
    }

    @BeforeEach
    void sayHi(){
        System.out.println("Hi");
    }
}

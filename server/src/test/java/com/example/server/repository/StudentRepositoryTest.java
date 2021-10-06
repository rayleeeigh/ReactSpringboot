package com.example.server.repository;
import com.example.server.model.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    //JUnit testing for  saveStudent
    @Test
    public void saveStudentTest(){
        System.out.println("HI");
        Student student = Student.builder()
                .name("Andrew")
                .email("andrew@email.com")
                .course("BSCS")
                .year(4)
                .build();
        studentRepository.save(student);

        //validate whether the student was added or not
        //Assertions.assertThat(student.getId()).isGreaterThan(0);
    }

}
package com.example.server.repository;

import com.example.server.model.Instructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class InstructorRepositoryTest {
    @Autowired
    private InstructorRepository instructorRepository;

    @Test
    void selectExistsEmail() {
        String email = "cm@gmail.com";
        Instructor instructor = new Instructor();
        instructor.setFirstName("Cris");
        instructor.setLastName("Militante");
        instructor.setEmail(email);

        instructorRepository.save(instructor);

        boolean exists =  instructorRepository.selectExistsEmail(email);

        assertThat(exists).isTrue();
    }
}
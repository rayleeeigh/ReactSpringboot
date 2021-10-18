package com.example.server.service;

import com.example.server.model.Instructor;
import org.junit.Assert;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
@RunWith(MockitoJUnitRunner.class)
class InstructorServiceImplTest {
    @Mock
    InstructorService instructorService;
    @InjectMocks
    private InstructorService test = new InstructorServiceImpl();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void ParseInstructorRecord(){
        String input = "Cris%#Militante%#cm@gmail.com";
        String delimiter = "%#";
        String[] token = input.split(delimiter);
        Instructor instructor = new Instructor();

        instructor.setFirstName(token[0]);
        instructor.setLastName(token[1]);
        instructor.setEmail(token[2]);

        Assert.assertEquals("Cris", instructor.getFirstName());
        Assert.assertEquals("Militante",instructor.getLastName());
        Assert.assertEquals("cm@gmail.com",instructor.getEmail());
    }
}
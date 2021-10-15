package com.example.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Instructor")
public class Instructor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "InstructorFirstName", nullable = false, length = 64)
    private String firstName;
    @Column(name = "InstructorLastName",nullable = false, length = 32)
    private String lastName;
    @Column(name = "InstructorEmail" , nullable = false, length = 255)
    private String email;
    @OneToMany(targetEntity = Student.class)
    private Set<Student> students;

    public Instructor(String firstName, String lastName, String email, Set<Student> students) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.students = students;
    }
}

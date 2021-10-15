package com.example.server.model;

import lombok.AllArgsConstructor;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Student")
public class Student implements Serializable {
    @Id
    @GeneratedValue( strategy =  GenerationType.IDENTITY)
    private int id;
    @Column(name="studentFirstName", nullable = false, length = 64)
    private String firstName;
    @Column(name = "studentLastName", nullable = false, length = 32)
    private String lastName;
    @Column(name = "studentEmail",nullable = false, length = 255)
    private String email;
    @Column(name = "studentCourse",nullable = false, length = 32)
    private String course;
    @Column(name = "studentYear",nullable = false,length = 1)
    private int year;
    @OneToOne(targetEntity = Contact.class, cascade = CascadeType.ALL)
    private Contact contact;
    @ManyToMany(targetEntity = Subject.class, cascade = CascadeType.ALL)
    private Set<Subject> subjects;

    public Student(String firstName, String lastName, String email, String course, int year, Contact contact, Set<Subject> subject) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.course = course;
        this.year = year;
        this.contact = contact;
        this.subjects = subject;
    }
}

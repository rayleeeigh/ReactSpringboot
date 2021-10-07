package com.example.server.model;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data //used to generate setters and getters
@AllArgsConstructor // used to generate all-arguments constructor
@NoArgsConstructor // used to generate a default no args constructor
@Entity //used to indicate that the annotated class is an entity class.
@Table(name = "Student") // used to specify more details about the table associated to the class.
public class Student {
    @Id //bind the annotated field to the primary key column
    @GeneratedValue( strategy =  GenerationType.IDENTITY) // defines the primary key generate.IDENTITY means the values of the primary key will be generated using auto increment.
    private int id;
    @Column(name="firstName", nullable = false, length = 64)
    private String firstName;
    @Column(name = "lastName", nullable = false, length = 32)
    private String lastName;
    @Column(name = "email",nullable = false, length = 255)
    private String email;
    @Column(name = "course",nullable = false, length = 32)
    private String course;
    @Column(name = "year",nullable = false,length = 1)
    private int year;

}

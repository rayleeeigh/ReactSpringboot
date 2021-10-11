package com.example.server.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Table(name = "Student") // used to specify more details about the table associated to the class.

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
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
    @ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="instructor_id",referencedColumnName = "instructor_id")
    @JsonIgnoreProperties("Student")
    private Instructor instructor;
}

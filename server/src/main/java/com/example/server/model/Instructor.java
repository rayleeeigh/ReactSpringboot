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
    @GeneratedValue( strategy =  GenerationType.IDENTITY)
    private int id;

    @OneToMany( targetEntity = Student.class,cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Student>students;


    @Column(name="instructorFirstname", nullable = false, length = 64)
    private String firstName;
    @Column(name="instructorLastname", nullable = false, length = 64)
    private String lastName;
    @Column(name="instructorEmail", nullable = false, length = 64)
    private String email;

}

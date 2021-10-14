package com.example.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import com.example.server.model.*;


import javax.persistence.*;
import java.util.List;

@Table(name = "instructor")

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Instructor {
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

package com.example.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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

    @OneToMany( targetEntity = Student.class,cascade = CascadeType.ALL , orphanRemoval = false)
    private List<Student> students;


    @Column(name="instructorFirstname", nullable = false, length = 64)
    private String firstName;
    @Column(name="instructorLastname", nullable = false, length = 64)
    private String lastName;
    @Column(name="instructorEmail", nullable = false, length = 64)
    private String email;

    public void removeStudentFromSubject(Student student){
        students.remove(student);
    }
}

package com.example.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "subject")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Subject")
public class Subject {
    @Id
    @GeneratedValue( strategy =  GenerationType.IDENTITY)
    @Column(name = "subjectId")
    private int id;
    @Column(name="subjectName", nullable = false, length = 64)
    private String name;

}

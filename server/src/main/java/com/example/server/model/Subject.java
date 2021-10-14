package com.example.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "subject")

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Subject {
    @Id
    @GeneratedValue( strategy =  GenerationType.IDENTITY)
    @Column(name = "subjectId")
    private int id;
    @Column(name="subjectName", nullable = false, length = 64)
    private String name;



}

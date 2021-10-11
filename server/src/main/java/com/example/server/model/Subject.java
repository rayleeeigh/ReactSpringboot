package com.example.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "subject")

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Subject {
    @Id
    @GeneratedValue( strategy =  GenerationType.IDENTITY)
    private int subject_id;
    @Column(name="subject_name", nullable = false, length = 64)
    private String subject_name;

}

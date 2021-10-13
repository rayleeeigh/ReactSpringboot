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
    private int instructor_id;
    @OneToMany(fetch=FetchType.EAGER, mappedBy = "instructor",cascade = CascadeType.ALL)
    @JsonIgnoreProperties("instructor")
    private List<Student>students;


    @Column(name="instructor_first_name", nullable = false, length = 64)
    private String instructor_first_name;
    @Column(name="instructor_last_name", nullable = false, length = 64)
    private String instructor_last_name;
    @Column(name="instructor_email", nullable = false, length = 64)
    private String instructor_email;

}

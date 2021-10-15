package com.example.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "Student") // used to specify more details about the table associated to the class.

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {
    @Id //bind the annotated field to the primary key column
    @GeneratedValue( strategy =  GenerationType.IDENTITY) // defines the primary key generate.IDENTITY means the values of the primary key will be generated using auto increment.
    @Column(name = "studentId")
    private int id;
    @Column(name="firstName", nullable = false, length = 64)
    private String firstName;
    @Column(name = "lastName", nullable = false, length = 32)
    private String lastName;
    @Column(name = "email",nullable = false)
    private String email;
    @Column(name = "course",nullable = false, length = 32)
    private String course;
    @Column(name = "year",nullable = false,length = 1)
    private int year;
    @Column( name = "instructorId" )
    private Integer instructorId;

    @JsonIgnore
    @OneToOne(targetEntity = Contact.class,cascade = CascadeType.ALL)
    @JoinColumn(name="contact_id")
    private Contact contact;

    @JsonIgnore
    @ManyToMany(targetEntity = Subject.class,cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(name = "student_enrolled", joinColumns = @JoinColumn(name = "studentId"),
            inverseJoinColumns = @JoinColumn(name="subjectId") )
    private Set<Subject> subjects = new HashSet<>();

    public void setStudent(String fname,String lname,String course,int year,String email){
        this.firstName=fname;
        this.lastName=lname;
        this.course=course;
        this.year=year;
        this.email=email;
    }


    public void addContactToStudent(Contact contact){this.contact=contact;}

    public int getInstructorId(){
        return this.instructorId;
    }

    public void setInstructorId(Integer InstructorId){
        this.instructorId = InstructorId;
    }

    public void enrollStud(Subject subject){
        this.subjects.add(subject);
    }
}

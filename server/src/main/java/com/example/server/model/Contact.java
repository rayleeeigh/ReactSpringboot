package com.example.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Contact")
public class Contact implements Serializable {
    @Id
    @GeneratedValue( strategy =  GenerationType.IDENTITY)
    private int id;


    @Column(name="guardianFirstname", nullable = false, length = 64)
    private String firstname;

    @Column(name="guardianLastname", nullable = false, length = 64)
    private String lastName;

    @Column(name="contactNumber", nullable = false, length = 64)
    private String number;

    @Column(name="addressCity", nullable = false, length = 64)
    private String address;

    @Column(name = "studentId" , nullable = true)
    private Integer studentId;

    public void setContact(String fname,String lname,String number, String adress){
        this.firstname=fname;
        this.lastName=lname;
        this.number=number;
        this.address=adress;
    }

}

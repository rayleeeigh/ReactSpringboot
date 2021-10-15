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
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column( name = "contactID")
    private int id;
    @Column( name = "contactFirstName")
    private String firstName;
    @Column( name= "contactLastName")
    private String lastName;
    @Column( name = "contactPhone")
    private String phone;
    @Column( name = "contactCity")
    private String city;

    public Contact(String firstName, String lastName, String phone, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.city = city;
    }
}

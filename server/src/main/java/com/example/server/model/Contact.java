package com.example.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Table(name = "contact")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Contact {
    @Id
    @GeneratedValue( strategy =  GenerationType.IDENTITY)
    private int contact_id;

    @JsonIgnore
    @OneToOne(mappedBy = "contact")
    private Student student;

    @Column(name="contact_guardian_fname", nullable = false, length = 64)
    private String contact_guardian_fname;

    @Column(name="contact_guardian_lname", nullable = false, length = 64)
    private String contact_guardian_lname;

    @Column(name="contact_number", nullable = false, length = 64)
    private String contact_number;

    @Column(name="contact_address_city", nullable = false, length = 64)
    private String contact_address_city;

    public void setContact(String fname,String lname,String number, String adress){
        this.contact_guardian_fname=fname;
        this.contact_guardian_lname=lname;
        this.contact_number=number;
        this.contact_address_city=adress;
    }

    public void setStudent(Student student){
        this.student=student;
    }
}

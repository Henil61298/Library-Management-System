package com.example.Libmansys.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity //This is the schema of how Student Table will look like
@Table(name = "student_info")
@Getter
@Setter
@NoArgsConstructor //This is like a default constructor
@AllArgsConstructor //This is like a constructor having all the arguments
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rollNo;

    @Column(nullable = false)
    private String name;

    private String branch;

    @Column(nullable = false)
    private Double cgpa;

    @Column(unique = true)
    private String emailId;
}

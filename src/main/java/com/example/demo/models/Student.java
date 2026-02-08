package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private  String name;

    @Column(nullable = false, unique = true)
    private  String email;

    @OneToMany(mappedBy = "student")
    private List<StudentEnrollment> enrollments;
}

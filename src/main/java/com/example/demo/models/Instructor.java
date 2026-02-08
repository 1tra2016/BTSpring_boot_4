package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "instructors")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "instructor_name", length = 100, nullable = false)
    private String instructorname;

    @Column(unique=true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "instructor")
    private List<Course> courses;

}

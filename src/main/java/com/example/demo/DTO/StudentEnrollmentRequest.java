package com.example.demo.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentEnrollmentRequest {
    private long courseId;
    private long studentId;
}

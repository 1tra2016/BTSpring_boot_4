package com.example.demo.services;

import com.example.demo.enums.CourseStatus;
import com.example.demo.models.Instructor;
import com.example.demo.models.Student;
import com.example.demo.repositories.EnrollmentRepository;
import com.example.demo.repositories.StudentRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService (StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student>  getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long studentId){
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found id: " + studentId));
    }

    public Student createStudent(Student student){
        return studentRepository.save(student);
    }

    public Student findStudentById(Long id){
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found id: " + id));
    }
    public Student updateStudent(Long id, Student newStudent){
        Student student = findStudentById(id);
        student.setName(newStudent.getName());
        student.setEmail(newStudent.getEmail());
        student.setEnrollments(newStudent.getEnrollments());

        return studentRepository.save(student);
    }

    public void deleteStudentById(Long studentId){
        studentRepository.deleteById(studentId);
    }

}

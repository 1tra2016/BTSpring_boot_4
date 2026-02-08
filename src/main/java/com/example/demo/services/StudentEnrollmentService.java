package com.example.demo.services;

import com.example.demo.models.Course;
import com.example.demo.models.Instructor;
import com.example.demo.models.Student;
import com.example.demo.models.StudentEnrollment;
import com.example.demo.repositories.CourseRepository;
import com.example.demo.repositories.EnrollmentRepository;
import com.example.demo.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentEnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final  StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public StudentEnrollmentService(
            EnrollmentRepository enrollmentRepository,
            StudentRepository studentRepository,
            CourseRepository courseRepository
    ) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public List<StudentEnrollment> getAllEnrollments(){
        List<StudentEnrollment> studentEnrollments = enrollmentRepository.findAll();
        return studentEnrollments;
    }

//    public Enrollment findById(Long id){
//        return enrollmentRepository.findById(id).orElse(null);
//    }

    public StudentEnrollment getEnrollmentById(Long id){
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found id: " + id));
    }

    public StudentEnrollment createEnrollment(StudentEnrollment studentEnrollment){
        return enrollmentRepository.save(studentEnrollment);
    }
    public StudentEnrollment updateEnrollment(Long id, StudentEnrollment studentEnrollment){
        StudentEnrollment old = enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found id: " + id));

        Student student = studentRepository.findById(studentEnrollment.getStudent().getId()).orElseThrow(null);
        if ((student) == null) throw new  RuntimeException("Student not found id: " + student.getId());
        old.setStudent(student);
        Course course = courseRepository.findById(studentEnrollment.getStudent().getId()).orElseThrow(null);
        if ((course) == null) throw new  RuntimeException("Student not found id: " + course.getId());
        old.setCourse(course);

        return enrollmentRepository.save(old);
    }
    public boolean deleteEnrollmentById(Long id){
        StudentEnrollment studentEnrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found id: " + id));
        enrollmentRepository.delete(studentEnrollment);
        return true;
    }
}

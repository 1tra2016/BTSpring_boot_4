package com.example.demo.repositories;

import com.example.demo.models.StudentEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<StudentEnrollment,Long> {
}

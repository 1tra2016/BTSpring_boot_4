package com.example.demo.services;


import com.example.demo.DTO.CourseCreateRequest;
import com.example.demo.DTO.CourseUpdateRequest;
import com.example.demo.models.Instructor;
import com.example.demo.models.Course;
import com.example.demo.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.NoSuchElementException;

@org.springframework.stereotype.Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final InstructorService instructorService;

    @Autowired
    public CourseService(CourseRepository courseRepository, InstructorService instructorService) {
        this.courseRepository = courseRepository;
        this.instructorService = instructorService;
    }

    public List<Course> getAllCourses(String search) {
        List<Course> courses = courseRepository.findAll();
        if (search != null) {
            courses = courses
                    .stream()
                    .filter(u -> u.getTitle().toLowerCase().contains(search.toLowerCase()))
                    .toList();
        }
        return courses;
    }

    //public Course findById(Long id){
    //    return courseRepository.findById(id).orElse(null);
    //}

    public Course getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found id: " + id));
        return course;
    }

    public Course createCourse(CourseCreateRequest req) {
        Instructor instructor = instructorService.getInstructorById(req.getInstructorId()); //getInstructorById sẽ ném RuntimeException nếu null
        Course course =  new Course();
        course.setTitle(req.getTitle());
        course.setStatus(req.getStatus());
        course.setInstructor(instructor);

        return courseRepository.save(course);
    }

    public Course updateCourse(Long id, CourseUpdateRequest req) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found id: " + id));

        Instructor instructor = instructorService.getInstructorById(req.getInstructorId());
        course.setTitle(req.getTitle());
        course.setStatus(req.getStatus());
        course.setInstructor(instructor);
        return courseRepository.save(course);
    }

    public boolean deleteCourse(Long id){
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found id: " + id));
        courseRepository.deleteById(id);
        return true;
    }
}

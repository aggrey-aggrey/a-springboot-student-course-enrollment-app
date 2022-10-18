package com.aggrey.springboot.application.demo.services;

import com.aggrey.springboot.application.demo.model.Course;
import com.aggrey.springboot.application.demo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private Course course;

    @Autowired
    public CourseService(CourseRepository courseRepository, Course course) {
        this.courseRepository = courseRepository;
        this.course = course;
    }

    public List<Course> getListOfCourses (){
        return courseRepository.findAll();
    }

    //search for a course based on id
    public Optional<Course> findCourse (Long id){
        return courseRepository.findById(id);
    }

    //get course by id
    public Course getCourse(Long id){
        return courseRepository.findById(id).get();
    }





}

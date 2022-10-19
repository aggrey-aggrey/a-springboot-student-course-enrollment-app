package com.aggrey.springboot.application.demo.services;

import com.aggrey.springboot.application.demo.model.Course;
import com.aggrey.springboot.application.demo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
    public Optional<Course> findCourse (Long id){
        return courseRepository.findById(id);
    }
    public Course getCourse(Long id){
        return courseRepository.findById(id).get();
    }
    public void createCourse(Course course) {
       Optional<Course> courseOptional = courseRepository.findByName(course.getName());

        if(courseOptional.isPresent()){
            throw new IllegalStateException("course already exist");
        }

        courseRepository.save(course);
    }

    public void updateCourse(Long courseId, String name, String description) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalStateException( "course with id " +  courseId + "does not exist"));

        if (name !=null && name.length() > 0 &&
        !Objects.equals(course.getName(), name)){
            course.setName(name);
        }

        if (description !=null && description.length() > 0 &&
                !Objects.equals(course.getDescription(), description)){
            course.setDescription(description);
        }
    }
}

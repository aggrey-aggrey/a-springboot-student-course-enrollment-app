package com.aggrey.springboot.application.demo.controller;

import com.aggrey.springboot.application.demo.model.Course;
import com.aggrey.springboot.application.demo.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/course")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping()
    public List<Course> addACourse(){
        return courseService.getListOfCourses();
    }

    @PostMapping
    public void createCourse(@RequestBody Course course){
        courseService.createCourse(course);

    }

    @PutMapping(path ="{courseId}")
    public void updateCourse(@PathVariable("courseId") Long id,
                             @RequestParam(required = false) String name,
                             @RequestParam(required = false) String description){

        courseService.updateCourse(id,name, description);

    }
}

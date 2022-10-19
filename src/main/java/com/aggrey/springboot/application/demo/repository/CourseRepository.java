package com.aggrey.springboot.application.demo.repository;

import com.aggrey.springboot.application.demo.model.Category;
import com.aggrey.springboot.application.demo.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Override
    Optional<Course> findById(Long aLong);

    Optional<Course> findByName(String name);
}
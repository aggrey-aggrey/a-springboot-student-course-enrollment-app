package com.aggrey.springboot.application.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@ToString
@Setter
@Getter
@Entity
@Table
@Component
public class Course {
    @Id
    @SequenceGenerator(
            name="course_sequence",
            sequenceName ="course_sequence")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence")
    @Column(name = "course_id")
    private Long courseId;
    @Column(name = "course_name")
    private String name;
    @Column(name ="course_description")
    private String description;
    @Transient
    private Category category;

    public Course(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public Course(Long courserId, String name, String description, Category category) {
        this.courseId = courserId;
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public Course() {

    }
}

package com.aggrey.springboot.application.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class Course {
    private Long courserId;
    private String name;
    private String description;
    private  Category category;


    public Course(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public Course(Long courserId, String name, String description, Category category) {
        this.courserId = courserId;
        this.name = name;
        this.description = description;
        this.category = category;
    }

}

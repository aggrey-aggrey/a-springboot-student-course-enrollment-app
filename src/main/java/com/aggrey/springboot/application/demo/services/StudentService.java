package com.aggrey.springboot.application.demo.services;

import com.aggrey.springboot.application.demo.model.Student;
import com.aggrey.springboot.application.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }



    public List<Student> getStudents() {

        return List.of(
                new Student(1L,
                        "Smith",
                        14,
                        LocalDate.of(2020, 11, 05),
                        "test@123.com"
        ));

    }
}

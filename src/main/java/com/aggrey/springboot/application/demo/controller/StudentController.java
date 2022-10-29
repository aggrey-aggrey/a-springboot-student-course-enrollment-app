package com.aggrey.springboot.application.demo.controller;

import com.aggrey.springboot.application.demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.aggrey.springboot.application.demo.services.StudentService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "{studentId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE', 'ROLE_STUDENT')")
    public Student getStudent (@PathVariable("studentId") Long studentId){
        return  studentService.getStudentById(studentId);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public List<Student> getStudents (){
        return studentService.getStudents();
    }
    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public void enrollNewStudent(@RequestBody Student student){
        studentService.enrollNewStudent(student);
    }

    @DeleteMapping(path="{studentId}")
    @PreAuthorize("hasAnyAuthority('student:write')")
    public void deleteStudent (@PathVariable("studentId")Long studentId){
        studentService.deleteStudent(studentId);
    }

    /*
    @PutMapping(path = "{studentId}")
    public void updateStudent (@PathVariable("studentId") Long studentId,
                               @RequestParam(required = false) String name,
                               @RequestParam(required = false) String email){
        studentService.updateStudent(studentId, name, email);
    }*/

    @PutMapping(path = "{studentId}")
    @PreAuthorize("hasAnyAuthority('student:write')")
    public void updateStudentInfo (@PathVariable("studentId") Long studentId,@RequestBody Student student){
        System.out.println(String.format("%s %s", student, student));
        studentService.updateStudent(studentId,student);
    }



}


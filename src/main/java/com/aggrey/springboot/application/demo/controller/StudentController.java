package com.aggrey.springboot.application.demo.controller;

import com.aggrey.springboot.application.demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.aggrey.springboot.application.demo.services.StudentService;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents (){
        return studentService.getStudents();

    }
    @PostMapping
    public void enrollNewStudent(@RequestBody Student student){
        studentService.enrollNewStudent(student);
    }

    @DeleteMapping(path="{studentId}")
    public void deleteStudent (@PathVariable("studentId")Long studentId){
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent (@PathVariable("studentId") Long studentId,
                               @RequestParam(required = false) String name,
                               @RequestParam(required = false) String email){
        studentService.updateStudent(studentId, name, email);
    }
}


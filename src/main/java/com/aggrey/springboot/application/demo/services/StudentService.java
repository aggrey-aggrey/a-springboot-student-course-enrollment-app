package com.aggrey.springboot.application.demo.services;

import com.aggrey.springboot.application.demo.model.Student;
import com.aggrey.springboot.application.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }
    public void enrollNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository
                .findStudentByEmail(student.getEmail());

        if (studentOptional.isPresent()) {
            throw new IllegalStateException("Email already exist");
        }
        studentRepository.save(student);

    }
    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);

        if (!exists){
            throw new IllegalStateException(
                    "student with id " + studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);
    }
    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "student with id " + studentId + "does not exist"
                        ));

        if (name != null && name.length () > 0 &&
        !Objects.equals(student.getName(),name)){
            student.setName(name);
        }

        if (email != null && email.length () > 0 &&
                !Objects.equals(student.getEmail(), email)){
            //check that email has not be taken
            Optional<Student> studentOptional = studentRepository
                    .findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("email already exist");
            }
            student.setEmail(email);
        }
    }

    public Student getStudentById(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);

        if (!exists) {
            throw new IllegalStateException(
                    "student with id " + studentId + " does not exist");
        }
           return studentRepository.findById(studentId).get();
    }
    public void updateStudent(Long studentId, Student student) {
        student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("student with id " + studentId + "does not exist"));
         studentRepository.save(student);

    }
}
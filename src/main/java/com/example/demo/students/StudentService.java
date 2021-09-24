package com.example.demo.students;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> getStudent(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findByEmail(student.getEmail());

        if(studentByEmail.isPresent()) {
            throw new IllegalStateException("The email is already exists in the database");
        }
        System.out.println(student.toString());
        studentRepository.save(student);
    }
}

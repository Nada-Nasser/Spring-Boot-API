package com.example.demo.students;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public void deleteStudent(Long studentID) {
        boolean flag = studentRepository.existsById(studentID);

        if(!flag){
            throw new IllegalStateException("There is no student in the database with this id");
        }
        studentRepository.deleteById(studentID);
        System.out.println(studentID + " deleted");
    }

    @Transactional
    public void updateStudent(Long studentID, String name, String email) {
        Student studentById = studentRepository.findById(studentID)
                .orElseThrow( () -> new IllegalStateException("There is no student in the database with this id")
        );

        if(name != null && name.length() > 0 && !name.equals(studentById.getName())){
            studentById.setName(name);
            System.out.println("Student name updated");
        }

        if(email != null && email.length() > 0 && !email.equals(studentById.getEmail())){

            Optional<Student> studentByEmail = studentRepository.findByEmail(email);
            if(studentByEmail.isPresent())
                throw new IllegalStateException("The email is already used by other student");

            studentById.setEmail(email);

            System.out.println("Student email updated");
        }
    }
}

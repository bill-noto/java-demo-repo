package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepo studentRepo;

    @Autowired
    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @GetMapping
    public List<StudentModel> getStudents() {
        return studentRepo.findAll();
    }

    public void addStudent(StudentModel student) {
        Optional<StudentModel> studentOptional =  studentRepo.findByEmail(student.getEmail());
        if (studentOptional.isPresent()){
            throw new IllegalStateException("email exists");
        }
        studentRepo.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepo.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("student " + studentId + " does not exist");
        }
            studentRepo.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        StudentModel student = studentRepo.findById(studentId).orElseThrow(() -> new IllegalStateException("student with id " + studentId + " does not exist"));

        if (name !=null && name.length() > 0 && !Objects.equals(name, student.getName())){
            student.setName(name);
        }

        if (email !=null && email.length() > 0 && !Objects.equals(email, student.getEmail())){
            Optional<StudentModel> studentOptional =  studentRepo.findByEmail(email);
            if (studentOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
    }
}

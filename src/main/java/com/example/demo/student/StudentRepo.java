package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<StudentModel, Long> {

    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<StudentModel> findByEmail(String email);

}

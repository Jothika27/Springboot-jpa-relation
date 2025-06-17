package com.example.jpa_relation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.jpa_relation.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);

    Optional<Student> findByStudentId(String studentId);

    @Query("SELECT s FROM Student s WHERE s.firstName LIKE %:name% OR s.lastName LIKE %:name%")
    List<Student> findByNameContaining(String name);

    @Query("SELECT s FROM Student s LEFT JOIN FETCH s.subjects WHERE s.id = :id")
    Optional<Student> findByIdWithSubjects(Long id);

    @Query("SELECT s FROM Student s JOIN s.subjects sub WHERE sub.id = :subjectId")
    List<Student> findBySubjectId(Long subjectId);
}

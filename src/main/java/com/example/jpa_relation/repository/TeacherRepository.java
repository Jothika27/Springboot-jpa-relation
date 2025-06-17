package com.example.jpa_relation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.jpa_relation.entity.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findByEmail(String email);

    List<Teacher> findByDepartmentId(Long departmentId);

    @Query("SELECT t FROM Teacher t WHERE t.firstName LIKE %:name% OR t.lastName LIKE %:name%")
    List<Teacher> findByNameContaining(String name);

    @Query("SELECT t FROM Teacher t LEFT JOIN FETCH t.subjects WHERE t.id = :id")
    Optional<Teacher> findByIdWithSubjects(Long id);
}

package com.example.jpa_relation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.jpa_relation.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Optional<Subject> findByCode(String code);

    List<Subject> findByDepartmentId(Long departmentId);

    List<Subject> findByTeacherId(Long teacherId);

    @Query("SELECT s FROM Subject s WHERE s.name LIKE %:name%")
    List<Subject> findByNameContaining(String name);

    @Query("SELECT s FROM Subject s LEFT JOIN FETCH s.students WHERE s.id = :id")
    Optional<Subject> findByIdWithStudents(Long id);
}

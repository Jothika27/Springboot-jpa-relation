package com.example.jpa_relation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.jpa_relation.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByName(String name);

    @Query("SELECT d FROM Department d LEFT JOIN FETCH d.teachers WHERE d.id = :id")
    Optional<Department> findByIdWithTeachers(Long id);

    @Query("SELECT d FROM Department d LEFT JOIN FETCH d.subjects WHERE d.id = :id")
    Optional<Department> findByIdWithSubjects(Long id);
}

package com.example.jpa_relation.repository;

import org.springframework.stereotype.Repository;

import com.example.jpa_relation.entity.Teacher;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Repository
public class TeacherRepositoryImpl implements TeacherRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Double findHighestSalary() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Double> cq = cb.createQuery(Double.class);
        Root<Teacher> root = cq.from(Teacher.class);
        cq.select(cb.max(root.get("salary")));
        return entityManager.createQuery(cq).getSingleResult();
    }
}

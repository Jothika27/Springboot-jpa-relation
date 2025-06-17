package com.example.jpa_relation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.jpa_relation.entity.Department;
import com.example.jpa_relation.repository.DepartmentRepository;

@Service
@Transactional
public class DepartmentService {
    @Autowired
    public DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    public Optional<Department> getDepartmentByName(String name) {
        return departmentRepository.findByName(name);
    }

    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department updateDepartment(Long id, Department departmentDetails) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));

        department.setName(departmentDetails.getName());
        department.setDescription(departmentDetails.getDescription());

        return departmentRepository.save(department);
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

    public Optional<Department> getDepartmentWithTeachers(Long id) {
        return departmentRepository.findByIdWithTeachers(id);
    }

    public Optional<Department> getDepartmentWithSubjects(Long id) {
        return departmentRepository.findByIdWithSubjects(id);
    }
}

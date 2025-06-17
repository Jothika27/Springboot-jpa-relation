package com.example.jpa_relation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.jpa_relation.entity.Department;
import com.example.jpa_relation.service.DepartmentService;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/departments")
@CrossOrigin(origins = "*")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        Optional<Department> department = departmentService.getDepartmentById(id);
        return department.map(value -> ResponseEntity.ok().body(value))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Department> getDepartmentByName(@PathVariable String name) {
        Optional<Department> department = departmentService.getDepartmentByName(name);
        return department.map(value -> ResponseEntity.ok().body(value))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/teachers")
    public ResponseEntity<Department> getDepartmentWithTeachers(@PathVariable Long id) {
        Optional<Department> department = departmentService.getDepartmentWithTeachers(id);

        return department.map(value -> ResponseEntity.ok().body(value))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/subjects")
    public ResponseEntity<Department> getDepartmentWithSubjects(@PathVariable Long id) {
        Optional<Department> department = departmentService.getDepartmentWithSubjects(id);
        return department.map(value -> ResponseEntity.ok().body(value))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Department> createDepartment(@Valid @RequestBody Department department) {
        try {
            Department savedDepartment = departmentService.saveDepartment(department);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedDepartment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id,
            @Valid @RequestBody Department departmentDetails) {
        try {
            Department updatedDepartment = departmentService.updateDepartment(id, departmentDetails);
            return ResponseEntity.ok(updatedDepartment);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        try {
            departmentService.deleteDepartment(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

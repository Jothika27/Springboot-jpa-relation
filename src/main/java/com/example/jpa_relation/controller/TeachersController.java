package com.example.jpa_relation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.jpa_relation.entity.Teacher;
import com.example.jpa_relation.service.TeacherService;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teachers")
@CrossOrigin(origins = "*")
public class TeachersController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> teachers = teacherService.getAllTeachers();
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Long id) {
        Optional<Teacher> teacher = teacherService.getTeacherById(id);
        return teacher.map(value -> ResponseEntity.ok().body(value))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Teacher> getTeacherByEmail(@PathVariable String email) {
        Optional<Teacher> teacher = teacherService.getTeacherByEmail(email);
        return teacher.map(value -> ResponseEntity.ok().body(value))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<Teacher>> getTeachersByDepartment(@PathVariable Long departmentId) {
        List<Teacher> teachers = teacherService.getTeachersByDepartment(departmentId);
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Teacher>> searchTeachersByName(@RequestParam String name) {
        List<Teacher> teachers = teacherService.searchTeachersByName(name);
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/{id}/subjects")
    public ResponseEntity<Teacher> getTeacherWithSubjects(@PathVariable Long id) {
        Optional<Teacher> teacher = teacherService.getTeacherWithSubjects(id);
        return teacher.map(value -> ResponseEntity.ok().body(value))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@Valid @RequestBody Teacher teacher) {
        try {
            Teacher savedTeacher = teacherService.saveTeacher(teacher);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedTeacher);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable Long id, @Valid @RequestBody Teacher teacherDetails) {
        try {
            Teacher updatedTeacher = teacherService.updateTeacher(id, teacherDetails);
            return ResponseEntity.ok(updatedTeacher);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        try {
            teacherService.deleteTeacher(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
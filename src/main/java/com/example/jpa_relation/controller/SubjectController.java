package com.example.jpa_relation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.jpa_relation.entity.Subject;
import com.example.jpa_relation.service.SubjectService;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/subjects")
@CrossOrigin(origins = "*")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public ResponseEntity<List<Subject>> getAllSubjects() {
        List<Subject> subjects = subjectService.getAllSubjects();
        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable Long id) {
        Optional<Subject> subject = subjectService.getSubjectById(id);
        return subject.map(value -> ResponseEntity.ok().body(value))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Subject> getSubjectByCode(@PathVariable String code) {
        Optional<Subject> subject = subjectService.getSubjectByCode(code);
        return subject.map(value -> ResponseEntity.ok().body(value))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<Subject>> getSubjectsByDepartment(@PathVariable Long departmentId) {
        List<Subject> subjects = subjectService.getSubjectsByDepartment(departmentId);

        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<Subject>> getSubjectsByTeacher(@PathVariable Long teacherId) {
        List<Subject> subjects = subjectService.getSubjectsByTeacher(teacherId);
        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Subject>> searchSubjectsByName(@RequestParam String name) {
        List<Subject> subjects = subjectService.searchSubjectsByName(name);
        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<Subject> getSubjectWithStudents(@PathVariable Long id) {
        Optional<Subject> subject = subjectService.getSubjectWithStudents(id);
        return subject.map(value -> ResponseEntity.ok().body(value))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Subject> createSubject(@Valid @RequestBody Subject subject) {
        try {
            Subject savedSubject = subjectService.saveSubject(subject);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedSubject);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subject> updateSubject(@PathVariable Long id, @Valid @RequestBody Subject subjectDetails) {
        try {
            Subject updatedSubject = subjectService.updateSubject(id, subjectDetails);
            return ResponseEntity.ok(updatedSubject);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
        try {
            subjectService.deleteSubject(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
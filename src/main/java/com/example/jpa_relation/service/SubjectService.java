package com.example.jpa_relation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.jpa_relation.entity.Subject;
import com.example.jpa_relation.repository.SubjectRepository;

@Service
@Transactional
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Optional<Subject> getSubjectById(Long id) {
        return subjectRepository.findById(id);
    }

    public Optional<Subject> getSubjectByCode(String code) {
        return subjectRepository.findByCode(code);
    }

    public List<Subject> getSubjectsByDepartment(Long departmentId) {
        return subjectRepository.findByDepartmentId(departmentId);
    }

    public List<Subject> getSubjectsByTeacher(Long teacherId) {
        return subjectRepository.findByTeacherId(teacherId);
    }

    public List<Subject> searchSubjectsByName(String name) {
        return subjectRepository.findByNameContaining(name);
    }

    public Subject saveSubject(Subject subject) {
        return subjectRepository.save(subject);

    }

    public Subject updateSubject(Long id, Subject subjectDetails) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found with id: " + id));

        subject.setName(subjectDetails.getName());
        subject.setCode(subjectDetails.getCode());
        subject.setCredits(subjectDetails.getCredits());
        subject.setDescription(subjectDetails.getDescription());
        subject.setDepartment(subjectDetails.getDepartment());
        subject.setTeacher(subjectDetails.getTeacher());

        return subjectRepository.save(subject);
    }

    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }

    public Optional<Subject> getSubjectWithStudents(Long id) {
        return subjectRepository.findByIdWithStudents(id);
    }
}
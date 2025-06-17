package com.example.jpa_relation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jpa_relation.entity.Teacher;
import com.example.jpa_relation.repository.TeacherRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Optional<Teacher> getTeacherById(Long id) {
        return teacherRepository.findById(id);
    }

    public Optional<Teacher> getTeacherByEmail(String email) {
        return teacherRepository.findByEmail(email);
    }

    public List<Teacher> getTeachersByDepartment(Long departmentId) {
        return teacherRepository.findByDepartmentId(departmentId);
    }

    public List<Teacher> searchTeachersByName(String name) {
        return teacherRepository.findByNameContaining(name);
    }

    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Teacher updateTeacher(Long id, Teacher teacherDetails) {
        Teacher teacher = teacherRepository.findById(id)

                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));

        teacher.setFirstName(teacherDetails.getFirstName());
        teacher.setLastName(teacherDetails.getLastName());
        teacher.setEmail(teacherDetails.getEmail());
        teacher.setPhone(teacherDetails.getPhone());
        teacher.setDepartment(teacherDetails.getDepartment());

        return teacherRepository.save(teacher);
    }

    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    public Optional<Teacher> getTeacherWithSubjects(Long id) {
        return teacherRepository.findByIdWithSubjects(id);
    }
}

package com.example.jpa_relation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.jpa_relation.entity.Student;
import com.example.jpa_relation.entity.Subject;
import com.example.jpa_relation.repository.StudentRepository;
import com.example.jpa_relation.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Optional<Student> getStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    public Optional<Student> getStudentByStudentId(String studentId) {
        return studentRepository.findByStudentId(studentId);
    }

    public List<Student> searchStudentsByName(String name) {
        return studentRepository.findByNameContaining(name);
    }

    public Student saveStudent(Student student) {

        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student studentDetails) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        student.setFirstName(studentDetails.getFirstName());
        student.setLastName(studentDetails.getLastName());
        student.setEmail(studentDetails.getEmail());
        student.setStudentId(studentDetails.getStudentId());
        student.setPhone(studentDetails.getPhone());
        student.setDateOfBirth(studentDetails.getDateOfBirth());

        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Optional<Student> getStudentWithSubjects(Long id) {
        return studentRepository.findByIdWithSubjects(id);
    }

    public Student enrollStudentInSubject(Long studentId, Long subjectId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found with id: " + subjectId));

        student.addSubject(subject);
        return studentRepository.save(student);
    }

    public Student unenrollStudentFromSubject(Long studentId, Long subjectId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found with id: " + subjectId));

        student.removeSubject(subject);

        return studentRepository.save(student);
    }

    public List<Student> getStudentsBySubject(Long subjectId) {
        return studentRepository.findBySubjectId(subjectId);
    }
}

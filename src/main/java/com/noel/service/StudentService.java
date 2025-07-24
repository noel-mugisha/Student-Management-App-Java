package com.noel.service;

import com.noel.exceptions.DuplicateStudentException;
import com.noel.model.Student;
import com.noel.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

public class StudentService {
    public static final StudentRepository repository = new StudentRepository();

    public void addStudent(Student student) throws DuplicateStudentException {
     try {
         repository.addStudent(student);
         System.out.println("Student added successfully");
         System.out.println("Student details: " + student);
     } catch (DuplicateStudentException e) {
         System.out.println("Error: " + e.getMessage());
     }
    }

    public void getAllStudents() {
        List<Student> students = repository.getStudents();
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public void getSingleStudent (int id) {
        Optional<Student> existingStudent = repository.getStudentById(id);
        if (existingStudent.isPresent()) {
            System.out.println("Student details: " + existingStudent.get());
        }
        else {
            System.out.println("Student not found");
        }
    }

    public void updateStudent (int id, Student student) {
        Optional<Student> updatedStudent = repository.updateStudent(id, student);
        if (updatedStudent.isPresent()) {
            System.out.println("Student updated successfully");
            System.out.println("Student details: " + updatedStudent.get());
        }
    }

    public void deleteStudent (int id) {
        boolean isStudentRemoved = repository.deleteStudent(id);
        if (isStudentRemoved) {
            System.out.println("Student deleted successfully");
        } else {
            System.out.println("Student not found");
        }
    }

}

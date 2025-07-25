package com.noel.service;

import com.noel.exceptions.DuplicateStudentException;
import com.noel.exceptions.InvalidAgeException;
import com.noel.exceptions.StudentNotFoundException;
import com.noel.model.Student;
import com.noel.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

public class StudentService {
    public static final StudentRepository repository = new StudentRepository();

    public void addStudent(Student student) throws DuplicateStudentException, InvalidAgeException {
     Optional<Student> existingStudent = repository.getStudentById(student.getId());
     if (existingStudent.isPresent()) {
         throw new DuplicateStudentException("Student with id " + student.getId() + " already exists");
     }

     if (student.getAge() < 16 || student.getAge() > 100) {
         throw new InvalidAgeException("Student age must be between 16 and 100");
     }

     repository.addStudent(student);
     System.out.println("Student " + student.getName() + " has been added successfully");
     System.out.println("Student details: " + student);
    }

    public void getAllStudents() {
        List<Student> students = repository.getStudents();
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public void getSingleStudent (int id) throws StudentNotFoundException {
        Optional<Student> existingStudent = repository.getStudentById(id);
        if (existingStudent.isPresent()) {
            System.out.println("Student details: " + existingStudent.get());
        }
        else {
            throw new StudentNotFoundException("Student with id " + id + " not found");
        }
    }

    public void updateStudent (int id, Student student) throws StudentNotFoundException {
        Optional<Student> updatedStudent = repository.updateStudent(id, student);
        if (updatedStudent.isPresent()) {
            System.out.println("Student updated successfully");
            System.out.println("Student details: " + updatedStudent.get());
        }
        else {
            throw new StudentNotFoundException("Student with id " + id + " not found");
        }
    }

    public void deleteStudent (int id) throws StudentNotFoundException {
        boolean isStudentRemoved = repository.deleteStudent(id);
        if (isStudentRemoved) {
            System.out.println("Student deleted successfully");
        } else {
            throw new StudentNotFoundException("Student with id " + id + " not found");
        }
    }

}

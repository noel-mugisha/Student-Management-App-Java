package com.noel.repository;

import com.noel.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentRepository {
    private static final List<Student> students = new ArrayList<>();

    // method to add a student
    public void addStudent(Student student) {
        students.add(student);
    }

    // method to get all students
    public List<Student> getStudents() {
        return students;
    }

    // method to get a single student
    public Optional<Student> getStudentById(int id) {
        return students.stream()
                .filter(s -> s.getId() == id)
                .findFirst();
    }

    // method to update a student
    public Optional<Student> updateStudent(int id, Student updatedStudent) {
        for (int i = 0; i<students.size(); i++) {
            if ( students.get(i).getId() == id) {
                students.set(i, updatedStudent);
                return Optional.of(students.get(i));
            }
        }
        return Optional.empty();
    }

    // method to delete a student
    public boolean deleteStudent(int id) {
        return students.removeIf(s -> s.getId() == id);
    }

}

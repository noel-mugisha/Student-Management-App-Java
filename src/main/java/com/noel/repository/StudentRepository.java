package com.noel.repository;

import com.noel.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    private static final List<Student> students = new ArrayList<Student>();

    // method to add a student
    public void addStudent(Student student) {
        students.add(student);
    }

    // method to get all students
    public List<Student> getStudents() {
        return students;
    }

    // method to remove a student
    public void removeStudent(Student student) { // I dont know well if it is true,
        students.remove(student);
    }

    // method to edit a student

    // method to get a single student
}

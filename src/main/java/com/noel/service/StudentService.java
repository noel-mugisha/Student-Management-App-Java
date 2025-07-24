package com.noel.service;

import com.noel.model.Student;
import com.noel.repository.StudentRepository;

import java.util.List;

public class StudentService {
    private final StudentRepository repo = new StudentRepository();

    public void addStudent(Student student) {
        repo.addStudent(student);
        System.out.println("Student " + student.getName() + " has been added successfully");
    }

    public void getAllStudents() {
        List <Student> students = repo.getStudents();
        students.stream().forEach(System.out::println);
    }
}

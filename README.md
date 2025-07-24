# Student Management Console App

A Java console application for managing student records.

## 📋 Project Overview

This application allows users to manage student information through a console interface. It's designed as a practice project to implement core Java concepts in a real-world scenario.

## ✨ Features

- **Student Management**
    - Add new students with validation
    - View all students
    - Update existing student information
    - Delete students from the system

- **Advanced Operations**
    - List students sorted by name (using Streams)
    - Filter students by grade threshold
    - Search functionality
    - Duplicate ID prevention

## 🎯 Learning Objectives

This project practices:
- **OOP Concepts**: Classes, Inheritance, Polymorphism, Encapsulation
- **Collections**: ArrayList, HashMap, sorting algorithms
- **Streams & Lambdas**: Filtering, mapping, sorting operations
- **Exception Handling**: Custom exceptions, input validation
- **Generics**: Type-safe collections and utilities
- **Java Best Practices**: Code organization, separation of concerns

## 🏗️ Project Structure

```
src/main/java/com/studentapp/
├── Main.java
├── model/Student.java
├── service/StudentService.java
├── repository/StudentRepository.java
├── exception/InvalidAgeException.java
├── exception/DuplicateStudentException.java
└── ui/ConsoleUI.java
```

## 🚀 Getting Started

### Prerequisites
- Java 11 or higher
- IDE (IntelliJ IDEA, Eclipse, or VS Code)

### Running the Application
1. Clone or download the project
2. Open in your preferred IDE
3. Navigate to `Main.java`
4. Run the main method
5. Follow the console prompts to interact with the application

## 📝 Student Data Model

Each student contains:
- **ID**: Unique identifier (Integer)
- **Name**: Student's full name (String)
- **Age**: Student's age with validation (Integer)
- **Grade**: Academic grade/GPA (Double)
- **Courses**: List of enrolled courses (List<String>)

## 🎮 Usage Examples

### Main Menu Options
1. Add Student
2. View All Students
3. Update Student
4. Delete Student
5. Search Students
6. Filter by Grade
7. Sort Students by Name
8. Exit

### Sample Operations
- Add a student with validation
- View students sorted alphabetically
- Filter students with grade > 3.5
- Handle duplicate ID attempts
- Manage invalid age inputs

## 🛠️ Technical Implementation

### Exception Handling
- `InvalidAgeException`: Thrown for ages outside valid range
- `DuplicateStudentException`: Thrown when attempting to add duplicate IDs
- Input validation for all user entries

### Collections Usage
- `HashMap<Integer, Student>`: Fast student lookup by ID
- `ArrayList<Student>`: Ordered student lists for display
- Stream operations for filtering and sorting

### Design Patterns
- Repository pattern for data access abstraction
- Service layer for business logic separation
- MVC-like structure for clean code organization

## 🎓 Skills Demonstrated

- **Object-Oriented Design**: Proper class structure and relationships
- **Error Handling**: Robust exception management
- **Data Structures**: Efficient use of Java Collections
- **Functional Programming**: Streams and Lambda expressions
- **Code Organization**: Clean architecture principles
- **User Experience**: Intuitive console interface

## 🔄 Future Enhancements

Potential improvements to consider:
- File persistence (save/load from files)
- More advanced search criteria
- Student course enrollment management
- Grade calculation utilities
- Export functionality (CSV, JSON)
- Unit testing implementation

## 📚 Learning Resources

This project reinforces concepts from:
- Java OOP fundamentals
- Collections Framework
- Stream API and Lambda expressions
- Exception handling best practices
- Generic programming
- Clean code principles

## 🛠️ Technologies Used

- Java Collections (ArrayList, HashMap)
- Streams & Lambdas
- Custom Exceptions
- OOP Principles

---

**Note**: This is a learning project focused on practicing Java fundamentals. The application uses in-memory storage and is designed for educational purposes.
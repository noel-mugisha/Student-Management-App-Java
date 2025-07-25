package com.noel.UI;

import com.noel.exceptions.DuplicateStudentException;
import com.noel.exceptions.InvalidAgeException;
import com.noel.exceptions.StudentNotFoundException;
import com.noel.model.Student;
import com.noel.service.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    // Create instances of service and scanner for user input
    private final StudentService studentService = new StudentService();
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Main method to start the application
     * This is the entry point that shows the main menu
     */
    public void start() {
        System.out.println("🎓 Welcome to Student Management System!");
        System.out.println("=" + "=".repeat(40));

        boolean running = true;

        while (running) {
            showMainMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    addStudentMenu();
                    break;
                case 2:
                    viewAllStudentsMenu();
                    break;
                case 3:
                    findStudentMenu();
                    break;
                case 4:
                    updateStudentMenu();
                    break;
                case 5:
                    deleteStudentMenu();
                    break;
                case 6:
                    System.out.println("👋 Thank you for using Student Management System!");
                    running = false;
                    break;
                default:
                    System.out.println("❌ Invalid choice! Please select 1-6.");
            }

            if (running) {
                pressEnterToContinue();
            }
        }

        scanner.close();
    }

    /**
     * Display the main menu options to the user
     */
    private void showMainMenu() {
        System.out.println("\n📋 MAIN MENU");
        System.out.println("-".repeat(20));
        System.out.println("1. ➕ Add Student");
        System.out.println("2. 📚 View All Students");
        System.out.println("3. 🔍 Find Student");
        System.out.println("4. ✏️  Update Student");
        System.out.println("5. 🗑️  Delete Student");
        System.out.println("6. 🚪 Exit");
        System.out.print("\nEnter your choice (1-6): ");
    }

    /**
     * Get user's menu choice with input validation
     * @return valid menu choice (1-6)
     */
    private int getUserChoice() {
        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            return choice;
        } catch (Exception e) {
            scanner.nextLine(); // Clear invalid input
            return -1; // Return invalid choice
        }
    }

    /**
     * Handle adding a new student
     * This method demonstrates exception handling in UI layer
     */
    private void addStudentMenu() {
        System.out.println("\n➕ ADD NEW STUDENT");
        System.out.println("-".repeat(20));

        try {
            // Get student details from user
            System.out.print("Enter Student ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("Enter Student Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Student Age: ");
            int age = scanner.nextInt();

            System.out.print("Enter Student Grade (0.0-4.0): ");
            double grade = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            // Get courses (optional)
            List<String> courses = getCourses();

            // Create student object
            Student student = new Student(id, name, age, grade, courses);

            // Try to add student through service layer
            studentService.addStudent(student);

        } catch (DuplicateStudentException e) {
            // Handle duplicate ID error
            System.err.println("❌ Duplicate Error: " + e.getMessage());
            System.out.println("💡 Please try again with a different student ID.");

        } catch (InvalidAgeException e) {
            // Handle invalid age error
            System.err.println("❌ Age Error: " + e.getMessage());
            System.out.println("💡 Please enter an age between 16-100.");

        } catch (Exception e) {
            // Handle any unexpected errors (like invalid input format)
            System.err.println("❌ Input Error: Invalid input format!");
            System.out.println("💡 Please make sure to enter numbers for ID, age, and grade.");
            scanner.nextLine(); // Clear any remaining invalid input
        }
    }

    /**
     * Handle viewing all students
     * This method shows how to call service methods that don't throw exceptions
     */
    private void viewAllStudentsMenu() {
        System.out.println("\n📚 ALL STUDENTS");
        System.out.println("-".repeat(20));

        try {
            studentService.getAllStudents();
        } catch (Exception e) {
            System.err.println("❌ Error retrieving students: " + e.getMessage());
        }
    }

    /**
     * Handle finding a specific student
     * This method demonstrates handling StudentNotFoundException
     */
    private void findStudentMenu() {
        System.out.println("\n🔍 FIND STUDENT");
        System.out.println("-".repeat(20));

        try {
            System.out.print("Enter Student ID to find: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Call service method that throws StudentNotFoundException
            studentService.getSingleStudent(id);

        } catch (StudentNotFoundException e) {
            // Handle student not found
            System.err.println("❌ " + e.getMessage());
            System.out.println("💡 Please check the student ID and try again.");

        } catch (Exception e) {
            // Handle invalid input
            System.err.println("❌ Input Error: Please enter a valid student ID (number).");
            scanner.nextLine(); // Clear invalid input
        }
    }

    /**
     * Handle updating an existing student
     * This method demonstrates handling multiple exceptions
     */
    private void updateStudentMenu() {
        System.out.println("\n✏️ UPDATE STUDENT");
        System.out.println("-".repeat(20));

        try {
            System.out.print("Enter Student ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // First, try to find the student to show current details
            try {
                studentService.getSingleStudent(id);
                System.out.println("\nEnter new details:");
            } catch (StudentNotFoundException e) {
                System.err.println("❌ " + e.getMessage());
                return; // Exit if student doesn't exist
            }

            // Get new student details
            System.out.print("Enter new Student Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter new Student Age: ");
            int age = scanner.nextInt();

            System.out.print("Enter new Student Grade (0.0-4.0): ");
            double grade = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            // Get new courses
            List<String> courses = getCourses();

            // Create updated student object (keeping the same ID)
            Student updatedStudent = new Student(id, name, age, grade, courses);

            // Try to update through service layer
            studentService.updateStudent(id, updatedStudent);

        } catch (StudentNotFoundException e) {
            System.err.println("❌ " + e.getMessage());
            System.out.println("💡 Please check the student ID and try again.");

        } catch (Exception e) {
            System.err.println("❌ Input Error: Invalid input format!");
            System.out.println("💡 Please make sure to enter valid data.");
            scanner.nextLine(); // Clear invalid input
        }
    }

    /**
     * Handle deleting a student
     * This method demonstrates simple exception handling
     */
    private void deleteStudentMenu() {
        System.out.println("\n🗑️ DELETE STUDENT");
        System.out.println("-".repeat(20));

        try {
            System.out.print("Enter Student ID to delete: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Show student details before deletion (optional)
            try {
                System.out.println("\nStudent to be deleted:");
                studentService.getSingleStudent(id);

                System.out.print("\nAre you sure you want to delete this student? (y/n): ");
                String confirmation = scanner.nextLine();

                if (confirmation.toLowerCase().startsWith("y")) {
                    studentService.deleteStudent(id);
                } else {
                    System.out.println("❌ Deletion cancelled.");
                }

            } catch (StudentNotFoundException e) {
                System.err.println("❌ " + e.getMessage());
            }

            } catch (Exception e) {
                System.err.println("❌ Input Error: Please enter a valid student ID (number).");
                scanner.nextLine(); // Clear invalid input
            }
    }

    /**
     * Helper method to get courses from user input
     * @return List of courses entered by user
     */
    private List<String> getCourses() {
        List<String> courses = new ArrayList<>();

        System.out.print("How many courses? ");
        try {
            int courseCount = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            for (int i = 0; i < courseCount; i++) {
                System.out.print("Enter course " + (i + 1) + ": ");
                String course = scanner.nextLine();
                courses.add(course);
            }
        } catch (Exception e) {
            System.out.println("Invalid input for course count. No courses added.");
            scanner.nextLine(); // Clear invalid input
        }

        return courses;
    }

    /**
     * Helper method to pause execution until user presses Enter
     * This improves user experience by preventing menu from appearing too quickly
     */
    private void pressEnterToContinue() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
}
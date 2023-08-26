import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagementSystem system = new StudentManagementSystem();

        while (true) {
            System.out.println("Student Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    // Add student logic
                    System.out.print("Enter student name: ");
                  String name = scanner.nextLine();
    System.out.print("Enter student roll number: ");
    int rollNumber = scanner.nextInt();
    scanner.nextLine(); // Consume the newline
    System.out.print("Enter student grade: ");
    String grade = scanner.nextLine();

    Student newStudent = new Student(name, rollNumber, grade);
    system.addStudent(newStudent);
    System.out.println("Student added successfully!");

                    break;
                case 2:
                    // Remove student logic
                    System.out.print("Enter student roll number to remove: ");
    int rollNumberToRemove = scanner.nextInt();
    scanner.nextLine(); // Consume the newline

    system.removeStudent(rollNumberToRemove);
    System.out.println("Student removed successfully!");
                    break;
                case 3:
                    // Search student logic
                    System.out.print("Enter student roll number to search: ");
    int rollNumberToSearch = scanner.nextInt();
    scanner.nextLine(); // Consume the newline

    Student foundStudent = system.searchStudent(rollNumberToSearch);
    if (foundStudent != null) {
        System.out.println("Student found: " + foundStudent.getName() + " (Roll Number: " + foundStudent.getRollNumber() + ")");
    } else {
        System.out.println("Student not found.");
    }
    break;
                    
                case 4:
                    // Display all students logic
                    List<Student> allStudents = system.getAllStudents();
    if (allStudents.isEmpty()) {
        System.out.println("No students in the system.");
    } else {
        System.out.println("List of all students:");
        for (Student student : allStudents) {
            System.out.println("Name: " + student.getName() + ", Roll Number: " + student.getRollNumber() + ", Grade: " + student.getGrade());
        }
    }
    break;
                    
                case 5:
                    System.out.println("Exiting the application.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

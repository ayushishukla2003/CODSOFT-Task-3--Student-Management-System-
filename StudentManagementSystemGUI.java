import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentManagementSystemGUI extends JFrame {
    private StudentManagementSystem system;

    private JTextField nameField;
    private JTextField rollNumberField;
    private JTextField gradeField;

    public StudentManagementSystemGUI(StudentManagementSystem system) {
        this.system = system;

        setTitle("Student Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(173, 216, 230));
        setLayout(new BorderLayout());


        JPanel inputPanel = new JPanel(new GridLayout(5,6));

      
        Font labelFont = new Font("SansSerif", Font.BOLD, 35); 

     

        Color labelColor = Color.BLACK; 
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(labelFont); 
        nameLabel.setForeground(labelColor); 
        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(40, 5));
       
        nameField.setBorder(new CompoundBorder(
                new LineBorder(Color.BLACK),
                new EmptyBorder(5, 5, 5, 5)));
        JLabel rollNumberLabel = new JLabel("Roll Number:");
        rollNumberLabel.setFont(labelFont); 
        rollNumberLabel.setForeground(labelColor);
        rollNumberField = new JTextField();
        rollNumberField.setPreferredSize(new Dimension(40, 5));

        rollNumberField.setBorder(new CompoundBorder(
                new LineBorder(Color.BLACK),
                new EmptyBorder(5, 5, 5, 5))); 
        JLabel gradeLabel = new JLabel("Grade:");
        gradeLabel.setFont(labelFont); 
        gradeLabel.setForeground(labelColor); 
        gradeField = new JTextField();
        gradeField.setPreferredSize(new Dimension(40, 5)); 
        
        gradeField.setBorder(new CompoundBorder(
                new LineBorder(Color.BLACK),
                new EmptyBorder(5, 5, 5, 5)));


        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(rollNumberLabel);
        inputPanel.add(rollNumberField);
        inputPanel.add(gradeLabel);
        inputPanel.add(gradeField);

        add(inputPanel, BorderLayout.CENTER);

        JButton addButton = new JButton("Add Student");
        JButton removeButton = new JButton("Remove Student");
        JButton searchButton = new JButton("Search Student");
        JButton displayButton = new JButton("Display All Students");
        JButton editButton = new JButton("Edit Student");
        JButton exitButton = new JButton("Exit");


        addButton.setBackground(Color.BLUE);
        removeButton.setBackground(Color.BLUE);
        searchButton.setBackground(Color.BLUE);
        displayButton.setBackground(Color.BLUE);
        editButton.setBackground(Color.BLUE);
        exitButton.setBackground(Color.BLUE);

        // Set text color (foreground) for buttons
        addButton.setForeground(Color.WHITE);
        removeButton.setForeground(Color.WHITE);
        searchButton.setForeground(Color.WHITE);
        displayButton.setForeground(Color.WHITE);
        editButton.setForeground(Color.WHITE);
        exitButton.setForeground(Color.WHITE);






        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(displayButton);
        buttonPanel.add(editButton);
        buttonPanel.add(exitButton);


        JScrollPane buttonScrollPane = new JScrollPane(buttonPanel);

        buttonScrollPane.setPreferredSize(new Dimension(400, 100));

        add(buttonScrollPane, BorderLayout.SOUTH);

     
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int rollNumber = Integer.parseInt(rollNumberField.getText());
                String grade = gradeField.getText();

                Student newStudent = new Student(name, rollNumber, grade);
                system.addStudent(newStudent);
                JOptionPane.showMessageDialog(null, "Student added successfully!");
                clearFields();
            }
        });

       

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int rollNumberToRemove = Integer.parseInt(rollNumberField.getText());
                    system.removeStudent(rollNumberToRemove);
                    JOptionPane.showMessageDialog(null, "Student removed successfully!");
                    clearFields();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid roll number.");
                }
            }
        });
        

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int rollNumberToSearch = Integer.parseInt(rollNumberField.getText());
                    Student foundStudent = system.searchStudent(rollNumberToSearch);
                    if (foundStudent != null) {
                        JOptionPane.showMessageDialog(null, "Student found: " + foundStudent.getName() + " (Roll Number: " + foundStudent.getRollNumber() + ")");
                    } else {
                        JOptionPane.showMessageDialog(null, "Student not found.");
                    }
                    clearFields();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid roll number.");
                }
            }
        });


        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                java.util.List<Student> allStudents = system.getAllStudents();
                if (allStudents.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No students in the system.");
                } else {
                    StringBuilder studentList = new StringBuilder("List of all students:\n");
                    for (Student student : allStudents) {
                        studentList.append("Name: ").append(student.getName()).append(", Roll Number: ").append(student.getRollNumber()).append(", Grade: ").append(student.getGrade()).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, studentList.toString());



                    JFrame displayFrame = new JFrame("All Students");
                    JTextArea textArea = new JTextArea(studentList.toString());
                    textArea.setEditable(false);
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    
                    displayFrame.add(scrollPane);
                    displayFrame.setSize(400, 300);
                    displayFrame.setVisible(true);



                }
            }
        });



        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int rollNumberToEdit;
                try {
                    rollNumberToEdit = Integer.parseInt(rollNumberField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid roll number.");
                    return;
                }

                Student studentToEdit = system.searchStudent(rollNumberToEdit);
                if (studentToEdit == null) {
                    JOptionPane.showMessageDialog(null, "Student not found.");
                    return;
                }

                EditStudentDialog editStudentDialog = new EditStudentDialog(studentToEdit);
                editStudentDialog.setVisible(true);
            }
        });
       
        
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int confirmExit = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
                if (confirmExit == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        


        pack();
        setLocationRelativeTo(null);
     

        

        setVisible(true);
    }

    private void clearFields() {
        nameField.setText("");
        rollNumberField.setText("");
        gradeField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentManagementSystem system = new StudentManagementSystem();
            new StudentManagementSystemGUI(system);
        });
    }
}











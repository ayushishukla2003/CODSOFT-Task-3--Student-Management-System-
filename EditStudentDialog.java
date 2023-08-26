import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditStudentDialog extends JDialog {
    private Student student;

    private JTextField nameField;
    private JTextField rollNumberField;
    private JTextField gradeField;

    public EditStudentDialog(Student student) {
        this.student = student;
        
        setTitle("Edit Student");
        setSize(300, 200);

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(student.getName());
        JLabel rollNumberLabel = new JLabel("Roll Number:");
        rollNumberField = new JTextField(String.valueOf(student.getRollNumber()));
        JLabel gradeLabel = new JLabel("Grade:");
        gradeField = new JTextField(student.getGrade());

        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(rollNumberLabel);
        inputPanel.add(rollNumberField);
        inputPanel.add(gradeLabel);
        inputPanel.add(gradeField);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(inputPanel, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Update the student details
                student.setName(nameField.getText());
                student.setRollNumber(Integer.parseInt(rollNumberField.getText()));
                student.setGrade(gradeField.getText());

                JOptionPane.showMessageDialog(null, "Student information updated successfully!");
                dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setLocationRelativeTo(null);
        setModal(true);
    }
}


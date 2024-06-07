package moneysafeguard_java_application2;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Moneysafeguard_java_application2 {

    public static void main(String[] args) {
        new ms();
    }
}

class ms extends JFrame implements ActionListener {
    JLabel m1, m3;
    JPasswordField f1;
    JButton log, cle, exi, creat; // Declare buttons

    String createdPIN; // Store the created PIN
    String createdFullName;
    String createdPhoneNumber;
    String createdChoice;

    public ms() {
        setTitle("MoneySafeguard");
        setLayout(null);

        m1 = new JLabel("Welcome to MoneySafeguard");
        m1.setBounds(100, 20, 200, 20);

        m3 = new JLabel("PIN:");
        m3.setBounds(50, 50, 200, 20);

        f1 = new JPasswordField();
        f1.setBounds(160, 50, 150, 20);

        log = new JButton("Log in");
        log.setBounds(50, 90, 90, 20);
        log.addActionListener(this);
        log.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                log.setBackground(Color.YELLOW); // Change to highlight color
            }

            public void mouseExited(MouseEvent e) {
                log.setBackground(null); // Change back to default color
            }
        });

        cle = new JButton("Clear");
        cle.setBounds(160, 90, 70, 20);
        cle.addActionListener(this);
        cle.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                cle.setBackground(Color.YELLOW); // Change to highlight color
            }

            public void mouseExited(MouseEvent e) {
                cle.setBackground(null); // Change back to default color
            }
        });

        exi = new JButton("Exit");
        exi.setBounds(250, 90, 70, 20);
        exi.addActionListener(this);
        exi.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                exi.setBackground(Color.YELLOW); // Change to highlight color
            }

            public void mouseExited(MouseEvent e) {
                exi.setBackground(null); // Change back to default color
            }
        });

        creat = new JButton("Create Account");
        creat.setBounds(130, 130, 130, 20);
        creat.addActionListener(this);
        creat.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                creat.setBackground(Color.YELLOW); // Change to highlight color
            }

            public void mouseExited(MouseEvent e) {
                creat.setBackground(null); // Change back to default color
            }
        });

        add(m1);
        add(m3);
        add(f1);
        add(log);
        add(cle);
        add(exi);
        add(creat);

        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cle) {
            f1.setText("");
        } else if (e.getSource() == exi) {
            dispose();
        } else if (e.getSource() == creat) {
            new Screen(this);
            this.setVisible(false); // Hide current screen
        } else if (e.getSource() == log) {
            String enteredPIN = new String(f1.getPassword());
            if (enteredPIN.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your PIN", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (enteredPIN.equals(createdPIN)) {
                new LoggedInScreen(createdFullName, createdChoice); // Pass createdFullName and createdChoice to LoggedInScreen constructor
                dispose();
            } else {
                JOptionPane invalidLoginDialog = new JOptionPane("Invalid PIN!", JOptionPane.ERROR_MESSAGE);
                JDialog dialog = invalidLoginDialog.createDialog(null, "Error");
                dialog.setLocation(100, 100); // Set the position of the dialog
                dialog.setVisible(true);
            }
        }
    }
}

class Screen extends JFrame implements ActionListener {
    JLabel m4, m6, m7, m8, m9;
    JTextField s2, s4;
    JPasswordField f2, f3;
    JComboBox<String> choiceBox;
    JButton create, back;
    ms mainScreen;

    public Screen(ms mainScreen) {
        this.mainScreen = mainScreen;

        setTitle("Create Account");
        setLayout(null);

        m4 = new JLabel("Full Name:");
        m4.setBounds(50, 20, 200, 20);

        s2 = new JTextField();
        s2.setBounds(165, 20, 150, 20);

        m6 = new JLabel("PIN:");
        m6.setBounds(50, 50, 200, 20);

        f2 = new JPasswordField();
        f2.setBounds(165, 50, 150, 20);

        m7 = new JLabel("Confirm PIN:");
        m7.setBounds(50, 85, 200, 20);

        f3 = new JPasswordField();
        f3.setBounds(165, 85, 150, 20);

        m8 = new JLabel("Phone Number:");
        m8.setBounds(50, 120, 200, 20);

        s4 = new JTextField();
        s4.setBounds(165, 120, 150, 20);

        m9 = new JLabel("Gender:");
        m9.setBounds(50, 155, 200, 20);

        choiceBox = new JComboBox<>(new String[]{"", "Male", "Female"});
        choiceBox.setBounds(165, 155, 150, 20);

        create = new JButton("Create");
        create.setBounds(130, 190, 90, 20);
        create.addActionListener(this);
        create.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                create.setBackground(Color.YELLOW); // Change to highlight color
            }

            public void mouseExited(MouseEvent e) {
                create.setBackground(null); // Change back to default color
            }
        });

        back = new JButton("Back");
        back.setBounds(230, 190, 90, 20);
        back.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                back.setBackground(Color.YELLOW); // Change to highlight color
            }

            public void mouseExited(MouseEvent e) {
                back.setBackground(null); // Change back to default color
            }
        });
        back.addActionListener(this);

        add(m4);
        add(s2);
        add(m6);
        add(f2);
        add(m7);
        add(f3);
        add(m8);
        add(s4);
        add(m9);
        add(choiceBox);
        add(create);
        add(back);

        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == create) {
            String fullName = s2.getText();
            String newPIN = new String(f2.getPassword());
            String confirmPIN = new String(f3.getPassword());
            String phoneNumber = s4.getText();
            String choice = (String) choiceBox.getSelectedItem();

            // Check if any field is empty
            if (fullName.isEmpty() || newPIN.isEmpty() || confirmPIN.isEmpty() || phoneNumber.isEmpty() || choice.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (!newPIN.equals(confirmPIN)) { // Check if PINs match
                JOptionPane.showMessageDialog(this, "PINs do not match", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                mainScreen.createdFullName = fullName;
                mainScreen.createdPIN = newPIN;
                mainScreen.createdPhoneNumber = phoneNumber;
                mainScreen.createdChoice = choice;
                JOptionPane.showMessageDialog(this, "Account created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                mainScreen.setVisible(true); // Show the main screen
                this.dispose(); // Close the account creation screen
            }
        } else if (e.getSource() == back) {
            mainScreen.setVisible(true); // Show the main screen
            this.dispose(); // Close the account creation screen
        }
    }
}

class LoggedInScreen extends JFrame {
    public LoggedInScreen(String fullName, String choice) {
        setTitle("Logged In");
        setLayout(new FlowLayout());

        JLabel welcomeMessage = new JLabel("Welcome, " + fullName + "! You are logged in.");
        add(welcomeMessage);

        JLabel choiceLabel = new JLabel("Your choice is: " + choice);
        add(choiceLabel);

        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

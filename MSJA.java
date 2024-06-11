package msja;



import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.HashMap;

public class MSJA {
    public static void main(String[] args) {
        new MS();
    }
}

class MS extends JFrame implements ActionListener {

    JLabel welcomeLabel, pinLabel;
    JPasswordField pinField;
    JButton loginButton, clearButton, exitButton, createButton;

    String createdPIN;
    String createdFullName;
    String createdPhoneNumber;
    String createdChoice;

    JPanel backgroundPanel;
    private static HashMap<String, String> accounts = new HashMap<>();

    public MS() {
        // Custom panel for background
        backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load background image
                Image img = new ImageIcon("ms.jpg").getImage();
                // Draw image at the specified location
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };

        backgroundPanel.setLayout(new GridBagLayout());
        setContentPane(backgroundPanel);

        // Set up the frame
        setTitle("MoneySafeGuard Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.CENTER;

        // Create and add components
        welcomeLabel = new JLabel("Welcome to MoneySafeGuard", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        backgroundPanel.add(welcomeLabel, gbc);

        pinLabel = new JLabel("Enter PIN:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        backgroundPanel.add(pinLabel, gbc);

        pinField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        backgroundPanel.add(pinField, gbc);

        loginButton = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 2;
        backgroundPanel.add(loginButton, gbc);
        loginButton.addActionListener(this);
        loginButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                loginButton.setBackground(Color.YELLOW); // Change to highlight color
            }

            public void mouseExited(MouseEvent e) {
                loginButton.setBackground(null); // Change back to default color
            }
        });

        clearButton = new JButton("Clear");
        gbc.gridx = 1;
        gbc.gridy = 2;
        backgroundPanel.add(clearButton, gbc);
        clearButton.addActionListener(this);
        clearButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                clearButton.setBackground(Color.YELLOW); // Change to highlight color
            }

            public void mouseExited(MouseEvent e) {
                clearButton.setBackground(null); // Change back to default color
            }
        });

        exitButton = new JButton("Exit");
        gbc.gridx = 0;
        gbc.gridy = 3;
        backgroundPanel.add(exitButton, gbc);
        exitButton.addActionListener(this);
        exitButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                exitButton.setBackground(Color.YELLOW); // Change to highlight color
            }

            public void mouseExited(MouseEvent e) {
                exitButton.setBackground(null); // Change back to default color
            }
        });

        createButton = new JButton("Create");
        gbc.gridx = 1;
        gbc.gridy = 3;
        backgroundPanel.add(createButton, gbc);
        createButton.addActionListener(this);
        createButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                createButton.setBackground(Color.YELLOW); // Change to highlight color
            }

            public void mouseExited(MouseEvent e) {
                createButton.setBackground(null); // Change back to default color
            }
        });
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String enteredPIN = new String(pinField.getPassword());
            if (enteredPIN.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your PIN", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (enteredPIN.length() < 6) {
                JOptionPane.showMessageDialog(this, "PIN should be at least 6 digits long", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (enteredPIN.equals(createdPIN)) {
                // Check if createdFullName and createdChoice are not null
                if (createdFullName != null && createdChoice != null) {
                    new NewScreen(this, createdFullName, createdChoice);
                    this.setVisible(false);
                    dispose();
                } else {
                    // Handle the case where createdFullName or createdChoice is null
                    JOptionPane.showMessageDialog(this, "Account information missing", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid PIN!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == clearButton) {
            pinField.setText("");
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        } else if (e.getSource() == createButton) {
            new Screen(this);
            this.setVisible(false);
        }
    }
}

class Screen extends JFrame implements ActionListener {
    JLabel fullNameLabel, pinLabel, confirmPinLabel, phoneNumberLabel, choiceLabel;
    JTextField fullNameField, phoneNumberField;
    JPasswordField pinField, confirmPinField;
    JComboBox<String> choiceBox;
    JButton createButton, backButton;
    MS mainScreen;

    public Screen(MS mainScreen) {
        this.mainScreen = mainScreen;

        setTitle("Create Account");
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.CENTER;

        fullNameLabel = new JLabel("Full Name:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(fullNameLabel, gbc);

        fullNameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(fullNameField, gbc);

        pinLabel = new JLabel("PIN:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(pinLabel, gbc);

        pinField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(pinField, gbc);

        confirmPinLabel = new JLabel("Confirm PIN:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(confirmPinLabel, gbc);

        confirmPinField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(confirmPinField, gbc);

        phoneNumberLabel = new JLabel("Phone Number:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(phoneNumberLabel, gbc);

        phoneNumberField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(phoneNumberField, gbc);

        choiceLabel = new JLabel("Choose Verification Method:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(choiceLabel, gbc);

        choiceBox = new JComboBox<>(new String[]{"Email", "Phone"});
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(choiceBox, gbc);

        createButton = new JButton("Create Account");
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(createButton, gbc);
        createButton.addActionListener(this);
        createButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                createButton.setBackground(Color.YELLOW); // Change to highlight color
            }

            public void mouseExited(MouseEvent e) {
                createButton.setBackground(null); // Change back to default color
            }
        });

        backButton = new JButton("Back");
        gbc.gridx = 1;
        gbc.gridy = 5;
        add(backButton, gbc);
        backButton.addActionListener(this);
        backButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                backButton.setBackground(Color.YELLOW); // Change to highlight color
            }

            public void mouseExited(MouseEvent e) {
                backButton.setBackground(null); // Change back to default color
            }
        });
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createButton) {
            String fullName = fullNameField.getText();
            String pin = new String(pinField.getPassword());
            String confirmPin = new String(confirmPinField.getPassword());
            String phoneNumber = phoneNumberField.getText();
            String choice = (String) choiceBox.getSelectedItem();

            if (fullName.isEmpty() || pin.isEmpty() || confirmPin.isEmpty() || phoneNumber.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (pin.length() < 6) {
                JOptionPane.showMessageDialog(this, "PIN should be at least 6 digits long", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (!pin.equals(confirmPin)) {
                JOptionPane.showMessageDialog(this, "PINs do not match", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                mainScreen.createdFullName = fullName;
                mainScreen.createdPIN = pin;
                mainScreen.createdPhoneNumber = phoneNumber;
                mainScreen.createdChoice = choice;
                JOptionPane.showMessageDialog(this, "Account created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                mainScreen.setVisible(true);
                this.dispose();
            }
        } else if (e.getSource() == backButton) {
            mainScreen.setVisible(true);
            this.dispose();
        }
    }
}

class NewScreen extends JFrame implements ActionListener {
    MS login;
    String fullName;
    String choice;
    JButton withdrawButton, depositButton, balanceButton, historyButton;

    public NewScreen(MS login, String fullName, String choice) {
        this.login = login;
        this.fullName = fullName;
        this.choice = choice;
        setTitle("Logged In");
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.CENTER;

        withdrawButton = new JButton("Withdraw");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(withdrawButton, gbc);
        withdrawButton.addActionListener(this);
        withdrawButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                withdrawButton.setBackground(Color.YELLOW); // Change to highlight color
            }

            public void mouseExited(MouseEvent e) {
                withdrawButton.setBackground(null); // Change back to default color
            }
        });

        depositButton = new JButton("Deposit");
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(depositButton, gbc);
        depositButton.addActionListener(this);
        depositButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                depositButton.setBackground(Color.YELLOW); // Change to highlight color
            }

            public void mouseExited(MouseEvent e) {
                depositButton.setBackground(null); // Change back to default color
            }
        });

        balanceButton = new JButton("Balance");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(balanceButton, gbc);
        balanceButton.addActionListener(this);
        balanceButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                balanceButton.setBackground(Color.YELLOW); // Change to highlight color
            }

            public void mouseExited(MouseEvent e) {
                balanceButton.setBackground(null); // Change back to default color
            }
        });

        historyButton = new JButton("Transaction History");
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(historyButton, gbc);
        historyButton.addActionListener(this);
        historyButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                historyButton.setBackground(Color.YELLOW); // Change to highlight color
            }

            public void mouseExited(MouseEvent e) {
                historyButton.setBackground(null); // Change back to default color
            }
        });

        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == withdrawButton) {
            new WithdrawScreen(this);
            this.setVisible(false);
        }else if (e.getSource() == depositButton) {
            new depositScreen(this);
            this.setVisible(false);
        }else if (e.getSource() == balanceButton) {
            new balanceScreen(this);
            this.setVisible(false);
        
        }else if (e.getSource() ==  historyButton) {
            new historyScreen(this);
            this.setVisible(false);
        
        }
    }
}
class WithdrawScreen extends JFrame implements ActionListener {// tanong mo lng kay chatbox basta ito ilalagay kona
    
    NewScreen withdraw;

    public WithdrawScreen(NewScreen withdraw) {
        
    }

  
    public void actionPerformed(ActionEvent e) {
        
    }
    }

class depositScreen extends JFrame implements ActionListener {
    
    NewScreen deposit;

    public depositScreen(NewScreen deposit) {
        
    }

  
    public void actionPerformed(ActionEvent e) {
        
    }
}

class balanceScreen extends JFrame implements ActionListener {
    
    NewScreen deposit;

    public balanceScreen(NewScreen balance) {
        
    }

  
    public void actionPerformed(ActionEvent e) {
        
    }
    }

class historyScreen extends JFrame implements ActionListener {
    
    NewScreen history;

    public historyScreen(NewScreen history) {
        
    }

  
    public void actionPerformed(ActionEvent e) {
        
    }
    }



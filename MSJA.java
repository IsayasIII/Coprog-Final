
package last.na.to.yawa;

import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;

public class MSJA1 {
    public static void main(String[] args) {
        new MS();
    }
}

class Data {
    private static Data instance;
    int i = -1;
    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> pins = new ArrayList<>();
    ArrayList<String> choice = new ArrayList<>();
    ArrayList<Double> balance = new ArrayList<>();

    private Data() {
        // Private constructor to enforce Singleton pattern
    }

    public static Data getInstance() {
        if (instance == null) {
            instance = new Data();
        }
        return instance;
    }

    public void writeFile() {
        try (FileWriter writer = new FileWriter("C:\\Users\\isaya\\Desktop\\arrayList.txt")) {
            writer.write("Names :\n");
            for (String item : names) {
                writer.write(item + "\n");
            }

            writer.write("\nPins :\n");
            for (String item : pins) {
                writer.write(item + "\n");
            }

            writer.write("\nBalance :\n");
            for (Double item : balance) {
                writer.write(item + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    

    public void addAcc(String fullName, String pin, String choice) {
        names.add(fullName);
        pins.add(pin);
        balance.add(0.0);
        this.choice.add(choice);
        i = names.size() - 1;
    }
}

class MS extends JFrame implements ActionListener {
    JLabel welcomeLabel, pinLabel;
    JPasswordField pinField;
    JButton loginButton, clearButton, exitButton, createButton;
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
            Data data = Data.getInstance();

            if (enteredPIN.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your PIN", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (enteredPIN.length() < 6) {
                JOptionPane.showMessageDialog(this, "PIN should be at least 6 digits long", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                int pinIndex = data.pins.indexOf(enteredPIN);
                if (pinIndex != -1) {
                    data.i = pinIndex;
                    new NewScreen(this, data.names.get(data.i), data.choice.get(data.i));
                    this.setVisible(false);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid PIN!", "Error", JOptionPane.ERROR_MESSAGE);
                }
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
    String fullName;
    String pin;
    String confirmPin;
    String phoneNumber;
    String choice;

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
        setVisible (true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createButton) {
            fullName = fullNameField.getText();
            pin = new String(pinField.getPassword());
            confirmPin = new String(confirmPinField.getPassword());
            phoneNumber = phoneNumberField.getText();
            choice = (String) choiceBox.getSelectedItem();

            if (fullName.isEmpty() || pin.isEmpty() || confirmPin.isEmpty() || phoneNumber.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (pin.length() < 6) {
                JOptionPane.showMessageDialog(this, "PIN should be at least 6 digits long", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (!pin.equals(confirmPin)) {
                JOptionPane.showMessageDialog(this, "PINs do not match", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Data data = Data.getInstance();
                data.addAcc(fullName, pin, choice);
                data.writeFile();

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
        } else if (e.getSource() == depositButton) {
            new DepositScreen(this);
            this.setVisible(false);
        } else if (e.getSource() == balanceButton) {
            new BalanceScreen(this);
            this.setVisible(false);
        } else if (e.getSource() == historyButton) {
            new HistoryScreen(this);
            this.setVisible(false);
        }
    }
}

class WithdrawScreen extends JFrame implements ActionListener {
    JLabel balanceLabel;
    JButton withdrawButton;
    JTextField withdrawAmountField;
    double balance = 0.0;
    NewScreen mainScreen;
    Data data = Data.getInstance();

    public WithdrawScreen(NewScreen mainScreen) {
        this.mainScreen = mainScreen;
        this.balance = data.balance.get(data.i); // inherit balance from mainScreen

        setTitle("Withdraw Cash");
        setLayout(new FlowLayout());

        balanceLabel = new JLabel("Balance: $" + String.format("%.2f", balance));
        add(balanceLabel);

        withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(this);
        add(withdrawButton);

        withdrawAmountField = new JTextField(10);
        add(withdrawAmountField);

        setSize(300, 100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == withdrawButton) {
            try {
                double withdrawAmount = Double.parseDouble(withdrawAmountField.getText());
                if (withdrawAmount <= 0) {
                    JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a positive value.");
                } else if (withdrawAmount > balance) {
                    JOptionPane.showMessageDialog(this, "Insufficient funds.");
                } else {
                    balance -= withdrawAmount;
                    balanceLabel.setText("Balance: $" + String.format("%.2f", balance));
                    data.balance.set(data.i, balance); // update mainScreen balance
                    this.dispose(); // close this window
                    mainScreen.setVisible(true); // show mainScreen
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid amount.");
            }
        }
    }
}

class DepositScreen extends JFrame implements ActionListener {
    JLabel balanceLabel;
    JButton depositButton;
    JTextField depositAmountField;
    NewScreen mainScreen;
    Data data = Data.getInstance();

    public DepositScreen(NewScreen mainScreen) {
        this.mainScreen = mainScreen;

        setTitle("Deposit Cash");
        setLayout(new FlowLayout());

        balanceLabel = new JLabel("Balance: $" + String.format("%.2f", data.balance.get(data.i)));
        add(balanceLabel);

        depositButton = new JButton("Deposit");
        depositButton.addActionListener(this);
        add(depositButton);

        depositAmountField = new JTextField(10);
        add(depositAmountField);

        setSize(300, 100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == depositButton) {
            try {
                double depositAmount = Double.parseDouble(depositAmountField.getText());
                if (depositAmount <= 0) {
                    JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a positive value.");
                } else {
                    data.balance.set(data.i, data.balance.get(data.i) + depositAmount); // update mainScreen balance
                    balanceLabel.setText("Balance: $" + String.format("%.2f", data.balance.get(data.i)));
                    this.dispose(); // close this window
                    mainScreen.setVisible(true); // show mainScreen
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid amount.");
            }
        }
    }
}

class BalanceScreen extends JFrame {
    JLabel balanceLabel;
    NewScreen mainScreen;
    Data data = Data.getInstance();

    public BalanceScreen(NewScreen mainScreen) {
        this.mainScreen = mainScreen;

        setTitle("Check Balance");
        setLayout(new FlowLayout());

        balanceLabel = new JLabel("Balance: $" + String.format("%.2f", data.balance.get(data.i)));
        add(balanceLabel);

        setSize(300, 100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}

class HistoryScreen extends JFrame {
    NewScreen mainScreen;

    public HistoryScreen(NewScreen mainScreen) {
        this.mainScreen = mainScreen;

        setTitle("Transaction History");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}

    


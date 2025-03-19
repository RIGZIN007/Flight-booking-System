//import java.awt.*;
//import java.awt.event.*;
//import javax.swing.*;
//import java.sql.*;
//
//public class RegisterFlight extends WindowAdapter implements ActionListener {
//
//    private JFrame f;
//    private TextField name, date;
//    private Button registerButton;
//    private Choice boardingCity, destinationCity, timeChoice;
//    private Dialog dg, dg1, dg2;
//
//    public RegisterFlight() {
//        f = new JFrame();
//        JPanel login = new JPanel();
//        login.setLayout(null);
//        login.setBackground(new Color(0, 0, 0, 60));
//        login.setBounds(200, 100, 600, 800);
//
//        // Set background image
//        ImageIcon background_image = new ImageIcon(ClassLoader.getSystemResource("background4.jpg"));
//        Image i2 = background_image.getImage().getScaledInstance(2000, 1400, Image.SCALE_DEFAULT);
//        JLabel background = new JLabel("", new ImageIcon(i2), JLabel.CENTER);
//        background.setBounds(0, 0, 1600, 900);
//        background.add(login);
//        f.add(background);
//
//        // Initialize components
//        name = new TextField(20);
//        date = new TextField(20);
//        boardingCity = createChoice(new String[]{"Select city", "Hyderabad", "Delhi", "Kolkata", "Mumbai"});
//        destinationCity = createChoice(new String[]{"Select city", "Hyderabad", "Delhi", "Kolkata", "Mumbai"});
//        timeChoice = createChoice(new String[]{"Select time", "1:00 AM", "7:00 AM", "8:00 PM", "9:00 PM"});
//
//        registerButton = new Button("Register Flight");
//
//        // Add components to login panel
//        addLabelAndField(login, "Flight Name:", name, 95);
//        addChoiceAndLabel(login, "Select Boarding:", boardingCity, 145);
//        addChoiceAndLabel(login, "Select Destination:", destinationCity, 195);
//        addChoiceAndLabel(login, "Select Time", timeChoice, 295);
//        addLabelAndField(login, "Enter your Date", date, 245);
//        login.add(registerButton);
//
//        // Register action listener
//        registerButton.addActionListener(this);
//
//        // JFrame settings
//        f.setLayout(null);
//        f.setVisible(true);
//        f.setSize(1200, 700);
//        f.setTitle("Register Flight");
//
//        // Final button settings
//        registerButton.setBounds(150, 400, 100, 50);
//    }
//
//    private void addLabelAndField(JPanel panel, String labelText, TextField field, int yPosition) {
//        Label label = new Label(labelText, Label.CENTER);
//        label.setForeground(Color.WHITE);
//        label.setBounds(30, yPosition, 100, 20);
//        panel.add(label);
//        field.setBounds(180, yPosition, 200, 20);
//        panel.add(field);
//    }
//
//    private void addChoiceAndLabel(JPanel panel, String labelText, Choice choice, int yPosition) {
//        Label label = new Label(labelText, Label.CENTER);
//        label.setForeground(Color.WHITE);
//        label.setBounds(20, yPosition, 120, 30);
//        panel.add(label);
//        choice.setBounds(180, yPosition, 200, 20);
//        panel.add(choice);
//    }
//
//    private Choice createChoice(String[] items) {
//        Choice choice = new Choice();
//        for (String item : items) {
//            choice.add(item);
//        }
//        return choice;
//    }
//
//    public void actionPerformed(ActionEvent ae) {
//        try {
//            if (name.getText().isEmpty() || date.getText().isEmpty()) {
//                showDialog("Oops !!!", "Empty Fields are required!!", dg);
//            } else if (boardingCity.getSelectedItem().equals(destinationCity.getSelectedItem())) {
//                showDialog("Oops !!!", "Boarding and destination cannot be same!", dg1);
//            } else {
//                // Insert flight registration into database
//                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/airline", "root", "");
//                     PreparedStatement stmt = con.prepareStatement("INSERT INTO registerdflights VALUES(?,?,?,?,?)")) {
//
//                    stmt.setString(1, name.getText());
//                    stmt.setString(2, boardingCity.getSelectedItem());
//                    stmt.setString(3, destinationCity.getSelectedItem());
//                    stmt.setString(4, date.getText());
//                    stmt.setString(5, timeChoice.getSelectedItem());
//                    stmt.executeUpdate();
//
//                    showDialog("Kudos !!!", "Congratulations!! Flight registered successfully", dg2);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void showDialog(String title, String message, Dialog dialog) {
//        dialog = new Dialog(f, title, true);
//        dialog.add(new Label(message));
//        dialog.addWindowListener(this);
//        dialog.pack();
//        dialog.setLocationRelativeTo(f);
//        dialog.setLocation(new Point(80, 80));
//        dialog.setSize(300, 300);
//        dialog.setVisible(true);
//    }
//
//    public void windowClosing(WindowEvent e) {
//        if (e.getSource() == dg) dg.dispose();
//        if (e.getSource() == dg1) dg1.dispose();
//        if (e.getSource() == dg2) dg2.dispose();
//    }
//
//    public static void main(String[] args) {
//        new RegisterFlight();
//    }
//}
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RegisterFlight extends JFrame implements ActionListener {

    private JTextField nameField, dateField;
    private JComboBox<String> boardingCityBox, destinationCityBox, timeBox;
    private JButton registerButton;

    public RegisterFlight() {
        setTitle("Register Flight");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        // Main Panel with Padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title Label
        JLabel titleLabel = new JLabel("Register Flight");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Flight Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Flight Name:"), gbc);

        gbc.gridx = 1;
        nameField = new JTextField(20);
        formPanel.add(nameField, gbc);

        // Boarding City
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Boarding City:"), gbc);

        gbc.gridx = 1;
        boardingCityBox = createComboBox(new String[]{"Select city", "Hyderabad", "Delhi", "Kolkata", "Mumbai"});
        formPanel.add(boardingCityBox, gbc);

        // Destination City
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Destination City:"), gbc);

        gbc.gridx = 1;
        destinationCityBox = createComboBox(new String[]{"Select city", "Hyderabad", "Delhi", "Kolkata", "Mumbai"});
        formPanel.add(destinationCityBox, gbc);

        // Flight Time
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Flight Time:"), gbc);

        gbc.gridx = 1;
        timeBox = createComboBox(new String[]{"Select time", "1:00 AM", "7:00 AM", "8:00 PM", "9:00 PM"});
        formPanel.add(timeBox, gbc);

        // Date
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(new JLabel("Date (YYYY-MM-DD):"), gbc);

        gbc.gridx = 1;
        dateField = new JTextField(20);
        formPanel.add(dateField, gbc);

        // Add formPanel to mainPanel
        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Register Button
        registerButton = new JButton("Register Flight");
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        registerButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(registerButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add Main Panel to Frame
        add(mainPanel);

        setVisible(true);
    }

    private JComboBox<String> createComboBox(String[] items) {
        return new JComboBox<>(items);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            String flightName = nameField.getText().trim();
            String date = dateField.getText().trim();
            String boardingCity = (String) boardingCityBox.getSelectedItem();
            String destinationCity = (String) destinationCityBox.getSelectedItem();
            String time = (String) timeBox.getSelectedItem();

            if (flightName.isEmpty() || date.isEmpty() || boardingCity.equals("Select city") || destinationCity.equals("Select city") || time.equals("Select time")) {
                JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (boardingCity.equals(destinationCity)) {
                JOptionPane.showMessageDialog(this, "Boarding and destination cities cannot be the same!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/airline", "root", "");
                     PreparedStatement stmt = con.prepareStatement("INSERT INTO registerdflights VALUES(?,?,?,?,?)")) {

                    stmt.setString(1, flightName);
                    stmt.setString(2, boardingCity);
                    stmt.setString(3, destinationCity);
                    stmt.setString(4, date);
                    stmt.setString(5, time);
                    stmt.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Flight registered successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error while registering flight.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RegisterFlight::new);
    }
}

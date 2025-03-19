//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.sql.*;
//
//public class Booking extends WindowAdapter implements ActionListener {
//    private JFrame f;
//    private JTextField name, phone, unique, date;
//    private Choice c, d, time;
//    private JButton bookButton;
//
//    public Booking() {
//        f = new JFrame("Book Flight");
//        JPanel login = new JPanel();
//        login.setLayout(null);
//        login.setBounds(100, 100, 600, 400);
//        login.setBackground(new Color(0, 0, 0, 60));
//
//        JLabel nameLabel = new JLabel("Full Name:");
//        JLabel phoneLabel = new JLabel("Phone No.:");
//        JLabel boardingLabel = new JLabel("Select Boarding:");
//        JLabel destinationLabel = new JLabel("Select Destination:");
//        JLabel timeLabel = new JLabel("Select Time");
//        JLabel dateLabel = new JLabel("Enter Date:");
//        JLabel uniqueLabel = new JLabel("Unique Code:");
//
//        name = new JTextField(20);
//        phone = new JTextField(20);
//        unique = new JTextField(20);
//        date = new JTextField(20);
//
//        c = new Choice();
//        d = new Choice();
//        time = new Choice();
//
//        c.add("Hyderabad");
//        d.add("Hyderabad");
//        time.add("1:00 AM");
//
//        bookButton = new JButton("Book Flight");
//        bookButton.setBounds(220, 300, 150, 30);
//        bookButton.addActionListener(this);
//
//        // Set bounds for components
//        nameLabel.setBounds(20, 45, 100, 20); name.setBounds(180, 45, 200, 20);
//        phoneLabel.setBounds(20, 95, 100, 20); phone.setBounds(180, 95, 200, 20);
//        boardingLabel.setBounds(20, 145, 120, 20); c.setBounds(180, 145, 200, 20);
//        destinationLabel.setBounds(20, 195, 120, 20); d.setBounds(180, 195, 200, 20);
//        timeLabel.setBounds(20, 245, 100, 20); time.setBounds(180, 245, 200, 20);
//        dateLabel.setBounds(20, 295, 100, 20); date.setBounds(180, 295, 200, 20);
//        uniqueLabel.setBounds(20, 345, 100, 20); unique.setBounds(180, 345, 200, 20);
//
//        login.add(nameLabel); login.add(name);
//        login.add(phoneLabel); login.add(phone);
//        login.add(boardingLabel); login.add(c);
//        login.add(destinationLabel); login.add(d);
//        login.add(timeLabel); login.add(time);
//        login.add(dateLabel); login.add(date);
//        login.add(uniqueLabel); login.add(unique);
//        login.add(bookButton);
//
//        f.add(login);
//        f.setSize(800, 500);
//        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        f.setVisible(true);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        try {
//            if (name.getText().isEmpty() || phone.getText().isEmpty() || unique.getText().isEmpty() || date.getText().isEmpty()) {
//                JOptionPane.showMessageDialog(f, "Please fill all fields.");
//                return;
//            }
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/airline", "root", "");
//            PreparedStatement stmt = con.prepareStatement("INSERT INTO bookings (name, phone, boarding, destination, date, time, uniqueCode) VALUES (?, ?, ?, ?, ?, ?, ?)");
//            stmt.setString(1, name.getText());
//            stmt.setString(2, phone.getText());
//            stmt.setString(3, c.getSelectedItem());
//            stmt.setString(4, d.getSelectedItem());
//            stmt.setString(5, date.getText());
//            stmt.setString(6, time.getSelectedItem());
//            stmt.setString(7, unique.getText());
//            stmt.executeUpdate();
//            stmt.close();
//            con.close();
//            JOptionPane.showMessageDialog(f, "Ticket booked successfully.");
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    @Override
//    public void windowClosing(WindowEvent e) {
//        f.dispose();
//    }
//
//    public static void main(String[] args) {
//        new Booking();
//    }
//}
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Booking extends WindowAdapter implements ActionListener {
    private JFrame frame;
    private JButton bookButton, cancelButton;

    public Booking() {
        // Frame setu
        frame = new JFrame("Airline Booking - Passenger");
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);  // Center the frame

        // Background panel with gradient
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Create gradient background
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(0, 128, 255);  // Light blue
                Color color2 = new Color(0, 51, 102);   // Dark blue
                GradientPaint gradient = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        frame.add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));  // Use BoxLayout for vertical layout

        // Welcome label with modern font
        JLabel welcomeLabel = new JLabel("Welcome to Airline Booking", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Roboto", Font.BOLD, 30));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(50)); // Spacer
        panel.add(welcomeLabel);
        panel.add(Box.createVerticalStrut(30)); // Spacer

        // Create buttons with modern design and hover effects
        bookButton = createButton("Book Flight");
        cancelButton = createButton("Cancel Ticket");

        // Add buttons with spacing
        panel.add(bookButton);
        panel.add(Box.createVerticalStrut(20));  // Spacer
        panel.add(cancelButton);

        // Final frame setup
        frame.setVisible(true);
    }

    // Create button with flat design, modern style, and hover effects
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 102, 204));  // Light blue color
        button.setFocusPainted(false);  // Remove focus outline
        button.setBorder(BorderFactory.createEmptyBorder());  // Remove border
        button.setPreferredSize(new Dimension(250, 50));
        button.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button

        // Flat button style and hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 128, 255));  // Light blue on hover
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));  // Change cursor on hover
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 102, 204));  // Reset to original color
                button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));  // Reset cursor
            }
        });

        button.addActionListener(this);
        return button;
    }

    // Handle button clicks for booking or canceling
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bookButton) {
            new FlightOptions();  // Redirect to booking page
        } else if (e.getSource() == cancelButton) {
            new Cancel();  // Redirect to cancel page
        }
    }

    // Handle window closing event
    @Override
    public void windowClosing(WindowEvent e) {
        frame.dispose();
    }

    // Main method to launch the application
    public static void main(String[] args) {
        new Airline1();
    }
}

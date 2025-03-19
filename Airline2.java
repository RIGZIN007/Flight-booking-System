//////import javax.swing.*;
//////import java.awt.*;
//////import java.awt.event.*;
//////import java.sql.*;
//////
//////public class Airline2 extends WindowAdapter implements ActionListener {
//////
//////    private JFrame f;
//////    private TextField t1, t2;
//////    private Button b1, b2;
//////    private Dialog dg1, dg2, dg3;
//////
//////    public Airline2() {
//////        f = new JFrame("Admin Login");
//////        f.setSize(1200, 700);
//////        f.setLayout(null);
//////
//////        JPanel login = new JPanel();
//////        login.setLayout(null);
//////        login.setBounds(250, 175, 600, 350);
//////        login.setBackground(new Color(0, 0, 0, 60));
//////
//////        ImageIcon background_image = new ImageIcon(getClass().getResource("background4.jpg"));
//////        JLabel background = new JLabel(new ImageIcon(background_image.getImage().getScaledInstance(2000, 1400, Image.SCALE_DEFAULT)));
//////        background.setBounds(0, 0, 1600, 900);
//////        f.add(background);
//////        background.add(login);
//////
//////        // Labels and TextFields
//////        login.add(createLabel("ADMIN LOGIN", 200, 100, 200, 40, Color.WHITE));
//////        login.add(createLabel("User Name", 100, 150, 100, 30, Color.WHITE));
//////        login.add(createLabel("Password", 100, 190, 100, 30, Color.WHITE));
//////
//////        t1 = new TextField(30);
//////        t2 = new TextField(30);
//////        t2.setEchoChar('*');
//////        t1.setBounds(220, 150, 200, 30);
//////        t2.setBounds(220, 190, 200, 30);
//////
//////        // Buttons
//////        b1 = createButton("Login", 200, 240);
//////        b2 = createButton("Cancel", 300, 240);
//////
//////        // Add components
//////        login.add(t1); login.add(t2); login.add(b1); login.add(b2);
//////
//////        // Event Listeners
//////        b1.addActionListener(this);
//////        b2.addActionListener(this);
//////        f.addWindowListener(this);
//////
//////        f.setVisible(true);
//////    }
//////
//////    // Helper method for creating labels
//////    private Label createLabel(String text, int x, int y, int w, int h, Color color) {
//////        Label label = new Label(text);
//////        label.setBounds(x, y, w, h);
//////        label.setForeground(color);
//////        return label;
//////    }
//////
//////    // Helper method for creating buttons
//////    private Button createButton(String text, int x, int y) {
//////        Button button = new Button(text);
//////        button.setBounds(x, y, 55, 30);
//////        button.setBackground(Color.BLACK);
//////        button.setForeground(Color.WHITE);
//////        return button;
//////    }
//////
//////    @Override
//////    public void actionPerformed(ActionEvent ae) {
//////        if (ae.getSource() == b1) {
//////            if (t1.getText().isEmpty() || t2.getText().isEmpty()) {
//////                showDialog("Oops,You can't Enter the leave any field empty", "Please fill the fields");
//////            } else {
//////                validateLogin();
//////            }
//////        } else {
//////            f.dispose();
//////        }
//////    }
//////
//////    // Validate login
//////    private void validateLogin() {
//////        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/airline", "root", "")) {
//////            Class.forName("com.mysql.cj.jdbc.Driver");
//////            String query = "SELECT password FROM adminlogins WHERE username=?";
//////            try (PreparedStatement pst = con.prepareStatement(query)) {
//////                pst.setString(1, t1.getText());
//////                ResultSet rs = pst.executeQuery();
//////                if (rs.next() && rs.getString(1).equals(t2.getText())) {
//////                    showDialog("Congrats, Login Successful", "Login Successfull");
//////                    new RegisterFlight();  // Navigate to Register Flight
//////                } else {
//////                    showDialog("Username or password is incorrect", "Login Failed");
//////                }
//////            }
//////        } catch (Exception e) {
//////            e.printStackTrace();
//////        }
//////    }
//////
//////    // Show dialog messages
//////    private void showDialog(String message, String title) {
//////        Dialog dialog = new Dialog(f, title, true);
//////        dialog.setSize(280, 280);
//////        dialog.setLocationRelativeTo(f);
//////        dialog.setLocation(new Point(80, 80));
//////        dialog.add(new Label(message));
//////        dialog.addWindowListener(this);
//////        dialog.setVisible(true);
//////    }
//////
//////    @Override
//////    public void windowClosing(WindowEvent e) {
//////        if (e.getSource() instanceof Dialog) {
//////            ((Dialog) e.getSource()).dispose();
//////        } else if (e.getSource() == f) {
//////            f.dispose();
//////        }
//////    }
//////
//////    public static void main(String[] args) {
//////        new Airline2();
//////    }
//////}
////import javax.swing.*;
////import java.awt.*;
////import java.awt.event.*;
////import java.sql.*;
////
////public class Airline2 extends WindowAdapter implements ActionListener {
////
////    private JFrame f;
////    private JTextField t1, t2;
////    private JButton b1, b2;
////    private JPanel loginPanel;
////
////    public Airline2() {
////        // Initialize JFrame
////        f = new JFrame("Admin Login");
////        f.setSize(600, 400);
////        f.setLocationRelativeTo(null);  // Center the window
////        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////        f.setLayout(new BorderLayout());
////
////        // Set background image
////        ImageIcon background_image = new ImageIcon(getClass().getResource("background4.jpg"));
////        JLabel background = new JLabel(new ImageIcon(background_image.getImage().getScaledInstance(600, 400, Image.SCALE_DEFAULT)));
////        f.add(background);
////
////        // Create and customize login panel
////        loginPanel = new JPanel();
////        loginPanel.setLayout(new GridBagLayout());
////        loginPanel.setOpaque(false);
////        loginPanel.setBackground(new Color(0, 0, 0, 180));
////        background.add(loginPanel);
////
////        // Create components
////        JLabel headerLabel = createLabel("ADMIN LOGIN", 24, Color.WHITE);
////        JLabel userLabel = createLabel("User Name", 18, Color.WHITE);
////        JLabel passLabel = createLabel("Password", 18, Color.WHITE);
////
////        t1 = new JTextField(20);
////        t2 = new JPasswordField(20);  // JPasswordField for better security
////        b1 = createButton("Login", Color.GREEN);
////        b2 = createButton("Cancel", Color.RED);
////
////        // Add components to the panel
////        GridBagConstraints gbc = new GridBagConstraints();
////        gbc.insets = new Insets(10, 10, 10, 10);
////        gbc.gridx = 0;
////        gbc.gridy = 0;
////        loginPanel.add(headerLabel, gbc);
////
////        gbc.gridx = 0;
////        gbc.gridy = 1;
////        loginPanel.add(userLabel, gbc);
////
////        gbc.gridx = 1;
////        gbc.gridy = 1;
////        loginPanel.add(t1, gbc);
////
////        gbc.gridx = 0;
////        gbc.gridy = 2;
////        loginPanel.add(passLabel, gbc);
////
////        gbc.gridx = 1;
////        gbc.gridy = 2;
////        loginPanel.add(t2, gbc);
////
////        gbc.gridx = 0;
////        gbc.gridy = 3;
////        loginPanel.add(b1, gbc);
////
////        gbc.gridx = 1;
////        gbc.gridy = 3;
////        loginPanel.add(b2, gbc);
////
////        // Event listeners
////        b1.addActionListener(this);
////        b2.addActionListener(this);
////
////        // Make window visible
////        f.setVisible(true);
////    }
////
////    // Helper method to create labels with text and font size
////    private JLabel createLabel(String text, int fontSize, Color color) {
////        JLabel label = new JLabel(text);
////        label.setFont(new Font("Arial", Font.PLAIN, fontSize));
////        label.setForeground(color);
////        return label;
////    }
////
////    // Helper method to create buttons with text and color
////    private JButton createButton(String text, Color color) {
////        JButton button = new JButton(text);
////        button.setFont(new Font("Arial", Font.BOLD, 14));
////        button.setBackground(color);
////        button.setForeground(Color.WHITE);
////        button.setFocusPainted(false);
////        button.setBorderPainted(false);
////        button.setPreferredSize(new Dimension(100, 40));
////        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
////        button.addMouseListener(new java.awt.event.MouseAdapter() {
////            public void mouseEntered(java.awt.event.MouseEvent evt) {
////                button.setBackground(color.darker());
////            }
////            public void mouseExited(java.awt.event.MouseEvent evt) {
////                button.setBackground(color);
////            }
////        });
////        return button;
////    }
////
////    @Override
////    public void actionPerformed(ActionEvent ae) {
////        if (ae.getSource() == b1) {
////            if (t1.getText().isEmpty() || t2.getText().isEmpty()) {
////                showDialog("Oops, You can't leave any field empty", "Please fill the fields");
////            } else {
////                validateLogin();
////            }
////        } else {
////            f.dispose();
////        }
////    }
////
////    // Validate login against database
////    private void validateLogin() {
////        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/airline", "root", "")) {
////            Class.forName("com.mysql.cj.jdbc.Driver");
////            String query = "SELECT password FROM adminlogins WHERE username=?";
////            try (PreparedStatement pst = con.prepareStatement(query)) {
////                pst.setString(1, t1.getText());
////                ResultSet rs = pst.executeQuery();
////                new RegisterFlight();
//////                if (rs.next() && rs.getString(1).equals(new String(t2.getPassword()))) {
//////                    showDialog("Congrats, Login Successful", "Login Successful");
//////                    new RegisterFlight();  // Open Register Flight window
//////                } else {
//////                    showDialog("Username or password is incorrect", "Login Failed");
//////                }
////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////    }
////
////    // Show a dialog box with the message
////    private void showDialog(String message, String title) {
////        JOptionPane.showMessageDialog(f, message, title, JOptionPane.INFORMATION_MESSAGE);
////    }
////
////    public static void main(String[] args) {
////        new Airline2();
////    }
////}
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.sql.*;
//
//public class Airline2 extends WindowAdapter implements ActionListener {
//
//    private JFrame f;
//    private JTextField t1, t2;
//    private JButton b1, b2;
//    private JPanel loginPanel;
//
//    public Airline2() {
//        // Initialize JFrame
//        f = new JFrame("Admin Login");
//        f.setSize(600, 400);
//        f.setLocationRelativeTo(null);  // Center the window
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.setLayout(new BorderLayout());
//
//        // Set background gradient
//        JPanel backgroundPanel = new JPanel() {
//            @Override
//            protected void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                Graphics2D g2d = (Graphics2D) g;
//                GradientPaint gradient = new GradientPaint(0, 0, Color.CYAN, 600, 400, Color.MAGENTA, true);
//                g2d.setPaint(gradient);
//                g2d.fillRect(0, 0, getWidth(), getHeight());
//            }
//        };
//        f.add(backgroundPanel);
//
//        // Create and customize login panel
//        loginPanel = new JPanel();
//        loginPanel.setLayout(new GridBagLayout());
//        loginPanel.setOpaque(false);
//        backgroundPanel.add(loginPanel);
//
//        // Create components
//        JLabel headerLabel = createLabel("ADMIN LOGIN", 24, Color.WHITE);
//        JLabel userLabel = createLabel("User Name", 18, Color.WHITE);
//        JLabel passLabel = createLabel("Password", 18, Color.WHITE);
//
//        t1 = new JTextField(20);
//        t2 = new JPasswordField(20);  // JPasswordField for better security
//        b1 = createButton("Login", Color.GREEN);
//        b2 = createButton("Cancel", Color.RED);
//
//        // Add components to the panel with GridBagLayout
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(10, 10, 10, 10);
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        loginPanel.add(headerLabel, gbc);
//
//        gbc.gridx = 0;
//        gbc.gridy = 1;
//        loginPanel.add(userLabel, gbc);
//
//        gbc.gridx = 1;
//        gbc.gridy = 1;
//        loginPanel.add(t1, gbc);
//
//        gbc.gridx = 0;
//        gbc.gridy = 2;
//        loginPanel.add(passLabel, gbc);
//
//        gbc.gridx = 1;
//        gbc.gridy = 2;
//        loginPanel.add(t2, gbc);
//
//        gbc.gridx = 0;
//        gbc.gridy = 3;
//        loginPanel.add(b1, gbc);
//
//        gbc.gridx = 1;
//        gbc.gridy = 3;
//        loginPanel.add(b2, gbc);
//
//        // Add tooltips
//        t1.setToolTipText("Enter your username");
//        t2.setToolTipText("Enter your password");
//        b1.setToolTipText("Click to log in");
//        b2.setToolTipText("Click to cancel");
//
//        // Event listeners
//        b1.addActionListener(this);
//        b2.addActionListener(this);
//
//        // Make window visible
//        f.setVisible(true);
//    }
//
//    // Helper method to create labels with text and font size
//    private JLabel createLabel(String text, int fontSize, Color color) {
//        JLabel label = new JLabel(text);
//        label.setFont(new Font("Arial", Font.BOLD, fontSize));
//        label.setForeground(color);
//        return label;
//    }
//
//    // Helper method to create buttons with text and color
//    private JButton createButton(String text, Color color) {
//        JButton button = new JButton(text);
//        button.setFont(new Font("Arial", Font.BOLD, 14));
//        button.setBackground(color);
//        button.setForeground(Color.WHITE);
//        button.setFocusPainted(false);
//        button.setBorderPainted(false);
//        button.setPreferredSize(new Dimension(100, 40));
//        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
//
//        // Adding hover effects for the buttons
//        button.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseEntered(java.awt.event.MouseEvent evt) {
//                button.setBackground(color.darker());
//            }
//            public void mouseExited(java.awt.event.MouseEvent evt) {
//                button.setBackground(color);
//            }
//        });
//        return button;
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent ae) {
//        if (ae.getSource() == b1) {
//            if (t1.getText().isEmpty() || t2.getText().isEmpty()) {
//                showDialog("Oops, You can't leave any field empty", "Please fill the fields");
//            } else {
//                validateLogin();
//            }
//        } else {
//            f.dispose();
//        }
//    }
//
//    // Validate login against database
//    private void validateLogin() {
//        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/airline", "root", "")) {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            String query = "SELECT password FROM adminlogins WHERE username=?";
//            try (PreparedStatement pst = con.prepareStatement(query)) {
//                pst.setString(1, t1.getText());
//                ResultSet rs = pst.executeQuery();
//                if (rs.next() && rs.getString(1).equals(new String(t2.getPassword()))) {
//                    showDialog("Congrats, Login Successful", "Login Successful");
//                    new RegisterFlight();  // Open Register Flight window
//                } else {
//                    showDialog("Username or password is incorrect", "Login Failed");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Show a dialog box with the message
//    private void showDialog(String message, String title) {
//        JOptionPane.showMessageDialog(f, message, title, JOptionPane.INFORMATION_MESSAGE);
//    }
//
//    public static void main(String[] args) {
//        new Airline2();
//    }
//}
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Airline2 extends WindowAdapter implements ActionListener {

    private JFrame f;
    private JTextField t1;
    private JPasswordField t2;  // Change to JPasswordField
    private JButton b1, b2;
    private JPanel loginPanel;

    public Airline2() {
        // Initialize JFrame
        f = new JFrame("Admin Login");
        f.setSize(600, 400);
        f.setLocationRelativeTo(null);  // Center the window
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());

        // Set background gradient
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(0, 0, Color.CYAN, 600, 400, Color.MAGENTA, true);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        f.add(backgroundPanel);

        // Create and customize login panel
        loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setOpaque(false);
        backgroundPanel.add(loginPanel);

        // Create components
        JLabel headerLabel = createLabel("ADMIN LOGIN", 24, Color.WHITE);
        JLabel userLabel = createLabel("User Name", 18, Color.WHITE);
        JLabel passLabel = createLabel("Password", 18, Color.WHITE);

        t1 = new JTextField(20);
        t2 = new JPasswordField(20);  // Changed to JPasswordField for password input
        b1 = createButton("Login", Color.GREEN);
        b2 = createButton("Cancel", Color.RED);

        // Add components to the panel with GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(headerLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        loginPanel.add(userLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        loginPanel.add(t1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        loginPanel.add(passLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        loginPanel.add(t2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        loginPanel.add(b1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        loginPanel.add(b2, gbc);

        // Add tooltips
        t1.setToolTipText("Enter your username");
        t2.setToolTipText("Enter your password");
        b1.setToolTipText("Click to log in");
        b2.setToolTipText("Click to cancel");

        // Event listeners
        b1.addActionListener(this);
        b2.addActionListener(this);

        // Make window visible
        f.setVisible(true);
    }

    // Helper method to create labels with text and font size
    private JLabel createLabel(String text, int fontSize, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, fontSize));
        label.setForeground(color);
        return label;
    }

    // Helper method to create buttons with text and color
    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(100, 40));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Adding hover effects for the buttons
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            if (t1.getText().isEmpty() || t2.getPassword().length == 0) {  // Use getPassword() for JPasswordField
                showDialog("Oops, You can't leave any field empty", "Please fill the fields");
            } else {
                validateLogin();
            }
        } else {
            f.dispose();
        }
    }

    // Validate login against database
    private void validateLogin() {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/airline", "root", "")) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String query = "SELECT password FROM adminlogins WHERE username=?";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, t1.getText());
                ResultSet rs = pst.executeQuery();
                if (rs.next() && rs.getString(1).equals(new String(t2.getPassword()))) {  // Convert char[] to String
                    showDialog("Congrats, Login Successful", "Login Successful");
                    new RegisterFlight();  // Open Register Flight window
                } else {
                    showDialog("Username or password is incorrect", "Login Failed");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Show a dialog box with the message
    private void showDialog(String message, String title) {
        JOptionPane.showMessageDialog(f, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new Airline2();
    }
}



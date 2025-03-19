//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//
//public class Airline1 extends WindowAdapter implements ActionListener {
//    private JFrame f;
//    private JButton book, cancel;
//
//    public Airline1() {
//        f = new JFrame("Airline Booking - Passenger");
//        JPanel login = new JPanel();
//        login.setLayout(null);
//        login.setBackground(new Color(0, 0, 0, 60));
//        login.setBounds(100, 100, 600, 400);
//
//        JLabel welcome = new JLabel("Welcome to Airline Booking");
//        welcome.setFont(new Font("Algerian", Font.BOLD, 20));
//        welcome.setForeground(Color.WHITE);
//        welcome.setBounds(160, 50, 300, 40);
//
//        book = new JButton("Book Flight");
//        cancel = new JButton("Cancel Ticket");
//        book.setBounds(220, 150, 150, 30);
//        cancel.setBounds(220, 200, 150, 30);
//        book.addActionListener(this);
//        cancel.addActionListener(this);
//
//        login.add(welcome);
//        login.add(book);
//        login.add(cancel);
//        f.add(login);
//
//        f.setSize(800, 500);
//        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        f.setVisible(true);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == book) {
//            new Booking();
//        } else if (e.getSource() == cancel) {
//            new Cancel();
//        }
//    }
//
//    @Override
//    public void windowClosing(WindowEvent e) {
//        f.dispose();
//    }
//
//    public static void main(String[] args) {
//        new Airline1();
//    }
//}
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class   Airline1 extends WindowAdapter implements ActionListener {
    private JFrame frame;
    private JButton bookButton, cancelButton;

    public Airline1() {
        // Frame setup
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
                Color color2 = new Color(0, 0, 128);    // Dark blue
                GradientPaint gradient = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        frame.add(panel);
        panel.setLayout(new GridBagLayout());  // Use GridBagLayout for flexibility

        // Welcome label with modern font
        JLabel welcomeLabel = new JLabel("Welcome to Airline Booking", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Roboto", Font.BOLD, 28));
        welcomeLabel.setForeground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 2;
        gbc.insets = new Insets(40, 0, 30, 0);  // Add some space around the label
        panel.add(welcomeLabel, gbc);

        // Create buttons with modern design and hover effects
        bookButton = createButton("Book Flight");
        cancelButton = createButton("Cancel Ticket");

        // Button positioning
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.gridy = 1;
        panel.add(bookButton, gbc);

        gbc.gridy = 2;
        panel.add(cancelButton, gbc);

        // Final frame setup
        frame.setVisible(true);
    }

    // Create button with flat design, modern style, and hover effects
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 20));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 0, 128));  // Dark blue color
        button.setFocusPainted(false);  // Remove focus outline
        button.setBorder(BorderFactory.createEmptyBorder());  // Remove border
        button.setPreferredSize(new Dimension(250, 50));

        // Flat button style and hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 0, 255));  // Light blue on hover
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));  // Change cursor on hover
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 0, 128));  // Reset to original dark blue
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
            new Booking();  // Redirect to booking page
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

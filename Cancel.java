//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.sql.*;
//
//public class Cancel extends WindowAdapter implements ActionListener {
//    private JFrame f;
//    private JTextField uniqueCode;
//    private JButton cancelButton;
//
//    public Cancel() {
//        f = new JFrame("Ticket Cancellation");
//        JPanel login = new JPanel();
//        login.setLayout(null);
//        login.setBounds(100, 100, 600, 400);
//        login.setBackground(new Color(0, 0, 0, 60));
//
//        JLabel uniqueLabel = new JLabel("Unique Code:");
//        uniqueCode = new JTextField(20);
//        cancelButton = new JButton("Cancel Ticket");
//        cancelButton.setBounds(220, 250, 150, 30);
//        cancelButton.addActionListener(this);
//
//        // Set bounds
//        uniqueLabel.setBounds(20, 45, 100, 20);
//        uniqueCode.setBounds(180, 45, 200, 20);
//
//        login.add(uniqueLabel);
//        login.add(uniqueCode);
//        login.add(cancelButton);
//        f.add(login);
//
//        f.setSize(800, 500);
//        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        f.setVisible(true);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        try {
//            if (uniqueCode.getText().isEmpty()) {
//                JOptionPane.showMessageDialog(f, "Please enter Unique Code.");
//                return;
//            }
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/airline", "root", "");
//            PreparedStatement stmt = con.prepareStatement("DELETE FROM bookings WHERE uniqueCode = ?");
//            stmt.setString(1, uniqueCode.getText());
//            int result = stmt.executeUpdate();
//            stmt.close();
//            con.close();
//            if (result > 0) {
//                JOptionPane.showMessageDialog(f, "Ticket cancelled successfully.");
//            } else {
//                JOptionPane.showMessageDialog(f, "No ticket found with this code.");
//            }
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
//        new Cancel();
//    }
//}
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Cancel extends WindowAdapter implements ActionListener {
    private JFrame f;
    private JTextField uniqueCode;
    private JButton cancelButton;
    private JProgressBar progressBar;

    public Cancel() {
        // Frame setup
        f = new JFrame("Ticket Cancellation");
        f.setSize(800, 500);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLocationRelativeTo(null);  // Center the frame

        // Background panel with gradient
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Create gradient background
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(0, 102, 204);  // Blue
                Color color2 = new Color(0, 51, 102);   // Darker Blue
                GradientPaint gradient = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(new GridBagLayout());
        f.add(panel);

        // GridBagConstraints for layout management
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);

        // Card-like Panel for Form
        JPanel cardPanel = new JPanel();
        cardPanel.setBackground(new Color(255, 255, 255, 200));  // Semi-transparent white background
        cardPanel.setLayout(new GridBagLayout());
        cardPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        cardPanel.setOpaque(true);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(cardPanel, gbc);

        // Form Label and Input
        JLabel uniqueLabel = new JLabel("Enter Unique Code:");
        uniqueLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
        uniqueLabel.setForeground(new Color(0, 51, 102));  // Dark Blue
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        cardPanel.add(uniqueLabel, gbc);

        uniqueCode = new JTextField(20);
        uniqueCode.setFont(new Font("Roboto", Font.PLAIN, 16));
        uniqueCode.setPreferredSize(new Dimension(250, 30));
        gbc.gridy = 1;
        cardPanel.add(uniqueCode, gbc);

        // Cancel Button with Modern Design
        cancelButton = new JButton("Cancel Ticket");
        cancelButton.setFont(new Font("Roboto", Font.PLAIN, 18));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setBackground(new Color(0, 102, 204));  // Blue color
        cancelButton.setFocusPainted(false);
        cancelButton.setBorder(BorderFactory.createEmptyBorder());
        cancelButton.setPreferredSize(new Dimension(250, 50));

        // Button Hover Effects
        cancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cancelButton.setBackground(new Color(0, 128, 255));  // Light blue on hover
                cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancelButton.setBackground(new Color(0, 102, 204));  // Reset to blue
                cancelButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        cancelButton.addActionListener(this);
        gbc.gridy = 2;
        cardPanel.add(cancelButton, gbc);

        // Progress Bar (for visual feedback on cancellation process)
        progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);  // Infinite loading animation
        progressBar.setStringPainted(true);
        progressBar.setString("Processing...");
        progressBar.setPreferredSize(new Dimension(300, 20));
        gbc.gridy = 3;
        cardPanel.add(progressBar, gbc);
        progressBar.setVisible(false);  // Initially hidden

        // Show window
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (uniqueCode.getText().isEmpty()) {
            JOptionPane.showMessageDialog(f, "Please enter a Unique Code.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Show the progress bar while processing the cancellation
        progressBar.setVisible(true);

        // Database cancellation logic
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/airline", "root", "");
            PreparedStatement stmt = con.prepareStatement("DELETE FROM bookings WHERE uniqueCode = ?");
            stmt.setString(1, uniqueCode.getText());

            int result = stmt.executeUpdate();
            stmt.close();
            con.close();

            // Hide the progress bar
            progressBar.setVisible(false);

            // Show success or failure messages
            if (result > 0) {
                JOptionPane.showMessageDialog(f, "Ticket cancelled successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(f, "No ticket found with this code.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            // Hide the progress bar on error
            progressBar.setVisible(false);
            JOptionPane.showMessageDialog(f, "An error occurred while processing the request.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        f.dispose();
    }

    public static void main(String[] args) {
        new Cancel();
    }
}

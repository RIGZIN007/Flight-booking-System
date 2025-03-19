//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//
//public class HomePage extends WindowAdapter implements ActionListener {
//	private JFrame f;
//	private JButton admin, passenger;
//
//	public HomePage() {
//		// Initialize components
//		f = new JFrame("Home Page");
//		JPanel login = new JPanel();
//		login.setLayout(null);
//		login.setBackground(new Color(0, 0, 0, 60));
//		login.setBounds(250, 100, 800, 500);
//
//		// Background Image
//		JLabel background = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/background4.jpg"))
//				.getImage().getScaledInstance(2000, 1400, Image.SCALE_DEFAULT)), JLabel.CENTER);
//		background.setBounds(0, 0, 1600, 900);
//		f.add(background);
//		background.add(login);
//
//		// Labels
//		String[] labelText = {"WELCOME", "TO", "AIRLINE BOOKING SYSTEM"};
//		JLabel[] labels = new JLabel[labelText.length];
//		Font labelFont = new Font("Algerian", Font.BOLD, 20);
//		for (int i = 0; i < labelText.length; i++) {
//			labels[i] = new JLabel(labelText[i], JLabel.CENTER);
//			labels[i].setFont(labelFont);
//			labels[i].setForeground(Color.WHITE);
//			labels[i].setBounds(300 - (i * 30), 60 + (i * 30), 120, 40);
//			login.add(labels[i]);
//		}
//
//		// Buttons
//		admin = new JButton("Admin");
//		passenger = new JButton("Passenger");
//		admin.setBounds(320, 230, 80, 30);
//		passenger.setBounds(320, 270, 80, 30);
//		admin.addActionListener(this);
//		passenger.addActionListener(this);
//		login.add(admin);
//		login.add(passenger);
//
//		// Final setup
//		f.setLayout(null);
//		f.setSize(700, 500);
//		f.setVisible(true);
//		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	}
//
//	@Override
//	public void windowClosing(WindowEvent e) {
//		f.dispose();
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent ae) {
//		if (ae.getSource() == admin) {
//			new Airline2(); // Admin page
//		} else if (ae.getSource() == passenger) {
//			new Airline1(); // Passenger page
//		}
//	}
//
//	public static void main(String[] args) {
//		new HomePage();
//	}
//}
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HomePage extends WindowAdapter implements ActionListener {

	private JFrame frame;
	private JButton adminButton, passengerButton;

	public HomePage() {
		// Frame setup
		frame = new JFrame("Airline Booking System");
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		// Center the window

		// Background panel with gradient
		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				// Create a gradient background
				Graphics2D g2d = (Graphics2D) g;
				Color color1 = new Color(0, 128, 255);
				Color color2 = new Color(0, 0, 128);
				GradientPaint gradient = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
				g2d.setPaint(gradient);
				g2d.fillRect(0, 0, getWidth(), getHeight());
			}
		};
		frame.add(panel);
		panel.setLayout(new GridBagLayout());  // Use GridBagLayout for responsiveness

		// Add labels with stylish fonts
		JLabel welcomeLabel = new JLabel("WELCOME TO AIRLINE BOOKING SYSTEM", JLabel.CENTER);
		welcomeLabel.setFont(new Font("Algerian", Font.BOLD, 32));
		welcomeLabel.setForeground(Color.WHITE);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = 2;
		gbc.insets = new Insets(20, 0, 20, 0);  // Add some space around the label
		panel.add(welcomeLabel, gbc);

		// Create buttons with hover effect
		adminButton = createButton("Admin");
		passengerButton = createButton("Passenger");

		// Button positioning
		gbc.gridwidth = 1;
		gbc.insets = new Insets(10, 20, 10, 20);
		gbc.gridy = 1;
		panel.add(adminButton, gbc);

		gbc.gridy = 2;
		panel.add(passengerButton, gbc);

		// Final frame setup
		frame.setVisible(true);
	}

	// Create button with custom style and hover effects
	private JButton createButton(String text) {
		JButton button = new JButton(text);
		button.setFont(new Font("Arial", Font.PLAIN, 20));
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(0, 0, 128));
		button.setFocusPainted(false);
		button.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 255), 2));
		button.setPreferredSize(new Dimension(200, 50));

		// Hover effect
		button.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(0, 0, 200));  // Change color on hover
			}
			public void mouseExited(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(0, 0, 128));  // Reset color when mouse exits
			}
		});

		button.addActionListener(this);
		return button;
	}

	// Handle button clicks
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == adminButton) {
			new Airline2();  // Admin page
		} else if (e.getSource() == passengerButton) {
			new Airline1();  // Passenger page
		}
	}

	// Close the window
	@Override
	public void windowClosing(WindowEvent e) {
		frame.dispose();
	}

	// Main method
	public static void main(String[] args) {
		new HomePage();
	}
}










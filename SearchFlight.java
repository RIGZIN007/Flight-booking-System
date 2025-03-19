//import java.awt.*;
//import java.sql.*;
//import java.awt.event.*;
//import javax.swing.*;
//
//public class SearchFlight implements ActionListener {
//
//	JFrame f;
//	Choice ch1, ch2;
//	Label r1, s1, fname, ftime;
//	Button sf;
//	TextField tf;
//	JPanel login = new JPanel();
//
//	public SearchFlight() {
//		f = new JFrame();
//		r1 = new Label();
//		s1 = new Label();
//		r1.setText("");
//		s1.setText("");
//		r1.setForeground(Color.WHITE);
//		s1.setForeground(Color.WHITE);
//		login.setSize(600, 350);
//		login.setLayout(null);
//		login.setBackground(new Color(0, 0, 0, 60));
//		login.setBounds(200, 100, 600, 500);
//
//		// Set background image
//		ImageIcon background_image = new ImageIcon(ClassLoader.getSystemResource("background4.jpg"));
//		Image i2 = background_image.getImage().getScaledInstance(2000, 1400, Image.SCALE_DEFAULT);
//		JLabel background = new JLabel("", new ImageIcon(i2), JLabel.CENTER);
//		background.setBounds(0, 0, 1600, 900);
//		background.add(login);
//		f.add(background);
//
//		// Initialize labels and components
//		initComponents();
//
//		// Add components to panel
//		login.add(r1);
//		login.add(s1);
//		login.add(new Label("Boarding:")).setBounds(80, 105, 70, 30);
//		login.add(ch1);
//		login.add(new Label("Destination:")).setBounds(80, 145, 70, 30);
//		login.add(ch2);
//		login.add(new Label("Date:")).setBounds(80, 185, 70, 30);
//		login.add(tf);
//		login.add(sf);
//		login.add(new Label("Results:")).setBounds(80, 280, 70, 30);
//		login.add(fname);
//		login.add(ftime);
//
//		f.setLayout(null);
//		f.setSize(1200, 700);
//		f.setVisible(true);
//	}
//
//	private void initComponents() {
//		ch1 = createChoice();
//		ch2 = createChoice();
//		tf = new TextField();
//		sf = new Button("Search Flight");
//		sf.setBounds(170, 240, 80, 30);
//		sf.addActionListener(this);
//
//		// Set bounds for components
//		ch1.setBounds(160, 110, 140, 40);
//		ch2.setBounds(160, 150, 140, 40);
//		tf.setBounds(160, 190, 140, 20);
//		r1.setBounds(80, 360, 70, 30);
//		s1.setBounds(280, 360, 70, 30);
//		fname = new Label("Flight Name");
//		ftime = new Label("Timings");
//	}
//
//	private Choice createChoice() {
//		Choice choice = new Choice();
//		choice.add("Select");
//		choice.add("Hyderabad");
//		choice.add("Delhi");
//		choice.add("Mumbai");
//		choice.add("Kolkata");
//		return choice;
//	}
//
//	public void actionPerformed(ActionEvent ae) {
//		try {
//			if (!ch1.getSelectedItem().equals(ch2.getSelectedItem())) {
//				// Connect to the database
//				Class.forName("com.mysql.jdbc.Driver");
//				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/airline", "root", "");
//				PreparedStatement stmt = connection.prepareStatement("SELECT flightname, time FROM registerdflights WHERE boarding = ? AND destination = ? AND date = ?");
//				stmt.setString(1, ch1.getSelectedItem());
//				stmt.setString(2, ch2.getSelectedItem());
//				stmt.setString(3, tf.getText());
//
//				ResultSet rs = stmt.executeQuery();
//
//				// Process results
//				if (rs.next()) {
//					r1.setText(rs.getString("flightname"));
//					s1.setText(rs.getString("time"));
//				}
//
//				stmt.close();
//				connection.close();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static void main(String[] args) {
//		new SearchFlight();
//	}
//}
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SearchFlight implements ActionListener {

	private JFrame f;
	private JComboBox<String> ch1, ch2;
	private JLabel r1, s1, fname, ftime, titleLabel;
	private JButton sf;
	private JTextField tf;
	private JPanel login;

	public SearchFlight() {
		// Frame setup
		f = new JFrame("Search Flight");
		f.setSize(1200, 700);
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);  // Center the window

		// Background setup
		JPanel backgroundPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				Color color1 = new Color(0, 102, 204);  // Light blue
				Color color2 = new Color(0, 51, 102);   // Dark blue
				GradientPaint gradient = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
				g2d.setPaint(gradient);
				g2d.fillRect(0, 0, getWidth(), getHeight());
			}
		};
		backgroundPanel.setLayout(new BorderLayout());
		backgroundPanel.setBounds(0, 0, 1200, 700);
		f.add(backgroundPanel);

		// Transparent login panel
		login = new JPanel();
		login.setLayout(null);
		login.setBackground(new Color(0, 0, 0, 150));  // Semi-transparent black background
		login.setBounds(200, 100, 600, 400);
		backgroundPanel.add(login);

		// Title Label
		titleLabel = new JLabel("Search Flight");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setBounds(200, 20, 250, 40);
		login.add(titleLabel);

		// Initialize components
		initComponents();

		// Set bounds for components
		ch1.setBounds(160, 110, 140, 40);
		ch2.setBounds(160, 150, 140, 40);
		tf.setBounds(160, 190, 140, 30);
		sf.setBounds(170, 240, 120, 40);
		r1.setBounds(160, 280, 250, 30);
		s1.setBounds(160, 310, 250, 30);

		// Add components to login panel
		login.add(new JLabel("Boarding:")).setBounds(80, 105, 70, 30);
		login.add(new JLabel("Destination:")).setBounds(80, 145, 90, 30);
		login.add(new JLabel("Date:")).setBounds(80, 185, 70, 30);
		login.add(new JLabel("Flight Name:")).setBounds(80, 275, 100, 30);
		login.add(new JLabel("Timings:")).setBounds(80, 305, 70, 30);
		login.add(ch1);
		login.add(ch2);
		login.add(tf);
		login.add(sf);
		login.add(r1);
		login.add(s1);

		// Display the frame
		f.setVisible(true);
	}

	private void initComponents() {
		// Choice for Boarding and Destination
		ch1 = new JComboBox<>(new String[] {"Select", "Hyderabad", "Delhi", "Mumbai", "Kolkata"});
		ch2 = new JComboBox<>(new String[] {"Select", "Hyderabad", "Delhi", "Mumbai", "Kolkata"});

		// TextField for Date
		tf = new JTextField();

		// Button to trigger the search
		sf = new JButton("Search Flight");
		sf.setFont(new Font("Arial", Font.PLAIN, 16));
		sf.setBackground(new Color(33, 150, 243));  // Blue color for the button
		sf.setForeground(Color.WHITE);
		sf.setFocusPainted(false);  // Remove focus outline
		sf.setBorder(BorderFactory.createEmptyBorder());  // Remove border
		sf.setPreferredSize(new Dimension(150, 40));
		sf.addActionListener(this);

		// Labels to display the result
		r1 = new JLabel("Flight Name: ");
		r1.setForeground(Color.WHITE);
		s1 = new JLabel("Timings: ");
		s1.setForeground(Color.WHITE);

		// Style for labels and buttons
		titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
		r1.setFont(new Font("Arial", Font.PLAIN, 18));
		s1.setFont(new Font("Arial", Font.PLAIN, 18));
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		try {
			if (!ch1.getSelectedItem().equals(ch2.getSelectedItem())) {
				// Connect to the database
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/airline", "root", "");
				PreparedStatement stmt = connection.prepareStatement("SELECT flightname, time FROM registerdflights WHERE boarding = ? AND destination = ? AND date = ?");
				stmt.setString(1, ch1.getSelectedItem().toString());
				stmt.setString(2, ch2.getSelectedItem().toString());
				stmt.setString(3, tf.getText());

				ResultSet rs = stmt.executeQuery();

				// Process results
				if (rs.next()) {
					r1.setText("Flight Name: " + rs.getString("flightname"));
					s1.setText("Timings: " + rs.getString("time"));
				} else {
					r1.setText("No flights found.");
					s1.setText("");
				}

				stmt.close();
				connection.close();
			} else {
				JOptionPane.showMessageDialog(f, "Boarding and Destination cannot be the same.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new SearchFlight();
	}
}

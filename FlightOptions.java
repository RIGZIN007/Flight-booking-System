import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FlightOptions extends JFrame {

    private Button[] buttons;
    private String[] buttonLabels = {"Book flight", "Search flight", "Cancel flight", "Exit"};
    private Color[] buttonColors = {Color.GREEN, Color.BLUE, Color.RED, Color.YELLOW};

    public FlightOptions() {
        setLayout(null);
        setSize(1200, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Background setup
        ImageIcon background_image = new ImageIcon(ClassLoader.getSystemResource("background.jpg"));
        ImageIcon backgroundIcon = new ImageIcon(background_image.getImage().getScaledInstance(2000, 1400, Image.SCALE_DEFAULT));
        JLabel background = new JLabel("", backgroundIcon, JLabel.CENTER);
        background.setBounds(0, 0, 1600, 900);
        add(background);

        // Login panel setup
        JPanel login = new JPanel();
        login.setLayout(null);
        login.setBackground(new Color(0, 0, 0, 60));
        login.setBounds(250, 175, 600, 350);
        background.add(login);

        // Title label
        JLabel l = new JLabel("Select your options....");
        l.setBounds(200, 25, 400, 50);
        l.setFont(new Font("ALGERIAN", Font.ITALIC, 30));
        login.add(l);

        // Button setup and action listeners
        buttons = new Button[4];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new Button(buttonLabels[i]);
            buttons[i].setBackground(buttonColors[i]);
            buttons[i].setBounds(50, 100 + (i * 50), 200, 30);
            buttons[i].addActionListener(createButtonListener(i));
            login.add(buttons[i]);
        }

        setVisible(true);
    }

    // Method to create ActionListener for each button based on index
    private ActionListener createButtonListener(int index) {
        return e -> {
            switch (index) {
                case 0 -> new Booking();
                case 1 -> new SearchFlight();
                case 2 -> new Cancel();
                case 3 -> System.exit(0);
            }
        };
    }

    public static void main(String[] args) {
        new FlightOptions();
    }
}

package APP.System_User_Interface;

import javax.swing.*;

import java.awt.*;

public class Secure_viewGUI extends JPanel {

    private JButton Stock;
    private JButton Orders;

    public Secure_viewGUI() {
        // Initialize the buttons
        Stock = new JButton("Stock");
        Orders = new JButton("Orders");

        // Use a BorderLayout to center the buttons
        setLayout(new BorderLayout());

        // Create a central panel to hold buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(Stock);
        buttonPanel.add(Orders);

        // Add the panel to the center of the main panel
        add(buttonPanel, BorderLayout.CENTER);
    }


     public void createAndShowGUI() 
     {
      Secure_viewGUI gui = new Secure_viewGUI();
      JFrame frame = new JFrame("Secure View");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(gui);
      frame.pack();
      frame.setVisible(true);
      Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
      int width = size.width;
      int height = size.height;
      frame.setSize(width, height);
      frame.setLocationRelativeTo(null);
    }

    // public static void main(String[] args) {
    //     // Create an instance of the GUI class

    //     Secure_viewGUI gui = new Secure_viewGUI();
    //     // Create a JFrame to hold the panel
    //     JFrame frame = new JFrame("Secure View");
    //     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //     frame.getContentPane().add(gui);

    //     // Set the frame size and make it visible
    //     frame.pack();
    //     frame.setVisible(true);
    //     Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    //     int width = size.width;
    //     int height = size.height;
    //     frame.setSize(width, height);
    //     frame.setLocationRelativeTo(null);

    // }
}

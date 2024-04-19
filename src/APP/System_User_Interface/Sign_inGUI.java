package APP.System_User_Interface;

import javax.imageio.ImageIO;
import javax.swing.*;

import APP.Security.UserAuth;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sign_inGUI extends JPanel {

  private JButton closeButton;
  private JButton loginButton;
  private final UserAuth userAuth; // Reference to UserAuth object
  private Color panelColor;
  private Image image; // Image object to hold the loaded image
  private JLabel app;


  public Sign_inGUI(UserAuth userAuth) {
    this.userAuth = userAuth;

    // Initialize GUI components
    this.setBackground(Color.WHITE);
    closeButton = new JButton("Close");
    loginButton = new JButton("Login");
    try {
      BufferedImage img = ImageIO.read(new File("image.png"));

      app = new JLabel(new ImageIcon(img));

      app.setBounds(400, 50, 500, 300);
      add(app);
  } catch (IOException e) {
      e.getMessage();
  }
    // Layout components (replace with your preferred layout manager)
    setLayout(null); // Use a more appropriate layout manager instead of null
    closeButton.setBounds(1000, 430, 200, 68);
    loginButton.setBounds(300, 430, 200, 68);

    // Color and event handling
    closeButton.setBackground(Color.RED);
    loginButton.setBackground(Color.GREEN);
    closeButton.addActionListener(new CloseButtonListener());
    loginButton.addActionListener(new LoginButtonListener());


    JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Create a panel with FlowLayout centered
    app.setBounds(500,-270,500,1000);
    
    if (image != null) {
      imagePanel.add(new JLabel(new ImageIcon(image))); // Add image to label
    }
    add(closeButton, BorderLayout.WEST);
    add(loginButton, BorderLayout.EAST);
    add(imagePanel, BorderLayout.CENTER);
    // Add components to the panel
    // add(closeButton);
    // add(loginButton);
    // add(imagePanel); // Image panel in the center

    // add(displayPanel);
  }

  private class CloseButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      System.exit(0);
    }
  }

  private class LoginButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      
      // UserAuth userAuth = new UserAuth();
      LoginGUI loginGUI = new LoginGUI(userAuth);
      userAuth.showLoginWindow();
      loginGUI.createAndShowGUI();
    }
  }
}

package APP.System_User_Interface;

import javax.swing.*;

import APP.AuthenticationandAuthorization.UserAuth;

import java.awt.*;
import java.awt.event.ActionListener;

public class Sign_inGUI extends JPanel {

  private JButton closeButton;
  private JButton loginButton;
  private final UserAuth userAuth; // Reference to UserAuth object

  public Sign_inGUI(UserAuth userAuth) {
    this.userAuth = userAuth;

    // Initialize GUI components
    closeButton = new JButton("Close");
    loginButton = new JButton("Login");

    // Layout components (replace with your preferred layout manager)
    setLayout(null); // Use a more appropriate layout manager instead of null
    closeButton.setBounds(1000, 430, 200, 68);
    loginButton.setBounds(300, 430, 200, 68);

    // Color and event handling
    closeButton.setBackground(Color.lightGray);
    loginButton.setBackground(Color.lightGray);
    closeButton.addActionListener(new CloseButtonListener());
    loginButton.addActionListener(new LoginButtonListener());

    // Add components to the panel
    add(closeButton);
    add(loginButton);
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
      userAuth.showLoginWindow(); // Delegate login handling to UserAuth
    }
  }
}

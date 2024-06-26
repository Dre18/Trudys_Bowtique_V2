package APP.System_User_Interface;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import APP.Security.UserAuth;

public class LoginGUI extends JPanel{

  private UserAuth userAuth; // Reference to UserAuth object
  private JLabel usernameLabel;
  private JLabel passwordLabel;
  private JTextField usernameField;
  private JPasswordField passwordField;
  private JButton loginButton;
  private JButton CloseButton;
  private JCheckBox showPassword;

  public LoginGUI(UserAuth userAuth) {
    this.userAuth = userAuth;

    // Initialize GUI components
    usernameLabel = new JLabel("USERNAME");
    passwordLabel = new JLabel("PASSWORD");
    usernameField = new JTextField();
    passwordField = new JPasswordField();
    loginButton = new JButton("LOGIN");
    CloseButton = new JButton("Close");
    showPassword = new JCheckBox("Show Password");

    // Layout components (replace with your preferred layout manager)
    JPanel displaypanel = new JPanel();
    // displaypanel.setPreferredSize(new Dimension(250, 150));
    setLayout(null); // Use a more appropriate layout instead of null
    usernameLabel.setBounds(50, 150, 100, 30);
    passwordLabel.setBounds(50, 220, 100, 30);
    // userTF.setBounds(150, 150, 150, 30);
   
    add(usernameLabel);
    add(passwordLabel);
    // container.add(userTF);
    add(usernameField);
    add(passwordField);
    add(showPassword);
    add(loginButton);
    add(CloseButton);
    // ... (add other components with their bounds)
    usernameField.setBounds(150, 150, 150, 30);
    passwordField.setBounds(150, 220, 150, 30);
    showPassword.setBounds(150, 250, 150, 30);
    loginButton.setBounds(50, 300, 100, 30);
    CloseButton.setBounds(200, 300, 100, 30);

    
    Color panelColor = new Color(123, 154, 239);
    setBackground(panelColor);
  
    
    loginButton.setBackground(Color.lightGray);
   
    CloseButton.setBackground(Color.lightGray);
    
    showPassword.setBackground(Color.lightGray);
    loginButton.addActionListener(new LoginButtonListener());
    showPassword.addActionListener(new showpassword());
    CloseButton.addActionListener(new CloseButtonListener());
  }

  public JButton getLoginButton() {
    return loginButton;
  }

  public JButton getCloseButton() {
    return CloseButton;
  }

  public JCheckBox getshowPassword() {
    return showPassword;
  }


  public String getUsername() {
    return usernameField.getText();
  }
  public JPasswordField getpasswordField()
  {
    return passwordField;
  }
  public String getPassword() {
    return String.valueOf(passwordField.getPassword());
  }

  public void showError(String message) {
    JOptionPane.showMessageDialog(this, message);
  }

  public void clearFields() {
    usernameField.setText("");
    passwordField.setText("");
  }

  public boolean performLogin() {
    String username = getUsername();
    String password = getPassword();

    // Implement your login algorithm here
    // This is a placeholder, replace with your actual logic
    if (username.equals("admin") && password.equals("admin")) {
        return true; // Login successful
    } else {
        return false; // Login failed
    }
}

  public void createAndShowGUI() {
        JFrame frame = new JFrame("Login");
        LoginGUI loginGUI = new LoginGUI(userAuth);
        frame.setPreferredSize(new Dimension(600, 700));
        loginGUI.setOpaque(true);
        frame.setContentPane(loginGUI);
        frame.pack();
        frame.setVisible(true);

    }

    private class LoginButtonListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent e) {

       

     
        if (performLogin()) {
          // Login successful, show secure view or perform other actions
          Secure_viewGUI secure_viewGUI = new Secure_viewGUI();
          secure_viewGUI.createAndShowGUI();
          LoginGUI loginGUI = new LoginGUI(userAuth);
          // loginGUI.setDefaultCloseOperation(EXIT_ON_CLOSE);
      } 
      else {
          showError("Invalid username or password!");
      }
  }
      
}


private class CloseButtonListener implements ActionListener {
  @Override
  public void actionPerformed(ActionEvent e) {
    System.exit(0);
  }
}


private class showpassword implements ActionListener {
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == showPassword) {
      if (showPassword.isSelected()) {
          passwordField.setEchoChar((char) 0);
      } else {
          passwordField.setEchoChar('*');
      }

}
  
}

}
}


package APP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Secure_viewGUI extends JPanel {

    
      private JLabel usernameLabel;
      private JLabel passwordLabel;
      private JTextField usernameField;
      private JPasswordField passwordField;
      private JButton loginButton;
      private JButton DoneButton;
      private JCheckBox showPassword;
    
      public Secure_viewGUI() {
       
    
        // Initialize GUI components
        
        loginButton = new JButton("LOGIN");
        DoneButton = new JButton("Done");
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
        add(DoneButton);
        // ... (add other components with their bounds)
        usernameField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        DoneButton.setBounds(200, 300, 100, 30);
    
        // Color and event handling
        Color panelColor = new Color(123, 154, 239);
        setBackground(panelColor);
        // ... (add components to the panel)
        
        loginButton.setBackground(Color.lightGray);
       
        DoneButton.setBackground(Color.lightGray);
        
        showPassword.setBackground(Color.lightGray);
       
      }
    
      public JButton getLoginButton() {
        return loginButton;
      }
    
      public JButton getDoneButton() {
        return DoneButton;
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
    
    
      public void createAndShowGUI() {
            JFrame frame = new JFrame("Login");
            Secure_viewGUI loginGUI = new Secure_viewGUI();
            frame.setPreferredSize(new Dimension(600, 700));
            loginGUI.setOpaque(true);
            frame.setContentPane(loginGUI);
            frame.pack();
            frame.setVisible(true);
    
        }
     
    }
    
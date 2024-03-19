package APP.AuthenticationandAuthorization;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginGUI extends JPanel{

  private UserAuth userAuth; // Reference to UserAuth object
  private JLabel usernameLabel;
  private JLabel passwordLabel;
  private JTextField usernameField;
  private JPasswordField passwordField;
  private JButton loginButton;
  private JButton doneButton;
  private JCheckBox showPasswordCheckbox;

  public LoginGUI(UserAuth userAuth) {
    this.userAuth = userAuth;

    // Initialize GUI components
    usernameLabel = new JLabel("USERNAME");
    passwordLabel = new JLabel("PASSWORD");
    usernameField = new JTextField();
    passwordField = new JPasswordField();
    loginButton = new JButton("LOGIN");
    doneButton = new JButton("Done");
    showPasswordCheckbox = new JCheckBox("Show Password");

    // Layout components (replace with your preferred layout manager)
    setLayout(null); // Use a more appropriate layout instead of null
    usernameLabel.setBounds(50, 150, 100, 30);
    passwordLabel.setBounds(50, 220, 100, 30);
    // ... (add other components with their bounds)

    // Color and event handling
    Color panelColor = new Color(123, 154, 239);
    setBackground(panelColor);
    // ... (add components to the panel)
    // loginButton.addActionListener(this);
    // doneButton.addActionListener(this);
    // showPasswordCheckbox.addActionListener(this);
  }

  public String getUsername() {
    return usernameField.getText();
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

  // @Override
  // public void actionPerformed(ActionEvent e) {
  //   if (e.getSource() == loginButton) 
  //   {
  //     userAuth.performLogin(getUsername(), getPassword()); // Delegate login logic
  //   } else if (e.getSource() == doneButton) {
  //     clearFields();
  //   } else if (e.getSource() == showPasswordCheckbox) {
  //     if (showPasswordCheckbox.isSelected()) {
  //       passwordField.setEchoChar((char) 0);
  //     } else {
  //       passwordField.setEchoChar('*');
  //     }
  //   }
  // }
}

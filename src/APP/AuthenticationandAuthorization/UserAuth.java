package APP.AuthenticationandAuthorization;
import javax.swing.*;

import APP.System_User_Interface.MainView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;


public class UserAuth implements ActionListener {

    private LoginGUI loginGUI;
  
    public UserAuth() {
      loginGUI = new LoginGUI(this); // Pass itself to LoginGUI constructor
    }

    public void showLoginWindow() {
        loginGUI.setVisible(true); // Make the existing LoginGUI visible
      }
  
    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == loginGUI.getLoginButton()) {
       ActionListener listener = new UserAuth(); // Replace with your implementation

    // Create Secure_viewGUI object
//     Secure_viewGUI secureViewGUI = new Secure_viewGUI(); 
// // 
//     // Create JFrame window
//     JFrame frame = new JFrame("Secure View");
//     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//     frame.setContentPane(secureViewGUI); // Set Secure_viewGUI as the content pane
//     frame.pack();
//     frame.setVisible(true);
  }
        }
    
}

    

  
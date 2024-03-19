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
  
    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == loginGUI.getLoginButton()) {
        // Get username and password from LoginGUI using its methods
        String username = loginGUI.getUsername();
        String password = loginGUI.getPassword();
        // Perform authentication logic with username and password
        // ... (replace with your actual authentication logic)
        if (username == "Admin" && password == "Admin") 
        {
          // Launch MainView or perform other actions on successful login
          // You can call methods from MainView here (if appropriate)
        //   LoginGUI loginGUI = new MainView();
        //   loginGUI.createAndShowGUI();
          loginGUI.setVisible(false);
            
        } else {
          loginGUI.showError("Invalid Username or Password"); // Inform LoginGUI
        }
      } else if (e.getSource() == loginGUI.getDoneButton()) {
        loginGUI.clearFields(); // Delegate clearing fields to LoginGUI
      } else if (e.getSource() == loginGUI.getshowPassword()) {
        // No action needed here, handled by LoginGUI
      }
    }
  }
  
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
        loginButton.addActionListener(this);
        doneButton.addActionListener(this);
        showPasswordCheckbox.addActionListener(this);
        
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginButton) {
            String userText;
            String pwdText;
            userText = userTF.getText();
            pwdText = String.valueOf(passwordField.getPassword());
           String[] user = { "Admin Admin" };
            int a = 0;
            for (int i = 0; i < user.length; i++) {
               String[] s = user[i].split(" ");
            //    if (userText.equals(s[0]) && pwdText.equals(s[1])) {
                userText.equals("Admin");
                pwdText.equals("Admin");
                MainView mainView = new MainView();

                
                mainView.createAndShowGUI();
                    a = 1;
                    setVisible(false);
            //    }
           }

            if (a == 0) {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }
        }

        if (e.getSource() == DoneButton) {
            userTF.setText("");
            passwordField.setText("");
        }

        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
                // userTF.disable();
                // passwordField.disable();
            } else {
                passwordField.setEchoChar('*');
            }
        }

        
    }
}
package APP.Main;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import APP.AuthenticationandAuthorization.LoginGUI;
import APP.AuthenticationandAuthorization.UserAuth;
import APP.System_User_Interface.Sign_inGUI;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * 
 **/

public class deploy extends JPanel {

    private JButton Close;
    public JButton logIn;
    private JTable table;
    private Color panelColor;
    static int a,b,c;

    
    /** 
     * @param args
     */
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UserAuth userAuth = new UserAuth(); // Create UserAuth object (assuming it handles login logic)
                    Sign_inGUI Sign_in_page = new Sign_inGUI(userAuth); // Pass UserAuth to Sign_in_page

                    Sign_in_page.setOpaque(true);
                    JFrame frame = new JFrame("Trudy's Bowtique");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setContentPane(Sign_in_page);
                    frame.pack();
                    frame.setVisible(true);

                    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
                    int width = size.width;
                    int height = size.height;
                    frame.setSize(width, height);
                    frame.setLocationRelativeTo(null);

                } catch (Exception d) {
                    JOptionPane.showInputDialog("System Error");

                }
                
                // DLineAlert datess= new DLineAlert();
                }
        });
    }

   

}
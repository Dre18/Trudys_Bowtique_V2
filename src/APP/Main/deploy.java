package APP.Main;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import APP.NotificationsandEvents.Notification;
import APP.Security.UserAuth;
import APP.System_User_Interface.Sign_inGUI;



import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.*;
import java.awt.TrayIcon.MessageType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
    // static final String DB_URL = "jdbc:mysql://sql5.freesqldatabase.com:3306/sql5700240";
    // static final String USER = "sql5700240";
    // static final String PASSWORD = "1rFJ2HigUE";
    
    /** 
     * @param args
     * @throws AWTException 
     * @throws SQLException 
     */
    public static void main(String[] args) throws AWTException, SQLException {

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
                
               
                }
        });

        if (SystemTray.isSupported()) {
            APP.NotificationsandEvents.Notification td = new Notification();
            // td.displayTray();
        } else {
            System.err.println("System tray not supported!");
        }

   
    }

   

}
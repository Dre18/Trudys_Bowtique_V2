// package APP.System_User_Interface;
// import javax.swing.JFrame;
// import javax.swing.JPanel;
// import javax.swing.JScrollPane;
// import javax.swing.JTable;
// import APP.AuthenticationandAuthorization.UserAuth;
// // import APP.NotificationsandEvents.DLineAlert;

// import java.awt.Dimension;
// import java.awt.Toolkit;
// import java.awt.event.ActionListener;
// import java.awt.event.ActionEvent;
// import java.awt.Color;
// import javax.swing.JButton;
// import javax.swing.JOptionPane;

// /**
//  *
//  * 
//  **/

// public class SystemDisplay extends JPanel {

//     private JButton Close;
//     public JButton logIn;
//     private JTable table;
//     private Color panelColor;
//     static int a,b,c;

    
//     /** 
//      * @param args
//      */
//     public static void main(String[] args) {

//         javax.swing.SwingUtilities.invokeLater(new Runnable() {
//             public void run() {
//                 try {
//                 SystemDisplay newContentPane = new SystemDisplay();
//                     newContentPane.setOpaque(true);
//                     JFrame frame = new JFrame("Trudy's Bowtique");
//                     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                     frame.setContentPane(newContentPane);
//                     frame.pack();
//                     frame.setVisible(true);

//                     Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
//                     int width = size.width;
//                     int height = size.height;
//                     frame.setSize(width, height);
//                     frame.setLocationRelativeTo(null);

//                 } catch (Exception d) {
//                     JOptionPane.showInputDialog("System Error");

//                 }
                
//                 // DLineAlert datess= new DLineAlert();
//                 }
//         });
//     }

//     public SystemDisplay() {

//         JPanel CommandPanel = new JPanel();
//         JPanel displayPanel = new JPanel();
      
//         displayPanel.setPreferredSize(displayPanel.getToolkit().getScreenSize());
//         displayPanel.setLayout(null);
//         panelColor = new Color(123, 154, 239);
//         displayPanel.setBackground(panelColor);
//         JScrollPane scrollPane = new JScrollPane(table);
//         add(scrollPane);

//         logIn = new JButton("Login");
//         logIn.setBounds(300, 430, 200, 68);
//         logIn.setBackground(Color.lightGray);
//         Close = new JButton("Close");
//         Close.setBounds(1000, 430, 200, 68);
//         Close.setBackground(Color.lightGray);
//         Close.addActionListener(new CloseButtonListener());
//         logIn.addActionListener(new LoginButtonListener());
//         displayPanel.add(logIn);
//         displayPanel.add(Close);
//         add(CommandPanel);
//         add(displayPanel);
//     }

//     private class CloseButtonListener implements ActionListener {
//         public void actionPerformed(ActionEvent e) {
//             System.exit(0);
//         }

//     }

//     private class LoginButtonListener implements ActionListener {
//         public void actionPerformed(ActionEvent e) {
//             if (e.getSource() == logIn) {

//                 try {
//                     SystemDisplay newContentPane = new SystemDisplay();
//                     APP.AuthenticationandAuthorization.UserAuth frame = new UserAuth();
//                     newContentPane.setOpaque(true);
//                     frame.setTitle("Sign in");
//                     frame.setPreferredSize(new Dimension(500, 600));
//                     frame.setResizable(true);
//                     frame.pack();
//                     frame.setVisible(true);
//                     newContentPane.setVisible(false);

//                 } catch (Exception d) {

//                 }

//             }

//         }

//     }

    


   

// }
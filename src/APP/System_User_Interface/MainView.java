// package APP.System_User_Interface;

// import javax.swing.JFrame;
// import javax.swing.JOptionPane;
// import javax.swing.JPanel;
// import javax.swing.JScrollPane;
// import javax.swing.JTable;
// import java.awt.event.ActionListener;
// import java.awt.event.ActionEvent;
// import java.awt.Color;
// import javax.swing.JButton;


// public class MainView extends JPanel {

//     JPanel CommandPanel;
//     JPanel displayPanel;
//     public JButton report;
//     public JButton Stock;
//     public JButton Order;
//     private JTable table;
//     private Color panelColor;

    

//    public MainView() {

//         CommandPanel = new JPanel();
//         displayPanel = new JPanel();
//         displayPanel.setPreferredSize(displayPanel.getToolkit().getScreenSize());
//         displayPanel.setLayout(null);
//         panelColor = new Color(200, 150, 200);
//         displayPanel.setBackground(panelColor);
//         JScrollPane scrollPane = new JScrollPane(table);
//         add(scrollPane);

//         Order = new JButton("Order");
//         Order.setBounds(300, 430, 200, 68);
//         Order.setBackground(Color.lightGray);
//         // Order.addActionListener(new OrderButtonListener());
        
//         Stock = new JButton("Stock");
//         Stock.setBounds(700, 430, 200, 68);
//         Stock.setBackground(Color.lightGray);
      

//         displayPanel.add(Order);
//         displayPanel.add(Stock);
//         displayPanel.add(report);

//         add(CommandPanel);
//         add(displayPanel);
//     }
  
//     public void createAndShowGUI() {
//         JFrame frame = new JFrame("Trudy's Bowtique");
//         MainView newContentPane = new MainView();
//         frame.setPreferredSize(frame.getToolkit().getScreenSize());
//         newContentPane.setOpaque(true);
//         frame.setContentPane(newContentPane);
//         frame.pack();
//         frame.setVisible(true);

//     }



// }
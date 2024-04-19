package APP.System_User_Interface;

import javax.swing.*;

import APP.NotificationsandEvents.StockAlert;
import APP.OrderManagement.Order;
import APP.StockManagement.Stock;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Secure_viewGUI extends JPanel {

    private JButton Stock;
    private JButton Orders;
    private JButton Report;
    private JButton stockAlertButton; // Button for opening StockAlertGUI



    public Secure_viewGUI() {
        // Initialize the buttons
        Stock = new JButton("Stock");
        Orders = new JButton("Orders");
        Report = new JButton("Report");
        stockAlertButton = new JButton("Critical Level"); // Create Stock Alert button
        Stock.setBackground(Color.cyan);
        Orders.setBackground(Color.cyan);
        Report.setBackground(Color.cyan);
        stockAlertButton.setBackground(Color.cyan);
        // Stock.setSize(200, 68);
     
        // Dimension buttonSize = new Dimension(200, 100);
        // Stock.setSize(buttonSize);
        // Orders.setSize(buttonSize);
        // Report.setSize(buttonSize);
        // stockAlertButton.setSize(buttonSize);

        // Use a GridLayout to center the buttons
        // setLayout(new GridLayout(4, 2));
        // JPanel buttonPanel = new JPanel(new GridLayout(4, 2));
        // Use a BorderLayout to center the buttons
        // setLayout(new BorderLayout());

        // Create a central panel to hold buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(Stock);
        buttonPanel.add(Orders);
        buttonPanel.add(Report);
        buttonPanel.add(stockAlertButton);
        buttonPanel.setBackground(Color.cyan);
        buttonPanel.setLayout(new GridLayout(4,2,25,25));
        setLayout(new BorderLayout());
        // Add the panel to the center of the main panel
        add(buttonPanel, BorderLayout.CENTER);
        add(buttonPanel);



        Stock.addActionListener(new StockButtonListener());
        Orders.addActionListener(new OrderButtonListener());
        Report.addActionListener(new ReportButtonListener());
        stockAlertButton.addActionListener(new StockAlertButtonListener());

    }


     public void createAndShowGUI() 
     {
      Secure_viewGUI gui = new Secure_viewGUI();
      JFrame frame = new JFrame("Secure View");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(gui);
      frame.pack();
      frame.setVisible(true);
      Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
      int width = size.width;
      int height = size.height;
      frame.setSize(width, height);
      frame.setLocationRelativeTo(null);
    }

    
    private class StockButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            APP.StockManagement.Stock stockManagement  = new Stock();
          
            // Login successful, show secure view or perform other actions
            APP.System_User_Interface.Stock_GUI stock_GUI  = new Stock_GUI(stockManagement);
            stock_GUI.createAndShowGUI();
        
        }
    }

    private class OrderButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
       
          
            // Login successful, show secure view or perform other actions
            APP.System_User_Interface.Order_GUI order_Gui  = new APP.System_User_Interface.Order_GUI();
            order_Gui.createAndShowGUI();
            
        
        }
    }


    private class StockAlertButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            StockAlert stockAlert = new StockAlert(); // Create an instance of StockAlert
            StockAlertGUI stockAlertGUI = new StockAlertGUI(stockAlert); // Pass the StockAlert object to the StockAlertGUI constructor
            stockAlertGUI.createAndShowGUI(); // Show StockAlertGUI
        }
    }

    private class ReportButtonListener implements ActionListener {
        private  final JOptionPane JJOptionPane = null;
    
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == Report)
            {
               try{
                APP.Monthly_Sales_Report.SalesReportGenerator.getInfo();
                // JOptionPane.showMessageDialog(null, this, "Monthly Report Saved", 0);
                // JJOptionPane.showMessageDialog(null, this, "Monthly Report Saved", 0);
                JOptionPane.showMessageDialog(null, "Monthly Report Saved", "Success", JOptionPane.INFORMATION_MESSAGE); // Improved message dialog

               }
               catch (Exception n)
               {
            
                
               }
                    
                }
            }
        }

        
   
}

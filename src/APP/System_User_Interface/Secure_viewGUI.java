package APP.System_User_Interface;

import javax.swing.*;

import APP.OrderManagement.Order;
import APP.StockManagement.Stock;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Secure_viewGUI extends JPanel {

    private JButton Stock;
    private JButton Orders;

    public Secure_viewGUI() {
        // Initialize the buttons
        Stock = new JButton("Stock");
        Orders = new JButton("Orders");

        // Use a BorderLayout to center the buttons
        setLayout(new BorderLayout());

        // Create a central panel to hold buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(Stock);
        buttonPanel.add(Orders);

        // Add the panel to the center of the main panel
        add(buttonPanel, BorderLayout.CENTER);


        Stock.addActionListener(new StockButtonListener());
        Orders.addActionListener(new OrderButtonListener());
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
            APP.StockManagement.Stock stock  = new Stock();
          
            // Login successful, show secure view or perform other actions
            APP.System_User_Interface.Stock_GUI stock_GUI  = new Stock_GUI(stock);
            stock_GUI.createAndShowGUI();
        
        }
    }

    private class OrderButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
       
          
            // Login successful, show secure view or perform other actions
            // APP.System_User_Interface.Order_GUI order_Gui  = new APP.System_User_Interface.Order_GUI();
            // order_Gui.createAndShowGUI();
            APP.OrderManagement.Orders order  = new APP.OrderManagement.Orders();
            order.createAndShowGUI();
        
        }
    }
   
}

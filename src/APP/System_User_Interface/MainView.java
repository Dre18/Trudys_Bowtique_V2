package APP.System_User_Interface;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JButton;


public class MainView extends JPanel {

    JPanel CommandPanel;
    JPanel displayPanel;
    public JButton report;
    public JButton Stock;
    public JButton Order;
    private JTable table;
    private Color panelColor;

    

   public MainView() {

        CommandPanel = new JPanel();
        displayPanel = new JPanel();
        displayPanel.setPreferredSize(displayPanel.getToolkit().getScreenSize());
        displayPanel.setLayout(null);
        panelColor = new Color(200, 150, 200);
        displayPanel.setBackground(panelColor);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        Order = new JButton("Order");
        Order.setBounds(300, 430, 200, 68);
        Order.setBackground(Color.lightGray);
        Order.addActionListener(new OrderButtonListener());
        
        Stock = new JButton("Stock");
        Stock.setBounds(700, 430, 200, 68);
        Stock.setBackground(Color.lightGray);
        Stock.addActionListener(new StockButtonListener());
        
        // report = new JButton (" Monthly Sales Report");
        // report.setBounds(1000, 430, 200, 68);
        // report.setBackground(Color.lightGray);
        // report.addActionListener(new ReportButtonListener());

        displayPanel.add(Order);
        displayPanel.add(Stock);
        displayPanel.add(report);

        add(CommandPanel);
        add(displayPanel);
    }
    
    // private class ReportButtonListener implements ActionListener {
    // private  final JOptionPane JJOptionPane = null;

    // public void actionPerformed(ActionEvent e) {
    //     if(e.getSource() == report)
    //     {
    //        try{
    //         APP.MonthlysalesReporting.SalesReportGenerator.getInfo();
    //         JJOptionPane.showMessageDialog(displayPanel, "Monthly Report Saved");
    //        }
    //        catch (Exception n)
    //        {
        
            
    //        }
                
    //         }
    //     }
    // }

 

    private class StockButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent x) {
            if (x.getSource() == Stock)
            {
                try{
                    APP.StockManagement.Stock stock = new APP.StockManagement.Stock();
            
                    stock.createAndShowGUI();
                    SystemDisplay newContentPane = new SystemDisplay();
                    newContentPane.setOpaque(false);

                }
                catch (Exception y)
                {

                }
            } 
            
        }

    }

    private class OrderButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == Order) {

                try {
                    APP.OrderManagement.Order Order = new APP.OrderManagement.Order();
                    SystemDisplay newContentPane = new SystemDisplay();
                    MainView mainView = new MainView();
                    newContentPane.setOpaque(true);
                    mainView.setOpaque(false);
                    

                } catch (Exception d) {

                }

            }

        }

    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Trudy's Bowtique");
        MainView newContentPane = new MainView();
        frame.setPreferredSize(frame.getToolkit().getScreenSize());
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.pack();
        frame.setVisible(true);

    }



}
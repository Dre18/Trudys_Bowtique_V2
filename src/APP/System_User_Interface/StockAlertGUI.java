package APP.System_User_Interface;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import APP.NotificationsandEvents.StockAlert;
import APP.StockManagement.Item;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StockAlertGUI extends JPanel {
    private StockAlert stockAlert;
    private DefaultTableModel model;
    private JTable table;
    private JButton checkStockButton;
    private JButton closeButton;
    private JButton showReportButton;


    public StockAlertGUI(StockAlert stockAlert) {
        super(new BorderLayout());
        this.stockAlert = stockAlert;

        JPanel buttonPanel = new JPanel();
        checkStockButton = new JButton("Check Stock");
        closeButton = new JButton("Close");
        showReportButton = new JButton("Critical Report");

        buttonPanel.add(checkStockButton);
        buttonPanel.add(closeButton);
        buttonPanel.add(showReportButton);


        checkStockButton.addActionListener(new CheckStockButtonListener());
        closeButton.addActionListener(new CloseButtonListener());
        showReportButton.addActionListener(new ShowReportButtonListener());


        model = new DefaultTableModel(new String[]{"Items", "Quantity", "Current Critical Level"}, 0);
        table = new JTable(model);
        table.getSelectionModel().addListSelectionListener(new TableSelectionListener());
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private class CheckStockButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.setRowCount(0);
            StockAlert stockAlert = new StockAlert();
            int overallCriticalLevel = stockAlert.getOverallCriticalLevel();
            if (overallCriticalLevel >= 0) {
                List<Item> lowStockItems = stockAlert.checkStockLevels();
                if (!lowStockItems.isEmpty()) {
                    for (Item item : lowStockItems) {
                        model.addRow(new Object[]{item.getItemName(), item.getItemQuantity(), overallCriticalLevel});
                    }
                    JOptionPane.showMessageDialog(null, "ALERT: The following items are below critical level.");
                } else {
                    JOptionPane.showMessageDialog(null, "All items are above critical level.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid critical level.");
            }
        }
    }

    private class TableSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int newOverallCriticalLevel = Integer.parseInt(JOptionPane.showInputDialog(null,
                            "Current overall critical level is " + stockAlert.getOverallCriticalLevel() + ".\nEnter new critical level:"));
                    stockAlert.setOverallCriticalLevel(newOverallCriticalLevel);
                    JOptionPane.showMessageDialog(null, "Critical level changed successfully.");
                }
            }
        }
    }

    private class CloseButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Window window = SwingUtilities.getWindowAncestor(StockAlertGUI.this);
            window.dispose();
        }
    }

    private class ShowReportButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            StockReportGUI stockReportGUI = new StockReportGUI(stockAlert);
            stockReportGUI.createAndShowGUI();
        }
    }


    public void createAndShowGUI() {
        JFrame frame = new JFrame("Stock Alert");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 300));
        frame.getContentPane().add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // public static void main(String[] args) {
    //     StockAlert stockAlert = new StockAlert();
    //     StockAlertGUI stockAlertGUI = new StockAlertGUI(stockAlert);
    //     stockAlertGUI.createAndShowGUI();
    // }
}

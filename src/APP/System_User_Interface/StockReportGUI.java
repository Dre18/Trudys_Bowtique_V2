package APP.System_User_Interface;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import APP.NotificationsandEvents.StockAlert;
import APP.NotificationsandEvents.StockReport;
import APP.StockManagement.Item;

import java.awt.*;
import java.util.List;

public class StockReportGUI extends JPanel {
    private StockReport stockReport;
    private DefaultTableModel model;
    private JTable table;
    private JButton generateReportButton;

    public StockReportGUI(StockAlert stockAlert) {
        super(new BorderLayout());
        this.stockReport = new StockReport(stockAlert);

        JPanel buttonPanel = new JPanel();
        generateReportButton = new JButton("Generate Report");
        buttonPanel.add(generateReportButton);

        generateReportButton.addActionListener(e -> {
            generateReport();
        });

        model = new DefaultTableModel(new String[]{"Item Name", "Quantity", "Stock Used Since Last Restock"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void generateReport() {
        model.setRowCount(0); // Clear previous data
        List<Item> lowStockItems = stockReport.getLowStockItems();
        if (!lowStockItems.isEmpty()) {
            for (Item item : lowStockItems) {
                int stockUsed = stockReport.calculateStockUsed(item);
                model.addRow(new Object[]{item.getItemName(), item.getItemQuantity(), stockUsed});
            }
        } else {
            JOptionPane.showMessageDialog(null, "No items are below the critical level.");
        }
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Stock Report");
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(600, 400));
        frame.getContentPane().add(this);
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }

}

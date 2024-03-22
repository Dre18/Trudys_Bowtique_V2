package APP.StockManagement;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Stock_GUI extends JPanel {

    private StockManagement stockManagement;
    private JButton updateButton;
    private JButton addItemButton;
    private JButton deleteButton;
    private JTable table;
    private JTextField itemField;
    private JTextField quantityField;

    public Stock_GUI()
    {
        
    }

    public Stock_GUI(StockManagement stockManagement) {
        this.stockManagement = stockManagement;
        setLayout(new GridLayout(2, 1));

        JPanel buttonPanel = new JPanel();
        updateButton = new JButton("Update Item");
        addItemButton = new JButton("Add Item");
        deleteButton = new JButton("Delete Item");

        buttonPanel.add(updateButton);
        buttonPanel.add(addItemButton);
        buttonPanel.add(deleteButton);

        updateButton.addActionListener(new UpdateButtonListener());
        addItemButton.addActionListener(new AddItemButtonListener());
        // deleteButton.addActionListener(new DeleteButtonListener());

        JScrollPane scrollPane = new JScrollPane(stockManagement.getTable());
        table = stockManagement.getTable();

        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        inputPanel.add(new JLabel("New Item:"));
        itemField = new JTextField(10);
        inputPanel.add(itemField);

        inputPanel.add(new JLabel("Amount:"));
        quantityField = new JTextField(10);
        inputPanel.add(quantityField);

        add(scrollPane);
        add(inputPanel);
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Stock");
        frame.setPreferredSize(frame.getToolkit().getScreenSize());
        frame.setContentPane(this);
        frame.pack();
        frame.setVisible(true);
    }

    private class UpdateButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            stockManagement.updateItem();
        }
    }

    private class AddItemButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            stockManagement.addItem(itemField.getText(), quantityField.getText());
            itemField.setText("");
            quantityField.setText("");
        }
    }

    // private class DeleteButtonListener implements ActionListener {
    //     @Override
    //     public void actionPerformed(ActionEvent e) {
    //         stockManagement.deleteItem();
    //     }
    // }
}

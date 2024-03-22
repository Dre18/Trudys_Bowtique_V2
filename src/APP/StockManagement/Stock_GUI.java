package APP.StockManagement;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.BorderUIResource;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Stock_GUI extends JPanel {

    private Stock stockManagement;
    private JButton updateButton;
    private JButton addItemButton;
    private JButton deleteButton;
  
    private JButton update;
    private JButton addItem;
    private JButton delete;
    
    
    private JScrollPane scrollPane;

    private DefaultTableModel model;
    private JTextField item;
    private JTextField quantity;
    private JPanel pnl;
    
    private String resadd;

    public Stock_GUI()
    {
        
    }

    public Stock_GUI(Stock stockManagement) {
        super(new GridLayout(2, 1));
        this.stockManagement = stockManagement;
        

        JPanel buttonPanel = new JPanel();
        updateButton = new JButton("Update Item");
        addItemButton = new JButton("Add Item");
        deleteButton = new JButton("Delete Item");

        buttonPanel.add(updateButton);
        buttonPanel.add(addItemButton);
        buttonPanel.add(deleteButton);

        updateButton.addActionListener(new UpdateButtonListener());
        addItemButton.addActionListener(new AddItemButtonListener());
        deleteButton.addActionListener(new DeleteButtonListener());

                
        this.stockManagement = stockManagement;

        model = new DefaultTableModel(new String[]{"Items", "Quantity"}, 0);
        
        JScrollPane scrollPane = new JScrollPane(stockManagement.getTable());
        add(scrollPane);

        JPanel inputPanel = new JPanel(new GridLayout(4, 0));
        JPanel pnl = new JPanel(new GridLayout(2, 2));
        pnl.add(new JLabel("New Item:"));
        item = new JTextField(1);
        pnl.add(item);
        

        pnl.add(new JLabel("Quantity:"));
        quantity = new JTextField(1);
        pnl.add(quantity);
        addItem = new JButton("Add Item");
        
        delete = new JButton("Delete Item");
        update = new JButton("Update Item");
        addItem.addActionListener(new AddItemButtonListener());
        update.addActionListener(new UpdateButtonListener());
        
        delete.addActionListener(new DeleteButtonListener());
        
        inputPanel.add(pnl, BorderLayout.PAGE_START);
        inputPanel.add(buttonPanel, BorderLayout.PAGE_START);
        
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
            stockManagement.addItem(item.getText(), quantity.getText());
            item.setText("");
            quantity.setText("");
        }
      

    }

    private class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            stockManagement.deleteItem();
        }
    }


   
}

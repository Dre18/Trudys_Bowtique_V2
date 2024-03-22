package APP.StockManagement;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Stock_GUI extends JPanel {

    private StockManagement stockManagement;
    private JButton updateButton;
    private JButton addItemButton;
    private JButton deleteButton;
    private JTable table;
    private JTextField itemField;
    private JTextField quantityField;

    private JButton update;
    private JButton addItem;
    private JButton delete;
    // private JPanel pnlCommand;
    // private JPanel pnlDisplay;
    private static ArrayList<Item> ilist;
    private JScrollPane scrollPane;
    // private JTable table;
    private DefaultTableModel model;
    private JTextField item;
    private JTextField quantity;
    private JPanel pnl;
    // private static final String file= "StockList.dat";
    private String resadd;

    public Stock_GUI()
    {
        
    }

    public Stock_GUI(StockManagement stockManagement) {
        super(new GridLayout(2, 1));
        this.stockManagement = stockManagement;
        // setLayout(new GridLayout(2, 1));

        JPanel buttonPanel = new JPanel();
        updateButton = new JButton("Update Item");
        addItemButton = new JButton("Add Item");
        deleteButton = new JButton("Delete Item");

        buttonPanel.add(updateButton);
        buttonPanel.add(addItemButton);
        buttonPanel.add(deleteButton);

        updateButton.addActionListener(new UpdateButtonListener());
        addItemButton.addActionListener(new AddItemButtonListener());
        // // deleteButton.addActionListener(new DeleteButtonListener());

        // JScrollPane scrollPane = new JScrollPane(stockManagement.getTable());
        // table = stockManagement.getTable();

        // JPanel inputPanel = new JPanel(new GridLayout(4, 0));
        // inputPanel.add(new JLabel("New Item:"));
        // itemField = new JTextField(10);
        // // inputPanel.new 
        // // setLayout(inputPanel);
        // inputPanel.add(itemField);

        // inputPanel.add(new JLabel("Amount:"));
        // quantityField = new JTextField(10);
        // // inputPanel.add(quantityField);
        // add(scrollPane);
        // // add(inputPanel);

        // super(new GridLayout(2, 1));
        this.stockManagement = stockManagement;

        model = new DefaultTableModel(new String[]{"Items", "Quantity"}, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(stockManagement.getTable());
        add(scrollPane);

        JPanel inputPanel = new JPanel(new GridLayout(2, 1));
        // inputPanel.add(new JLabel("New Item:"));
        item = new JTextField(10);
        // inputPanel.add(item);

        // inputPanel.add(new JLabel("Quantity:"));
        // quantity = new JTextField(10);
        // inputPanel.add(quantity);
        addItem = new JButton("Add Item");
        // inputPanel.add(addItem);
        delete = new JButton("Delete Item");
        update = new JButton("Update Item");
        addItem.addActionListener(new AddItemButtonListener());
        update.addActionListener(new UpdateButtonListener());
        // update.setBackground(Color.ORANGE);
        // delete.setBackground(Color.red);
        // delete.addActionListener(new DeleteButtonListener());
        // inputPanel.add(update, BorderLayout.CENTER);
        // inputPanel.add(delete, BorderLayout.CENTER);
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
        // public void actionPerformed(ActionEvent e) {
        //     stockManagement.addItem(item.getText(), quantity.getText());
        //     item.setText("");
        //     quantity.setText("");
        // }

    }

    // private class DeleteButtonListener implements ActionListener {
    //     @Override
    //     public void actionPerformed(ActionEvent e) {
    //         stockManagement.deleteItem();
    //     }
    // }


    // private class DeleteButtonListener implements ActionListener {
    //     public void actionPerformed(ActionEvent a) {
    //         if (a.getSource()==deleteButton){
    //             int row = table.getSelectedRow();
    //             String val ="";


    //            if (createJOptionpane("Are you sure you want to delete this item")==0){
    //                 for (Item i : ilist) {
    //                     if (i.getItemName().equals(table.getValueAt(row, 0))){
    //                         val=i.getItemName();
    //                         removeRecord(val);
    //                         ilist.remove(i);
    //                         model.setRowCount(0);
    //                         showTable(ilist);
    //                         break;
                
    //                     }   
    //                 }
    //             }

    //         }
    //     }
}

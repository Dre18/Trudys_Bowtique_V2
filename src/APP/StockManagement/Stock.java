package APP.StockManagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Stock {

    private static final String FILE_NAME = "StockList.dat";
    private ArrayList<Item> itemList;
    private DefaultTableModel tableModel;
    private JTable table;
    private static ArrayList<Item> ilist;
    private String resadd;

    public Stock() {
        itemList = loadStock(FILE_NAME);
        tableModel = new DefaultTableModel(new String[]{"Items", "Quantity"}, 0);
        table = new JTable(tableModel);
        showTable(itemList);
    }

    public JTable getTable() {
        return table;
    }

    private ArrayList<Item> loadStock(String fileName) {
        Scanner scanner = null;
        ArrayList<Item> itemList = new ArrayList<>();
        resadd = "";

        try {
            scanner = new Scanner(new File(fileName));
            while (scanner.hasNext()) {
                String[] nextLine = scanner.nextLine().split(" ");
                if (nextLine[0].equals("resadd")) {
                    resadd = nextLine[1];
                } else if (!nextLine[0].isEmpty()) {
                    String name = nextLine[0];
                    int quantity = Integer.parseInt(nextLine[1]);
                    itemList.add(new Item(name, quantity));
                }
            }
            scanner.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "System Error Unable to load items");
        }
        return itemList;
    }

    public void reduceStock(String item_name, int c) {
        for (Item i : ilist) {
            if (item_name.equals(i.getItemName())) {
                int reduction = (resadd.isEmpty()) ? 0 : Integer.parseInt(resadd);
                int newQuantity = i.getItemQuantity() - Math.max(c - reduction, 0);
                i.changeQuantity(newQuantity);
                break;
            }
        }
    }

    public void addItem(String itemName, String quantityString) {
        try {
            int quantity = Integer.parseInt(quantityString);
            Item newItem = new Item(itemName, quantity);
            itemList.add(newItem);
            addToTable(newItem);
            writeStockToFile(); // Call the new method to write to file
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Quantity Invalid");
        }
    }

    private void addToTable(Item item) {
        String[] itemData = {item.getItemName(), String.valueOf(item.getItemQuantity())};
        tableModel.addRow(itemData);
    }

   

    private void showTable(ArrayList<Item> ilist) {
        if (ilist.size() > 0) {
            for (Item i : ilist) {
                {
                    addToTable(i);
                }
            }
        }
    }
    private void writeStockToFile() {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        PrintWriter printWriter = null;

        try {
            fileWriter = new FileWriter(FILE_NAME);
            bufferedWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferedWriter);

            for (Item item : itemList) {
                printWriter.println(item.getItemName() + " " + item.getItemQuantity());
            }

            printWriter.flush();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Something Went Wrong");
        } finally {
            try {
                if (printWriter != null) {
                    printWriter.close();
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    
    public void updateItem() {
        int selectedRow = table.getSelectedRow();
    
        if (selectedRow >= 0) {
            // Stock del = new Stock.deleteItem();
            String newName = JOptionPane.showInputDialog(null, "Enter Updated name:");
            String test = newName;
            Item itemToDelete = itemList.get(selectedRow);
    
                // Remove the item from the list
                itemList.remove(itemToDelete);
    
                // Remove the item from the table model
                tableModel.removeRow(selectedRow);
                writeStockToFile();
                
    
            if (test != null){
                String newQuantityString = JOptionPane.showInputDialog(null, "Enter Updated quantity:");
                if( newQuantityString != null) {
                
                try {
                    
                    addItem(test,newQuantityString);
    
                    // // Update table model with new values
                    tableModel.setValueAt(test, selectedRow, 0);
                    tableModel.setValueAt(newQuantityString, selectedRow, 1);
    
                    // Update the file with changes
                    writeStockToFile();
    
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Quantity Invalid");
                }
            }
        }

    } 
        else {
            JOptionPane.showMessageDialog(null, "Please select an item to update");
        }
    }
    



    public void deleteItem() {
        int selectedRow = table.getSelectedRow();
    
        if (selectedRow >= 0) {
            if (JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this item?","Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
    
                // Get the item to be deleted
                Item itemToDelete = itemList.get(selectedRow);
    
                // Remove the item from the list
                itemList.remove(itemToDelete);
    
                // Remove the item from the table model
                tableModel.removeRow(selectedRow);
    
                // Update the file with the changes
                writeStockToFile();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select an item to delete");
        }
    }



}
    

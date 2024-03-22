package APP.StockManagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class StockManagement {

    private static final String FILE_NAME = "StockList.dat";
    private ArrayList<Item> itemList;
    private DefaultTableModel tableModel;
    private JTable table;
    private static ArrayList<Item> ilist;
    private String resadd;

    public StockManagement() {
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

    // public int createJOptionpane(String str){
    //     int n = JOptionPane.showConfirmDialog(pnl, str,"Confirmation",JOptionPane.YES_NO_OPTION);
    //     return n;
    // }

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
        // Implement logic to update the selected item in the table and itemList
        // Update the table model accordingly
        int selectedRow = table.getSelectedRow();

        if (selectedRow >= 0) {
            String newName = JOptionPane.showInputDialog(null, "Enter new name:");
            String newQuantityString = JOptionPane.showInputDialog(null, "Enter new quantity:");

            if (newName != null && newQuantityString != null) {
                try {
                    int newQuantity = Integer.parseInt(newQuantityString);
                    Item itemToUpdate = itemList.get(selectedRow);
                    itemToUpdate.setItemName(newName);
                    itemToUpdate.changeQuantity(newQuantity);
                    tableModel.setValueAt(newName, selectedRow, 0);
                    tableModel.setValueAt(newQuantity, selectedRow, 1);
                    writeStockToFile(); // Update the file after successful update
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Quantity Invalid");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select an item to update");
        }
    }


    
    

}
    

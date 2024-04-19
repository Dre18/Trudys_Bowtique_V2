package APP.NotificationsandEvents;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import APP.StockManagement.Item;

public class StockAlert {
    // private static final String FILE_NAME = "StockList.csv";
    private List<Item> itemList;
    private Map<String, Integer> criticalLevels;
    private int overallCriticalLevel; // New field for overall critical level

    public StockAlert() {
        itemList = loadStock(APP.StockManagement.Stock.FILE_NAME);
        criticalLevels = new HashMap<>();
        for (Item item : itemList) {
            // Set default critical level as 10 for each item
            criticalLevels.put(item.getItemName(), 10);
        }
        // Set default overall critical level
        overallCriticalLevel = 10;
    }

    private List<Item> loadStock(String fileName) {
        List<Item> itemList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(APP.StockManagement.Stock.FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length == 2) {
                    String itemName = parts[0].trim();
                    int quantity = Integer.parseInt(parts[1].trim());
                    itemList.add(new Item(itemName, quantity));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return itemList;
    }

    public void setCriticalLevel(String itemName, int newCriticalLevel) {
        if (criticalLevels.containsKey(itemName)) {
            // Ensure that the new critical level is not less than 0
            if (newCriticalLevel >= 0) {
                criticalLevels.put(itemName, newCriticalLevel);
            } else {
                System.out.println("New critical level cannot be negative.");
            }
        } else {
            System.out.println("Item not found in stock: " + itemName);
        }
    }

    public int getCriticalLevel(String itemName) {
        return criticalLevels.getOrDefault(itemName, -1);
    }

    // Methods for managing overall critical level

    public void setOverallCriticalLevel(int newOverallCriticalLevel) {
        if (newOverallCriticalLevel >= 0) {
            overallCriticalLevel = newOverallCriticalLevel;
        } else {
            System.out.println("Critical level cannot be negative.");
        }
    }

    public int getOverallCriticalLevel() {
        return overallCriticalLevel;
    }

    // Method for checking stock levels (unchanged)

    public List<Item> checkStockLevels() {
        List<Item> lowStockItems = new ArrayList<>();
        for (Item item : itemList) {
            int quantity = item.getItemQuantity();
            int criticalLevel = criticalLevels.get(item.getItemName());
            if (quantity < criticalLevel) {
                lowStockItems.add(item);
            }
        }
        return lowStockItems;
    }

    // Method to change overall critical level
    public void changeOverallCriticalLevel() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Current critical level: " + overallCriticalLevel);
            System.out.println("Enter new  critical level ");
            int newOverallCriticalLevel = scanner.nextInt();

            if (newOverallCriticalLevel > overallCriticalLevel) {
                setOverallCriticalLevel(newOverallCriticalLevel);
                System.out.println("Critical level changed successfully.");
            } else {
                System.out.println("New critical level must be above the current level.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Critical level not changed.");
        }
    }

    // public static void main(String[] args) {
    //     StockAlert stockAlert = new StockAlert();
    //     List<Item> lowStockItems = stockAlert.checkStockLevels();
    //     if (!lowStockItems.isEmpty()) {
    //         System.out.println("ALERT: The following items are below critical level:");
    //         for (int i = 0; i < lowStockItems.size(); i++) {
    //             Item item = lowStockItems.get(i);
    //             System.out.println((i + 1) + ". " + item.getItemName() + ": Quantity = " + item.getItemQuantity());
    //         }

    //         stockAlert.changeOverallCriticalLevel(); // Prompt user to change overall critical level
    //     } else {
    //         System.out.println("All items are above the critical level.");
    //     }
    // }
}

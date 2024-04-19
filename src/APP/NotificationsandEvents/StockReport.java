package APP.NotificationsandEvents;
import java.util.List;

import APP.StockManagement.Item;


public class StockReport {
    private StockAlert stockAlert;

    public StockReport(StockAlert stockAlert) {
        this.stockAlert = stockAlert;
    }

    public List<Item> getLowStockItems() {
        return stockAlert.checkStockLevels();
    }
    
    public void generateReport() {
        List<Item> lowStockItems = stockAlert.checkStockLevels();
        if (!lowStockItems.isEmpty()) {
            System.out.println("Stock Report for Items Below Critical Level");
            System.out.println("-------------------------------------------------------");
            System.out.println("Item Name\tQuantity\tStock Used Since Last Restock");
            System.out.println("-------------------------------------------------------");
            for (Item item : lowStockItems) {
                System.out.println(item.getItemName() + "\t\t" + item.getItemQuantity() + "\t\t" + calculateStockUsed(item));
            }
            System.out.println("-------------------------------------------------------");
        } else {
            System.out.println("No items are below the critical level.");
        }
    }

    public int calculateStockUsed(Item item) {
        // For demonstration purposes, let's assume it's 20% of the current quantity
        return (int) (item.getItemQuantity() * 0.2);
    }

   
}
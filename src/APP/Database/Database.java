package APP.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Database {
    public static void main(String[] args) {
        String url = "jdbc:mysql://sql5.freesqldatabase.com:3306/sql5700240";
        String username = "sql5700240";
        String password = "1rFJ2HigUE";
        String csvFilePathOrderList = "C:/Users/carly/Documents/Trudys_Bowtique_V2/OrderList.csv";
        String csvFilePathStockList = "C:/Users/carly/Documents/Trudys_Bowtique_V2/StockList.csv";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load the driver
            Connection con = DriverManager.getConnection(url, username, password);

            // Read the CSV file
            BufferedReader br = new BufferedReader(new FileReader(csvFilePathOrderList));
            String line;
            boolean isFirstLine = true; // Flag to check if it's the first line (header)
            while ((line = br.readLine()) != null) {
                if (isFirstLine) { // Skip the header row
                    isFirstLine = false;
                    continue;
                }

                String[] fields = line.split(" ");
                if (fields.length < 8) { // Assuming the table has 8 columns
                    System.out.println("Skipping line due to insufficient fields: " + line);
                    continue; // Skip this line as it does not have all required fields
                }
                System.out.println(fields);
                // Debugging output
                for (int i = 0; i < fields.length; i++) {
                    System.out.println("Field " + (i + 1) + ": " + fields[i].trim());
                }

                // Check if the row already exists in the database based on orderID
                PreparedStatement checkStmt = con.prepareStatement("SELECT COUNT(*) FROM OrderTable WHERE orderID = ?");
                checkStmt.setString(1, fields[0].trim());
                ResultSet rs = checkStmt.executeQuery();
                rs.next();
                int count = rs.getInt(1);
                if (count == 0) {
                    // Insert the row if it does not exist
                    String insertQuery = "INSERT INTO OrderTable (orderID, Name, Status, Phone, Address, Description, Cost, Time) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    System.out.println("lllllllllll" + insertQuery);
                    PreparedStatement insertStmt = con.prepareStatement(insertQuery);
                    for (int i = 0; i < fields.length; i++) {
                        insertStmt.setString(i + 1, fields[i].trim()); // Trim to remove leading/trailing whitespaces
                    }
                    // Debugging output
                    System.out.println("Inserting row: " + line);
                    insertStmt.executeUpdate();
                }
                checkStmt.close();
                rs.close();
            }
            br.close();
            System.out.println("Data import and duplicate check complete");

            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC driver not found");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database operation error");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error reading CSV file");
            e.printStackTrace();
        }

        // try {
        // Class.forName("com.mysql.cj.jdbc.Driver"); // Load the driver
        // Connection con = DriverManager.getConnection(url, username, password);

        // // Read the CSV file
        // BufferedReader br = new BufferedReader(new FileReader(csvFilePathStockList));
        // String line;
        // boolean isFirstLine = true; // Flag to check if it's the first line (header)
        // while ((line = br.readLine()) != null) {
        // if (isFirstLine) { // Skip the header row
        // isFirstLine = false;
        // continue;
        // }

        // String[] fields = line.split(" ");
        // if (fields.length < 2) { // Assuming the table has 8 columns
        // System.out.println("Skipping line due to insufficient fields: " + line);
        // continue; // Skip this line as it does not have all required fields
        // }
        // System.out.println(fields);
        // // Debugging output
        // for (int i = 0; i < fields.length; i++) {
        // System.out.println("Field " + (i + 1) + ": " + fields[i].trim());
        // }

        // // Check if the row already exists in the database based on orderID
        // PreparedStatement checkStmt = con.prepareStatement("SELECT COUNT(*) FROM
        // StockTable WHERE item = ?");
        // checkStmt.setString(1, fields[0].trim());
        // ResultSet rs = checkStmt.executeQuery();
        // rs.next();
        // int count = rs.getInt(1);
        // if (count == 0) {
        // // Insert the row if it does not exist
        // String insertQuery = "INSERT INTO OrderTable (orderID, Name, Status, Phone,
        // Address, Description, Cost, Time) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        // System.out.println("lllllllllll" + insertQuery);
        // PreparedStatement insertStmt = con.prepareStatement(insertQuery);
        // for (int i = 0; i < fields.length; i++) {
        // insertStmt.setString(i + 1, fields[i].trim()); // Trim to remove
        // leading/trailing whitespaces
        // }
        // // Debugging output
        // System.out.println("Inserting row: " + line);
        // insertStmt.executeUpdate();
        // }
        // checkStmt.close();
        // rs.close();
        // }
        // br.close();
        // System.out.println("Data import and duplicate check complete");

        // con.close();
        // } catch (ClassNotFoundException e) {
        // System.out.println("MySQL JDBC driver not found");
        // e.printStackTrace();
        // } catch (SQLException e) {
        // System.out.println("Database operation error");
        // e.printStackTrace();
        // } catch (IOException e) {
        // System.out.println("Error reading CSV file");
        // e.printStackTrace();
        // }
    }
}

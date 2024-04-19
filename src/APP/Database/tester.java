
package APP.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CheckMySQLDriver {
    public static void main(String[] args) {
        try {
            // Establish a connection to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username",
                    "password");

            // Check if the connection is successful
            if (connection != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to connect to the database!");
            }

            // Close the connection
            connection.close();
        } catch (SQLException e) {
            System.out.println("An error occurred while connecting to the database.");
            e.printStackTrace();
        }
    }
}

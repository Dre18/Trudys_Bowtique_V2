package APP.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    public static void main(String[] args) {
        String url = "jdbc:mysql://sql5.freesqldatabase.com:3306/sql5700240";
        String username = "sql5700240";
        String password = "1rFJ2HigUE";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Add this line to load the driver
            Connection con = DriverManager.getConnection(url, username, password);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM OrderTable");

            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " +
                        resultSet.getString(3) + " " + resultSet.getString(4) + " " +
                        resultSet.getInt(5) + " " + resultSet.getString(6) + " " +
                        resultSet.getString(7) + " " + resultSet.getInt(8));
            }

            System.out.println("Database connection successful");

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

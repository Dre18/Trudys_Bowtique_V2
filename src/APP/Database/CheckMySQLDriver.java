package APP.Database;

public class CheckMySQLDriver {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL JDBC Driver is successfully added to your classpath!");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
        }
    }
}
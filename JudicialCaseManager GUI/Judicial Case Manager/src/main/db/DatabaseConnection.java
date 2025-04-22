package main.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class DatabaseConnection {

    private static Connection connection;

    private DatabaseConnection() {}

    public static Connection getConnection() throws SQLException {

        try {
            // Example for loading MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully.");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver loading failed: " + e);
        }
        if (connection == null || connection.isClosed()) {
            try  {


                String url = "jdbc:mysql://localhost:3306/judicialsystem";
                String user = "root";
                String password = "$Ashwin1803";

                connection = DriverManager.getConnection(url, user, password);
                System.out.println("✅ Database connected successfully!");

            } catch (Exception e) {
                e.printStackTrace();
                throw new SQLException("Failed to connect to the database");
            }
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("🔒 Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

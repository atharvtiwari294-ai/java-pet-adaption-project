package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/pet_adoption_db";
    private static final String USER = "root";
    private static final String PASSWORD = "sastra@123";

    // Common connection method for all DAOs
    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✔ Database Connected!");
        } catch (Exception e) {
            System.out.println("❌ DB Error: " + e.getMessage());
        }
        return con;
    }
}
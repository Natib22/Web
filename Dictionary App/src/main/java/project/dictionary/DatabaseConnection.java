package project.dictionary;

import java.sql.*;

public class DatabaseConnection {


    private static final String URL = "jdbc:sqlite:src/main/resources/Dictionary.db";



    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
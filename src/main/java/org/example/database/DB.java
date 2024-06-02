package org.example.database;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private final static DBConfig config = new DBConfig();
    private final static String DB_URL = config.getURL();
    private static Connection connection;

    public static Connection connect() throws SQLException {
        connection = DriverManager.getConnection(DB_URL);
        return connection;
    }

    public static void setup() {
        new DBSetup(connection);
    }


}

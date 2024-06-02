package org.example.database;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private final static DBConfig config = new DBConfig();
    private final static String DB_URL = config.getURL();

    public static Connection connect() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL);

        new DBSetup(connection);

        return connection;
    }


}

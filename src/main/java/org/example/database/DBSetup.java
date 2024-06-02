package org.example.database;

import java.sql.Connection;

public class DBSetup {
    private Connection connection;

    public DBSetup(Connection connection) {
        this.connection = connection;
    }

    private String vehicleTableCreationString() {
        return """
                
                """;
    }

    private String instructionsTableCreationString() {
        return """
                """;
    }

    private String resourcesTableCreationString() {
        return """
                """;
    }
}

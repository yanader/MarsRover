package org.example.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBSetup {
    private Connection connection;

    public DBSetup(Connection connection) {
        this.connection = connection;
        seedDatabase();
    }

    public void seedDatabase() {
        try (Statement statement = connection.createStatement()){
            statement.execute(vehicleTableCreationString());
            statement.execute(instructionsTableCreationString());
            statement.execute(resourcesTableCreationString());
        } catch (SQLException e) {
            System.out.println("Seeding failed");
            throw new RuntimeException(e);
        }

    }

    private String vehicleTableCreationString() {
        return """
                CREATE TABLE IF NOT EXISTS vehicles (
                             id SERIAL PRIMARY KEY,
                             vehicle_type varchar(20));
                """;
    }

    private String instructionsTableCreationString() {
        return """
                CREATE TABLE IF NOT EXISTS instructions (
                            id SERIAL PRIMARY KEY,
                            vehicle_id INT REFERENCES vehicles(id),
                            instruction varchar(255));
                """;
    }

    private String resourcesTableCreationString() {
        return """
                CREATE TABLE IF NOT EXISTS resources (
                            id SERIAL PRIMARY KEY,
                            vehicle_id INT REFERENCES vehicles(id),
                            resource varchar(20));
                """;
    }
}

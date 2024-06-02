package org.example.database;

import org.example.dataclasses.Instruction;
import org.example.dataclasses.Resource;
import org.example.logic.Vehicle;

import java.sql.*;

public class DB {
    private final static DBConfig config = new DBConfig();
    private final static String DB_URL = config.getURL();
    private static Connection connection;

    public static Connection connect() throws SQLException {
        connection = DriverManager.getConnection(DB_URL);
        return connection;
    }

    public static void setup() {
        checkConnection();
        new DBSetup(connection);
    }

    public static void logNewVehicle(Vehicle vehicle) {
        checkConnection();
        String sql = """
                     INSERT INTO vehicles (id, vehicle_type)
                     VALUES (?, ?);
                     """;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, vehicle.getId());
            statement.setString(2, vehicle.getClass().getSimpleName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void logNewInstruction(Vehicle vehicle, Instruction[] instruction) {
        checkConnection();
        String sql = """
                    INSERT INTO instructions (vehicle_id, instruction)
                    VALUES (?, ?);
                    """;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, vehicle.getId());
            statement.setString(2, instructionArrayAsString(instruction));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void logNewResource(Vehicle vehicle, Resource resource) {
        checkConnection();
        String sql = """
                    INSERT INTO resources (vehicle_id, resource)
                    VALUES (?, ?);
                    """;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, vehicle.getId());
            statement.setString(2, resource.name());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void checkConnection() {
        if (connection == null) {
            try {
                connect();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static ResultSet getVehicleDetails() {
        String sql = "SELECT * FROM vehicles;";
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet getInstructionDetails() {
        String sql = "SELECT * FROM instructions;";
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet getResourceDetails() {
        String sql = "SELECT * FROM resources;";
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static String instructionArrayAsString(Instruction[] instructions) {
        StringBuilder sb = new StringBuilder();
        for (Instruction i : instructions) {
            sb.append(i.getInstructionAsString());
        }
        return sb.toString();
    }


}

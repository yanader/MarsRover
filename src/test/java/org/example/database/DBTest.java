package org.example.database;


import org.example.dataclasses.Instruction;
import org.example.dataclasses.Position;
import org.example.dataclasses.Resource;
import org.example.logic.Miner;
import org.example.logic.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DBTest {

    Connection connection = null;


    @BeforeEach
    void setup() {
        try {
            connection = DB.connect();
            DB.setup();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void connectReturnsConnection() {
        assertNotNull(connection);
    }

    @ParameterizedTest
    @ValueSource(strings = {"vehicles", "instructions", "resources"})
    void databaseTablesExist(String input) throws SQLException {
        assertTrue(checkTableExists(input));
    }

    @Test
    void logNewVehicleLogsNewVehicle() throws SQLException {
        Miner miner = new Miner(new Position(0, 0));
        DB.logNewVehicle(miner);

        ResultSet results = connection.createStatement().executeQuery("SELECT * FROM vehicles");

        assertTrue(results.next());

        int actualId = results.getInt("id");
        String actualName = results.getString("vehicle_type");

        assertEquals(miner.getClass().getSimpleName(), actualName);
    }

    @Test
    void logInstructionLogsNewInstruction() throws SQLException {
        Miner miner = new Miner(new Position(0, 0));
        Instruction[] instructions = {Instruction.D};
        DB.logNewVehicle(miner);
        DB.logNewInstruction(miner, instructions);

        ResultSet results = connection.createStatement().executeQuery("SELECT * FROM instructions");

        assertTrue(results.next());

        int actualId = results.getInt("id");
        String actualInstructionString = results.getString("instruction");

        assertEquals(1, actualId);
        assertEquals("D", actualInstructionString);
    }

    @Test
    void logResourceLogsNewResource() throws SQLException {
        Miner miner = new Miner(new Position(0, 0));
        Resource resource = Resource.GOLD;
        DB.logNewVehicle(miner);
        DB.logNewResource(miner, resource);

        ResultSet results = connection.createStatement().executeQuery("SELECT * FROM resources");

        assertTrue(results.next());

        int actualId = results.getInt("id");
        String actualInstructionString = results.getString("resource");

        assertEquals(1, actualId);
        assertEquals("GOLD", actualInstructionString);
    }




    private boolean checkTableExists(String tableName) throws SQLException {
        try (ResultSet rs = connection.getMetaData().getTables(null, null, tableName.toUpperCase(), null)) {
            return rs.next();
        }
    }


}
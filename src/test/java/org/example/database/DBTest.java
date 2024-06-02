package org.example.database;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DBTest {

    Connection connection = null;

    @BeforeEach
    void setup() {
        try {
            connection = DB.connect();
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

    private boolean checkTableExists(String tableName) throws SQLException {
        try (ResultSet rs = connection.getMetaData().getTables(null, null, tableName.toUpperCase(), null)) {
            return rs.next();
        }
    }
}
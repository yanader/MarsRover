package org.example.database;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
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
}
package org.example.database;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DBSetupTest {

    @Mock
    Connection mockConnection;

    @Mock
    Statement mockStatement;

    @BeforeEach
    void setup() throws SQLException {
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        new DBSetup(mockConnection);
    }

    @Test
    void seedDatabase() {
        try {
            verify(mockConnection).createStatement();
            verify(mockStatement, times(3)).execute(anyString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
package org.example.database;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DBConfigTest {

    @Test
    void getURLReturnsString() {
        DBConfig config = new DBConfig();
        assertEquals(String.class, config.getURL().getClass());
    }
}
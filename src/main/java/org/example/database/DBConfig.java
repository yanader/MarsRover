package org.example.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBConfig {
    private final Properties properties;

    public DBConfig() {
        properties = new Properties();
        String configFilePath = "config/db.properties";

        File configFile = new File(configFilePath);

        try {
            FileInputStream configFileReader = new FileInputStream(configFile);
            properties.load(configFileReader);
            configFileReader.close();
        } catch (IOException e) {
            System.out.println("File not found");
            throw new RuntimeException(e);
        }
    }

    public String getURL() {
        return this.properties.getProperty("db.url");
    }

}

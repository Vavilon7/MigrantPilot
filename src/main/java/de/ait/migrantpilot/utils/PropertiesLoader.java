package de.ait.migrantpilot.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

    private PropertiesLoader() {
    }

    private static final String PROP_FILE = "/data.properties";

    public static String loadProperty(String name) {

        Properties properties = new Properties();

        try (InputStream resource = PropertiesLoader.class.getResourceAsStream(PROP_FILE)) {
            if (resource == null) {
                throw new IllegalStateException("Properties file not found: " + PROP_FILE);
            }
            properties.load(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String value = "";
        if (name != null) {
            value = properties.getProperty(name);
        }
        return value;
    }
}

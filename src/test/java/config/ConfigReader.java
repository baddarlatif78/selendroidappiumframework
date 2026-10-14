package config;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public final class ConfigReader {
    private static final Properties PROPERTIES = new Properties();
    static {
        String configPath = System.getProperty("configFile", "config.properties");
        Path path = Path.of(configPath);
        try (InputStream input = Files.exists(path)
                ? Files.newInputStream(path)
                : ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) throw new IllegalStateException("Cannot find config.properties at " + configPath);
            PROPERTIES.load(input);
        } catch (Exception e) {
            throw new ExceptionInInitializerError("Failed to load " + configPath + ": " + e.getMessage());
        }
    }
    private ConfigReader() {}
    public static String get(String key) {
        String override = System.getProperty(key);
        return override != null ? override : PROPERTIES.getProperty(key);
    }
    public static String get(String key, String fallback) {
        String value = get(key);
        return value == null || value.isBlank() ? fallback : value;
    }
    public static int getInt(String key, int fallback) {
        try { return Integer.parseInt(get(key)); } catch (Exception e) { return fallback; }
    }
}
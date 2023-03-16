package logic;

import annotation.Config;
import interfaces.DatabaseConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigService {
    public static DatabaseConfig load(Class<?> clazz) {
        if (clazz.isAnnotationPresent(Config.class)) {
            Properties properties = new Properties();
            InputStream reader = ConfigService.class
                    .getClassLoader()
                    .getResourceAsStream("config/db.properties");
            try {
                properties.load(reader);
                return new DatabaseConfigImpl(properties);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}

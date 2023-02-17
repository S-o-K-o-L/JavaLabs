package logic;

import annotation.Config;
import interfaces.DatabaseConfig;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
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
                DatabaseConfigImpl databaseConfigImpl = new DatabaseConfigImpl();
                for (Field field : databaseConfigImpl
                        .getClass()
                        .getDeclaredFields()) {
                    field.setAccessible(true);
                    if (field.getType().toString().equals("boolean")) {
                        String ssl = properties.getProperty("db." + field.getName());
                        boolean boolSsl = Boolean.parseBoolean(ssl);
                        field.setBoolean(databaseConfigImpl, boolSsl);
                    } else {
                        field.set(databaseConfigImpl,
                                properties.getProperty("db." + field.getName()));
                    }
                }
                return databaseConfigImpl;
            } catch (IOException | IllegalAccessException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}

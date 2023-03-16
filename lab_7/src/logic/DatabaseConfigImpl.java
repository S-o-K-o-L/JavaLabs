package logic;

import annotation.Property;
import interfaces.DatabaseConfig;

import java.util.Properties;

public class DatabaseConfigImpl implements DatabaseConfig {
    private final Properties properties;
    public DatabaseConfigImpl(Properties properties) {
        this.properties = properties;
    }
    @Override
    @Property("db.jdbcUrl")
    public String getJdbcUrl() {
        return properties.getProperty("db.jdbcUrl");
    }

    @Override
    @Property("db.user")
    public String getUser() {
        return properties.getProperty("db.user");
    }

    @Override
    @Property("db.password")
    public String getPassword() {
        return properties.getProperty("db.password");
    }

    @Override
    @Property("db.ssl")
    public boolean getSsl() {
        String ssl = properties.getProperty("db.ssl");
        return Boolean.parseBoolean(ssl);
    }
}

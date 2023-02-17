package logic;

import interfaces.DatabaseConfig;

public class DatabaseConfigImpl implements DatabaseConfig {
    private String user;
    private String password;
    private boolean ssl;
    private String jdbcUrl;

    @Override
    public String getJdbcUrl() {
        return jdbcUrl;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean getSsl() {
        return ssl;
    }
}

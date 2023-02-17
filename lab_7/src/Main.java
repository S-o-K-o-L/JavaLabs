import logic.ConfigService;
import interfaces.DatabaseConfig;

// properties
public class Main {
    public static void main(String[] args) {
        DatabaseConfig config1 = ConfigService.load(DatabaseConfig.class);
        System.out.println(config1.getJdbcUrl());
        System.out.println(config1.getUser());
        System.out.println(config1.getSsl());
        System.out.println(config1.getPassword());
    }
}

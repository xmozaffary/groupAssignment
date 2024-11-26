package se.demo.config;

public class DatabaseConnectionFactory {
    public static DatabaseConnection createDatabaseConnection(int choice){
        switch (choice){
            case 1 -> {
                return new MySQlConnection();
            }
            case 2 -> {
                return new H2Connection();
            }
            default -> throw new IllegalStateException("Ogiltigt val: " + choice);
        }
    }
}
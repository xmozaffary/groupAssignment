package se.demo.config;
import io.github.cdimascio.dotenv.Dotenv;

public class DatabaseConfig {
    private static final Dotenv  dotenv = Dotenv.load();


    public static String getMySqlDbUrl() {
        return dotenv.get("MYSQL_DB_URL");
    }

    public static String getMySqlUsername() {
        return dotenv.get("MYSQL_DB_USERNAME");
    }

    public static String getMySqlPassword(){
        return dotenv.get("MYSQL_DB_PASSWORD");
    }

    public static String getMySQlDialect(){
        return dotenv.get("MYSQL_DB_DIALECT");
    }

    public static String getH2DbUrl(){
        return dotenv.get("H2_DB_URL");
    }

    public static String getH2DbUsername(){
        return dotenv.get("H2_DB_USERNAME");
    }

    public static String getH2DbPassword(){
        return dotenv.get("H2_DB_PASSWORD");
    }

    public static String getH2DbDialect(){
        return dotenv.get("H2_DB_DIALECT");
    }
}
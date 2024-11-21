package se.demo.config;




import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import se.demo.config.DatabaseConfig;
import se.demo.entity.Todo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                /* Attempt to create the database if it doesn't exist */
                createDatabaseIfNotExists();

                Configuration configuration = new Configuration();
                configuration.setProperty("hibernate.connection.url", DatabaseConfig.getDbUrl());
                configuration.setProperty("hibernate.connection.username", DatabaseConfig.getDbUsername());
                configuration.setProperty("hibernate.connection.password", DatabaseConfig.getDbPassword());
                configuration.setProperty("hibernate.dialect", DatabaseConfig.getDbDialect());
                configuration.setProperty("hibernate.hbm2ddl.auto", "update");
                configuration.setProperty("hibernate.show_sql", "true");

                configuration.addAnnotatedClass(Todo.class);

                sessionFactory = configuration.buildSessionFactory();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return sessionFactory;
    }
    private void createDatabaseIfNotExists() throws SQLException {
        String url = DatabaseConfig.getDbUrl();
        String dbName = "todo_db";
        String username = DatabaseConfig.getDbUsername();
        String password = DatabaseConfig.getDbPassword();

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {
            String checkDbQuery = "SHOW DATABASES LIKE '" + dbName + "'";
            var resultSet = statement.executeQuery(checkDbQuery);

            if(!resultSet.next()){
                String createDbQuery = "CREATE DATABASE " + dbName;
                statement.execute(createDbQuery);
                System.out.println("Database " + dbName + " created successfully");
            }else {
                System.out.println("Database " + dbName + " already exists");
            }
        }
    }
}


// Vi har skapat en create databaseconnectionifnotexists
// funktion för att skapa vår databas och om det inte lyckas eller den redan finns kastar vi en exception
// Det var det första vi kom på

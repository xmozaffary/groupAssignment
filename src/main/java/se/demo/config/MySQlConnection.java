package se.demo.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import se.demo.entity.Todo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQlConnection implements DatabaseConnection {
    private static SessionFactory sessionFactory;
    @Override
    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                createDatabaseIfNotExists();

                Configuration configuration = new Configuration();
                configuration.setProperty("hibernate.connection.url", DatabaseConfig.getMySqlDbUrl());
                configuration.setProperty("hibernate.connection.username", DatabaseConfig.getMySqlUsername());
                configuration.setProperty("hibernate.connection.password", DatabaseConfig.getMySqlPassword());
                configuration.setProperty("hibernate.dialect", DatabaseConfig.getMySQlDialect());
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
        String url = DatabaseConfig.getMySqlDbUrl();
        String dbName = "todo_db";
        String username = DatabaseConfig.getMySqlUsername();
        String password = DatabaseConfig.getMySqlPassword();

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
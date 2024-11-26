package se.demo.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import se.demo.entity.Todo;

public class H2Connection implements DatabaseConnection {
    private static SessionFactory sessionFactory;
    @Override
    public SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            try{
                Configuration configuration = new Configuration();
                configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
                configuration.setProperty("hibernate.connection.url", DatabaseConfig.getH2DbUrl());
                configuration.setProperty("hibernate.connection.username", DatabaseConfig.getH2DbUsername());
                configuration.setProperty("hibernate.connection.password", "");
                configuration.setProperty("hibernate.dialect", DatabaseConfig.getH2DbDialect());
                configuration.setProperty("hibernate.hbm2ddl.auto", "create-drop");
                configuration.setProperty("hibernate.show_sql", "true");

                configuration.addAnnotatedClass(Todo.class);

                sessionFactory = configuration.buildSessionFactory();
                System.out.println("SessionFactory är initialiserad för H2.");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}

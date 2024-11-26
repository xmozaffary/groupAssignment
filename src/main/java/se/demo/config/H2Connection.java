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
                configuration.setProperty("hibernate.connection.url", "jdbc:h2:mem:todo_db");
                configuration.setProperty("hibernate.connection.username", "sa");
                configuration.setProperty("hibernate.connection.password", "");
                configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
                configuration.setProperty("hibernate.hbm2ddl.auto", "create-drop");
                configuration.setProperty("hibernate.show_sql", "true");

                configuration.addAnnotatedClass(Todo.class);

                sessionFactory = configuration.buildSessionFactory();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}

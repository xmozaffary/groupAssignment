
package se.demo.config;
import org.hibernate.SessionFactory;

public interface DatabaseConnection {
    SessionFactory getSessionFactory();
}
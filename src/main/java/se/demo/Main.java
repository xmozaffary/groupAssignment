package se.demo;

import se.demo.config.DatabaseConnection;
import se.demo.dao.TodoDAO;
import se.demo.dao.impl.TodoDAOImpl;
import se.demo.service.TodoService;
import se.demo.ui.MenuSystem;

public class Main {
    public static void main(String[] args) {

        DatabaseConnection databaseConnection = new DatabaseConnection();
        TodoDAO todoDAO = new TodoDAOImpl(databaseConnection.getSessionFactory());
        TodoService todoService = new TodoService(todoDAO);

        MenuSystem menuSystem = new MenuSystem(todoService);
        menuSystem.displayMenu();
    }
}

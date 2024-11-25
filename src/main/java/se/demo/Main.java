package se.demo;

import se.demo.config.DatabaseConnection;
import se.demo.dao.TodoDAO;
import se.demo.dao.impl.TodoDAOImpl;
import se.demo.service.TodoService;
import se.demo.ui.MenuSystem;

public class Main {
    public static void main(String[] args) {
        // init db connection
        DatabaseConnection databaseConnection = new DatabaseConnection();

        // init DAOs
        TodoDAO todoDAO = new TodoDAOImpl(databaseConnection.getSessionFactory());


        // init service
        TodoService todoService = new TodoService(todoDAO);


        // init and start menu
        MenuSystem menuSystem = new MenuSystem(todoService);
        menuSystem.displayMenu();
    }
}

// 2 ändringar
// databas nämn
// paramater
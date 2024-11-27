package se.demo;

import se.demo.config.DatabaseConnection;
import se.demo.config.DatabaseConnectionFactory;
import se.demo.dao.TodoDAO;
import se.demo.dao.impl.TodoDAOImpl;
import se.demo.service.TodoService;
import se.demo.ui.ChoiceDatabase;
import se.demo.ui.MenuSystem;


public class Main {
    public static void main(String[] args) {

       ChoiceDatabase choiceDatabase = new ChoiceDatabase();
        int choice = choiceDatabase.choiceDatabase();
        DatabaseConnection databaseConnection = DatabaseConnectionFactory.createDatabaseConnection(choice);
        System.out.println(databaseConnection.getSessionFactory());
        if(databaseConnection.getSessionFactory() == null){
            System.out.println("Database connection failed!");
            return;
        }
        TodoDAO todoDAO = new TodoDAOImpl(databaseConnection.getSessionFactory());
        TodoService todoService = new TodoService(todoDAO);
        MenuSystem menuSystem = new MenuSystem(todoService);
        menuSystem.displayMenu();
    }
}

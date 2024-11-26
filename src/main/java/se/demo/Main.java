package se.demo;

import se.demo.config.DatabaseConnection;
import se.demo.config.DatabaseConnectionFactory;
import se.demo.dao.TodoDAO;
import se.demo.dao.impl.TodoDAOImpl;
import se.demo.service.TodoService;
import se.demo.ui.MenuSystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n╔════════════════════════════╗");
        System.out.println("║        Välja databas       ║");
        System.out.println("╠════════════════════════════╣");
        System.out.println("║ 1. MySql                   ║");
        System.out.println("║ 2. H2                      ║");
        System.out.println("╚════════════════════════════╝");
        System.out.print("Välj ett alternativ: ");
        int choice = scanner.nextInt();
        scanner.nextLine();


        DatabaseConnection databaseConnection = DatabaseConnectionFactory.createDatabaseConnection(choice);

        TodoDAO todoDAO = new TodoDAOImpl(databaseConnection.getSessionFactory());
        TodoService todoService = new TodoService(todoDAO);

        MenuSystem menuSystem = new MenuSystem(todoService);
        menuSystem.displayMenu();
    }
}

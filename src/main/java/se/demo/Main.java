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
        DatabaseConnection databaseConnection = null;

//        int choice = scanner.nextInt();
//        scanner.nextLine();


        while (databaseConnection == null) {
            System.out.print("Ange ditt val (1 eller 2): ");
            int choice = scanner.nextInt();
            databaseConnection = DatabaseConnectionFactory.createDatabaseConnection(choice);

            if (databaseConnection == null) {
                System.out.println("Ogiltligt val. Försök igen.");
            }
        }

        // databaseConnection = DatabaseConnectionFactory.createDatabaseConnection(choice);

//        if (databaseConnection == null) {
//            System.out.println("Ogiltigt val av databas. Programmet avslutas.");
//            return;
//        }
        TodoDAO todoDAO = new TodoDAOImpl(databaseConnection.getSessionFactory());
        TodoService todoService = new TodoService(todoDAO);

        MenuSystem menuSystem = new MenuSystem(todoService);
        menuSystem.displayMenu();


    }
}

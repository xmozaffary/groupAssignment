package se.demo.ui;

import java.util.Scanner;

public class ChoiceDatabase {
    public int choiceDatabase(){

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
        return choice;
    }
}

package se.demo.ui;

import se.demo.entity.Todo;
import se.demo.service.TodoService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MenuSystem {
    private final Scanner scanner;
    private final TodoService todoService;

    public MenuSystem(TodoService todoService){
        this.scanner = new Scanner(System.in);
        this.todoService = todoService;
    }


    public void displayMenu(){
        while (true){
            System.out.println("\n╔════════════════════════════╗");
            System.out.println("║       TODO HANTERARE       ║");
            System.out.println("╠════════════════════════════╣");
            System.out.println("║ 1. Lägg till ny todo       ║");
            System.out.println("║ 2. Visa alla todos         ║");
            System.out.println("║ 3. Markera todo som klar   ║");
            System.out.println("║ 4. Ta bort todo            ║");
            System.out.println("║ 5. Avsluta                 ║");
            System.out.println("╚════════════════════════════╝");
            System.out.print("Välj ett alternativ: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1 -> addTodo();
                case 2 -> viewAllTodo();
                case 3 -> updateTodo();
                case 4 -> deleteTodo();
                case 5 -> {
                    System.out.println("Avsluta");
                    return;
                }
                default -> System.out.printf("Ogiltigt val. Försök igen.");
            }
        }
    }

    private void addTodo(){
        System.out.print("Ange todo-beskrivning: ");
        String description = scanner.nextLine();
        Todo todo = new Todo(description, false);
        todoService.addTodo(todo);
        System.out.println("Todo tillagd framgångsrikt!");
    }

    private void viewAllTodo(){
        List<Todo> todos = todoService.getAllTodo();
        if(todos.isEmpty()){
            System.out.println("Ingen todos att visa.");
        } else {
            System.out.println("\n╔════╦═══════════════════════════════════════╦════════════╦═══════════╗");
            System.out.println("║ ID ║ Beskrivning                           ║ Skapad     ║ Status    ║");
            System.out.println("╠════╬═══════════════════════════════════════╬════════════╬═══════════╣");

                for(Todo todo : todos){
                    System.out.printf("║ %-2d ║ %-37s ║ %-10s ║ %-9s ║%n",
                            todo.getId(),
                            todo.getDescription().length() > 37 ? todo.getDescription().substring(0, 34) + "..." : todo.getDescription(),
                            todo.getCreatedAt().toString().substring(0, 10),
                            todo.isCompleted() ? "Klar" : "Pågående"
                    );
                }
            System.out.println("╚════╩═══════════════════════════════════════╩════════════╩═══════════╝");
        }
    }

    private void updateTodo(){
        viewAllTodo();
        System.out.print("Ange ID för todo att markera som klar: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Optional<Todo> optionalTodo = todoService.getTodoById(id);
        if(optionalTodo.isPresent()){
            Todo todo = optionalTodo.get();
            todo.setCompleted(true);
            todoService.updateTodo(todo);
            System.out.println("Todo markerad som klar!");
        }else {
            System.out.println("Ingen todo hittades med det ID:t.");
        }
    }
    private void deleteTodo(){
        viewAllTodo();
        System.out.print("Ange ID för todo att ta bort: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Optional<Todo> optionalTodo = todoService.getTodoById(id);
        if(optionalTodo.isPresent()){
            Todo todo = optionalTodo.get();
            todoService.deleteTodo(todo);
            System.out.println("Todo borttagen!");
        }else {
            System.out.println("Ingen todo hittades med det ID:t.");
        }
    }
}


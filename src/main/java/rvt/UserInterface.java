package rvt;

import java.util.Scanner;

public class UserInterface {
    
    String user_input;
    TodoList todo = new TodoList();
    Scanner scanner;
    Boolean program_status = true;

    public UserInterface(Scanner scanner) {
        this.scanner = scanner;
    }
    public void start() {
        while (program_status) { 
            System.out.printf("Command: ");
            try {
            switch (this.user_input = scanner.nextLine().toLowerCase().trim()) {
                case "add" -> {
                    System.out.printf("To add: ");
                    todo.add(this.user_input = scanner.nextLine());
                    }
                case "remove" -> {
                    System.out.printf("Which one is removed? ");
                    todo.remove(Integer.parseInt(this.user_input = scanner.nextLine()));
                    }
                case "stop" -> this.program_status = false;
                case "list" -> todo.print();
            }
        } catch(NumberFormatException e) {
            System.out.println("ERROR! Try again." + e);
        }
        }
    }
}

package rvt;

import java.util.Scanner;

public class UserInterface {
    
    String user_input;
    TodoList todo;
    Scanner scanner;
    Boolean program_status = true;

    public UserInterface(TodoList list, Scanner scanner) {
        this.todo = list;
        this.scanner = scanner;
    }
    public void start() {
        while (program_status) { 
            System.out.printf("Command: ");
            try {
            switch (this.user_input = scanner.nextLine().toLowerCase().trim()) {
                case "add":
                    System.out.printf("To add: ");
                    todo.add(this.user_input = scanner.nextLine());
                    break;
                case "remove":
                    System.out.printf("Which one is removed? ");
                    todo.remove(Integer.parseInt(this.user_input = scanner.nextLine()));
                    break;
                case "stop":
                    this.program_status = false;
                    break;
                case "list":
                    todo.print();
            }
        } catch(Exception e) {
            System.out.println("ERROR! Try again.");
        }
        }
    }
}

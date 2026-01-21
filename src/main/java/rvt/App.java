package rvt;

import java.util.Scanner;


public class App {
    public static void main(String[] args) {
        TodoList list = new TodoList();
        // ...existing code...
        try (Scanner scanner = new Scanner(System.in)) {
            UserInterface ui = new UserInterface(list, scanner);
            ui.start();
        }
        
    }
}

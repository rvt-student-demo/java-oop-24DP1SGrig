package rvt;

import java.util.Scanner;


public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserInterface app = new UserInterface(scanner);
        app.start();
        
    }
    
}
 
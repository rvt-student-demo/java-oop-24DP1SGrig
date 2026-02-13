package rvt;

import java.util.Scanner;

import StudentuRegistracija.CLI;
import StudentuRegistracija.FileHandler;

public class App {
    public static void main(String[] args) {
        FileHandler file = new FileHandler();
        Scanner scanner = new Scanner(System.in);
        CLI cli = new CLI(file, scanner);
        cli.start();
        
    }
}

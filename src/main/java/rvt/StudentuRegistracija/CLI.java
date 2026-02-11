package rvt.StudentuRegistracija;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CLI {
    String user_input;
    Scanner scanner;
    FileHandler fileHandler;
    Boolean program_status = true;
    Matcher matcher;
    Pattern pattern;

    public CLI(FileHandler file, Scanner scanner) {
        this.fileHandler = file;
        this.scanner = scanner;
    }

    public void start() {
        while (program_status) { 
            System.out.printf("Command: ");
            try {
            switch (this.user_input = scanner.nextLine().toLowerCase().trim()) {
                case "register":
                    System.out.printf("Registration form:\nName: ");
                    String Vards = scanner.nextLine();
                    System.out.printf("Surname: ");
                    String Uzvards = scanner.nextLine();
                    System.out.printf("E-pasts: ");
                    String Epasts = scanner.nextLine();
                    System.out.printf("Personas kods: ");
                    int PerKods = Integer.parseInt(scanner.nextLine());
                    System.out.printf("Reg Datums: ");
                    String RegDatums = scanner.nextLine();;
                    System.out.printf("Reg laiks: ");
                    String RegLaiks = scanner.nextLine();;

                    fileHandler.register(Vards, Uzvards, Epasts, PerKods, LocalDate.parse(RegDatums), RegLaiks);
                    break;
                case "remove":
                    System.out.printf("Enter personas kods.\nWhich one is removed? ");
                    fileHandler.remove(Integer.parseInt(this.user_input = scanner.nextLine()));
                    break;
                case "exit":
                    this.program_status = false;
                    break;
                case "show":
                    fileHandler.show();
                    break;
                case "edit":
                    System.out.println("Enter PerKods: ");
                    fileHandler.edit(Integer.parseInt(this.user_input = scanner.nextLine()));
            }
        } catch(Exception e) {
            System.out.println("ERROR! Try again.");
        }
        }
    }
}

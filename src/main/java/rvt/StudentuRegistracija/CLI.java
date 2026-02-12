package rvt.StudentuRegistracija;

import java.time.LocalDate;
import java.util.Scanner;

public class CLI {
    String user_input;
    Scanner scanner;
    FileHandler fileHandler;
    InputChecker input = new InputChecker();
    Boolean program_status = true;

    public CLI(FileHandler file, Scanner scanner) {
        this.fileHandler = file;
        this.scanner = scanner;
    }

    public void start() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.printf("Welcome to student registration system!\nAvailable commands: register, show, remove, edit, exit\n");
        while (program_status) { 
            System.out.printf("Command: ");
            try {
            switch (this.user_input = scanner.nextLine().toLowerCase().trim()) {
                case "register":
                    System.out.printf("Registration form:\n");

                    String name = input.inputName();
                    String surname = input.inputSurname();
                    String Email = input.inputEmail();
                    String PersonalCode = input.inputPersonalCode();
                    String RegisterDate = input.inputRegisterDate();
                    String RegisterTime = input.inputRegisterTime();

                    fileHandler.register(name, surname, Email, PersonalCode, LocalDate.parse(RegisterDate), RegisterTime);
                    break;
                case "remove":
                    System.out.printf("Enter personas kods.\nWhich one is removed? ");
                    fileHandler.remove(this.user_input = scanner.nextLine());
                    break;
                case "exit":
                    this.program_status = false;
                    break;
                case "show":
                    fileHandler.show();
                    break;
                case "edit":
                    System.out.println("Enter PerKods: ");
                    fileHandler.edit(this.user_input = scanner.nextLine());
            }
        } catch(Exception e) {
            System.out.println("ERROR! Try again.");
        }
        }
    }
}

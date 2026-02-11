package rvt.StudentuRegistracija;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    ArrayList<Students> list;
    Scanner reader;
    private final String filePath = "data/register.csv";

    public FileHandler() {
        list = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(new File(this.filePath));
            while (fileScanner.hasNextLine()) {
                String[] parametrs = fileScanner.nextLine().split(",");
                for (int i = 0; i < parametrs.length; i++) {
                    parametrs[i] = parametrs[i].trim();
                }
                Students timeStudent = new Students(
                    parametrs[0],
                    parametrs[1],
                    parametrs[2],
                    Integer.parseInt(parametrs[3]),
                    LocalDate.parse(parametrs[4]),
                    parametrs[5]
                );
                list.add(timeStudent);
            }
            fileScanner.close();
            this.reader = new Scanner(System.in);
        }
        catch (IOException e) {
            System.out.println(e + " Constructor error");
        }
    }

    public void register(String Vards, String Uzvards, String Epasts, int PerKods, LocalDate RegDatums, String RegLaiks) {
        Students students = new Students(Vards, Uzvards, Epasts, PerKods, RegDatums, RegLaiks);
        list.add(students);
        try (FileWriter writer = new FileWriter(this.filePath)) {
            for (Students elem : list) {
                writer.write(elem.toString() + "\n");
            }
        }
        catch (IOException e) {
            System.out.println("Error with writing in file in register function" + e);
        }
    }

    public void show() {
        if (list.isEmpty()) {
            System.out.println("No registrations found.");
            return;
        }

        String header = "Vards | Uzvards | Epasts | PerKods | RegDatums | RegLaiks";
        System.out.println(header);
        System.out.println("-".repeat(header.length()));

        for (Students s : list) {
            String[] parts = s.toString().split(", ");
            System.out.println(String.join(" | ", parts));
        }
    }

    public void remove(int PerKods) {
        boolean removed = list.removeIf(elem -> elem.getPerKods() == PerKods);
        if (removed) {
            try (FileWriter writer = new FileWriter(this.filePath)) {
                for (Students stud : list) {
                    writer.write(stud.toString() + "\n");
                }
            } catch (IOException e) {
                System.out.println("Remove error: " + e);
            }
        }
    }

    public void edit(int PerKods) {
        for (Students elem : list) {
            if (elem.getPerKods() == PerKods) {
                System.out.println(
                    "What you want to edit?\nV - vards U - uzvards E - epasts P - Personas kods D - Registracijas dautms T - Registracijas laiks"
                );
                String user_input = reader.nextLine().trim();
                switch(user_input) {
                    case "V" -> {
                        System.out.println("Enter new name:");
                        user_input = reader.nextLine().trim();
                        elem.chanheVards(user_input);
                    }
                    case "U" -> {
                        System.out.println("Enter new name:");
                        user_input = reader.nextLine().trim();
                        elem.chanheUzvards(user_input);
                    }
                    case "E" -> {
                        System.out.println("Enter new e-pasts:");
                        user_input = reader.nextLine().trim();
                        elem.chanheEpasts(user_input);
                    }
                    case "P" -> {
                        System.out.println("Enter new personal code:");
                        user_input = reader.nextLine().trim();
                        elem.chanhePerKods(Integer.parseInt(user_input));
                    }
                    case "D" -> {
                        System.out.println("Enter new registration date:");
                        user_input = reader.nextLine().trim();
                        elem.chanheDatums(LocalDate.parse(user_input));
                    }
                    case "T" -> {
                        System.out.println("Enter new registration time:");
                        user_input = reader.nextLine().trim();
                        elem.chanheLaiks(user_input);
                    }
                }

                try (FileWriter writer = new FileWriter(this.filePath)) {
                    if (!list.isEmpty()) {
                        for (Students stud : list) {
                            writer.write(stud.toString() + "\n");
                        }
                    }
                    writer.flush();
                } catch (IOException e) {
                    System.out.println("abobus " + e);
                }
            }
        }
    }

}

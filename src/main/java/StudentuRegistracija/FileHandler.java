package StudentuRegistracija;

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
                    parametrs[3],
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

    public void register(String Vards, String Uzvards, String Epasts, String PerKods, LocalDate RegDatums, String RegLaiks) {
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

        String[] headers = new String[] {
            "No.",
            "Name",
            "Surname",
            "Email",
            "Personal Code",
            "Registration Date",
            "Registration Time"
        };

        ArrayList<String[]> rows = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Students s = list.get(i);
            String[] parts = s.toString().split(",\\s*");
            String[] row = new String[headers.length];
            row[0] = String.valueOf(i + 1);
            for (int c = 1; c < headers.length; c++) {
                row[c] = (c - 1 < parts.length) ? parts[c - 1] : "";
            }
            rows.add(row);
        }

        int[] widths = new int[headers.length];
        for (int c = 0; c < headers.length; c++) widths[c] = headers[c].length();
        for (String[] row : rows) {
            for (int c = 0; c < row.length; c++) {
                if (row[c] != null) widths[c] = Math.max(widths[c], row[c].length());
            }
        }

        StringBuilder fmt = new StringBuilder();
        for (int c = 0; c < headers.length; c++) {
            fmt.append("%-").append(widths[c]).append("s");
            if (c < headers.length - 1) fmt.append(" | ");
        }
        String format = fmt.toString();

        System.out.printf(format + "%n", (Object[]) headers);

        StringBuilder sep = new StringBuilder();
        for (int c = 0; c < headers.length; c++) {
            sep.append("-".repeat(widths[c]));
            if (c < headers.length - 1) sep.append("-+-");
        }
        System.out.println(sep.toString());

        for (String[] row : rows) {
            System.out.printf(format + "%n", (Object[]) row);
        }
    }

    public void remove(String PerKods) {
        boolean removed = list.removeIf(elem -> elem.getPerKods().equals(PerKods));
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

    public void edit(String PerKods) {
        InputChecker input = new InputChecker();

        for (Students elem : list) {
            if (elem.getPerKods().equals(PerKods)) {
                System.out.println(
                    "What you want to edit?\nV - vards U - uzvards E - epasts P - Personas kods D - Registracijas dautms T - Registracijas laiks"
                );
                String user_input = reader.nextLine().trim();
                switch(user_input) {
                    case "V" -> {
                        System.out.println("Enter new name:");
                        user_input = input.inputName();
                        elem.chanheVards(user_input);
                    }
                    case "U" -> {
                        System.out.println("Enter new surname:");
                        user_input = input.inputSurname();
                        elem.chanheUzvards(user_input);
                    }
                    case "E" -> {
                        System.out.println("Enter new e-pasts:");
                        user_input = input.inputEmail();
                        elem.chanheEpasts(user_input);
                    }
                    case "P" -> {
                        System.out.println("Enter new personal code:");
                        user_input = input.inputPersonalCode();
                        elem.chanhePerKods(user_input);
                    }
                    case "D" -> {
                        System.out.println("Enter new registration date:");
                        user_input = input.inputRegisterDate();
                        elem.chanheDatums(LocalDate.parse(user_input));
                    }
                    case "T" -> {
                        System.out.println("Enter new registration time:");
                        user_input = input.inputRegisterTime();
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

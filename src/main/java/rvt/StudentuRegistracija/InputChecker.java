package rvt.StudentuRegistracija;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputChecker {
    Scanner scanner = new Scanner(System.in);
    Matcher matcher;
    Pattern pattern;

    public InputChecker() {
        
    }

    public String inputName() {
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();

        this.pattern = Pattern.compile("[\\p{Punct}\\p{Digit}]");
        this.matcher = pattern.matcher(name);

        while (matcher.find() || name.length() < 3) {
            System.out.println("In name can't be special symbols or digits. Try again.");
            System.out.print("Name: ");
            
            name = scanner.nextLine().trim();
            matcher.reset(name); 
        }

        return name;
    }

    public String inputSurname() {
        System.out.print("Surname: ");
        String surname = scanner.nextLine().trim();

        this.pattern = Pattern.compile("[\\p{Punct}\\p{Digit}]");
        this.matcher = pattern.matcher(surname);

        while (matcher.find() || surname.length() < 3) { 
            System.out.println("Invalid surname! It must be at least 3 chars long and contain no digits/special symbols. Try again.");
            System.out.print("Surname: ");
            
            surname = scanner.nextLine().trim();
            matcher.reset(surname); 
        }

        return surname;
    }

    public String inputEmail() {
        System.out.printf("Email: ");
        String email = scanner.nextLine().trim();

        this.matcher = Pattern.compile("^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.(com|org|lv)$").matcher(email);

        while (!matcher.matches() || email.length() < 5) { 
            System.out.println("Error: Invalid format, unsupported domain (.com, .org, .lv), or too short.");
            
            System.out.printf("Email: ");
            email = scanner.nextLine().trim();
            matcher.reset(email);
        }

        return email;
    }

    public String inputPersonalCode() {
        System.out.printf("Personal Code(e.g. 123456-78901): ");
        String code = scanner.nextLine().trim();

        this.matcher = Pattern.compile("^\\d{6}-\\d{5}$").matcher(code);

        while (!matcher.matches()) { 
            System.out.println("Error: Invalid format. Use XXXXXX-XXXXX (11 digits and a dash).");
            
            System.out.print("Personal Code: ");
            code = scanner.nextLine().trim();
            matcher.reset(code);
        }
        return code;
    }

    public String inputRegisterDate() {
        String date;

        while (true) {
            System.out.print("Registration Date (YYYY-MM-DD): ");
            date = scanner.nextLine().trim();

            try {
                LocalDate.parse(date); 
                return date; 
            } catch (DateTimeParseException e) {
                System.out.println("Error: Invalid date or format. Please use YYYY-MM-DD.");
            }
        }
    }

    public String inputRegisterTime() {
        String time;
        while (true) {
            System.out.print("Registration Time (HH:mm): ");
            time = scanner.nextLine().trim();

            try {
                LocalTime.parse(time); 
                return time; 
            } catch (DateTimeParseException e) {
                System.out.println("Error: Invalid time. Use HH:mm (e.g., 08:30 or 23:15).");
            }
        }
    }

    

}

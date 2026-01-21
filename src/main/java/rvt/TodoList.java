package rvt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TodoList {
    ArrayList<String> list;
    Scanner reader;
    private final String filePath = "data/todo.csv";

    public TodoList() {
        list = new ArrayList<>();
        try {
            this.reader = new Scanner(new File(this.filePath));
            if (reader.hasNext()) {
                String[] tasks = reader.nextLine().split(",");
                list.addAll(Arrays.asList(tasks));
                reader.close();
            }
        }
        catch (IOException e) {
            System.out.println(e + "WTF");
        }
    }
    
    public void add(String task) {
        list.add(task);
        try (FileWriter writer = new FileWriter(this.filePath, true)) {
            writer.write(task + ",");
            writer.flush();
            writer.close();
        }
        catch (IOException e) {
            System.out.println("abobus " + e);
        }
    }

    public void print() {
        for (int index = 0; index < list.size(); index++) {
            System.out.printf("%d: %s\n", index + 1, list.get(index));
        }
    }

    public void remove(int number) {
        list.remove(number - 1);
        try (FileWriter writer = new FileWriter(this.filePath)) {
            if (!list.isEmpty()) {
                writer.write(String.join(",", list));
                writer.write(",");
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println("abobus " + e);
        }
    }
    
}

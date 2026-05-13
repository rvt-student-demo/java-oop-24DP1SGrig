package rvt;

import java.util.ArrayList;
import java.util.Scanner;

public class TodoList {
    Scanner reader;
    TodoDB db;
    private ArrayList<Integer> lastIds;
    public TodoList() {
        this.db = new TodoDB();
        this.lastIds = new ArrayList<>();
    }
    
    public void add(String task) {
        db.addRow(task);
    }

    public void print() {
        ArrayList<String> rows = db.getData();
        lastIds.clear();
        for (int i = 0; i < rows.size(); i++) {
            String row = rows.get(i);
            String[] parts = row.split(",", 2);
            int id = Integer.parseInt(parts[0]);
            String task = parts.length > 1 ? parts[1] : "";
            lastIds.add(id);
            System.out.printf("%d %s\n", i + 1, task);
        }
    }

    public void remove(int number) {
        if (number <= 0 || number > lastIds.size()) {
            throw new IllegalArgumentException("Invalid task number: " + number);
        }
        int dbId = lastIds.get(number - 1);
        db.deleteRow(dbId);
    }
    
}
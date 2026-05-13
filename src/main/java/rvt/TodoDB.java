package rvt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TodoDB {
    
    private static final String DB_URL = "jdbc:sqlite:todo.db";

    public TodoDB() {
        initSchema();
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    private void initSchema() {
        String sql = "CREATE TABLE IF NOT EXISTS todo ("
                + "id INTEGER PRIMARY KEY,"
                + "task TEXT NOT NULL)";
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Scheme init failed: " + e.getMessage());
        }
    }

    public void addRow(String text) {
        String sql = "INSERT INTO todo(task) VALUES (?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, text);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Row insert failed: " + e.getMessage());
        }
    }

    public ArrayList<String> getData() {
        String sql = "SELECT * FROM todo";
        ArrayList<String> tasks= new ArrayList<>();
        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet res = stmt.executeQuery(sql)) {
            while (res.next()) {
                tasks.add(res.getInt(1) + "," + res.getString(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Get data failed: " + e.getMessage());
        }
        return tasks;
    }

    public void editRow(int taskID, String task) {
        String sql = "UPDATE todo SET task = ? WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, task);
            pstmt.setInt(2, taskID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Row update failed: " + e.getMessage());
        }
    }
    
    public void deleteRow(int taskID) {
        String sql = "DELETE FROM todo WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, taskID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Row delete failed: " + e.getMessage());
        }
    }
}

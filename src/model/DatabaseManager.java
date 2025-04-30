package model;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/eight_queens_game";
    private static final String USER = "root"; // Change if your MySQL username is different
    private static final String PASSWORD = ""; // Add your MySQL password here

    public static void initDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS solutions (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    solution TEXT UNIQUE,
                    recognized BOOLEAN DEFAULT FALSE
                );
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS game_results (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    player_name VARCHAR(255) NOT NULL,
                    time_seconds BIGINT NOT NULL,
                    method VARCHAR(50) NOT NULL,
                    status VARCHAR(10) NOT NULL
                );
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS player_attempts (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    player_name VARCHAR(255) NOT NULL,
                    solution TEXT NOT NULL
                );
            """);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void saveSolution(String method, String solution) {
        String insert = "INSERT IGNORE INTO solutions(solution) VALUES (?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(insert)) {
            pstmt.setString(1, solution);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void saveResult(String playerName, long timeElapsed, String method, String status) {
        String query = "INSERT INTO game_results(player_name, time_seconds, method, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, playerName);
            pstmt.setLong(2, timeElapsed);
            pstmt.setString(3, method);
            pstmt.setString(4, status);

            // Debugging output
            System.out.println("Inserting result: " + playerName + " " + timeElapsed + " " + status);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isRecognized(String solution) {
        String query = "SELECT recognized FROM solutions WHERE solution = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, solution);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return rs.getBoolean("recognized");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void markRecognized(String solution) {
        String update = "UPDATE solutions SET recognized = TRUE WHERE solution = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(update)) {
            pstmt.setString(1, solution);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void clearRecognizedFlags() {
        String update = "UPDATE solutions SET recognized = FALSE";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Set<String> getAllSolutions() {
        Set<String> set = new HashSet<>();
        String query = "SELECT solution FROM solutions";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                set.add(rs.getString("solution"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return set;
    }

    public static void savePlayerAttempt(String name, String solution) {
        String insert = "INSERT INTO player_attempts(player_name, solution) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(insert)) {
            pstmt.setString(1, name);
            pstmt.setString(2, solution);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void savePlayerSolution(String name, String solution) {
        savePlayerAttempt(name, solution);
    }

    public static Object isDuplicateSolution(String solution) {
        return null;
    }
}

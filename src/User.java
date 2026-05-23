import java.sql.*;
import java.util.Scanner;

public class User {
    // Register a new user
    public static void registerUser(Scanner scanner) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Users (user_name, user_email) VALUES (?, ?)")) {

            System.out.print("Enter your name: ");
            String name = scanner.nextLine();
            System.out.print("Enter your email: ");
            String email = scanner.nextLine();

            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.executeUpdate();

            System.out.println("User registered successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // View all existing users in the database
    public static void viewUsers() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Users")) {

            System.out.println("\n--- Registered Users ---");
            while (rs.next()) {
                System.out.println("User ID: " + rs.getInt("user_id") +
                        ", Name: " + rs.getString("user_name") +
                        ", Email: " + rs.getString("user_email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("\nEnter 1 for Main Menu");
            int value = new Scanner(System.in).nextInt();
        }
    }
}

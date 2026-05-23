import java.sql.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Booking {

    // Book a ticket for a movie
    public static void bookTicket(Scanner scanner) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Bookings (user_id, movie_id, seats) VALUES (?, ?, ?)")) {

            System.out.print("Enter your user ID: ");
            int userId = scanner.nextInt();
            System.out.print("Enter movie ID: ");
            int movieId = scanner.nextInt();
            System.out.print("Enter number of seats: ");
            int seats = scanner.nextInt();

            pstmt.setInt(1, userId);
            pstmt.setInt(2, movieId);
            pstmt.setInt(3, seats);
            pstmt.executeUpdate();

            // Update available seats
            updateAvailableSeats(conn, movieId, seats);

            System.out.println("Ticket booked successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("\nEnter 1 for Main Menu");
            int value = new Scanner(System.in).nextInt();
        }
    }

    // Update available seats after booking
    private static void updateAvailableSeats(Connection conn, int movieId, int seats) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement("UPDATE Movies SET available_seats = available_seats - ? WHERE movie_id = ?")) {
            pstmt.setInt(1, seats);
            pstmt.setInt(2, movieId);
            pstmt.executeUpdate();
        }
    }

    public static void viewMyTickets(Scanner scanner) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "SELECT b.booking_id, m.movie_name, m.showtime, b.seats, b.booking_time " +
                             "FROM Bookings b JOIN Movies m ON b.movie_id = m.movie_id " +
                             "WHERE b.user_id = ?")) {

            System.out.print("Enter your User ID to view tickets: ");
            int userId = scanner.nextInt();

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("\n--- Your Bookings ---");
            boolean hasResults = false;
            while (rs.next()) {
                hasResults = true;
                System.out.println("Booking ID: " + rs.getInt("booking_id"));
                System.out.println("Movie Name: " + rs.getString("movie_name"));
                System.out.println("Showtime: " + rs.getString("showtime"));
                System.out.println("Seats: " + rs.getInt("seats"));
                System.out.println("Booking Time: " + rs.getTimestamp("booking_time"));
                System.out.println("-------------------------");
            }

            if (!hasResults) {
                System.out.println("No bookings found for User ID: " + userId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println("\nEnter any key to return to Main Menu...");
            scanner.nextLine(); // consume leftover newline
            scanner.nextLine(); // pause
        }
    }
}

import java.sql.*;
import java.util.*;

public class Movie {
    // List all movies
    public static void listMovies() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Movies")) {

            System.out.println("\n--- Available Movies ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("movie_id") +
                        ", Name: " + rs.getString("movie_name") +
                        ", Seats: " + rs.getInt("available_seats") +
                        ", Showtime: " + rs.getString("showtime"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("\nEnter 1 for Main Menu");
            int value = new Scanner(System.in).nextInt();
        }
    }

    // Add a new movie
    public static void addMovie(Scanner scanner) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Movies (movie_name, available_seats, showtime) VALUES (?, ?, ?)")) {

            System.out.print("Enter movie name: ");
            String name = scanner.nextLine();
            System.out.print("Enter available seats: ");
            int seats = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter showtime: ");
            String showtime = scanner.nextLine();

            pstmt.setString(1, name);
            pstmt.setInt(2, seats);
            pstmt.setString(3, showtime);
            pstmt.executeUpdate();

            System.out.println("Movie added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("\nEnter 1 for Main Menu");
            int value = new Scanner(System.in).nextInt();
        }
    }

// Binary Search to Find a Movie  (Efficient Search)
    public static void searchMovieByName(Scanner scanner) {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Movies")) {

            List<MovieDTO> movies = new ArrayList<>();
            while (rs.next()) {
                movies.add(new MovieDTO(
                        rs.getInt("movie_id"),
                        rs.getString("movie_name"),
                        rs.getInt("available_seats"),
                        rs.getString("showtime")
                ));
            }

            movies.sort(Comparator.comparing(MovieDTO::getName));

            System.out.print("Enter movie name to search: ");
            String target = scanner.nextLine();

            int index = binarySearchByName(movies, target);

            if (index != -1) {
                System.out.println("Movie Found: " + movies.get(index));
            } else {
                System.out.println("Movie not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int binarySearchByName(List<MovieDTO> movies, String target) {
        int left = 0, right = movies.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int cmp = movies.get(mid).getName().compareToIgnoreCase(target);
            if (cmp == 0) return mid;
            else if (cmp < 0) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }
}

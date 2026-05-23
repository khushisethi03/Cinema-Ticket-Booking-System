import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n--- Cinema Ticket Booking System ---");
                System.out.println("1. List Movies");
                System.out.println("2. Add Movie");
                System.out.println("3. Register User");
                System.out.println("4. View Users");
                System.out.println("5. Book Ticket");
                System.out.println("6. View My Tickets");
                System.out.println("7. Search Movie by Name");
                System.out.println("8. Exit");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1 -> Movie.listMovies();
                    case 2 -> Movie.addMovie(scanner);
                    case 3 -> User.registerUser(scanner);
                    case 4 -> User.viewUsers();  // View all users
                    case 5 -> Booking.bookTicket(scanner);
                    case 6 -> Booking.viewMyTickets(scanner);
                    case 7 -> Movie.searchMovieByName(scanner);
                    case 8 -> {
                        System.out.println("Exiting...");
                        return;
                    }

                    default -> System.out.println("Invalid option, please try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

USE cinema_db;

-- Insert sample data into Movies table
INSERT INTO Movies (movie_name, available_seats, showtime)
VALUES
    ('Dangal', 50, '2024-10-01 18:00'),
    ('3 Idiots', 100, '2024-10-02 20:00'),
    ('RRR', 120, '2024-10-03 21:00'),
    ('Sholay', 80, '2024-10-04 17:30'),
    ('Pathaan', 60, '2024-10-05 19:45');

-- Insert sample data into Users table
INSERT INTO Users (user_name, user_email)
VALUES
    ('Amit Sharma', 'amit.sharma@gmail.com'),
    ('Priya Verma', 'priya.verma@yahoo.com'),
    ('Rohan Mehta', 'rohan.mehta@gmail.com'),
    ('Sneha Gupta', 'sneha.gupta@gmail.com'),
    ('Karan Patel', 'karan.patel@hotmail.com');

-- Insert sample data into Bookings table
-- (Assumes a column `booking_time` exists with default CURRENT_TIMESTAMP)
INSERT INTO Bookings (user_id, movie_id, seats)
VALUES
    (1, 1, 2),  -- Amit Sharma books 2 seats for 'Dangal'
    (2, 2, 4),  -- Priya Verma books 4 seats for '3 Idiots'
    (3, 3, 3),  -- Rohan Mehta books 3 seats for 'RRR'
    (4, 4, 1),  -- Sneha Gupta books 1 seat for 'Sholay'
    (5, 5, 2);  -- Karan Patel books 2 seats for 'Pathaan'

-- Adjust available seats in Movies table accordingly
UPDATE Movies SET available_seats = available_seats - 2 WHERE movie_id = 1;
UPDATE Movies SET available_seats = available_seats - 4 WHERE movie_id = 2;
UPDATE Movies SET available_seats = available_seats - 3 WHERE movie_id = 3;
UPDATE Movies SET available_seats = available_seats - 1 WHERE movie_id = 4;
UPDATE Movies SET available_seats = available_seats - 2 WHERE movie_id = 5;

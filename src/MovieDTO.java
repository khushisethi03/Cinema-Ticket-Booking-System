class MovieDTO {
    private int id;
    private String name;
    private int availableSeats;
    private String showtime;

    public MovieDTO(int id, String name, int seats, String showtime) {
        this.id = id;
        this.name = name;
        this.availableSeats = seats;
        this.showtime = showtime;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public String getShowtime() {
        return showtime;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Seats: " + availableSeats + ", Showtime: " + showtime;
    }
}

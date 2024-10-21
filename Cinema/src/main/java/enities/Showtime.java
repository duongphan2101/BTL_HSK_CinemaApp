package enities;

public class Showtime {
    private int showtimeId;
    private Movie movie;
    private Theater theater;
    private String showDate;
    private String startTime;
    
	public Showtime() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Showtime(int showtimeId, Movie movie, Theater theater, String showDate, String startTime) {
		super();
		this.showtimeId = showtimeId;
		this.movie = movie;
		this.theater = theater;
		this.showDate = showDate;
		this.startTime = startTime;
	}

	public Showtime(int showtimeId) {
		super();
		this.showtimeId = showtimeId;
	}

	public int getShowtimeId() {
		return showtimeId;
	}

	public void setShowtimeId(int showtimeId) {
		this.showtimeId = showtimeId;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Theater getTheater() {
		return theater;
	}

	public void setTheater(Theater theater) {
		this.theater = theater;
	}

	public String getShowDate() {
		return showDate;
	}

	public void setShowDate(String showDate) {
		this.showDate = showDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@Override
	public String toString() {
		return "Showtime [showtimeId=" + showtimeId + ", movie=" + movie + ", theater=" + theater + ", showDate="
				+ showDate + ", startTime=" + startTime + "]";
	}
}

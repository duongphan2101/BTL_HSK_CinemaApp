package enities;

public class Seat {
	private int seatId;
	private Theater theater;
	private String seatNumber;
	private SeatStatus seatStatus;
	
	public Seat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Seat(int seatId) {
		super();
		this.seatId = seatId;
	}

	public Seat(int seatId, Theater theater, String seatNumber, SeatStatus seatStatus) {
		super();
		this.seatId = seatId;
		this.theater = theater;
		this.seatNumber = seatNumber;
		this.seatStatus = seatStatus;
	}

	public int getSeatId() {
		return seatId;
	}

	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}

	public Theater getTheater() {
		return theater;
	}

	public void setTheater(Theater theater) {
		this.theater = theater;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public SeatStatus getSeatStatus() {
		return seatStatus;
	}

	public void setSeatStatus(SeatStatus seatStatus) {
		this.seatStatus = seatStatus;
	}
	
	 @Override
     public boolean equals(Object obj) {
         if (this == obj) return true;
         if (obj == null || getClass() != obj.getClass()) return false;
         Seat seat = (Seat) obj;
         return seatNumber.equals(seat.seatNumber);
     }

     @Override
     public int hashCode() {
         return seatNumber.hashCode();
     }

	@Override
	public String toString() {
		return "Seat [seatId=" + seatId + ", theater=" + theater + ", seatNumber=" + seatNumber + ", seatStatus="
				+ seatStatus + "]";
	}
}

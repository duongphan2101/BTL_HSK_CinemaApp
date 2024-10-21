package enities;

public class Ticket {
	private int ticketId;
	private Showtime showtime;
	private Theater theater;
	private String seatNumber;
	private float price;
	private String purchaseTime;
	
	public Ticket() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ticket(int ticketId, Showtime showtime, Theater theater, String seatNumber, float price,
			String purchaseTime) {
		super();
		this.ticketId = ticketId;
		this.showtime = showtime;
		this.theater = theater;
		this.seatNumber = seatNumber;
		this.price = price;
		this.purchaseTime = purchaseTime;
	}

	public Ticket(int ticketId) {
		super();
		this.ticketId = ticketId;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public Showtime getShowtime() {
		return showtime;
	}

	public void setShowtime(Showtime showtime) {
		this.showtime = showtime;
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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getPurchaseTime() {
		return purchaseTime;
	}

	public void setPurchaseTime(String purchaseTime) {
		this.purchaseTime = purchaseTime;
	}

	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", showtime=" + showtime + ", theater=" + theater + ", seatNumber="
				+ seatNumber + ", price=" + price + ", purchaseTime=" + purchaseTime + "]";
	}
}

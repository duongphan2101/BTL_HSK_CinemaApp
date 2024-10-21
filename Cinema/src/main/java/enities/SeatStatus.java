package enities;

public class SeatStatus {
	private int status_id;
	private String status_name;
	
	public SeatStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SeatStatus(int status_id, String status_name) {
		super();
		this.status_id = status_id;
		this.status_name = status_name;
	}

	public SeatStatus(int status_id) {
		super();
		this.status_id = status_id;
	}

	public int getStatus_id() {
		return status_id;
	}

	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}

	public String getStatus_name() {
		return status_name;
	}

	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}

	@Override
	public String toString() {
		return "SeatStatus [status_id=" + status_id + ", status_name=" + status_name + "]";
	}
}

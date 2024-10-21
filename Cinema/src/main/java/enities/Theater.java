package enities;

public class Theater {
	private int theaterId;
	private String name;
	private String location;
	private int capacity;

	public Theater() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Theater(int theaterId) {
		super();
		this.theaterId = theaterId;
	}

	public Theater(int theaterId, String name, String location, int capacity) {
		super();
		this.theaterId = theaterId;
		this.name = name;
		this.location = location;
		this.capacity = capacity;
	}

	public int getTheaterId() {
		return theaterId;
	}

	public void setTheaterId(int theaterId) {
		this.theaterId = theaterId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public String toString() {
		return "Theater [theaterId=" + theaterId + ", name=" + name + ", location=" + location + ", capacity="
				+ capacity + "]";
	}

}

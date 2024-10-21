package enities;

public class EmployeeType {
	private int typeId;
	private String position;

	public EmployeeType() {

	}
	public EmployeeType(int typeId) {
		this.typeId = typeId;
	}

	public EmployeeType(int typeId, String position) {
		super();
		this.typeId = typeId;
		this.position = position;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "EmployeeType [typeId=" + typeId + ", position=" + position + "]";
	}

}

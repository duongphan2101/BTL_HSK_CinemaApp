package enities;

public class CurrentEmp {
	private static int employeeId;
	private static String name;
	private static EmployeeType employeeType;
	private static String birthDate;
	private static String phone;
	private static String email;
	
	public CurrentEmp() {
		
	}
	public CurrentEmp(int employeeId) {
		super();
		CurrentEmp.employeeId = employeeId;
	}


	public CurrentEmp(int employeeId, String name, EmployeeType employeeType, String birthDate, String phone,
			String email) {
		super();
		CurrentEmp.employeeId = employeeId;
		CurrentEmp.name = name;
		CurrentEmp.employeeType = employeeType;
		CurrentEmp.birthDate = birthDate;
		CurrentEmp.phone = phone;
		CurrentEmp.email = email;
	}
	public static int getEmployeeId() {
		return employeeId;
	}
	public static void setEmployeeId(int employeeId) {
		CurrentEmp.employeeId = employeeId;
	}
	public static String getName() {
		return name;
	}
	public static void setName(String name) {
		CurrentEmp.name = name;
	}
	public static EmployeeType getEmployeeType() {
		return employeeType;
	}
	public static void setEmployeeType(EmployeeType employeeType) {
		CurrentEmp.employeeType = employeeType;
	}
	public static String getBirthDate() {
		return birthDate;
	}
	public static void setBirthDate(String birthDate) {
		CurrentEmp.birthDate = birthDate;
	}
	public static String getPhone() {
		return phone;
	}
	public static void setPhone(String phone) {
		CurrentEmp.phone = phone;
	}
	public static String getEmail() {
		return email;
	}
	public static void setEmail(String email) {
		CurrentEmp.email = email;
	}
	
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", name=" + name + ", employeeType=" + employeeType
				+ ", birthDate=" + birthDate + ", phone=" + phone + ", email=" + email + "]";
	}
}

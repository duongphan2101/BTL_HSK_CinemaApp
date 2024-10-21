package enities;

public class Account {
	private int account_id;
	private String username, password;
	private Employee employee;
	
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(int account_id) {
		super();
		this.account_id = account_id;
	}

	public Account(int account_id, String username, String password, Employee employee) {
		super();
		this.account_id = account_id;
		this.username = username;
		this.password = password;
		this.employee = employee;
	}

	public int getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Account [account_id=" + account_id + ", username=" + username + ", password=" + password + ", employee="
				+ employee + "]";
	}
}

package enities;

public class Customer {
    private static int customerId;
    private static String name;
    private static String phone;
    
	public Customer() {
		
	}
	
	public Customer(int customerId) {
		super();
		Customer.customerId = customerId;
	}
	
	public Customer(int customerId, String name, String phone) {
		super();
		Customer.customerId = customerId;
		Customer.name = name;
		Customer.phone = phone;
	}
	public static int getCustomerId() {
		return customerId;
	}
	public static void setCustomerId(int customerId) {
		Customer.customerId = customerId;
	}
	public static String getName() {
		return name;
	}
	public static void setName(String name) {
		Customer.name = name;
	}
	public static String getPhone() {
		return phone;
	}
	public static void setPhone(String phone) {
		Customer.phone = phone;
	}
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", name=" + name + ", phone=" + phone + "]";
	}
    
    
}

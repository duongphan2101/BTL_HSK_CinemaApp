package enities;

public class Invoice {
	private int invoiceId;
	private float totalAmount;
	private String purchaseTime;
	private Employee employee;
	
	public Invoice(int invoiceId, float totalAmount, String purchaseTime, Employee employee) {
		super();
		this.invoiceId = invoiceId;
		this.totalAmount = totalAmount;
		this.purchaseTime = purchaseTime;
		this.employee = employee;
	}
	public Invoice() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Invoice(int invoiceId) {
		super();
		this.invoiceId = invoiceId;
	}
	public int getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}
	
	public float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getPurchaseTime() {
		return purchaseTime;
	}
	public void setPurchaseTime(String purchaseTime) {
		this.purchaseTime = purchaseTime;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	@Override
	public String toString() {
		return "Invoice [invoiceId=" + invoiceId + ", totalAmount=" + totalAmount
				+ ", purchaseTime=" + purchaseTime + ", employee=" + employee + "]";
	}
}

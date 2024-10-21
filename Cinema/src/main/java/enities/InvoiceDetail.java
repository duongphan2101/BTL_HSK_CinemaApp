package enities;

public class InvoiceDetail {
	private Invoice invoice;
	private Ticket ticket;
	private int quantity;
	private float pricePerTicket;
	
	public InvoiceDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InvoiceDetail(Invoice invoice, Ticket ticket, int quantity, float pricePerTicket) {
		super();
		this.invoice = invoice;
		this.ticket = ticket;
		this.quantity = quantity;
		this.pricePerTicket = pricePerTicket;
	}
	public InvoiceDetail(Invoice invoice) {
		super();
		this.invoice = invoice;
	}
	public Invoice getInvoice() {
		return invoice;
	}
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getPricePerTicket() {
		return pricePerTicket;
	}
	public void setPricePerTicket(float pricePerTicket) {
		this.pricePerTicket = pricePerTicket;
	}
	@Override
	public String toString() {
		return "InvoiceDetail [invoice=" + invoice + ", ticket=" + ticket + ", quantity=" + quantity
				+ ", pricePerTicket=" + pricePerTicket + "]";
	}
}

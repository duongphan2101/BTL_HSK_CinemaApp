package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connect.DBConnect;
import enities.CurrentEmp;
import enities.Employee;
import enities.EmployeeType;
import enities.Invoice;
import enities.InvoiceDetail;
import enities.Showtime;
import enities.Theater;
import enities.Ticket;

public class DAO_Ticktet_Invoice {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public void addTicket(Ticket ticket) {
	    String query = "INSERT INTO Ticket (showtime_id, theater_id, seat_number, price, purchase_time) VALUES (?, ?, ?, ?, ?);";
	        try {
	            new DBConnect();
	            conn = DBConnect.getConnection();
	            ps = conn.prepareStatement(query);

	            ps.setInt(1, ticket.getShowtime().getShowtimeId());
	            ps.setInt(2, ticket.getTheater().getTheaterId());
	            ps.setString(3, ticket.getSeatNumber());
	            ps.setDouble(4, ticket.getPrice());
	            ps.setString(5, ticket.getPurchaseTime());
	            ps.executeUpdate();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (ps != null) ps.close();
	                if (conn != null) conn.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	}
	
	public void addInvoice(Invoice invoice) {
	    String query = "INSERT INTO Invoice (total_amount, purchase_time, employee_id) VALUES (?, ?, ?);";
	        try {
	            new DBConnect();
	            conn = DBConnect.getConnection();
	            ps = conn.prepareStatement(query);
	            ps.setFloat(1, invoice.getTotalAmount());
	            ps.setString(2, invoice.getPurchaseTime());
	            invoice.getEmployee();
				ps.setInt(3, invoice.getEmployee().getEmployeeId());
	            ps.executeUpdate();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (ps != null) ps.close();
	                if (conn != null) conn.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	}
	
	public void updateInvoiceTotalAmount(int invoiceId, float newTotalAmount) {
	    String query = "UPDATE Invoice SET total_amount = ? WHERE invoice_id = ?;";
	    try {
	        new DBConnect();
	        conn = DBConnect.getConnection();
	        ps = conn.prepareStatement(query);
	        ps.setFloat(1, newTotalAmount); 
	        ps.setInt(2, invoiceId); 
	        ps.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}

	
	public void addInvoiceDetail(InvoiceDetail invoiceDetail) {
	    String query = "INSERT INTO InvoiceDetail (invoice_id, ticket_id, quantity, price_per_ticket) VALUES (?, ?, ?, ?);";
	        try {
	            new DBConnect();
	            conn = DBConnect.getConnection();
	            ps = conn.prepareStatement(query);
	            ps.setInt(1, invoiceDetail.getInvoice().getInvoiceId());
	            ps.setInt(2, invoiceDetail.getTicket().getTicketId());
	            ps.setInt(3, invoiceDetail.getQuantity());
	            ps.setFloat(4, invoiceDetail.getPricePerTicket());
	            
	            ps.executeUpdate();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (ps != null) ps.close();
	                if (conn != null) conn.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	}
	
	public Integer getLastInvoiceId() {
	    String query = "SELECT TOP 1 invoice_id FROM Invoice ORDER BY invoice_id DESC";
	    Integer lastInvoiceId = null;
	    
	    try {
	        new DBConnect();
	        conn = DBConnect.getConnection();
	        ps = conn.prepareStatement(query);
	        rs = ps.executeQuery();
	        
	        if (rs.next()) {
	            lastInvoiceId = rs.getInt("invoice_id");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    return lastInvoiceId;
	}
	
	public Integer getLastTicketId() {
	    String query = "select top 1 ticket_id from Ticket order by ticket_id desc";
	    Integer lastInvoiceId = null;
	    
	    try {
	        new DBConnect();
	        conn = DBConnect.getConnection();
	        ps = conn.prepareStatement(query);
	        rs = ps.executeQuery();
	        
	        if (rs.next()) {
	            lastInvoiceId = rs.getInt("ticket_id");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    return lastInvoiceId;
	}
	
	public Invoice getInvoicebyID(int id){
		String query = "select * from Invoice where invoice_id = ?";
		try {
			new DBConnect();
			conn = DBConnect.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
//				DAO_Employee dao = new DAO_Employee();
				Employee emp = new Employee(CurrentEmp.getEmployeeId(), CurrentEmp.getName(), new EmployeeType(CurrentEmp.getEmployeeType().getTypeId()), CurrentEmp.getBirthDate(), CurrentEmp.getPhone(), CurrentEmp.getEmail());
	           return new Invoice(rs.getInt(1),
	        		   rs.getInt(2),
	        		   rs.getString(3),
	        		   emp);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    return null;
	}
	
	public Ticket getTicketbyID(int id){
		String query = "select * from Ticket where ticket_id = ?";
		try {
			new DBConnect();
			conn = DBConnect.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				DAO_ShowTime dao = new DAO_ShowTime();
				DAO_Theater tdao = new DAO_Theater();
				Theater theater = tdao.getTheaterbyID(rs.getInt(6));
				Showtime show = dao.getShowTimebyID(rs.getInt(2));
	           return new Ticket(rs.getInt(1),
	        		   show, 
	        		   theater, 
	        		   rs.getString(3),
	        		   rs.getFloat(4),
	        		   rs.getString(5));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    return null;
	}
	
	public List<InvoiceDetail> getDetailbyInvoiceId(int id){
		List<InvoiceDetail> list = new ArrayList<InvoiceDetail>();
		String query = "select * from InvoiceDetail where invoice_id = ?";
		try {
			new DBConnect();
			conn = DBConnect.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				DAO_Ticktet_Invoice dao = new DAO_Ticktet_Invoice();
				Invoice i = dao.getInvoicebyID(rs.getInt(1));
				Ticket t = dao.getTicketbyID(rs.getInt(2));
				list.add(new InvoiceDetail(i,
						t,
						rs.getInt(3),
						rs.getFloat(4))
					);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void main(String[] args) {
		DAO_Ticktet_Invoice dao = new DAO_Ticktet_Invoice();
		List<InvoiceDetail> lst = dao.getDetailbyInvoiceId(11);
		System.out.println(lst);
	}
	
}

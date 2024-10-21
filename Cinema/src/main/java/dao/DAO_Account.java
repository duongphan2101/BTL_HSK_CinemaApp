package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connect.DBConnect;
import enities.Account;
import enities.CurrentEmp;
import enities.Employee;
import enities.EmployeeType;
import service.CustomOptionPane;

public class DAO_Account {
	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	 
	    public Account getAccount(String user, String pass) {
	        String query = "select * from Account where username = ? and password = ? "; 
	        try {
	            new DBConnect();
	            conn = DBConnect.getConnection();
	            ps = conn.prepareStatement(query);
	            ps.setString(1, user);
	            ps.setString(2, pass);
	            rs = ps.executeQuery();
	            if (rs.next()) {
	            	DAO_Employee dao = new DAO_Employee();
	            	CurrentEmp emp = dao.getEmployeeByID(rs.getInt(4));
	            	@SuppressWarnings("static-access")
					Employee e = new Employee(emp.getEmployeeId(), emp.getEmail(), new EmployeeType(emp.getEmployeeType().getTypeId()), emp.getBirthDate(), emp.getPhone(), emp.getEmail());
	               return new Account(rs.getInt(1),
	            		   rs.getString(2),
	            		   rs.getString(3),
	            		   e);
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
	    
	    public void addAccount(Account a, int id) {
	        String query = "INSERT INTO Account (username, password, employee_id) VALUES (?, ?, ?)";
	        try {
	            new DBConnect();
	            conn = DBConnect.getConnection();
	            ps = conn.prepareStatement(query);
	            ps.setString(1, a.getUsername());
	            ps.setString(2, a.getPassword());  
	            ps.setInt(3, id);
	            ps.executeUpdate();
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        } 
	    }
	    
	    public Account getAccountbyEmployeeId(int emp_id) {
	    	String query = "select * from Account where employee_id = ?"; 
	        try {
	            new DBConnect();
	            conn = DBConnect.getConnection();
	            ps = conn.prepareStatement(query);
	            ps.setInt(1, emp_id);
	            rs = ps.executeQuery();
	            if (rs.next()) {
	            	DAO_Employee dao = new DAO_Employee();
	            	CurrentEmp emp = dao.getEmployeeByID(rs.getInt(4));
	            	@SuppressWarnings("static-access")
					Employee e = new Employee(emp.getEmployeeId(), emp.getEmail(), new EmployeeType(emp.getEmployeeType().getTypeId()), emp.getBirthDate(), emp.getPhone(), emp.getEmail());
	               return new Account(rs.getInt(1),
	            		   rs.getString(2),
	            		   rs.getString(3),
	            		   e);
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
	    
	    public void updatePassword(String newPass, int id) {
	    	String query = "update Account set password = ? where account_id = ?";
	        try {
	            new DBConnect();
	            conn = DBConnect.getConnection();
	            ps = conn.prepareStatement(query);
	            ps.setString(1, newPass);
	            ps.setInt(2, id);
	            int rowsAffected = ps.executeUpdate();
	            if (rowsAffected > 0) {
	                CustomOptionPane.showNotice("Đổi mật khẩu thành công!");
	            } else {
	                CustomOptionPane.showMess("Đổi Mật Khẩu thất bại!");
	            }
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
	    
	    public static void main(String[] args) {
			DAO_Account dao = new DAO_Account();
			Account a = dao.getAccountbyEmployeeId(1);
			System.out.println(a);
		}
}

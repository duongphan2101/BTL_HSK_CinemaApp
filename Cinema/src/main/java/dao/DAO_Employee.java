package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connect.DBConnect;
import enities.Account;
import enities.CurrentEmp;
import enities.Employee;
import enities.EmployeeType;
import service.CustomOptionPane;

public class DAO_Employee {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    
    public List<Account> getAllEmployee(){
    	List<Account> list = new ArrayList<Account>();
		String query = "select a.* from Account a \r\n"
				+ "inner join Employee e on a.employee_id = e.employee_id\r\n"
				+ "where e.type_id in(2,3);";
		try {
			new DBConnect();
			conn = DBConnect.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				DAO_Employee dao = new DAO_Employee();
				Employee e = dao.getEmployeebyID(rs.getInt(4));
				list.add(new Account(rs.getInt(1), rs.getString(2), rs.getString(3), e));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

    
    public CurrentEmp getEmployeeByID(int id) {
        String query = "SELECT * FROM Employee WHERE employee_id = ?";
        CurrentEmp employee = null;
        try {
            new DBConnect();
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                employee = new CurrentEmp(
                    rs.getInt(1),
                    rs.getString(2),
                    new EmployeeType(rs.getInt(3)),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6)
                );
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
        return employee;
    }
    
    public Employee getEmployeebyID(int id) {
        String query = "SELECT * FROM Employee WHERE employee_id = ?";
        Employee employee = null;
        try {
            new DBConnect();
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                employee = new Employee(
                    rs.getInt(1),
                    rs.getString(2),
                    new EmployeeType(rs.getInt(3)),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6)
                );
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
        return employee;
    }

   
    public void addEmployee(Employee employee) {
        String query = "INSERT INTO Employee (name, type_id, birth_day, phone, email) VALUES (?, ?, ?, ?, ?)";
        try {
            new DBConnect();
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, employee.getName());
            ps.setInt(2, employee.getEmployeeType().getTypeId());  
            ps.setString(3, employee.getBirthDate());
            ps.setString(4, employee.getPhone());
            ps.setString(5, employee.getEmail());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                CustomOptionPane.showNotice("Thêm nhân viên thành công!");
            } else {
                CustomOptionPane.showMess("Không có nhân viên nào được thêm.");
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

    
    public void updateEmployee(Employee employee) {
        String query = "UPDATE Employee SET name = ?, type_id = ?, birth_day = ?, phone = ?, email = ? WHERE employee_id = ?";
        try {
            new DBConnect();
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, employee.getName());
            ps.setInt(2, employee.getEmployeeType().getTypeId());  
            ps.setString(3, employee.getBirthDate());
            ps.setString(4, employee.getPhone());
            ps.setString(5, employee.getEmail());
            ps.setInt(6, employee.getEmployeeId()); 

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                CustomOptionPane.showNotice("Cập nhật nhân viên thành công!");
            } else {
                CustomOptionPane.showMess("Cập nhật nhân viên không thành công.");
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

    public void deleteAccount(int employeeId) {
        String query = "DELETE FROM Account WHERE employee_id = ?";
        try {
            new DBConnect();
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, employeeId);  
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
    public void deleteEmployee(int employeeId) {
        String query = "DELETE FROM Employee WHERE employee_id = ?";
        try {
            new DBConnect();
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, employeeId);  

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                CustomOptionPane.showNotice("Xóa nhân viên thành công!");
            } else {
                CustomOptionPane.showMess("Xóa nhân viên không thành công.");
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
    
    public Integer getlastEmployeeID() {
        String query = "select top 1 e.employee_id from Employee e order by employee_id desc";
        int id = 0;
        try {
            new DBConnect();
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
    
    public static void main(String[] args) {
		DAO_Employee dao = new DAO_Employee();
		System.out.println(dao.getlastEmployeeID());
	}

    
}

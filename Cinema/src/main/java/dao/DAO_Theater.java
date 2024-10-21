package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connect.DBConnect;
import enities.Seat;
import enities.SeatStatus;
import enities.Theater;

public class DAO_Theater {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	
	
	public Theater getTheaterbyID(int id){
		String query = "select * from Theater where theater_id = ?";
		Theater theater = null;
		try {
			new DBConnect();
			conn = DBConnect.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				theater = new Theater(rs.getInt(1),
						rs.getString(2), 
						rs.getString(3), 
						rs.getInt(4));
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
	    return theater;
	}
	
	public String[] getAllTheater() {
	    List<String> list = new ArrayList<>();
	    String query = "select t.name from Theater t";
	    
	    try {
	        new DBConnect();
	        conn = DBConnect.getConnection();
	        ps = conn.prepareStatement(query);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            list.add(rs.getString(1));
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
	    return list.toArray(new String[0]);
	}
	
	public int getTheaterIdByTitle(String title) {
	    int Id = -1;
	    String query = "select t.theater_id from Theater t where t.name = ? ";
	    try {
	        new DBConnect();
	        conn = DBConnect.getConnection();
	        ps = conn.prepareStatement(query);
	        ps.setString(1, title);
	        rs = ps.executeQuery();
	        if (rs.next()) {
	            Id = rs.getInt("theater_id");
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

	    return Id;
	}
	
	public List<Seat> getAllSeatfromTheater(int id){
		List<Seat> list = new ArrayList<Seat>();
		String query = "select s.* from Seat s where s.theater_id = ?";
		try {
			new DBConnect();
			conn = DBConnect.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				DAO_Theater dao = new DAO_Theater();
				Theater t = dao.getTheaterbyID(rs.getInt(2));
				list.add(new Seat(rs.getInt(1),
						t,
						rs.getString(3),
						new SeatStatus(rs.getInt(4))));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void ChuyenTrangThaiGhe(int id) {
	    String query = "update Seat set status_id = 2 where seat_id = ?";
	    try {
	        new DBConnect();
	        conn = DBConnect.getConnection();
	        ps = conn.prepareStatement(query);
	        ps.setInt(1, id);
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
	
	public void ChuyenTrangThaiGheThanhTrong(int id) {
	    String query = "update Seat set status_id = 1 where seat_id = ?";
	    try {
	        new DBConnect();
	        conn = DBConnect.getConnection();
	        ps = conn.prepareStatement(query);
	        ps.setInt(1, id);
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
	
	public static void main(String[] args) {
		DAO_Theater dao = new DAO_Theater();
		dao.ChuyenTrangThaiGheThanhTrong(1);
	}
}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.jfree.data.category.DefaultCategoryDataset;

import connect.DBConnect;
import enities.Movie;
import enities.MovieStatus;
import service.CustomOptionPane;

public class DAO_Movie {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public List<Movie> getAllMovie(){
		List<Movie> list = new ArrayList<Movie>();
		String query = "select * from Movie";
		try {
			new DBConnect();
			conn = DBConnect.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new Movie(rs.getInt(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getInt(4), 
						rs.getString(5), 
						rs.getString(6), 
						new MovieStatus(rs.getInt(7)),
						rs.getString(8))
					);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public String[] getAllTitleMovieDangChieu() {
	    List<String> list = new ArrayList<>();
	    String query = "SELECT m.title FROM Movie m where m.status_id = 1";
	    
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
	
	public List<Movie> getAllMovieDangChieu(){
		List<Movie> list = new ArrayList<Movie>();
		String query = "select m.* from Movie m\r\n"
				+ "inner join MovieStatus ms on m.status_id = ms.status_id \r\n"
				+ "where ms.status_id = 1";
		try {
			new DBConnect();
			conn = DBConnect.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new Movie(rs.getInt(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getInt(4), 
						rs.getString(5), 
						rs.getString(6), 
						new MovieStatus(rs.getInt(7)),
						rs.getString(8))
					);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public DefaultCategoryDataset getListMovieSeller(String ns, String ne) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    String query = "SELECT m.title, COUNT(t.ticket_id) AS total_tickets\r\n"
	    		+ "FROM Movie m\r\n"
	    		+ "INNER JOIN Showtime s ON s.movie_id = m.movie_id\r\n"
	    		+ "INNER JOIN Ticket t ON t.showtime_id = s.showtime_id\r\n"
	    		+ "inner join InvoiceDetail d on d.ticket_id =  t.ticket_id\r\n"
	    		+ "Inner join Invoice hd on hd.invoice_id = d.invoice_id\r\n"
	    		+ "WHERE hd.purchase_time BETWEEN ? AND ?\r\n"
	    		+ "GROUP BY m.movie_id, m.title, m.release_date, m.director, m.genre, m.status_id, m.duration, m.Img\r\n"
	    		+ "ORDER BY total_tickets DESC";
	    try {
	        new DBConnect();
	        conn = DBConnect.getConnection();
	        ps = conn.prepareStatement(query);
	        ps.setString(1, ns);
	        ps.setString(2, ne);
	        rs = ps.executeQuery();
	        while (rs.next()) {
	            String movieTitle = rs.getString("title");
	            int totalTickets = rs.getInt("total_tickets");
	            dataset.addValue(totalTickets, "Phim", movieTitle);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return dataset;
	}


	
	public Movie getMoviebyID(int id){
		String query = "select * from Movie where movie_id = ?";
		Movie movie = null;
		try {
			new DBConnect();
			conn = DBConnect.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
	            movie = new Movie(rs.getInt(1), 
	                              rs.getString(2), 
	                              rs.getString(3), 
	                              rs.getInt(4), 
	                              rs.getString(5), 
	                              rs.getString(6), 
	                              new MovieStatus(rs.getInt(7)), 
	                              rs.getString(8));  
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
	    return movie;
	}
	
	public void addMovie(Movie movie) {
	    String query = "INSERT INTO Movie (title, genre, duration, release_date, director, status_id, Img) VALUES (?, ?, ?, ?, ?, 2, ?);";
	        try {
	            new DBConnect();
	            conn = DBConnect.getConnection();
	            ps = conn.prepareStatement(query);

	            ps.setString(1, movie.getTitle());
	            ps.setString(2, movie.getGenre());
	            ps.setInt(3, movie.getDuration());
	            ps.setString(4, movie.getReleaseDate());
	            ps.setString(5, movie.getDirector());
	            ps.setString(6, movie.getImg());
	            int rowsAffected = ps.executeUpdate();
	            if (rowsAffected > 0) {
	                System.out.println("Thêm phim thành công!");
	            } else {
	                System.out.println("Không có phim nào được thêm.");
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
	
	public void updateMovie(Movie movie) {
	    
	    String query = "UPDATE Movie SET title = ?, genre = ?, duration = ?, release_date = ?, director = ?, Img = ? WHERE movie_id = ?";

	    try {
	        new DBConnect();
	        conn = DBConnect.getConnection();
	        ps = conn.prepareStatement(query);
	        ps.setString(1, movie.getTitle());
	        ps.setString(2, movie.getGenre());
	        ps.setInt(3, movie.getDuration());
	        ps.setString(4, movie.getReleaseDate());
	        ps.setString(5, movie.getDirector());
	        ps.setString(6, movie.getImg());
	        ps.setInt(7, movie.getMovieId());

	        int rowsAffected = ps.executeUpdate();
	        if (rowsAffected > 0) {
	            CustomOptionPane.showMess("Cập Nhật Phim Thành Công");
	        } else {
	        	CustomOptionPane.showMess("Cập Nhật Phim Không Thành Công");
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
	
	public void NgungChieu(int id) {
	    
	    String query = "update Movie set status_id = 2 where movie_id = ?";

	    try {
	        new DBConnect();
	        conn = DBConnect.getConnection();
	        ps = conn.prepareStatement(query);
	        ps.setInt(1, id);

	        int rowsAffected = ps.executeUpdate();
	        if (rowsAffected > 0) {
	            CustomOptionPane.showMess("Đã Chuyển Trạng Thái Phim Thành Ngừng Chiếu");
	        } else {
	        	CustomOptionPane.showMess("Cập Nhật Phim Không Thành Công");
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
	
public void DangChieu(int id) {
	    
	    String query = "update Movie set status_id = 1 where movie_id = ?";

	    try {
	        new DBConnect();
	        conn = DBConnect.getConnection();
	        ps = conn.prepareStatement(query);
	        ps.setInt(1, id);

	        int rowsAffected = ps.executeUpdate();
	        if (rowsAffected > 0) {
	            CustomOptionPane.showMess("Đã Chuyển Trạng Thái Phim Thành Đang Chiếu");
	        } else {
	        	CustomOptionPane.showMess("Cập Nhật Phim Không Thành Công");
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
	
	public void deleteMovie(int movieId) {
	    
	    String query = "DELETE FROM Movie WHERE movie_id = ?";

	    try {
	        new DBConnect();
	        conn = DBConnect.getConnection();
	        ps = conn.prepareStatement(query);
	        ps.setInt(1, movieId);

	        int rowsAffected = ps.executeUpdate();
	        if (rowsAffected > 0) {
	        	 CustomOptionPane.showMess("Xóa Phim Thành Công");
	        } else {
	        	 CustomOptionPane.showMess("Xóa Phim Không Thành Công");
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

	public int getMovieIdByTitle(String title) {
	    int movieId = -1;
	    String query = "SELECT m.movie_id FROM Movie m WHERE m.title = ? ";
	    try {
	        new DBConnect();
	        conn = DBConnect.getConnection();
	        ps = conn.prepareStatement(query);
	        ps.setString(1, title);
	        rs = ps.executeQuery();
	        if (rs.next()) {
	            movieId = rs.getInt("movie_id");
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

	    return movieId;
	}


	
	public static void main(String[] args) {
		
	}

}

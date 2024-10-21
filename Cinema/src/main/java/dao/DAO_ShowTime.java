package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import connect.DBConnect;
import enities.Movie;
import enities.Showtime;
import enities.Theater;
import service.CustomOptionPane;

public class DAO_ShowTime {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public List<Showtime> getAllShowTime(){
		List<Showtime> list = new ArrayList<Showtime>();
		String query = "select * from ShowTime";
		DAO_Movie dao = new DAO_Movie();
		DAO_Theater dao1 = new DAO_Theater();
		try {
			new DBConnect();
			conn = DBConnect.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				Movie movie = dao.getMoviebyID(rs.getInt(2));
				Theater theater = dao1.getTheaterbyID(rs.getInt(3));
				list.add(new Showtime(rs.getInt(1),
						movie,
						theater,
						rs.getString(4),
						rs.getString(5))
					);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Showtime> getAllShowTimebyMovieandDate(int movieId, String date){
		List<Showtime> list = new ArrayList<Showtime>();
		String query = "select s.* from Showtime s\r\n"
				+ " inner join Movie m on m.movie_id = s.movie_id\r\n"
				+ " inner join Theater t on t.theater_id = s.theater_id\r\n"
				+ " where m.movie_id = ?  and s.show_date = ?";
		DAO_Movie dao = new DAO_Movie();
		DAO_Theater dao1 = new DAO_Theater();
		try {
			new DBConnect();
			conn = DBConnect.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, movieId);
			ps.setString(2, date);
			rs = ps.executeQuery();
			while(rs.next()) {
				Movie movie = dao.getMoviebyID(rs.getInt(2));
				Theater theater = dao1.getTheaterbyID(rs.getInt(3));
				list.add(new Showtime(rs.getInt(1),
						movie,
						theater,
						rs.getString(4),
						rs.getString(5))
					);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void addShowTime(Showtime showTime) {
	    String checkQuery = "SELECT COUNT(*) FROM Showtime WHERE theater_id = ? AND show_date = ? AND start_time = ?";
	    String insertQuery = "INSERT INTO Showtime (movie_id, theater_id, show_date, start_time) VALUES (?, ?, ?, ?);";
	    
	    try {  
	        new DBConnect();
	        conn = DBConnect.getConnection();
	        ps = conn.prepareStatement(checkQuery);
	        ps.setInt(1, showTime.getTheater().getTheaterId());
	        ps.setString(2, showTime.getShowDate());
	        ps.setString(3, showTime.getStartTime());
	        
	        rs = ps.executeQuery();
	        if (rs.next() && rs.getInt(1) > 0) {
	            CustomOptionPane.showNotice("Phòng chiếu đã có suất chiếu vào giờ này. Vui lòng chọn giờ khác.");
	            return;
	        }
	        ps = conn.prepareStatement(insertQuery);
	        ps.setInt(1, showTime.getMovie().getMovieId());
	        ps.setInt(2, showTime.getTheater().getTheaterId());
	        ps.setString(3, showTime.getShowDate());
	        ps.setString(4, showTime.getStartTime());

	        int rowsAffected = ps.executeUpdate();
	        if (rowsAffected > 0) {
	            CustomOptionPane.showNotice("Thêm Suất Chiếu Thành Công!");
	        } else {
	            CustomOptionPane.showNotice("Có Lỗi trong quá trình thêm suất chiếu!");
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
	}

	
	public void updateShowTime(Showtime showTime) {
	    String checkQuery = "SELECT COUNT(*) FROM Showtime WHERE theater_id = ? AND show_date = ? AND start_time = ? AND showtime_id != ?";

	    String updateQuery = "UPDATE Showtime SET movie_id = ?, theater_id = ?, show_date = ?, start_time = ? WHERE showtime_id = ?";

	    try {
	        new DBConnect();
	        conn = DBConnect.getConnection();
	        ps = conn.prepareStatement(checkQuery);
	        ps.setInt(1, showTime.getTheater().getTheaterId());
	        ps.setString(2, showTime.getShowDate());
	        ps.setString(3, showTime.getStartTime());
	        ps.setInt(4, showTime.getShowtimeId());
	        rs = ps.executeQuery();

	        if (rs.next() && rs.getInt(1) > 0) {
	            CustomOptionPane.showNotice("Phòng chiếu đã có suất chiếu vào giờ này. Vui lòng chọn giờ khác.");
	            return;
	        }

	        ps = conn.prepareStatement(updateQuery);
	        ps.setInt(1, showTime.getMovie().getMovieId());
	        ps.setInt(2, showTime.getTheater().getTheaterId());
	        ps.setString(3, showTime.getShowDate());
	        ps.setString(4, showTime.getStartTime());
	        ps.setInt(5, showTime.getShowtimeId());

	        int rowsAffected = ps.executeUpdate();
	        if (rowsAffected > 0) {
	            CustomOptionPane.showNotice("Cập Nhật Suất Chiếu Thành Công");
	        } else {
	            CustomOptionPane.showNotice("Cập Nhật Suất Chiếu Không Thành Công");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        CustomOptionPane.showNotice("Lỗi khi cập nhật suất chiếu: " + e.getMessage());
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}


	public void deleteShowTime(int showTimeId) {
	    
	    String query = "DELETE FROM ShowTime WHERE showtime_id = ?";
	
	    try {
	        new DBConnect();
	        conn = DBConnect.getConnection();
	        ps = conn.prepareStatement(query);
	        ps.setInt(1, showTimeId);
	
	        int rowsAffected = ps.executeUpdate();
	        if (rowsAffected > 0) {
	        	 CustomOptionPane.showMess("Xóa Suất Chiếu Thành Công");
	        } else {
	        	 CustomOptionPane.showMess("Xóa Suất Chiếu Không Thành Công");
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
	
	public List<Showtime> getAllShowTimebyShowDate(String date){
		List<Showtime> list = new ArrayList<Showtime>();
		String query = "select * from ShowTime where show_date = ?";
		DAO_Movie dao = new DAO_Movie();
		DAO_Theater dao1 = new DAO_Theater();
		try {
			new DBConnect();
			conn = DBConnect.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, date);
			rs = ps.executeQuery();
			while(rs.next()) {
				Movie movie = dao.getMoviebyID(rs.getInt(2));
				Theater theater = dao1.getTheaterbyID(rs.getInt(3));
				list.add(new Showtime(rs.getInt(1),
						movie,
						theater,
						rs.getString(4),
						rs.getString(5))
					);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public Showtime getShowTimebyID(int id){
		String query = "select * from ShowTime where showtime_id = ?";
		Showtime movie = null;
		try {
			new DBConnect();
			conn = DBConnect.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				DAO_Movie dao = new DAO_Movie();
				Movie e = dao.getMoviebyID(rs.getInt(2));
				DAO_Theater theaterDao = new DAO_Theater();
				Theater t = theaterDao.getTheaterbyID(rs.getInt(3));
	            movie = new Showtime(id, 
	            		e, 
	            		t, 
	            		rs.getString(4), 
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
	    return movie;
	}
	
	private List<Showtime> getEndedShowtimes() {
        List<Showtime> endedShowtimes = new ArrayList<>();
        String query = "SELECT s.showtime_id, s.movie_id, s.theater_id, s.show_date, s.start_time, m.duration\r\n"
        		+ "FROM Showtime s\r\n"
        		+ "JOIN Movie m ON s.movie_id = m.movie_id\r\n"
        		+ "WHERE DATEADD(MINUTE, m.duration, \r\n"
        		+ "    TRY_CONVERT(DATETIME2, s.show_date + 'T' + s.start_time + ':00', 126)) <= CURRENT_TIMESTAMP;";

		try {
			new DBConnect();
			conn = DBConnect.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
            while (rs.next()) {
                int showtimeId = rs.getInt("showtime_id");
                int movieId = rs.getInt("movie_id");
                int theaterId = rs.getInt("theater_id");
                String showDate = rs.getString("show_date");
                String startTime = rs.getString("start_time");
                
                DAO_Movie dao = new DAO_Movie();
				Movie e = dao.getMoviebyID(movieId);
				
                DAO_Theater theaterDao = new DAO_Theater();
				Theater t = theaterDao.getTheaterbyID(theaterId);
				
                Showtime showtime = new Showtime(showtimeId, e, t, showDate, startTime);
                endedShowtimes.add(showtime);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return endedShowtimes;
    }
	
	private void updateSeatsStatus(int theaterId) {
	    String sql = "UPDATE Seat SET status_id = ? WHERE theater_id = ? AND status_id = (SELECT status_id FROM SeatStatus WHERE status_name = N'Có khách')";
	    Connection conn = null;
	    PreparedStatement statement = null;

	    try {
	        conn = DBConnect.getConnection();
	        statement = conn.prepareStatement(sql);
	        statement.setInt(1, 1); 
	        statement.setInt(2, theaterId);

	        statement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (statement != null) statement.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}


	
    public void checkAndUpdateSeats() {
    	DAO_ShowTime dao = new DAO_ShowTime();
        List<Showtime> endedShowtimes = dao.getEndedShowtimes();

        for (Showtime showtime : endedShowtimes) {
            updateSeatsStatus(showtime.getTheater().getTheaterId());
        }
    }
    
    public void startShowtimeChecker() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            checkAndUpdateSeats();
        }, 0, 90, TimeUnit.MINUTES);
    }
	
	public static void main(String[] args) {
		DAO_ShowTime dao = new DAO_ShowTime();
		List<Showtime> lst = dao.getAllShowTimebyMovieandDate(1010, "13-09-2024");
		for(Showtime s : lst) {
			System.out.println(s);
		}
	}
}

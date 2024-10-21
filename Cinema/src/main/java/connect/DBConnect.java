package connect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=db_movie;useUnicode=true;characterEncoding=UTF-8";
    private static final String USER = "sa";
    private static final String PASS = "123";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();        
        } catch (ClassNotFoundException e) {
            e.printStackTrace();     
        }
        return conn;
    }   
}

package Connection;
import java.sql.*;
public class ConnectDataBase {

    public static Connection connectToSql() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded successfully.");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "root");
        return con;
    }
}


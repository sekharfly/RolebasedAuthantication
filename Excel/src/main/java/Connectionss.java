import java.sql.Connection;
import java.sql.DriverManager;


public class Connectionss {
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con =DriverManager.getConnection("jdbc:mysql://10.10.0.141:3306/ajr", "itadmin", "ajr@123");
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}

}

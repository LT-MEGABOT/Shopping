package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcUtil {

	static JdbcUtil ju = null;
	Connection connection;

	private JdbcUtil() {

		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/tgou?characterEncoding=utf8";
			String name = "root";
			String password = "0830";
			connection = DriverManager.getConnection(url, name, password);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static JdbcUtil getInstace() {

		if (ju == null) {
			ju = new JdbcUtil();
		}
		return ju;
	}

	public Connection getConn() {
		return connection;
	}

	public void setCloseConn(Connection conn) {

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void setClosePs(PreparedStatement ps) {

		if (ps != null) {

			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}

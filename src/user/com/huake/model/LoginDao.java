package user.com.huake.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import user.com.huake.pojo.User;
import utils.JdbcUtil;

public class LoginDao {

	public boolean selectUser(User user) {

		try {
			Connection conn = JdbcUtil.getInstace().getConn();
			String sql = "SELECT  * FROM  user WHERE account =? AND password = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getAccount());
			ps.setString(2, user.getPassword());
			ResultSet res = ps.executeQuery();

			return res.next();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}

package admin.com.huake.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import admin.com.huake.pojo.Admin;
import utils.JdbcUtil;

public class AdminDao {

	public boolean SelectAdmin(Admin admin) {

		try {

			Connection conn = JdbcUtil.getInstace().getConn();
			String sql = "SELECT  * FROM  admin WHERE user_name =? AND user_password = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, admin.getUser_name());
			ps.setString(2, admin.getUser_password());
			
			ResultSet res = ps.executeQuery();
			return res.next();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}
}
